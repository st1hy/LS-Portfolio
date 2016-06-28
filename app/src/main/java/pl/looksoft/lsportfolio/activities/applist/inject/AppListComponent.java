package pl.looksoft.lsportfolio.activities.applist.inject;

import dagger.Component;
import pl.looksoft.lsportfolio.activities.applist.AppList;
import pl.looksoft.lsportfolio.application.inject.ApplicationComponent;
import pl.looksoft.lsportfolio.base.inject.PerActivity;

@Component(dependencies = ApplicationComponent.class)
@PerActivity
public interface AppListComponent {

    void inject(AppList activity);
}
