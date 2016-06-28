package pl.looksoft.lsportfolio.application;

import android.app.Application;
import android.support.annotation.NonNull;

import pl.looksoft.lsportfolio.application.inject.ApplicationComponent;
import pl.looksoft.lsportfolio.application.inject.ApplicationModule;
import pl.looksoft.lsportfolio.application.inject.DaggerApplicationComponent;

public class LsApplication extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @NonNull
    public ApplicationComponent getComponent() {
        if (component == null) {
            component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return component;
    }
}
