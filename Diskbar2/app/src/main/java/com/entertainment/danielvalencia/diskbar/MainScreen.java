package com.entertainment.danielvalencia.diskbar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.entertainment.danielvalencia.diskbar.com.entertainment.tools.DownloadItems;
import com.entertainment.danielvalencia.diskbar.com.entertainment.tools.Notifications;

import java.util.Timer;
import java.util.TimerTask;

public class MainScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        refresh();
        reviewNews();
    }

    private Timer timerNews = new Timer();
    private Timer timerInvitation = new Timer();

    public void reviewNews() {

        Intent resultIntent = new Intent(this, MainScreen.class);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        timerNews.schedule(new TimerTask() {
            @Override
            public void run() {
                Notifications.launchNotifications(MainScreen.this,"New places","Diskbar has new places!!!",resultPendingIntent);
            }
        }, 10000, 60000);

        /*
        timerNews.schedule(new TimerTask() {
            @Override
            public void run() {
                Notifications.launchNotifications(MainScreen.this,"Event","If you do not know what will you do this night, check our places!!!",resultPendingIntent);
            }
        }, 1000, 30000);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            refresh();
            return true;
        } else if (id == R.id.search) {
            Intent i = new Intent(this, SearchScreen.class );
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        new DownloadItems(this).execute();
    }
}
