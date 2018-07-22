package jp.co.sakamoto.androidproject.domain.model;

import jp.co.sakamoto.androidproject.data.entity.User;

public class GetUserResult {
    private boolean isSuccess;
    private String message;
    private User user;

    public static GetUserResult newInstance(boolean isSuccess, String message, User user) {
        GetUserResult model = new GetUserResult();
        model.isSuccess = isSuccess;
        model.message = message;
        model.user = user;
        return model;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
