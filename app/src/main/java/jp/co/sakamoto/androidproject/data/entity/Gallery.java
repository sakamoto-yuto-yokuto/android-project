package jp.co.sakamoto.androidproject.data.entity;

import android.support.annotation.NonNull;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

@Table
public class Gallery {
    @PrimaryKey(auto = false)
    @Column("id")
    private String id;

    @Column("name")
    private String name;

    public static Gallery newInstance(@NonNull String id, @NonNull String name) {
        Gallery gallery = new Gallery();
        gallery.id = id;
        gallery.name = name;
        return gallery;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
