package com.example.verpflichtemicht.Klassen;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.example.verpflichtemicht.MainActivity;
import com.example.verpflichtemicht.R;
import com.google.firebase.messaging.FirebaseMessagingService;

public class NotificationKlasse {
    @RequiresApi(api = Build.VERSION_CODES.O)

    public void makenotification(Context context, String title, String message, Integer ueberChannelID){



        final String CHANNEL_ID = "HEADS_UP_NOTIFICATION";


        //Context context = getApplicationContext();







        NotificationChannel channel =
                new NotificationChannel(
                        CHANNEL_ID,
                        "Heads Up Notification",
                        NotificationManager.IMPORTANCE_HIGH
                );
        context.getSystemService(NotificationManager.class).createNotificationChannel(channel);

        Notification.Builder notification =
                new Notification.Builder(context, CHANNEL_ID)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setAutoCancel(true);

        NotificationManagerCompat.from(context).notify(1, notification.build());
    }
}
