package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static double tls = 0;
    TextView textView;
    BroadcastReceiver mMessageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.eur);
        Intent i= new Intent(this, DovizServis.class);
        this.startService(i);


        mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent ıntent) {
                textView.setText(ıntent.getExtras().getString("eur"));
            }
        };


        IntentFilter ıntentFilter = new IntentFilter();
        ıntentFilter.addAction("döviz.action");
        Intent ıntent = new Intent();
        registerReceiver(mMessageReceiver,ıntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mMessageReceiver);
    }
}

