package com.example.verpflichtemicht.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.verpflichtemicht.LoginNeu;

import org.json.JSONException;

public class Tokenvalid extends Service {
    public Tokenvalid() {
        evauluiereToken();
    }
    public  void  evauluiereToken(){
        SharedPreferences sharedPref = this.getSharedPreferences("AppData",0);
        String Authentifizierungstoken = sharedPref.getString("Authentifizierungstokens",null);
        int UserID = sharedPref.getInt("UserID",0);

        String url = "http://192.168.178.32:80/verpflichtemich/tokencheck.php?aufgabe=PruefeToken&Authentifizierungstoken="+Authentifizierungstoken+"&UserID="+UserID;
        Log.d("logapi", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("logapi", "ok");

                        Log.d("logapi", response.trim());
                        BearbeiteFeedback( response);


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("logapi", error.toString());
                        Log.d("logapi", "nee");
                        Fehler();

                    }

                    ;
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public  void BearbeiteFeedback(String response){

    }
    public  void  Fehler(){
        Intent i = new Intent(this, LoginNeu.class);
        startActivity(i);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}