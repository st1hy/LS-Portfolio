package pl.looksoft.lsportfolio.activities.appdetail;

import android.content.Intent;
import android.support.annotation.Nullable;

import org.parceler.Parcels;

import javax.inject.Inject;

import pl.looksoft.lsportfolio.network.AppsResponse;

public class AppDetailModel {
    final Intent intent;

    @Inject
    public AppDetailModel(Intent intent) {
        this.intent = intent;
    }

    @Nullable
    public AppsResponse.App getApp() {
        if (intent != null)
            return Parcels.unwrap(intent.getParcelableExtra(AppDetail.EXTRA_APP_DATA_PARCEL));
        return null;
    }
}
