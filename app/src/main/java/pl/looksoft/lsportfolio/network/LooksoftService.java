package pl.looksoft.lsportfolio.network;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface LooksoftService {
    @GET("api/main")
    Observable<AppsResponse> getApps(@Query("lang") String lang);

    @GET("api/product/{id}")
    Observable<AppsResponse> getAppDetails(@Path("id") int id);
}
