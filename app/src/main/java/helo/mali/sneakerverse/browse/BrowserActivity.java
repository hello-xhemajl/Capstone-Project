package helo.mali.sneakerverse.browse;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.sneakers.Sneakers;

public class BrowserActivity extends AppCompatActivity implements
        BrowserAdapter.SneakersVh.OnSneakersEventsListener {

    BrowserViewModel browserVm;

    @BindView(R.id.browser_recycler_view)
    RecyclerView browserRv;
    BrowserAdapter browserAdapter;

    List<Sneakers> sneakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);

        browserVm = ViewModelProviders.of(this).get(BrowserViewModel.class);

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
        Toast.makeText(this, "item clieced " + position, Toast.LENGTH_SHORT)
                .show();
    }
}
