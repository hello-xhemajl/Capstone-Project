package helo.mali.sneakerverse.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.browse.BrowserActivity;
import helo.mali.sneakerverse.signin.SigninActivity;

public class SneakersAppWidget extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, Long sneakersId, String sneakersName) {

        boolean shouldDisplayEmptyView = (sneakersId == null);

        // Update the body of the widget to display favorite sneakers, or show the empty view if
        // user has not favorited any sneakers yet
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sneakers_app_widget);
        views.setTextViewText(R.id.favorite_sneakers_text_view,
                shouldDisplayEmptyView ? context.getString(R.string.appwidget_empty_view_text) :
                        context.getString(R.string.appwidget_body_text, sneakersName));


        // Initially clicking app widget should send user to sign in. If user favorited any sneakers,
        // clicking should send her to sneakers detail view(provided with sneakersId, {@link BrowserActivity}
        // starts {@link SneakersDetailFragment})
        Intent intent = new Intent(context, shouldDisplayEmptyView ? SigninActivity.class: BrowserActivity.class);
        intent.putExtra(BrowserActivity.EXTRA_SNEAKERS_ID, sneakersId);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.appwidget_view, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateAllWidgets(Context context, AppWidgetManager appWidgetManager,
                                        int[] appWidgetIds, Long sneakersId, String sneakersName) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, sneakersId, sneakersName);
        }
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        SneakersWidgetUpdaterService.startActionUpdateWidget(context);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
