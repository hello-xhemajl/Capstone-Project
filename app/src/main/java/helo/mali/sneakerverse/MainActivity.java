package helo.mali.sneakerverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import helo.mali.sneakerverse.browse.BrowserFragment;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new BrowserFragment())
                .commit();
    }


}
