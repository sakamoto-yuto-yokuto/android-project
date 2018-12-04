package jp.co.sakamoto.androidproject.presentation.viewmodel.adapter;

import android.databinding.ObservableField;

import jp.co.sakamoto.androidproject.presentation.viewmodel.ViewModel;

public class GalleryListItemViewModel extends ViewModel {
    public ObservableField<String> name = new ObservableField<>();

    public GalleryListItemViewModel(String defName) {
        this.name.set(defName);
    }
}
