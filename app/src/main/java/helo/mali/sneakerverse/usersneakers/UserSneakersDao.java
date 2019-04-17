package helo.mali.sneakerverse.usersneakers;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

@Dao
public interface UserSneakersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(UserSneakers userSneakers);

    @Query("DELETE FROM user_sneakers WHERE userId = :userId AND sneakersId = :sneakersId")
    void deleteByUserIdSneakersId(String userId, Long sneakersId);

    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId")
    LiveData<UserWithSneakers> findUserAndFavoriteSneakers(String userId);

    @Query("SELECT * FROM user_sneakers WHERE userId = :userId ORDER BY id DESC LIMIT 1")
    UserSneakers findLatestFavoriteSneakers(String userId);
}
