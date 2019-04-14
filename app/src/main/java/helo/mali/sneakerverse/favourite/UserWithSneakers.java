package helo.mali.sneakerverse.favourite;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import helo.mali.sneakerverse.user.User;

public class UserWithSneakers {
    @Embedded
    User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "userId",
            entity = UserSneakers.class,
            projection = "sneakersId"
    )
    List<Long> sneakersIds;
}
