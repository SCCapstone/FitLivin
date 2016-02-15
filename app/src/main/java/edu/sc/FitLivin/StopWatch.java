package edu.sc.FitLivin;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Button;


import edu.sc.FitLivin.R;

public class StopWatch extends Activity implements View.OnClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public StopWatch() {
        // Required empty public constructor
    }


    Button startChron;
    Button stopChron;
    Button resetChron;
    private Chronometer watch;
    long timeStopped = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stopwatch);
        startChron = (Button)findViewById(R.id.start);
        stopChron = (Button)findViewById(R.id.stop);
        resetChron = (Button) findViewById(R.id.reset);
        watch = (Chronometer) findViewById(R.id.chronometer);
        startChron.setOnClickListener(this);
        stopChron.setOnClickListener(this);
        resetChron.setOnClickListener(this);



    }
    //reset
    public void resetButtonClick(View v) {
        watch.setBase(SystemClock.elapsedRealtime());
        timeStopped = 0;


    }
    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()){
            case R.id.start:
                watch.setBase(SystemClock.elapsedRealtime() + timeStopped);
                watch.start();
                 break;
            case R.id.stop:
                timeStopped = watch.getBase()+SystemClock.elapsedRealtime();
                watch.stop();
                break;
            case R.id.reset:
                watch.setBase(SystemClock.elapsedRealtime() + timeStopped);
                watch.start();
                break;



        }

    }
}
