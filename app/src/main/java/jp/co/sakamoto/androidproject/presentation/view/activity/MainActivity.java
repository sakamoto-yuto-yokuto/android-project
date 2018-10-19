package jp.co.sakamoto.androidproject.presentation.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import jp.co.sakamoto.androidproject.R;
import jp.co.sakamoto.androidproject.databinding.ActivityMainBinding;
import jp.co.sakamoto.androidproject.presentation.viewmodel.activity.MainActivityViewModel;

public class MainActivity extends BaseActivity {
    @Inject
    MainActivityViewModel viewModel;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.subscriptions.add(viewModel);
        this.binding.setViewModel(viewModel);
        Toolbar toolbar = (Toolbar) this.binding.toolbar;
        setSupportActionBar(toolbar);

        subscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void subscribe() {
        this.subscriptions.add(this.viewModel.skipCommand.subscribe(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
            finish();
        }));
    }
}
