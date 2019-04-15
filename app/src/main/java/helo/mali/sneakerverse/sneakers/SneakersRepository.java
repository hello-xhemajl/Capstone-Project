package helo.mali.sneakerverse.sneakers;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;

import helo.mali.sneakerverse.AppDatabase;
import helo.mali.sneakerverse.helper.AppExecutors;

public class SneakersRepository {
    private SneakersDao sneakersDao;
    private Executor executor;

    public SneakersRepository(Context context) {
        this.sneakersDao = AppDatabase.getInstance(context).sneakersDao();
        this.executor = AppExecutors.getInstance().diskIO();
    }

    public LiveData<List<Sneakers>> getSneakers(){
        return sneakersDao.findAll();
    }

    public LiveData<List<Sneakers>> getSneakersByIds(List<Long> sneakersIds){
        return sneakersDao.findByIds(sneakersIds);
    }

    public void save(Sneakers... sneakers){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                sneakersDao.save(sneakers);
            }
        });
    }
}
