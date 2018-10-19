package jp.co.sakamoto.androidproject;

import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import jp.co.sakamoto.androidproject.framework.dagger.DaggerAppComponent;

public class AndroidApplication extends DaggerApplication {
    @Singleton
    public static AndroidApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .create(this);
    }

    public static AndroidApplication getInstance() {
        return application;
    }
}
