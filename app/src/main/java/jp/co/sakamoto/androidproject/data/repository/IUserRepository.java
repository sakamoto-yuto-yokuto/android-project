package jp.co.sakamoto.androidproject.data.repository;

import io.reactivex.Single;
import jp.co.sakamoto.androidproject.data.entity.User;
import jp.co.sakamoto.androidproject.domain.model.LoginChallenge;
import jp.co.sakamoto.androidproject.domain.model.LoginResult;
import jp.co.sakamoto.androidproject.domain.model.SaveUserResult;

public interface IUserRepository extends IRepository<User> {
    Single<LoginResult> login(LoginChallenge model);
    Single<SaveUserResult> saveUser(User user);
}
