package jp.co.sakamoto.androidproject.framework.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.co.sakamoto.androidproject.AndroidApplication;

@Module
class AppModule {
    @Singleton
    @Provides
    Context provideContext(AndroidApplication application) {
        return application.getApplicationContext();
    }
}
