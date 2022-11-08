package com.example.verpflichtemicht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.verpflichtemicht.Klassen.TokenThema;

public class LoginNeu extends AppCompatActivity {
    TokenThema tokenThema = new TokenThema();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_neu);

    }
    public  void  verifiziere(View view){
        EditText nameFeld = this.findViewById(R.id.TV_name_input);
        EditText pwFeld = this.findViewById(R.id.TV_PW_input);
        String name = nameFeld.getText().toString();
        String pw = pwFeld.getText().toString();
        tokenThema.AnmeldeName = name;
        tokenThema.Passwort = pw;

        tokenThema.TokenAbholen(this,view);


    }
    public  void Callbackverifiziere(View view){

        Log.d("logapi", "Token abholen fertig");
        Log.d("logapi", tokenThema.AnmeldeName);
        Log.d("logapi", tokenThema.Token);



        SharedPreferences sharedPref = this.getSharedPreferences("AppData",0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Authentifizierungstokens", tokenThema.Token);
        editor.putInt("User_ID", tokenThema.UserID);
        editor.putString("userconfig_Alarm", tokenThema.AlarmZeit);


        editor.commit();

        finish();


    }
    public void Fehlermeldung(View view){
        Toast.makeText(this,"Fehler in der Anmeldung!",
                Toast.LENGTH_SHORT).show();
    }
}