package pl.looksoft.lsportfolio.activities.applist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.looksoft.lsportfolio.R;

public class AppViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.app_list_item_name)
    TextView name;
    @BindView(R.id.app_list_item_image)
    ImageView imageView;

    int appId;
    final Callback callback;

    public AppViewHolder(View itemView, final Callback callback) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.callback = callback;
    }

    @OnClick(R.id.app_list_item_root)
    public void onClicked() {
        callback.onItemClicked(appId);
    }

    public TextView getName() {
        return name;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public interface Callback {
        void onItemClicked(int id);
    }
}
