package jp.co.sakamoto.androidproject.presentation.view.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.databinding.ItemDrawerBinding;
import jp.co.sakamoto.androidproject.presentation.viewmodel.adapter.DrawerItemViewModel;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private Activity context;
    private List<DrawerItemViewModel> viewModels;

    public DrawerAdapter(Activity context, List<DrawerItemViewModel> viewModels) {
        this.context = context;
        this.viewModels = viewModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DrawerItemViewModel viewModel = this.viewModels.get(position);
        holder.update(viewModel);
    }

    @Override
    public int getItemCount() {
        return this.viewModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDrawerBinding binding;

        ViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
        }

        void update(DrawerItemViewModel viewModel) {
            this.binding.setViewModel(viewModel);
        }
    }
}
