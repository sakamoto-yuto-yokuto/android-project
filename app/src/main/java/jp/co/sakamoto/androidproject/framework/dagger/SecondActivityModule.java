package jp.co.sakamoto.androidproject.framework.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jp.co.sakamoto.androidproject.presentation.view.fragment.GalleryFragment;
import jp.co.sakamoto.androidproject.presentation.view.fragment.ToolsFragment;

@Module
abstract class SecondActivityModule {
    @ContributesAndroidInjector
    abstract GalleryFragment contributeGalleryFragment();

    @ContributesAndroidInjector
    abstract ToolsFragment contributeToolsFragment();
}
