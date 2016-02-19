package edu.sc.FitLivin;

import android.app.Activity;


import android.os.Bundle;
import android.os.SystemClock;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Button;

public class stopwatch_activity extends Activity implements OnClickListener
{


    public stopwatch_activity() {
        // Required empty public constructor
    }


    Button startChron;
    Button stopChron;
    Button resetChron;
    private Chronometer chron;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch_activity);
        buttons();
     }
    //set variables to buttons
    public void buttons(){

    startChron = (Button)findViewById(R.id.start);
        startChron.setOnClickListener(this);
    stopChron = (Button)findViewById(R.id.stop);
        stopChron.setOnClickListener(this);
    resetChron = (Button) findViewById(R.id.reset);
        resetChron.setOnClickListener(this);
    chron = (Chronometer) findViewById(R.id.chronometer);

}
    //if else if statement to handle starting and stopping the stopwatch
    public void onClick(View v){
        if(v == startChron){
            chron.start();
        }
        else if (v == stopChron){
            chron.stop();
        }
        else if (v == resetChron){
            chron.setBase(SystemClock.elapsedRealtime());
        }

    }




}
