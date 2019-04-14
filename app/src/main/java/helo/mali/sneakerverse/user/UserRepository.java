package helo.mali.sneakerverse.user;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.concurrent.Executor;

import helo.mali.sneakerverse.AppDatabase;
import helo.mali.sneakerverse.helper.AppExecutors;

public class UserRepository {
    private UserDao userDao;
    private Executor executor;

    public UserRepository(Context context) {

        userDao = AppDatabase.getInstance(context).userDao();
        executor = AppExecutors.getInstance().diskIO();
    }

    public LiveData<User> getUser(String userId) {
        return userDao.findById(userId);
    }

    public void save(final User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.save(user);
            }
        });
    }
}
