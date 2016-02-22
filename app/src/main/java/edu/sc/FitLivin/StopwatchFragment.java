package edu.sc.FitLivin;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

public class StopwatchFragment extends Fragment
{


    public StopwatchFragment() {
        // Required empty public constructor
    }


    Button startChron;
    Button stopChron;
    Button resetChron;
    private Chronometer chron;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stopwatch_main, container, false);
        chron = (Chronometer) v.findViewById(R.id.chronometer);
        startChron = (Button)v.findViewById(R.id.start);
        startChron.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                chron.start();
            }
        });
        stopChron = (Button) v.findViewById(R.id.stop);
        stopChron.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                chron.stop();
            }
        });
        resetChron = (Button) v.findViewById(R.id.reset);
        resetChron.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                chron.setBase(SystemClock.elapsedRealtime());
            }
        });






        return v;
    }
    //set variables to buttons

    //if else if statement to handle starting and stopping the stopwatch





}
