package pl.looksoft.lsportfolio.activities.appdetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.activities.appdetail.inject.AppDetailComponent;
import pl.looksoft.lsportfolio.activities.appdetail.inject.AppDetailModule;
import pl.looksoft.lsportfolio.activities.appdetail.inject.DaggerAppDetailComponent;
import pl.looksoft.lsportfolio.base.BaseActivity;
import pl.looksoft.lsportfolio.base.SimpleSubscriber;
import pl.looksoft.lsportfolio.network.AppsResponse;
import pl.looksoft.lsportfolio.network.DetailResponse;
import pl.looksoft.lsportfolio.network.LooksoftService;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

public class AppDetail extends BaseActivity {

    public static final String EXTRA_APP_DATA_PARCEL = "extra app data";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_image)
    ImageView image;
    @BindView(R.id.detail_description)
    TextView description;
    @BindView(R.id.detail_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.detail_links)
    View links;
    @BindView(R.id.details_button_app_store)
    View buttonAppStore;
    @BindView(R.id.details_button_google_play)
    View buttonGooglePlay;
    @BindView(R.id.details_button_win_store)
    View buttonWinStore;

    CompositeSubscription subscriptions = new CompositeSubscription();

    AppDetailComponent component;

    @Inject
    LooksoftService service;
    @Inject
    AppDetailModel model;
    @Inject
    Picasso picasso;
    @Inject
    AppGalleryAdapter adapter;

    protected AppDetailComponent getComponent() {
        if (component == null) {
            component = DaggerAppDetailComponent.builder()
                    .applicationComponent(getAppComponent())
                    .appDetailModule(new AppDetailModule(this))
                    .build();
        }
        return component;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
        getComponent().inject(this);
        setSupportActionBar(toolbar);
        checkNotNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        loadDetail();
    }

    @Override
    protected void onStop() {
        super.onStop();
        subscriptions.clear();
    }

    void loadDetail() {
        AppsResponse.App app = model.getApp();
        if (app != null) {
            bindApp(app);
            subscriptions.add(subscribeLoader(app.getId()));
        }
    }

    private void bindApp(AppsResponse.App app) {
        setTitle(app.getName());
        name.setText(app.getName());
        picasso.cancelRequest(image);
        picasso.load(app.getIcon()).centerCrop().fit().into(image);
        description.setText(app.getDescription());
    }

    @NonNull
    private Subscription subscribeLoader(int appId) {
        return service.getAppDetails(appId, getString(R.string.lang_code))
                .filter(new Func1<DetailResponse, Boolean>() {
                    @Override
                    public Boolean call(DetailResponse response) {
                        return response.getStatus() == 200;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleSubscriber<DetailResponse>() {
                    @Override
                    public void onNext(DetailResponse appsResponse) {
                        Log.d("detail", appsResponse.toString());
                        bindDetailData(appsResponse.getData());
                    }
                });
    }


    private void bindDetailData(DetailResponse.Data data) {
        setTitle(data.getName());
        name.setText(data.getName());
        description.setText(data.getDescription());
        adapter.update(data.getGallery());

        setupLinkButtons(data);
    }

    private void setupLinkButtons(DetailResponse.Data data) {
        buttonAppStore.setVisibility(View.GONE);
        buttonGooglePlay.setVisibility(View.GONE);
        buttonWinStore.setVisibility(View.GONE);
        for (final DetailResponse.Link link : data.getLink()) {
            String url = link.getUrl();
            if (url.contains("itunes.apple.com")) {
                setupLinkButton(buttonAppStore, link);
            } else if (url.contains("play.google.com")) {
                setupLinkButton(buttonGooglePlay, link);
            } else if (url.contains("windowsphone.com")) {
                setupLinkButton(buttonWinStore, link);
            }
        }
        links.setVisibility(View.VISIBLE);
    }

    private void setupLinkButton(final View button, final DetailResponse.Link link) {
        button.setVisibility(View.VISIBLE);
        subscriptions.add(RxView.clicks(button).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                openUrl(link.getUrl());
            }
        }));
    }

    void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

}
