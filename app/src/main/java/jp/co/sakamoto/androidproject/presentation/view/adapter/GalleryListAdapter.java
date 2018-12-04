package jp.co.sakamoto.androidproject.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import android.support.annotation.NonNull;
import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.data.entity.Gallery;
import jp.co.sakamoto.androidproject.databinding.ItemGalleryListBinding;
import jp.co.sakamoto.androidproject.presentation.viewmodel.adapter.GalleryListItemViewModel;

public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.ViewHolder> {
    private List<GalleryListItemViewModel> viewModels = new ArrayList<>();

    public void setEntities(List<Gallery> entities) {
        if (entities == null || entities.size() == 0) {
            this.viewModels.clear();
        } else {
            this.viewModels = Observable.fromIterable(entities)
                    .map(entity -> new GalleryListItemViewModel(entity.getName()))
                    .toList()
                    .blockingGet();
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalleryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery_list, parent, false);
        return new GalleryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryListAdapter.ViewHolder holder, int position) {
        holder.update(this.viewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return this.viewModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemGalleryListBinding binding;

        ViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
        }

        void update(GalleryListItemViewModel viewModel) {
            this.binding.setViewModel(viewModel);
        }
    }
}
