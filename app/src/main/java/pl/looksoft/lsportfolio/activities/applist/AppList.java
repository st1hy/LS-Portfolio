package pl.looksoft.lsportfolio.activities.applist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.activities.applist.inject.AppListComponent;
import pl.looksoft.lsportfolio.activities.applist.inject.AppListModule;
import pl.looksoft.lsportfolio.activities.applist.inject.DaggerAppListComponent;
import pl.looksoft.lsportfolio.activities.contact.Contact;
import pl.looksoft.lsportfolio.base.BaseActivity;
import pl.looksoft.lsportfolio.base.SimpleSubscriber;
import pl.looksoft.lsportfolio.network.AppsResponse;
import pl.looksoft.lsportfolio.network.LooksoftService;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class AppList extends BaseActivity implements OnNavigationItemSelectedListener, AppListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.app_list_swipe_refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.app_list_recycler)
    RecyclerView recyclerView;

    ActionBarDrawerToggle toggle;

    AppListComponent component;

    @Inject
    LooksoftService service;
    @Inject
    AppsAdapter adapter;

    final CompositeSubscription subscriptions = new CompositeSubscription();

    protected AppListComponent getComponent() {
        if (component == null) {
            component = DaggerAppListComponent.builder()
                    .applicationComponent(getAppComponent())
                    .appListModule(new AppListModule(this))
                    .build();
        }
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_list_activity);
        ButterKnife.bind(this);
        getComponent().inject(this);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationView.setNavigationItemSelectedListener(this);

        refreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.ls_primary));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        subscriptions.add(RxSwipeRefreshLayout.refreshes(refreshLayout)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        refresh();
                    }
                }));
        refresh();
    }


    private void refresh() {
        refreshLayout.setRefreshing(true);
        subscriptions.add(
                service.getApps(getString(R.string.lang_code))
                        .filter(new Func1<AppsResponse, Boolean>() {
                            @Override
                            public Boolean call(AppsResponse appsResponse) {
                                return appsResponse.getStatus() == 200;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SimpleSubscriber<AppsResponse>() {
                            @Override
                            public void onNext(AppsResponse appsResponse) {
                                adapter.update(appsResponse.getData().getPortfolio());
                                refreshLayout.setRefreshing(false);
                            }
                        })
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        drawer.removeDrawerListener(toggle);
        subscriptions.clear();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_contact) {
            startActivity(new Intent(this, Contact.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void openDetailActivity(int appId) {
        //TODO
    }
}
