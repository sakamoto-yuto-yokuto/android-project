package jp.co.sakamoto.androidproject.presentation.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import javax.inject.Inject;

import jp.co.sakamoto.androidproject.AndroidApplication;
import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.databinding.ActivityMainBinding;
import jp.co.sakamoto.androidproject.presentation.viewmodel.activity.MainActivityViewModel;

public class MainActivity extends BaseActivity {
    @Inject
    MainActivityViewModel viewModel;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AndroidApplication.getInstance().getAppComponent().inject(this);
        this.subscriptions.add(viewModel);
        this.binding.setViewModel(viewModel);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
