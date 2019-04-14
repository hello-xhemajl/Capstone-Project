package helo.mali.sneakerverse.signin;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import helo.mali.sneakerverse.user.User;
import helo.mali.sneakerverse.user.UserRepository;

public class SigninViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    private LiveData<User> user;

    public SigninViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}

