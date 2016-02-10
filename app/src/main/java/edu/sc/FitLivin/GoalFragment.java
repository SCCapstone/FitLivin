package edu.sc.FitLivin;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseUser;

import edu.sc.FitLivin.R;


public class GoalFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_goal, container, false);
        final  MainActivity main = new MainActivity();

        final TextView BenchG = (TextView) v.findViewById(R.id.currentBenchGoal);
        final TextView SquatG = (TextView) v. findViewById(R.id.currentSquatGoal);
        final TextView DLG = (TextView) v. findViewById(R.id.currentDeadLiftGoal);
        final TextView WeightG = (TextView) v.findViewById(R.id.currentWeightGoal);
        final TextView MileTimeG = (TextView) v.findViewById(R.id.currentMileTimeGoal);
        final EditText changeBenchG = (EditText) v.findViewById(R.id.changeBenchGoal);
        final EditText changeSquatG = (EditText) v.findViewById(R.id.changeSquatGoal);
        final EditText changeDeadLiftG = (EditText) v.findViewById(R.id.changeDeadliftGoal);
        final EditText changeMileTimeG = (EditText) v.findViewById(R.id.changeMileTimeGoal);
        final EditText changeWeightG = (EditText) v.findViewById(R.id.changeWeightGoal);


        ParseQuery Weightquery = ParseQuery.getQuery("WeightGoal");
        ParseQuery Benchquery = ParseQuery.getQuery("BenchGoal");
        ParseQuery Squatquery = ParseQuery.getQuery("SquatGoal");
        ParseQuery MileTimequery = ParseQuery.getQuery("MileTimeGoal");
        ParseQuery DeadLiftquery = ParseQuery.getQuery("DeadLiftGoal");
        Button setWeightG = (Button) v.findViewById(R.id.setWeight);
        Button setBenchG = (Button) v.findViewById(R.id.setBench);
        Button setSquatG = (Button) v.findViewById(R.id.setSquat);
        Button setDeadLiftG = (Button) v.findViewById(R.id.setDeadLift);
        Button setMileTimeG = (Button) v.findViewById(R.id.setMileTime);

        setWeightG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer weight;
                String weightS = changeWeightG.getText().toString();
                weight = Integer.valueOf(weightS);

                WeightG.setText("" + weight);

                String s = ParseUser.getCurrentUser().getUsername();
                main.WeightGoal(weight,s);
            }
        });
        setBenchG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer bench;
                String benchS = changeBenchG.getText().toString();
                bench = Integer.valueOf(benchS);

                BenchG.setText("" + bench);

                String s = ParseUser.getCurrentUser().getUsername();
                main.BenchGoal(bench,s);
            }
        });

        setSquatG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer squat;
                String squatS = changeSquatG.getText().toString();
                squat = Integer.valueOf(squatS);

                SquatG.setText("" + squat);

                String s = ParseUser.getCurrentUser().getUsername();
                main.SquatGoal(squat,s);
            }
        });

        setDeadLiftG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deadLift;
                String deadLiftS = changeDeadLiftG.getText().toString();
                deadLift = Integer.valueOf(deadLiftS);

                DLG.setText("" + deadLift);

                String s = ParseUser.getCurrentUser().getUsername();
                main.DeadLiftGoal(deadLift,s);
            }
        });

        setMileTimeG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer mileTime;
                String mileTimeS = changeMileTimeG.getText().toString();
                mileTime = Integer.valueOf(mileTimeS);

                MileTimeG.setText("" + mileTime);

                String s = ParseUser.getCurrentUser().getUsername();
                main.MileTimeGoal(mileTime,s);
            }
        });


        return v;
    }



}
