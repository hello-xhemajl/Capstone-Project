package helo.mali.sneakerverse.usersneakers;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import helo.mali.sneakerverse.user.User;

public class UserWithSneakers {
    @Embedded
    User user;

    @Relation(
            parentColumn = "userId",
            entityColumn = "userId",
            entity = UserSneakers.class,
            projection = "sneakersId"
    )
    List<Long> sneakersIds;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Long> getFavoriteSneakersIds() {
        return sneakersIds;
    }

    public void setSneakersIds(List<Long> sneakersIds) {
        this.sneakersIds = sneakersIds;
    }
}
