package helo.mali.sneakerverse.user;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE userId LIKE :userId")
    LiveData<User> findById(String userId);

    @Insert(onConflict = REPLACE)
    void save(User user);
}