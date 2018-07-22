package jp.co.sakamoto.androidproject.data.entity;

import android.support.annotation.Nullable;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

import io.reactivex.annotations.NonNull;

@Table
public class User {
    @PrimaryKey(auto = false)
    @Column("id")
    private String id;

    @Column("password")
    private String password;

    @Column("token")
    @Nullable
    private String token;

    public static User newInstance(@NonNull String id, @NonNull String password, @NonNull String token) {
        User user = new User();
        user.id = id;
        user.password = password;
        user.token = token;
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
