package jp.co.sakamoto.androidproject.framework.di;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.github.gfx.android.orma.AccessThreadConstraint;
import com.github.gfx.android.orma.migration.ManualStepMigration;
import com.github.gfx.android.orma.migration.OrmaMigration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.co.sakamoto.androidproject.BuildConfig;
import jp.co.sakamoto.androidproject.data.entity.OrmaDatabase;
import jp.co.sakamoto.androidproject.data.entity.User;
import jp.co.sakamoto.androidproject.definition.Constant;
import jp.co.sakamoto.androidproject.framework.retrofit.ApiInterface;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class FrameworkModule {
    private static final int DB_VERSION_1 = 1;

    private OrmaDatabase orma;

    public FrameworkModule(Context context) {
        orma = OrmaDatabase.builder(context)
                .readOnMainThread(AccessThreadConstraint.NONE)
                .writeOnMainThread(AccessThreadConstraint.NONE)
                .migrationStep(DB_VERSION_1, new ManualStepMigration.ChangeStep() {
                    @Override
                    public void change(@NonNull ManualStepMigration.Helper helper) {
                        Log.d("ManualStepMigration", "Do Migration VERSION : " + DB_VERSION_1);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    public OrmaDatabase provideOrmaDatabase() {
        return this.orma;
    }

    @Provides
    @Singleton
    public ApiInterface provideApiInterface(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiInterface.class);
    }
}
