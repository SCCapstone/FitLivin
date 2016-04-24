/*********
 * Class StopwatchFragment.java
 *
 * Provides the user with a Stopwatch so that they can monitor their time intervals
 *
 *
 */


package edu.sc.FitLivin;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

public class StopwatchFragment extends Fragment implements OnClickListener {

    //Empty constructor
    public StopwatchFragment() {
        // Required empty public constructor
    }

    //Assigned the buttons and the chronometer
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
        //Creates title for the action bar
        getActivity().getActionBar()
                .setTitle("Stopwatch");
        //initialize buttons and sets the onClickListener
        startChron = (Button)v.findViewById(R.id.start);
               startChron.setOnClickListener(this);
        stopChron = (Button)v.findViewById(R.id.stop);
               stopChron.setOnClickListener(this);
        resetChron = (Button)v.findViewById(R.id.reset);
               resetChron.setOnClickListener(this);
        chron = (Chronometer) v.findViewById(R.id.chronometer);
        return v;


    }
    //method which deals with button clicks. Start, stop, and reset are given functionality here
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
