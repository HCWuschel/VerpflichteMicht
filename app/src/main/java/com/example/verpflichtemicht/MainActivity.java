package com.example.verpflichtemicht;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.verpflichtemicht.Klassen.Berechtigungen;
import com.example.verpflichtemicht.Klassen.TokenThema;
import com.example.verpflichtemicht.service.Tokenvalid;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.verpflichtemicht.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public  int Vorbereitung = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        //VorbereitungTestung();
       StartRoutine();
        //Testzugang();
        TestderBerechtigungen();
        //requestCamera();


    }

    private void TestderBerechtigungen() {
        Berechtigungen berechtigungen = new Berechtigungen(this);
       // berechtigungen.initalueberpruefung();

    }


    public  void  VorbereitungTestung(){
        if(Vorbereitung == 0){
            Toast.makeText(this,"Shared Prefs gelöscht!",
                    Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = this.getSharedPreferences("AppData",0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Authentifizierungstokens", "");
            editor.putInt("User_ID", 0);

            editor.commit();
            Vorbereitung +=1;
        }


    }
    public  void StartRoutine(){
        SharedPreferences sharedPref = this.getSharedPreferences("AppData",0);
        String Authentifizierungstoken = sharedPref.getString("Authentifizierungstokens",null);
        String Bootinfo = sharedPref.getString("Bootinfo","leereBootinfo");
        Toast.makeText(this,Bootinfo,
                Toast.LENGTH_SHORT).show();
        int UserID = sharedPref.getInt("User_ID",0);

        if(Authentifizierungstoken.equals("")){
            Toast.makeText(this,"Kein Token",
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, LoginNeu.class);
            startActivity(i);
           // this.finish();
        }else{
            TokenThema tokenThema = new TokenThema();
           tokenThema.UserID = UserID;
           tokenThema.Token = Authentifizierungstoken;
            tokenThema.Tokenvalidierung(this);




        }

    }
    public void TokenStimmt(){
            Toast.makeText(this,"Token stimmt",
                    Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPref = this.getSharedPreferences("AppData",0);
        String userconfig_Alarm = sharedPref.getString("userconfig_Alarm",null);
        Toast.makeText(this,userconfig_Alarm,
                Toast.LENGTH_SHORT).show();


    }
    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch(state) {
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(context,"Bluetoot stimmt",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(context,"Bluetoot stimmt",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(context,"Bluetoot stimmt",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(context,"Bluetoot stimmt",
                                Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        }
    };
}