package com.example.verpflichtemicht.reciver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.verpflichtemicht.Klassen.NotificationKlasse;
import com.example.verpflichtemicht.MainActivity;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPref = context.getSharedPreferences("AppData",0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Bootinfo", "Irgendwas von boot");
        editor.commit();
        //Toast.makeText(context, "Bootreciver hat funktioniert", Toast.LENGTH_LONG).show();
        Log.d("logapi", "Reciver kommt");

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        NotificationKlasse nots = new NotificationKlasse();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nots.makenotification(context,"Titel-Ich komme vom Bootreciver(irgendwas)", "Boot hat wirklich funktioniert",3);
        }
        try {
            if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
                Toast.makeText(context, "Bootreciver hat funktioniert (ACTION_BOOT_COMPLETED)", Toast.LENGTH_LONG).show();
                NotificationKlasse not = new NotificationKlasse();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    not.makenotification(context,"Titel-Ich komme vom Bootreciver(ACTION_BOOT_COMPLETED)", "Boot hat wirklich funktioniert",3);
                }
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }

        } catch (Exception ex) {

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }
}