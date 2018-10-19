package jp.co.sakamoto.androidproject.domain.usecase;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import jp.co.sakamoto.androidproject.data.entity.User;
import jp.co.sakamoto.androidproject.data.repository.IUserRepository;
import jp.co.sakamoto.androidproject.definition.Message;
import jp.co.sakamoto.androidproject.domain.model.LoginChallenge;
import jp.co.sakamoto.androidproject.domain.model.LoginResult;

public class LoginUser extends Usecase {
    private IUserRepository userRepository;

    @Inject
    public LoginUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<LoginResult> login(LoginChallenge model) {
        return this.userRepository.login(model).flatMap(loginResult -> Single.create(emitter -> {
            if (loginResult.isSuccess()) {
                User user = User.newInstance(model.getUserId(), model.getPassword(), loginResult.getToken());
                Disposable disposable = this.userRepository.saveUser(user).subscribe(saveUserResult -> {
                    String result = saveUserResult.isSuccess() ? LoginResult.RESULT_OK : LoginResult.RESULT_NG;
                    String message = saveUserResult.isSuccess() ? Message.LOGIN_SUCCESS : saveUserResult.getMessage();
                    String token = saveUserResult.isSuccess() ? loginResult.getToken() : null;
                    emitter.onSuccess(LoginResult.newInstance(result, message, token));
                });
                this.subscriptions.add(disposable);
            } else {
                emitter.onSuccess(LoginResult.newInstance(loginResult.getResult(), loginResult.getMessage(), null));
            }
        }));
    }
}
