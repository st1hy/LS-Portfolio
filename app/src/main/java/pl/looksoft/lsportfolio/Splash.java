package pl.looksoft.lsportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class Splash extends AppCompatActivity {

    CompositeSubscription subscriptions = new CompositeSubscription();

    final Action1<Long> openAppList = new Action1<Long>() {
        @Override
        public void call(Long aLong) {
            Intent intent = new Intent(Splash.this, AppList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscriptions.add(Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(openAppList));
    }

    @Override
    protected void onPause() {
        super.onPause();
        subscriptions.clear();
    }
}
