package jp.co.sakamoto.androidproject.presentation.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import jp.co.sakamoto.androidproject.AndroidApplication;
import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.databinding.FragmentDrawerBinding;
import jp.co.sakamoto.androidproject.presentation.view.adapter.DrawerAdapter;
import jp.co.sakamoto.androidproject.presentation.viewmodel.fragment.DrawerFragmentViewModel;

public class DrawerFragment extends BaseFragment {
    @Inject
    DrawerFragmentViewModel viewModel;
    private FragmentDrawerBinding binding;
    private DrawerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drawer, container, false);
        AndroidApplication.getInstance().getAppComponent().inject(this);
        this.subscriptions.add(this.viewModel);
        this.binding.setViewModel(this.viewModel);
        this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return this.binding.getRoot();
    }
}
