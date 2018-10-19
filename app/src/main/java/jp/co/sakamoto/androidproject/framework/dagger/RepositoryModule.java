package jp.co.sakamoto.androidproject.framework.dagger;


import dagger.Module;
import dagger.Provides;
import jp.co.sakamoto.androidproject.data.entity.OrmaDatabase;
import jp.co.sakamoto.androidproject.data.repository.IUserRepository;
import jp.co.sakamoto.androidproject.data.repository.impl.UserRepository;
import retrofit2.Retrofit;

@Module
public class RepositoryModule {
    @Provides
    public IUserRepository provideUserRepository(OrmaDatabase db, Retrofit retrofit) {
        return new UserRepository(db, retrofit);
    }
}
