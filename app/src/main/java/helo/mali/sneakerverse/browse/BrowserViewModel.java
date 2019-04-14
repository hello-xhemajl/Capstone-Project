package helo.mali.sneakerverse.browse;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import helo.mali.sneakerverse.favourite.UserSneakersBuilder;
import helo.mali.sneakerverse.favourite.UserSneakersRepository;
import helo.mali.sneakerverse.sneakers.Sneakers;
import helo.mali.sneakerverse.sneakers.SneakersRepository;
import helo.mali.sneakerverse.user.User;

public class BrowserViewModel extends AndroidViewModel {
    private SneakersRepository sneakersRepository;
    private UserSneakersRepository userSneakersRepository;

    private LiveData<List<Sneakers>> sneakers;
    private MutableLiveData<Sneakers> selectedSneakers;

    public BrowserViewModel(@NonNull Application application) {
        super(application);
        sneakersRepository = new SneakersRepository(application);
        userSneakersRepository = new UserSneakersRepository(application);
        selectedSneakers = new MutableLiveData<>();
    }

    public LiveData<List<Sneakers>> getSneakers() {
        if (sneakers == null) {
            sneakers = sneakersRepository.getSneakers();
        }

        return sneakers;
    }

    public LiveData<Sneakers> getSelectedSneakers() {
        return selectedSneakers;
    }

    public void selectSneakers(Sneakers sneakers) {
        selectedSneakers.setValue(sneakers);
    }

    public void favouriteSneakers(String userId, Long sneakersId) {
        userSneakersRepository.save(UserSneakersBuilder.anUserSneakersTuple()
                .withUserId(userId)
                .withSneakersId(sneakersId)
                .build());
    }

    public void unFavouriteSneakers(String userId, Long sneakersId) {
        userSneakersRepository.deleteByUserIdSneakersId(userId, sneakersId);
    }
}
