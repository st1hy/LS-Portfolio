package pl.looksoft.lsportfolio.application.inject;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.looksoft.lsportfolio.application.LsApplication;

@Module
public class ApplicationModule {
    private final LsApplication application;

    public ApplicationModule(@NonNull LsApplication application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application.getBaseContext();
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Context context) {
        return Picasso.with(context);
    }
}
