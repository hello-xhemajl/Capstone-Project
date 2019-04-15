package helo.mali.sneakerverse.favorites;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.helper.CircleTransform;
import helo.mali.sneakerverse.sneakers.Sneakers;
import helo.mali.sneakerverse.user.User;
import helo.mali.sneakerverse.usersneakers.UserWithSneakers;

public class FavoritesFragment extends Fragment implements FavoritesAdapter.SneakersVh.OnSneakersEventsListener {
    private FavoritesViewModel favoritesVm;

    private User user;
    private List<Sneakers> favoriteSneakers;


    private FavoritesAdapter favoritesAdapter;

    @BindView(R.id.favorites_recycler_view)
    RecyclerView favoritesRecyclerView;

    @BindView(R.id.avatar_image_view)
    ImageView avatarImageView;

    @BindView(R.id.name_text_view)
    TextView nameTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoritesVm = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        favoritesAdapter = new FavoritesAdapter(this);
        favoritesRecyclerView.setAdapter(favoritesAdapter);

        favoritesVm.getUserWithSneakersIds(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .observe(this, new Observer<UserWithSneakers>() {
                    @Override
                    public void onChanged(@Nullable UserWithSneakers userWithSneakersIds) {
                        user = userWithSneakersIds.getUser();
                        onBindUserView();

                        List<Long> favoriteSneakersIds = userWithSneakersIds.getFavoriteSneakersIds();
                        favoritesVm.getFavoriteSneakers(favoriteSneakersIds).observe(FavoritesFragment.this,
                                new Observer<List<Sneakers>>() {
                                    @Override
                                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                                        favoriteSneakers = sneakers;
                                        onBindSneakersView();
                                    }
                                });
                    }
                });
    }

    @Override
    public void onSneakersClicked(int position) {

    }

    public void onBindUserView(){
        Picasso.get()
                .load(user.getAvatarUri())
                .transform(new CircleTransform())
                .into(avatarImageView);

        nameTextView.setText(user.getName());
    }

    public void onBindSneakersView(){
        favoritesAdapter.setSneakers(favoriteSneakers);
    }

}
