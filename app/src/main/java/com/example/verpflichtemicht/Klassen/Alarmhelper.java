package com.example.verpflichtemicht.Klassen;
import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.example.verpflichtemicht.AlarmFenster;
import com.example.verpflichtemicht.MainActivity;
import com.example.verpflichtemicht.reciver.AlarmKommtReceiver;


public class Alarmhelper {

    public  void  AlarmSetzen(Context context){
        Log.d("logapi", "Alarmhelper");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmKommtReceiver.class);
        intent.setAction(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 234324243, intent, 0);
        alarmManager.setExactAndAllowWhileIdle (AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (1 * 1000), pendingIntent);
        Log.d("logapi", "Alarm wurde gesetzt");

    }
}
