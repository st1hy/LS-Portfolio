package pl.looksoft.lsportfolio.activities.appdetail.inject;

import android.content.Intent;

import dagger.Module;
import dagger.Provides;
import pl.looksoft.lsportfolio.activities.appdetail.AppDetail;

@Module
public class AppDetailModule {
    final AppDetail activity;

    public AppDetailModule(AppDetail activity) {
        this.activity = activity;
    }

    @Provides
    Intent provideIntent() {
        return activity.getIntent();
    }
}
