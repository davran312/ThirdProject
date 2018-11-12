package com.example.user.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running = true ;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if(savedInstanceState  != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        initButtons();
        runTimer();
    }

    private void initButtons() {
        Button start = findViewById(R.id.btn_start);
        Button pause = findViewById(R.id.btn_pause);
        Button reset = findViewById(R.id.btn_stop);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0 ;
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);
    }

    private void runTimer(){
        final TextView timeView = findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours =  seconds/3600;
                int minutes = (seconds%3600)/60;
                int sec = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",
                        hours,minutes,sec);
                timeView.setText(time);
                if(running){ seconds++;
                }
                if(sec == 30){
                    Toast.makeText(StopwatchActivity.this,"Time is over",Toast.LENGTH_SHORT).show();
                }
                handler.postDelayed(this,10);
            }
        });

    }


}
