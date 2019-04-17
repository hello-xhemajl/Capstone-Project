package helo.mali.sneakerverse.browse;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import helo.mali.sneakerverse.BottomNavigationFragment;
import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.browse.detail.SneakersDetailFragment;
import helo.mali.sneakerverse.favorites.FavoritesActivity;
import helo.mali.sneakerverse.sneakers.Sneakers;

import static helo.mali.sneakerverse.BottomNavigationFragment.LOCATION_BROWSER;
import static helo.mali.sneakerverse.BottomNavigationFragment.LOCATION_FAVORITES;

public class BrowserActivity extends AppCompatActivity implements
        FragmentManager.OnBackStackChangedListener,
        BottomNavigationFragment.OnNavigateListener {

    public static final String EXTRA_SNEAKERS_ID = "extra_sneakers_id";
    public static final String EXTRA_IS_FROM_WIDGET = "extra_is_from_widget";

    BrowserViewModel browserVm;

    @BindView(R.id.bottom_navigation_container)
    View bottomNavigationContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);

        browserVm = ViewModelProviders.of(this).get(BrowserViewModel.class);

        // Has this all been started for displaying {@link SneakersDetailFragment}?
        if (getIntent().hasExtra(EXTRA_SNEAKERS_ID)) {

            // If intent is from widget, up button should send user to {@link BrowserFragment}
            if(getIntent().getBooleanExtra(EXTRA_IS_FROM_WIDGET, false)){
                addBrowserFragment();
            }

            browserVm.getSneakersById(getIntent().getLongExtra(EXTRA_SNEAKERS_ID, 0))
                    .observe(this, new Observer<Sneakers>() {
                        @Override
                        public void onChanged(@Nullable Sneakers sneakers) {
                            // Required. {@link BrowserViewModel} is shared between {@link BrowserFragment}
                            // and {@link SneakersDetailFragment}, which expects some sneakers be selected
                            browserVm.selectSneakers(sneakers);
                            navigateToSneakersDetails();
                        }
                    });
        } else {
            // only add {@link BrowserFragment}
            addBrowserFragment();
        }

        addBottomNavigation();
    }

    @Override
    public void onNavigate(int location) {
        switch (location){
            case LOCATION_FAVORITES:
                navigateToFavorites();
                break;
            case LOCATION_BROWSER:
                break;
        }
    }

    @Override
    public void onBackStackChanged() {
        maybeDisplayBottomNavigation();
        maybeFinish();
    }

    private void addBrowserFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new BrowserFragment(), BrowserFragment.TAG)
                .commit();
    }

    private void addBottomNavigation(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.bottom_navigation_container, BottomNavigationFragment.newInstance(LOCATION_BROWSER))
                .commit();
    }

    private void maybeDisplayBottomNavigation(){
        bottomNavigationContainer.setVisibility(getSupportFragmentManager().getBackStackEntryCount() > 0
                ? View.INVISIBLE: View.VISIBLE);
    }

    private void maybeFinish(){
        if(getSupportFragmentManager().getBackStackEntryCount() == 0
            && getSupportFragmentManager().findFragmentByTag(BrowserFragment.TAG) == null){
            finish();
        };
    }

    private void navigateToSneakersDetails() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SneakersDetailFragment())
                .addToBackStack(null)
                .commit();
    }

    private void navigateToFavorites() {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
        finish();
    }


}
