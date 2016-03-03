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

public class StopwatchFragment extends Fragment implements OnClickListener {


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
        getActivity().getActionBar()
                .setTitle("Stopwatch");
        startChron = (Button)v.findViewById(R.id.start);
               startChron.setOnClickListener(this);
        stopChron = (Button)v.findViewById(R.id.stop);
               stopChron.setOnClickListener(this);
        resetChron = (Button)v.findViewById(R.id.reset);
               resetChron.setOnClickListener(this);
        chron = (Chronometer) v.findViewById(R.id.chronometer);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == startChron){
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
