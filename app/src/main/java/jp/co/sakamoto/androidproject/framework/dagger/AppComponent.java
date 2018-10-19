package jp.co.sakamoto.androidproject.framework.dagger;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import jp.co.sakamoto.androidproject.AndroidApplication;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        FrameworkModule.class,
        RepositoryModule.class,
        ServiceModule.class
})
interface AppComponent extends AndroidInjector<AndroidApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AndroidApplication> {}
}
