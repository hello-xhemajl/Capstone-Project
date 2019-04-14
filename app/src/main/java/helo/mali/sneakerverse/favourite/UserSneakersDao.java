package helo.mali.sneakerverse.favourite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface UserSneakersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(UserSneakers userSneakers);

    @Query("DELETE FROM user_sneakers WHERE userId = :userId AND sneakersId = :sneakersId")
    void deleteByUserIdSneakersId(String userId, Long sneakersId);
}
