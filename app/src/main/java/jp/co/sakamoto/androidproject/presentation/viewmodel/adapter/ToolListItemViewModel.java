package jp.co.sakamoto.androidproject.presentation.viewmodel.adapter;

import android.databinding.ObservableField;

import javax.inject.Inject;

import jp.co.sakamoto.androidproject.presentation.viewmodel.ViewModel;

public class ToolListItemViewModel extends ViewModel {
    public ObservableField<String> name = new ObservableField<>();

    public ToolListItemViewModel(String defName) {
        this.name.set(defName);
    }
}
