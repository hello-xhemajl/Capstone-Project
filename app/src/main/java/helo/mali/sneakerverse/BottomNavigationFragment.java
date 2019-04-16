package helo.mali.sneakerverse;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavigationFragment extends Fragment {
    public static final int LOCATION_BROWSER = 0;
    public static final int LOCATION_FAVORITES = 1;

    public static final String ARGUMENT_CHECKED_LOCATION = "checkedLocation";

    public static BottomNavigationFragment newInstance(int checkedLocation){
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_CHECKED_LOCATION, checkedLocation);
        BottomNavigationFragment bottomNavigationFragment
                = new BottomNavigationFragment();
        bottomNavigationFragment.setArguments(arguments);
        return bottomNavigationFragment;
    }

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationViewEx bottomNavigationView;

    OnNavigateListener listener;

    public interface OnNavigateListener{
        void onNavigate(int location);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof OnNavigateListener)){
            throw new IllegalStateException("Activity must implement OnNavigateListener interface");
        }

        listener = (OnNavigateListener) context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView.setTextVisibility(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_browse:
                        listener.onNavigate(LOCATION_BROWSER);
                        return true;
                    case R.id.item_favorites:
                        listener.onNavigate(LOCATION_FAVORITES);
                        return true;
                }
                return false;
            }
        });

        // Indicate location
        assert getArguments() != null;
        bottomNavigationView.setCurrentItem(getArguments().getInt(ARGUMENT_CHECKED_LOCATION, 0));
    }
}
