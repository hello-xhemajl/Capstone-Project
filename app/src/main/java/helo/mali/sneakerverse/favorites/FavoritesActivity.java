package helo.mali.sneakerverse.favorites;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import helo.mali.sneakerverse.R;

public class FavoritesActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FavoritesFragment())
                .commit();
    }

    @Override
    public void onBackStackChanged() {
        maybeDisplayBottomNavigation();
        maybeFinish();
    }

    private void maybeDisplayBottomNavigation(){

    }

    private void maybeFinish(){
        if(getSupportFragmentManager().getBackStackEntryCount() < 0){
            finish();
        };
    }
}
