package jp.co.sakamoto.androidproject.presentation.viewmodel.adapter;

import android.databinding.ObservableField;

import jp.co.sakamoto.androidproject.presentation.viewmodel.ViewModel;

public class GalleryListItemViewModel extends ViewModel {

    public ObservableField<String> name = new ObservableField<>();

    public static GalleryListItemViewModel newInstance(String name) {
        GalleryListItemViewModel viewModel = new GalleryListItemViewModel();
        viewModel.name.set(name);
        return viewModel;
    }
}
