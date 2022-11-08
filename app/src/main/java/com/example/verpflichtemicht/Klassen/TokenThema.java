package com.example.verpflichtemicht.Klassen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.verpflichtemicht.LoginNeu;
import com.example.verpflichtemicht.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.Iterator;
import java.util.Map;


public class TokenThema {
    public String AnmeldeName;
    public String Passwort;
    public String Token;
    public String AlarmZeit;
    public int UserID;

    public void TokenAbholen(LoginNeu loginNeu, View view) {
        Log.d("logapi", "Anfrage kommt");

        String url = "http://192.168.178.32:80/verpflichtemich/tokencheck.php?aufgabe=TokenAbholen&anmeldename=" + this.AnmeldeName + "&PW=" + this.Passwort;
        Log.d("logapi", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("logapi", "ok");

                        Log.d("logapi", response.trim());
                        try {
                            tokenspeichern(response, loginNeu, view);
                        } catch (JSONException e) {

                            Log.d("logapi", e.toString());
                            loginNeu.Fehlermeldung(view);


                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("logapi", error.toString());
                        Log.d("logapi", "nee");
                        loginNeu.Fehlermeldung(view);

                    }

                    ;
                });

        RequestQueue requestQueue = Volley.newRequestQueue(loginNeu);
        requestQueue.add(stringRequest);
    }

    public void tokenspeichern(String response, LoginNeu loginNeu, View view) throws JSONException {
        //this.Token = response;
        Log.d("logapi", response);
        JSONArray jsonArray = new JSONArray(response.trim());
        Log.d("logapi", "JSON:");
        Log.d("logapi", jsonArray.toString());
        if (jsonArray.length() > 0) {
            for (int i = 0; i < 1; i++) {
                JSONObject objekt = jsonArray.getJSONObject(i);
                Log.d("logapi", "Objekt:");
                Log.d("logapi", objekt.toString());
                this.Token = objekt.getString("User_Token");
                this.UserID = objekt.getInt("User_ID");
                this.AlarmZeit = objekt.getString("userconfig_Alarm");


            }
            loginNeu.Callbackverifiziere(view);
        } else {
            loginNeu.Fehlermeldung(view);
        }

    }


    public void Tokenvalidierung(MainActivity mainActivity) {
        String url = "http://192.168.178.32:80/verpflichtemich/tokencheck.php?aufgabe=PruefeToken&Authentifizierungstoken=" + this.Token + "&UserID=" + this.UserID;
        Log.d("logapi", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("logapi", "ok");

                        Log.d("logapi", response.trim());
                        BearbeiteFeedback(response, mainActivity);


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("logapi", error.toString());
                        Log.d("logapi", "nee");
                        Fehler(mainActivity);

                    }

                    ;
                });

        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        requestQueue.add(stringRequest);
    }

    public void BearbeiteFeedback(String response, MainActivity mainActivity) {
        if (response.equals("Token Fehler")) {
            Fehler(mainActivity);

        } else {
            try {
                Log.d("logapi", "token okay");

                objekteAusResponse(response);
                SharedPreferences sharedPref = mainActivity.getSharedPreferences("AppData",0);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Authentifizierungstokens", this.Token);
                editor.putInt("User_ID", this.UserID);
                Log.d("logapi", this.AlarmZeit);

                editor.putString("userconfig_Alarm", this.AlarmZeit);


                editor.commit();
            } catch (JSONException e) {

                Log.d("logapi", e.toString());


            }
        }
        mainActivity.TokenStimmt();

    }


    public void Fehler(MainActivity mainActivity) {
        Intent i = new Intent(mainActivity, LoginNeu.class);
        mainActivity.startActivity(i);
    }

    public void objekteAusResponse(String response) throws JSONException {
        Log.d("logapi", "objekte aus response");

        Log.d("logapi", response);
        JSONArray jsonArray = new JSONArray(response.trim());
        Log.d("logapi", "JSON:");
        Log.d("logapi", jsonArray.toString());
        if (jsonArray.length() > 0) {
            for (int i = 0; i < 1; i++) {
                JSONObject objekt = jsonArray.getJSONObject(i);
                Log.d("logapi", "Objekt:");
                Log.d("logapi", objekt.toString());
                this.Token = objekt.getString("User_Token");
                this.UserID = objekt.getInt("User_ID");
                this.AlarmZeit = objekt.getString("userconfig_Alarm");


            }
        }
    }
}




