package jp.co.sakamoto.androidproject.framework.retrofit;


import io.reactivex.Single;
import jp.co.sakamoto.androidproject.domain.model.LoginChallenge;
import jp.co.sakamoto.androidproject.domain.model.LoginResult;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("login")
    Single<Response<LoginResult>> login(@Body LoginChallenge model);
}
