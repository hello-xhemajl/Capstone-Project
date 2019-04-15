package helo.mali.sneakerverse;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import helo.mali.sneakerverse.usersneakers.UserSneakers;
import helo.mali.sneakerverse.usersneakers.UserSneakersDao;
import helo.mali.sneakerverse.sneakers.Sneakers;
import helo.mali.sneakerverse.sneakers.SneakersDao;
import helo.mali.sneakerverse.user.User;
import helo.mali.sneakerverse.user.UserDao;

@Database(entities = {Sneakers.class, User.class, UserSneakers.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "sneakerverse";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }

        return sInstance;
    }

     public abstract SneakersDao sneakersDao();
     public abstract UserDao userDao();
     public abstract UserSneakersDao userSneakersDao();
}
