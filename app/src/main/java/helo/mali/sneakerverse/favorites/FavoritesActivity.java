package helo.mali.sneakerverse.favorites;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import helo.mali.sneakerverse.BottomNavigationFragment;
import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.browse.BrowserActivity;

import static helo.mali.sneakerverse.BottomNavigationFragment.LOCATION_BROWSER;
import static helo.mali.sneakerverse.BottomNavigationFragment.LOCATION_FAVORITES;

public class FavoritesActivity extends AppCompatActivity implements
        FragmentManager.OnBackStackChangedListener,
        BottomNavigationFragment.OnNavigateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FavoritesFragment())
                .commit();

        addBottomNavigation();
    }

    @Override
    public void onNavigate(int location) {
        switch (location){
            case LOCATION_FAVORITES:
                break;
            case LOCATION_BROWSER:
                navigateToBrowser();
                break;
        }
    }

    @Override
    public void onBackStackChanged() {
        maybeFinish();
    }

    private void addBottomNavigation(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.bottom_navigation_container, BottomNavigationFragment.newInstance(LOCATION_FAVORITES))
                .commit();
    }

    private void maybeFinish(){
        if(getSupportFragmentManager().getBackStackEntryCount() < 0){
            finish();
        };
    }

    private void navigateToBrowser(){
        Intent intent = new Intent(this, BrowserActivity.class);
        startActivity(intent);
        // Disable default opening transition because it not suited when both activities
        // have a bottom navigation view
        overridePendingTransition(0, 0);
        finish();
    }
}