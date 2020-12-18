package com.example.activitylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning=false;
    private int seconds=0;
    TextView textTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTimer=findViewById(R.id.textTimer);
        if (savedInstanceState!=null) {
            seconds=savedInstanceState.getInt("seconds");
            isRunning=savedInstanceState.getBoolean("isRunning");
            Log.d("hi","saveDataOnCreate");
        }
        runTimer();
        Log.d("hi","hello");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("isRunning",isRunning);
        Log.d("hi","saveData");
    }

    public void onClickStartTimer(View view) {
        isRunning=true;
    }

    public void onClickPauseTimer(View view) {
        isRunning=false;
    }

    public void onClickResetTimer(View view) {
        isRunning=false;
        seconds=0;
    }

    private void runTimer(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%60)/60;
                int secs=seconds%60;

                String str=String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                textTimer.setText(str);

                if(isRunning){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }
}