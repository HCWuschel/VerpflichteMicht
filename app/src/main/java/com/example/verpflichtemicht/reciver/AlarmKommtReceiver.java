package com.example.verpflichtemicht.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.verpflichtemicht.AlarmFenster;
import com.example.verpflichtemicht.MainActivity;

public class AlarmKommtReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("logapi", "Reciver hört");
        Log.d("logapi", context.toString());

       Intent AlarmFenster = new Intent(context.getApplicationContext(), com.example.verpflichtemicht.AlarmFenster.class);
        AlarmFenster.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
    context.startActivity(AlarmFenster);


} catch (Exception e) {
    Log.d("logapi", e.toString());
}
    }
}
