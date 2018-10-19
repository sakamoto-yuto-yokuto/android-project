package jp.co.sakamoto.androidproject.data.entity;

import android.support.annotation.NonNull;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

@Table
public class Tool {
    @PrimaryKey(auto = false)
    @Column("id")
    private String id;

    @Column("name")
    private String name;

    public static Tool newInstance(@NonNull String id, @NonNull String name) {
        Tool tool = new Tool();
        tool.id = id;
        tool.name = name;
        return tool;
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
