package jp.co.sakamoto.androidproject.framework.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jp.co.sakamoto.androidproject.presentation.view.activity.MainActivity;
import jp.co.sakamoto.androidproject.presentation.view.activity.SecondActivity;

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = { SecondActivityModule.class })
    abstract SecondActivity contributeSecondActivity();
}
