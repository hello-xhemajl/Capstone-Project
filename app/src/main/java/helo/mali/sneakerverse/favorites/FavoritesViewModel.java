package helo.mali.sneakerverse.favorites;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import helo.mali.sneakerverse.sneakers.Sneakers;
import helo.mali.sneakerverse.sneakers.SneakersRepository;
import helo.mali.sneakerverse.usersneakers.UserSneakersRepository;
import helo.mali.sneakerverse.usersneakers.UserWithSneakers;

public class FavoritesViewModel  extends AndroidViewModel {

    private LiveData<UserWithSneakers> userWithSneakersIds;
    private LiveData<List<Sneakers>> userFavoriteSneakers;

    private UserSneakersRepository userSneakersRepository;

    private SneakersRepository sneakersRepository;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        userSneakersRepository = new UserSneakersRepository(application);
        sneakersRepository = new SneakersRepository(application);
    }

    public LiveData<UserWithSneakers> getUserWithSneakersIds(String userId){
        if(userWithSneakersIds == null){
            userWithSneakersIds = userSneakersRepository.getUserAndFavoriteSneakersIds(userId);
        }

        return userWithSneakersIds;
    }

    public LiveData<List<Sneakers>> getFavoriteSneakers(List<Long> sneakersIds){
        if(userFavoriteSneakers == null){
            userFavoriteSneakers = sneakersRepository.getSneakersByIds(sneakersIds);
        }

        return userFavoriteSneakers;
    }

}
