package com.example.verpflichtemicht.Klassen;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Berechtigungen extends ActivityCompat{
    public Context context;

    public Berechtigungen(Context contexti) {
        context = contexti;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initalueberpruefung() {


        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_BOOT_COMPLETED) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Keine Boot berechtigung",
                    Toast.LENGTH_SHORT).show();

            }


         else {
            Toast.makeText(context, " Boot berechtigung",
                    Toast.LENGTH_SHORT).show();

        }
    }


}
