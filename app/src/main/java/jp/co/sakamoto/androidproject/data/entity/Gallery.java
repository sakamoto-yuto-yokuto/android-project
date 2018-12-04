package jp.co.sakamoto.androidproject.data.entity;

import android.support.annotation.NonNull;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

@Table
public class Gallery {
    @PrimaryKey(auto = false)
    @Column("id")
    private String id;

    @Column("name")
    private String name;

    public Gallery(@Setter @NonNull String id, @Setter String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
