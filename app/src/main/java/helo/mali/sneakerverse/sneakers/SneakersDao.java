package helo.mali.sneakerverse.sneakers;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SneakersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Sneakers... sneakers);

    @Query("SELECT * FROM sneakers")
    LiveData<List<Sneakers>> findAll();

    @Query("SELECT * FROM sneakers WHERE sneakersId IN (:sneakersIds)")
    LiveData<List<Sneakers>> findByIds(List<Long> sneakersIds);
}
