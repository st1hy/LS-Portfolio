package pl.looksoft.lsportfolio.application.inject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.looksoft.lsportfolio.network.LooksoftService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://www.looksoft.pl")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Singleton
    @Provides
    LooksoftService provideLooksoftService(Retrofit retrofit) {
        return retrofit.create(LooksoftService.class);
    }
}
