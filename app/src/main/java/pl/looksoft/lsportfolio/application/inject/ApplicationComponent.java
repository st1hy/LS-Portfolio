package pl.looksoft.lsportfolio.application.inject;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import pl.looksoft.lsportfolio.network.LooksoftService;
import pl.looksoft.lsportfolio.network.NetworkModule;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    Picasso getPicasso();

    LooksoftService getLooksoftService();
}
