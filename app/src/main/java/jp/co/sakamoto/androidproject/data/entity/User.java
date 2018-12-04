package jp.co.sakamoto.androidproject.data.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

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

    public User(@Setter @NonNull String id, @Setter String password, @Setter String token) {
        this.id = id;
        this.password = password;
        this.token = token;
    }

    public String getId() {
        return id;
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
