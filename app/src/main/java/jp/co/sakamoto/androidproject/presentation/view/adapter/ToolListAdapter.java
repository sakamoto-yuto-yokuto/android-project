package jp.co.sakamoto.androidproject.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.data.entity.Tool;
import jp.co.sakamoto.androidproject.databinding.ItemToolListBinding;
import jp.co.sakamoto.androidproject.presentation.viewmodel.adapter.ToolListItemViewModel;

public class ToolListAdapter extends RecyclerView.Adapter<ToolListAdapter.ViewHolder> {
    private List<ToolListItemViewModel> viewModels = new ArrayList<>();

    public void setEntities(List<Tool> entities) {
        if (entities == null || entities.size() == 0) {
            this.viewModels.clear();
        } else {
            this.viewModels = Observable.fromIterable(entities)
                    .map(entity -> ToolListItemViewModel.newInstance(entity.getName()))
                    .toList()
                    .blockingGet();
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ToolListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tool_list, parent, false);
        return new ToolListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolListAdapter.ViewHolder holder, int position) {
        holder.update(this.viewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return this.viewModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemToolListBinding binding;

        ViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
        }

        void update(ToolListItemViewModel viewModel) {
            this.binding.setViewModel(viewModel);
        }
    }
}