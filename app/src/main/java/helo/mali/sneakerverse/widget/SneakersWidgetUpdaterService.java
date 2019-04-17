package helo.mali.sneakerverse.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import helo.mali.sneakerverse.sneakers.Sneakers;
import helo.mali.sneakerverse.sneakers.SneakersRepository;
import helo.mali.sneakerverse.usersneakers.UserSneakers;
import helo.mali.sneakerverse.usersneakers.UserSneakersRepository;

public class SneakersWidgetUpdaterService extends IntentService {
    public static final String ACTION_UPDATE_WIDGET = "helo.mali.sneakerverse";

    public SneakersWidgetUpdaterService() {
        super("SneakersWidgetUpdaterService");
    }

    public static void startActionUpdateWidget(Context context) {
        Intent intent = new Intent(context, SneakersWidgetUpdaterService.class);
        intent.setAction(ACTION_UPDATE_WIDGET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (ACTION_UPDATE_WIDGET.equals(intent.getAction())) {
            handleActionUpdateWidget();
        }
    }

    private void handleActionUpdateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appwidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, SneakersAppWidget.class));

        UserSneakersRepository userSneakersRepository = userSneakersRepository = new UserSneakersRepository(this);
        SneakersRepository sneakersRepository = sneakersRepository = new SneakersRepository(this);

        FirebaseUser signedInUser = FirebaseAuth.getInstance().getCurrentUser();

        // Get latest favorited sneakers for user
        UserSneakers favoriteSneakers = signedInUser == null ? null
                : userSneakersRepository.getLatestFavoriteSneakers(signedInUser.getUid());

        if (favoriteSneakers == null) {
            // App widget should display the empty view when there are no favorites
            // {@link SneakersAppWidget} detects that when sneakersId is null
            SneakersAppWidget.updateAllWidgets(this, appWidgetManager, appwidgetIds, null, null);
            return;
        }

        // Get full sneakers info
        Sneakers sneakers = sneakersRepository.getSneakersByIdNotLive(favoriteSneakers.getSneakersId());
        SneakersAppWidget.updateAllWidgets(this, appWidgetManager, appwidgetIds, sneakers.getSneakersId(), sneakers.getName());
    }
}
