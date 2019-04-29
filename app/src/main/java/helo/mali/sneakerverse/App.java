package helo.mali.sneakerverse;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            populateDatabase();
            requestPeriodicSync();

            // mark first time has ran.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

    }

    private void populateDatabase() {
        new AppDatabasePopulator(this)
                .populateSneakers();
    }

    private void requestPeriodicSync() {
        // Sync only when network is not type roaming
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_ROAMING).build();
        PeriodicWorkRequest syncSneakersRequest =
                // Sync seneakers with backend every week
                new PeriodicWorkRequest.Builder(SyncWorker.class, 7, TimeUnit.DAYS)
                        .setConstraints(constraints)
                        .build();

        WorkManager.getInstance()
                .enqueue(syncSneakersRequest);
    }
}
