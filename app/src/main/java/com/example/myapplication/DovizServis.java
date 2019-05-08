package com.example.myapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class DovizServis extends Service {

    Timer timer = new Timer();
    Handler handler;

    @Override
    public IBinder onBind(Intent ıntent) {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                istekGonder();
            }
        }, 0, 5000);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    public void istekGonder(){
      //  String urlApi = "https://api.themoviedb.org/3/movie/76341?api_key=47cbbbccb755ef6db434c085338e951d";
          String url = "https://api.exchangeratesapi.io/latest";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject rates = jsonObject.getJSONObject("rates");

                    Double tl = rates.getDouble("TRY");
                    Log.d("fsdfasdfa",tl.toString());

                    Intent yayin = new Intent();
                    yayin.setAction("döviz.action");
                    yayin.putExtra("eur",tl.toString());
                    sendBroadcast(yayin);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(istek);
    }
}
