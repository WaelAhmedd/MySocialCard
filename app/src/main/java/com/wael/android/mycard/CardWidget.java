package com.wael.android.mycard;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.wael.android.mycard.Card.model.Card;
import com.wael.android.mycard.Main.View.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class CardWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int  appWidgetId) {


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        SharedPreferences prefs =
                context.getSharedPreferences("MyPrefs", 0);
        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.card_widget);
        views.setTextViewText(R.id.user_name_widget,prefs.getString("User_Name",null)
                );
        String sso=prefs.getString("User_Name",null);
        views.setOnClickPendingIntent(R.id.open_username,pendingIntent);
       appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

