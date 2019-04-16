package helo.mali.sneakerverse.browse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.browse.BrowserFragment;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new BrowserFragment())
                .commit();
    }


}
