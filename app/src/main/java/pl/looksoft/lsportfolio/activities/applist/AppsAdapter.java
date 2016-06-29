package pl.looksoft.lsportfolio.activities.applist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.network.AppsResponse;

public class AppsAdapter extends RecyclerView.Adapter<AppViewHolder> implements AppViewHolder.Callback {

    final Picasso picasso;
    final AppListView view;

    List<AppsResponse.App> apps = Collections.emptyList();

    @Inject
    public AppsAdapter(Picasso picasso, AppListView view) {
        this.picasso = picasso;
        this.view = view;
    }

    public void update(List<AppsResponse.App> apps) {
        this.apps = apps;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.app_list_item;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AppViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        ImageView imageView = holder.getImageView();
        picasso.cancelRequest(imageView);
        AppsResponse.App app = apps.get(position);
        picasso.load(app.getIcon()).centerCrop().fit().into(imageView);
        holder.getName().setText(app.getName());
        holder.setApp(app);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    @Override
    public void onItemClicked(AppsResponse.App app) {
        view.openDetailActivity(Parcels.wrap(app));
    }
}
