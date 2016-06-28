package pl.looksoft.lsportfolio.activities.applist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.activities.applist.inject.AppListComponent;
import pl.looksoft.lsportfolio.activities.applist.inject.DaggerAppListComponent;
import pl.looksoft.lsportfolio.activities.contact.Contact;
import pl.looksoft.lsportfolio.base.BaseActivity;
import pl.looksoft.lsportfolio.base.SimpleSubscriber;
import pl.looksoft.lsportfolio.network.AppsResponse;
import pl.looksoft.lsportfolio.network.LooksoftService;
import rx.android.schedulers.AndroidSchedulers;

public class AppList extends BaseActivity implements OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ActionBarDrawerToggle toggle;

    AppListComponent component;

    @Inject
    LooksoftService service;

    protected AppListComponent getComponent() {
        if (component == null) {
            component = DaggerAppListComponent.builder()
                    .applicationComponent(getAppComponent())
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        service.getApps(getString(R.string.lang_code))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleSubscriber<AppsResponse>() {
                    @Override
                    public void onNext(AppsResponse appsResponse) {
                        super.onNext(appsResponse);
                        Log.d("Apps", appsResponse.toString());
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        drawer.removeDrawerListener(toggle);
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

}
