package pl.looksoft.lsportfolio.activities.appdetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import pl.looksoft.lsportfolio.R;

public class AppImagesAdapter extends RecyclerView.Adapter<GalleryImageViewHolder> {

    final Picasso picasso;

    List<String> gallery = Collections.emptyList();

    @Inject
    public AppImagesAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    public void update(List<String> gallery) {
        this.gallery = gallery;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.detail_gallery_image;
    }

    @Override
    public GalleryImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new GalleryImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryImageViewHolder holder, int position) {
        ImageView imageView = holder.getImageView();
        picasso.cancelRequest(imageView);
        picasso.load(gallery.get(position)).into(imageView);
    }

    @Override
    public int getItemCount() {
        return gallery.size();
    }
}
