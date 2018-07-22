package jp.co.sakamoto.androidproject.domain.model;

public class SaveUserResult {
    private boolean isSuccess;
    private String message;

    public static SaveUserResult newInstance(boolean isSuccess, String message) {
        SaveUserResult model = new SaveUserResult();
        model.isSuccess = isSuccess;
        model.message = message;
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
}
