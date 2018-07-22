package jp.co.sakamoto.androidproject;

import android.app.Application;
import javax.inject.Singleton;

import jp.co.sakamoto.androidproject.framework.di.AppComponent;
import jp.co.sakamoto.androidproject.framework.di.DaggerAppComponent;
import jp.co.sakamoto.androidproject.framework.di.FrameworkModule;
import jp.co.sakamoto.androidproject.framework.di.RepositoryModule;
import jp.co.sakamoto.androidproject.framework.di.ServiceModule;

public class AndroidApplication extends Application {
    @Singleton
    public static AndroidApplication application;

    @Singleton
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initializeInjector();

        appComponent.inject(this);
    }

    public static AndroidApplication getInstance() {
        return application;
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .serviceModule(new ServiceModule())
                .repositoryModule(new RepositoryModule())
                .frameworkModule(new FrameworkModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
