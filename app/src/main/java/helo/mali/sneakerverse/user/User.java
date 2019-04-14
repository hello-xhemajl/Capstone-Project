package helo.mali.sneakerverse.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @NonNull
    @PrimaryKey
    private String userId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "avatar_uri")
    private String avatarUri;

    public User(String userId, String name, String avatarUri) {
        this.userId = userId;
        this.name = name;
        this.avatarUri = avatarUri;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }
}
