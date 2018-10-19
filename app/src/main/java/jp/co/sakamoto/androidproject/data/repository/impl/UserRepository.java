package jp.co.sakamoto.androidproject.data.repository.impl;

import java.net.HttpURLConnection;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.co.sakamoto.androidproject.data.entity.OrmaDatabase;
import jp.co.sakamoto.androidproject.data.entity.User;
import jp.co.sakamoto.androidproject.data.repository.IUserRepository;
import jp.co.sakamoto.androidproject.definition.Message;
import jp.co.sakamoto.androidproject.domain.model.LoginChallenge;
import jp.co.sakamoto.androidproject.domain.model.LoginResult;
import jp.co.sakamoto.androidproject.domain.model.SaveUserResult;
import jp.co.sakamoto.androidproject.framework.retrofit.ApiInterface;
import retrofit2.Retrofit;

public class UserRepository implements IUserRepository {
    private CompositeDisposable subscriptions = new CompositeDisposable();
    private OrmaDatabase db;
    private ApiInterface apiInterface;

    public UserRepository(OrmaDatabase db, Retrofit retrofit) {
        this.db = db;
        this.apiInterface = retrofit.create(ApiInterface.class);
    }

    @Override
    public void dispose() {
        if (!this.isDisposed()) this.subscriptions.dispose();
    }

    @Override
    public boolean isDisposed() {
        return this.subscriptions.isDisposed();
    }

    @Override
    public Single<LoginResult> login(LoginChallenge model) {
        return Single.create(emitter -> {
            try {
                Disposable disposable = this.apiInterface.login(model).subscribeOn(Schedulers.newThread()).subscribe(response -> {
                    LoginResult result;
                    if (response != null && response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                        result = response.body();
                    } else {
                        result = LoginResult.newInstance(LoginResult.RESULT_NG, Message.DEFAULT_ERROR, null);
                    }
                    emitter.onSuccess(result);
                }, e -> {
                    e.printStackTrace();
                    if (!emitter.isDisposed()) emitter.onError(e);
                });
                this.subscriptions.add(disposable);
            } catch (Exception e) {
                e.printStackTrace();
                if (!emitter.isDisposed()) emitter.onError(e);
            }
        });
    }

    @Override
    public Single<SaveUserResult> saveUser(User user) {
        return Single.create(emitter -> {
            SaveUserResult result = SaveUserResult.newInstance(true, null);
            try {
                db.transactionSync(() -> {
                    db.deleteFromUser().execute();
                    db.insertIntoUser(user);
                });
                emitter.onSuccess(result);
            } catch (Exception e) {
                e.printStackTrace();
                if (!emitter.isDisposed()) emitter.onError(e);
            }
        });
    }
}