package helo.mali.sneakerverse.favourite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index(value = {"userId", "sneakersId"},
        unique = true)}, tableName = "user_sneakers")
public class UserSneakers {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String userId;

    private Long sneakersId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSneakersId() {
        return sneakersId;
    }

    public void setSneakersId(Long sneakersId) {
        this.sneakersId = sneakersId;
    }
}
