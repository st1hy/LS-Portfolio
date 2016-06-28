package pl.looksoft.lsportfolio.base;

import android.util.Log;

import rx.Subscriber;
import rx.android.BuildConfig;

public class SimpleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (BuildConfig.DEBUG) Log.e("Subscription", "Error", e);
    }

    @Override
    public void onNext(T t) {

    }
}
