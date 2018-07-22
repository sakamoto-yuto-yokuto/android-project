package jp.co.sakamoto.androidproject.domain.model;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

public class LoginResult {
    public static final String RESULT_OK = "OK";
    public static final String RESULT_NG = "NG";

    @SerializedName("result")
    private String result;

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    public static LoginResult newInstance(@NonNull String result, String message, String token) {
        LoginResult model = new LoginResult();
        model.result = result;
        model.message = message;
        model.token = token;
        return model;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return this.result != null && RESULT_OK.equals(this.result);
    }
}
