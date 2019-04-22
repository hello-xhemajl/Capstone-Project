package helo.mali.sneakerverse.browse;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.browse.detail.SneakersDetailFragment;
import helo.mali.sneakerverse.sneakers.Sneakers;

public class BrowserFragment extends Fragment implements
        BrowserAdapter.SneakersVh.OnSneakersEventsListener {
    public static final String TAG = BrowserFragment.class.getSimpleName();

    BrowserViewModel browserVm;

    @BindView(R.id.browser_recycler_view)
    RecyclerView browserRv;
    BrowserAdapter browserAdapter;

    List<Sneakers> sneakers;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_browser, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        browserVm = ViewModelProviders.of(getActivity()).get(BrowserViewModel.class);

        browserAdapter = new BrowserAdapter(this);
        browserRv.setAdapter(browserAdapter);

        browserVm.getSneakers().observe(this, new Observer<List<Sneakers>>() {
            @Override
            public void onChanged(@Nullable List<Sneakers> lSneakers) {
                sneakers = lSneakers;
                browserAdapter.setSneakers(sneakers);
            }
        });
    }

    @Override
    public void onSneakersClicked(int position) {
        Sneakers sneakers = this.sneakers.get(position);
        browserVm.selectSneakers(sneakers);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SneakersDetailFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}
