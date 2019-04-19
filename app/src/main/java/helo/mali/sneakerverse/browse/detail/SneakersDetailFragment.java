package helo.mali.sneakerverse.browse.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import helo.mali.sneakerverse.AdConfigurer;
import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.browse.BrowserViewModel;
import helo.mali.sneakerverse.sneakers.Sneakers;
import helo.mali.sneakerverse.usersneakers.UserWithSneakers;
import helo.mali.sneakerverse.widget.SneakersWidgetUpdaterService;

public class SneakersDetailFragment extends Fragment {

    BrowserViewModel browserVm;

    @BindView(R.id.favourite_fab)
    FloatingActionButton favouriteButton;

    @BindView(R.id.backdrop)
    ImageView backdropImageView;

    @BindView(R.id.design_story_text_view)
    TextView designStoryTextView;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Sneakers sneakers;

    boolean hasMarkedAsFavorite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_sneakers_detail, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        maybeDisplayAds(view);

        browserVm = ViewModelProviders.of(getActivity()).get(BrowserViewModel.class);
        browserVm.getSelectedSneakers().observe(this, new Observer<Sneakers>() {
            @Override
            public void onChanged(@Nullable Sneakers lSneakers) {
                sneakers = lSneakers;
                notifySneakersChanged(sneakers);
            }
        });

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSneakersfavourite();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    public void onSneakersfavourite() {
        hasMarkedAsFavorite = !hasMarkedAsFavorite;

        // Get user of the app
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(hasMarkedAsFavorite){
            browserVm.favouriteSneakers(user.getUid(), sneakers.getSneakersId());
            SneakersWidgetUpdaterService.startActionUpdateWidget(getContext());
        } else {
            browserVm.unFavouriteSneakers(user.getUid(), sneakers.getSneakersId());
            SneakersWidgetUpdaterService.startActionUpdateWidget(getContext());
        }

        // Change fab to indicate marked/unmarked as favorite
        favouriteButton.setImageResource(hasMarkedAsFavorite ?
                R.drawable.ic_favorite_black_24dp :
                R.drawable.ic_favorite_border_black_24dp);

        // Show toast to indicate marked/unmarked as favorite
        Toast.makeText(getContext(), hasMarkedAsFavorite ?
                        R.string.action_marked_favorite :
                        R.string.action_unmarked_favorite,
                Toast.LENGTH_SHORT)
                .show();

        // Refresh content description of the fab button because its purpose is negated
        favouriteButton.setContentDescription(hasMarkedAsFavorite
                ? getString(R.string.action_unmark_favorite)
                : getString(R.string.action_mark_favorite));
    }

    public void maybeDisplayAds(View view){
        AdConfigurer adConfigurer = new AdConfigurer();
        adConfigurer.configureAds(view);
    }

    public void notifySneakersChanged(Sneakers sneakers) {
        collapsingToolbarLayout.setTitle(sneakers.getName());
        designStoryTextView.setText(sneakers.getDesignStory());
        backdropImageView.setContentDescription(sneakers.getImageContentDescription());
        Picasso.get()
                .load(sneakers.getImageUri())
                .into(backdropImageView);

        // Check if sneakers are marked as favorites and update fab button accordingly
        browserVm.getUserWithSneakersIds(FirebaseAuth.getInstance().getCurrentUser().getUid()).observe(this,
                new Observer<UserWithSneakers>() {
                    @Override
                    public void onChanged(@Nullable UserWithSneakers userWithSneakers) {
                        boolean areFavorite = userWithSneakers.getFavoriteSneakersIds().contains(sneakers.getSneakersId());

                        hasMarkedAsFavorite = areFavorite;

                        favouriteButton.setImageResource(areFavorite ?
                                R.drawable.ic_favorite_black_24dp :
                                R.drawable.ic_favorite_border_black_24dp);

                        // Set content description to communicate fab's purpose
                        favouriteButton.setContentDescription(areFavorite
                                ? getString(R.string.action_unmark_favorite)
                                : getString(R.string.action_mark_favorite));
                    }
                });

    }
}
