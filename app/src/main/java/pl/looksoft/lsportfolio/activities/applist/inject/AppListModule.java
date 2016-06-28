package pl.looksoft.lsportfolio.activities.applist.inject;

import dagger.Module;
import dagger.Provides;
import pl.looksoft.lsportfolio.activities.applist.AppList;
import pl.looksoft.lsportfolio.activities.applist.AppListView;
import pl.looksoft.lsportfolio.base.inject.PerActivity;

@Module
public class AppListModule {
    final AppList activity;

    public AppListModule(AppList activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public AppListView provideView() {
        return activity;
    }
}
