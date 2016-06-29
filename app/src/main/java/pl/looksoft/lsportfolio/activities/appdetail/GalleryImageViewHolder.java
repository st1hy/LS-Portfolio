package pl.looksoft.lsportfolio.activities.appdetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.looksoft.lsportfolio.R;

public class GalleryImageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.detail_gallery_image)
    ImageView imageView;

    public GalleryImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
