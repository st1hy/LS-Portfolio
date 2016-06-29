package pl.looksoft.lsportfolio.activities.applist;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.network.AppsResponse;

public class AppViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.app_list_item_root)
    View root;
    @BindView(R.id.app_list_item_name)
    TextView name;
    @BindView(R.id.app_list_item_image)
    ImageView imageView;

    AppsResponse.App app;
    final Callback callback;

    public AppViewHolder(View itemView, final Callback callback) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.callback = callback;
    }

    @OnClick(R.id.app_list_item_root)
    public void onClicked() {
        callback.onItemClicked(app);
    }

    public TextView getName() {
        return name;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setApp(AppsResponse.App app) {
        this.app = app;
    }

    public void setBackground(@DrawableRes int background) {
        root.setBackgroundResource(background);
    }

    public interface Callback {
        void onItemClicked(AppsResponse.App app);
    }
}
