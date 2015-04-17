package com.entertainment.danielvalencia.diskbar.com.entertainment.tools;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;

import com.entertainment.danielvalencia.diskbar.MainScreen;
import com.entertainment.danielvalencia.diskbar.R;

/**
 * Created by daniel.valencia on 17/04/2015.
 */
public class Notifications {

    public static void launchNotifications(Activity activity, String title, String text, PendingIntent pendingIntent) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(activity)
                        .setSmallIcon(R.drawable.disco_icon)
                        .setContentTitle(title)
                        .setContentText(text);

        mBuilder.setContentIntent(pendingIntent);
        int mNotificationId = 001;
        //mBuilder.setNumber(mNotificationId);

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) activity.getSystemService(MainScreen.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }
}
