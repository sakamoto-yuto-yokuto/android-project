package jp.co.sakamoto.androidproject.presentation.viewmodel.activity;

import android.databinding.ObservableField;

import javax.inject.Inject;

import jp.co.sakamoto.androidproject.presentation.viewmodel.ViewModel;

public class SecondActivityViewModel extends ViewModel {
    public final ObservableField<Boolean> isLoading = new ObservableField<>(false);

    @Inject
    public SecondActivityViewModel() {
    }
}
