package pl.looksoft.lsportfolio.activities.appdetail.inject;

import dagger.Component;
import pl.looksoft.lsportfolio.activities.appdetail.AppDetail;
import pl.looksoft.lsportfolio.application.inject.ApplicationComponent;
import pl.looksoft.lsportfolio.base.inject.PerActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AppDetailModule.class)
public interface AppDetailComponent {

    void inject(AppDetail activity);
}
