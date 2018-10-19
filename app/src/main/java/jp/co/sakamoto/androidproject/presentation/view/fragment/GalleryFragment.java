package jp.co.sakamoto.androidproject.presentation.view.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.databinding.FragmentGalleryBinding;
import jp.co.sakamoto.androidproject.presentation.view.adapter.GalleryListAdapter;
import jp.co.sakamoto.androidproject.presentation.viewmodel.fragment.GalleryFragmentViewModel;

public class GalleryFragment extends BaseFragment {
    @Inject
    GalleryFragmentViewModel viewModel;
    private FragmentGalleryBinding binding;
    private GalleryListAdapter adapter;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false);
        this.subscriptions.add(this.viewModel);
        this.binding.setViewModel(this.viewModel);

        this.adapter = new GalleryListAdapter();
        this.binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.binding.recyclerView.setAdapter(this.adapter);
        this.viewModel.galleries.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                adapter.setEntities(viewModel.galleries.get());
            }
        });

        this.viewModel.getGalleries();

        return this.binding.getRoot();
    }
}
