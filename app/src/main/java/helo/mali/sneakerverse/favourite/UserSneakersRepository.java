package helo.mali.sneakerverse.favourite;

import android.content.Context;

import java.util.concurrent.Executor;

import helo.mali.sneakerverse.AppDatabase;
import helo.mali.sneakerverse.helper.AppExecutors;

public class UserSneakersRepository {
    private UserSneakersDao userSneakersDao;
    private Executor executor;

    public UserSneakersRepository(Context context){
        userSneakersDao = AppDatabase.getInstance(context).userSneakersDao();
        executor = AppExecutors.getInstance().diskIO();
    }

    public void save(UserSneakers userSneakers){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userSneakersDao.save(userSneakers);
            }
        });
    }

    public void deleteByUserIdSneakersId(String userId, Long sneakersId){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userSneakersDao.deleteByUserIdSneakersId(userId, sneakersId);
            }
        });
    }
}
