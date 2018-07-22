package jp.co.sakamoto.androidproject.framework.di;

import javax.inject.Singleton;

import dagger.Component;
import jp.co.sakamoto.androidproject.AndroidApplication;
import jp.co.sakamoto.androidproject.presentation.view.activity.MainActivity;
import jp.co.sakamoto.androidproject.presentation.view.fragment.DrawerFragment;

@Singleton
@Component(modules = {RepositoryModule.class, ServiceModule.class, FrameworkModule.class})
public interface AppComponent {
    // Application
    void inject(AndroidApplication application);

    // Activity
    void inject(MainActivity activity);

    // Fragment
    void inject(DrawerFragment fragment);

    // ViewModel

    // Repository

    // Usecase
}
