package pl.looksoft.lsportfolio.base;

import android.util.Log;

import rx.Subscriber;

public class SimpleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("Subscription", "Error", e);
    }

    @Override
    public void onNext(T t) {

    }
}
