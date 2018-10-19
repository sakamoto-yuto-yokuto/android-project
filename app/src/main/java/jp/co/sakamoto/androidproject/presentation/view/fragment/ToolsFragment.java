package jp.co.sakamoto.androidproject.presentation.view.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.databinding.FragmentToolsBinding;
import jp.co.sakamoto.androidproject.presentation.view.adapter.ToolListAdapter;
import jp.co.sakamoto.androidproject.presentation.view.helper.RxRecyclerViewScrollHelper;
import jp.co.sakamoto.androidproject.presentation.viewmodel.fragment.ToolsFragmentViewModel;

public class ToolsFragment extends BaseFragment {
    @Inject
    ToolsFragmentViewModel viewModel;
    private FragmentToolsBinding binding;
    private ToolListAdapter adapter;
    // スクロール最下部でのイベント通知Subject
    private RxRecyclerViewScrollHelper recyclerViewScrollSubject = new RxRecyclerViewScrollHelper();
    private LinearLayoutManager linearLayoutManager;
    private boolean isInitialize = false;

    public static ToolsFragment newInstance() {
        ToolsFragment fragment = new ToolsFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        if (!isInitialize) AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (!isInitialize) {
            this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tools, container, false);
            this.subscriptions.add(this.viewModel);
            this.binding.setViewModel(this.viewModel);

            this.adapter = new ToolListAdapter();
            this.linearLayoutManager = new LinearLayoutManager(getActivity());
            this.binding.recyclerView.setLayoutManager(this.linearLayoutManager);
            this.binding.recyclerView.setAdapter(this.adapter);
            this.viewModel.tools.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    adapter.setEntities(viewModel.tools.get());
                }
            });
            this.recyclerViewScrollSubject.observable().subscribe(event -> viewModel.loadNextPage());

            this.viewModel.getFirstData();
        }
        isInitialize = true;

        return this.binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        // onResumeでRecyclerViewのスクロールの監視を開始し、購読する
        this.recyclerViewScrollSubject.start(this.binding.recyclerView, this.linearLayoutManager);
    }

    @Override
    public void onPause() {
        super.onPause();
        // onResumeでRecyclerViewのスクロールの監視の購読を終了する
        this.recyclerViewScrollSubject.stop();
    }
}
