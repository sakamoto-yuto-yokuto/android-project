package jp.co.sakamoto.androidproject.framework.dagger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.github.gfx.android.orma.migration.ManualStepMigration;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.co.sakamoto.androidproject.BuildConfig;
import jp.co.sakamoto.androidproject.data.entity.OrmaDatabase;
import jp.co.sakamoto.androidproject.definition.ApiConfig;
import jp.co.sakamoto.androidproject.definition.DbConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class FrameworkModule {
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(ApiConfig.CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(ApiConfig.READ_TIMEOUT_SEC, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder().build();
                    return chain.proceed(request);
                }).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OrmaDatabase provideOrmaDatabase(Context context) {
        return OrmaDatabase.builder(context)
                .name(DbConfig.DB_NAME)
                .migrationStep(DbConfig.DB_VERSION_1, new ManualStepMigration.ChangeStep() {
                    @Override
                    public void change(@NonNull ManualStepMigration.Helper helper) {
                        Log.d("ManualStepMigration", "Do Migration VERSION : " + DbConfig.DB_VERSION_1);
                    }
                })
                .build();
    }
}
