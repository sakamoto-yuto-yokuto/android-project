package jp.co.sakamoto.androidproject.framework.di;


import dagger.Module;
import dagger.Provides;
import jp.co.sakamoto.androidproject.data.entity.OrmaDatabase;
import jp.co.sakamoto.androidproject.data.repository.IUserRepository;
import jp.co.sakamoto.androidproject.data.repository.impl.UserRepository;
import jp.co.sakamoto.androidproject.framework.retrofit.ApiInterface;

@Module
public class RepositoryModule {
    @Provides
    public IUserRepository provideUserRepository(OrmaDatabase db, ApiInterface apiInterface) {
        return new UserRepository(db, apiInterface);
    }
}
