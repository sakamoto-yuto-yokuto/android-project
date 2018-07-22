package jp.co.sakamoto.androidproject.presentation.viewmodel.activity;

import android.databinding.ObservableField;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import jp.co.sakamoto.androidproject.domain.model.LoginChallenge;
import jp.co.sakamoto.androidproject.domain.usecase.LoginUser;
import jp.co.sakamoto.androidproject.presentation.viewmodel.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private LoginUser loginUser;

    public final ObservableField<String> userId = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<String> result = new ObservableField<>();
    public final ObservableField<String> message = new ObservableField<>();
    public final ObservableField<String> token = new ObservableField<>();
    public final ObservableField<Boolean> isLoading = new ObservableField<>(false);

    @Inject
    public MainActivityViewModel(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public void loginCommand() {
        if (userId.get() == null || userId.get().isEmpty() || password.get() == null || password.get().isEmpty()) {
            result.set(null);
            message.set("ユーザーID、パスワードを入力してください。");
            token.set(null);
        } else {
            isLoading.set(true);
            Disposable disposable = this.loginUser.login(LoginChallenge.newInstance(userId.get(), password.get())).subscribe(resultModel -> {
                result.set(resultModel.getResult());
                message.set(resultModel.getMessage());
                token.set(resultModel.getToken());
                isLoading.set(false);
            });
            this.subscriptions.add(disposable);
        }
    }
}
