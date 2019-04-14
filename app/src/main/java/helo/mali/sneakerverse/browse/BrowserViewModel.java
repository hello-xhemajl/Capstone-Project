package helo.mali.sneakerverse.browse;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import helo.mali.sneakerverse.sneakers.Sneakers;
import helo.mali.sneakerverse.sneakers.SneakersRepository;

public class BrowserViewModel extends AndroidViewModel {
    private SneakersRepository sneakersRepository;

    private LiveData<List<Sneakers>> sneakers;

    public BrowserViewModel(@NonNull Application application) {
        super(application);
        sneakersRepository = new SneakersRepository(application);
    }

    public LiveData<List<Sneakers>> getSneakers(){
        if(sneakers == null){
            sneakers = sneakersRepository.getSneakers();
        }

        return sneakers;
    }
}
