package jp.co.sakamoto.androidproject.domain.model;

import com.google.gson.annotations.SerializedName;

public class LoginChallenge {
    @SerializedName("user_id")
    private String userId;

    @SerializedName("password")
    private String password;

    public static LoginChallenge newInstance(String userId, String password) {
        LoginChallenge model = new LoginChallenge();
        model.userId = userId;
        model.password = password;
        return model;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
