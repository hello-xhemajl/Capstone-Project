package helo.mali.sneakerverse.browse;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.browse.BrowserFragment;
import helo.mali.sneakerverse.browse.detail.SneakersDetailFragment;
import helo.mali.sneakerverse.sneakers.Sneakers;

public class BrowserActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    public static final String EXTRA_SNEAKERS_ID = "extra_sneakers_id";

    BrowserViewModel browserVm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        browserVm = ViewModelProviders.of(this).get(BrowserViewModel.class);

        // Has this all been started for displaying {@link SneakersDetailFragment}?
        if (getIntent().hasExtra(EXTRA_SNEAKERS_ID)) {
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
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new BrowserFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackStackChanged() {
        maybeDisplayBottomNavigation();
        maybeFinish();
    }

    private void maybeDisplayBottomNavigation(){

    }

    private void maybeFinish(){
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            finish();
        };
    }

    private void navigateToSneakersDetails() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SneakersDetailFragment())
                .addToBackStack(null)
                .commit();
    }


}
