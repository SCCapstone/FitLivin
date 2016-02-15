package edu.sc.FitLivin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import edu.sc.FitLivin.R;


public class GoalFragment extends Fragment {
    MediaPlayer mp;

private AlertDialog.Builder dialogBuilder;

    private void weightLossDialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Weight Loss Goal: Complete!");
        mp = MediaPlayer.create(getActivity(), R.raw.applause);
        mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mp.stop();
                mp = null;
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void weightGainDialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Weight Gain Goal: Complete!");
        mp = MediaPlayer.create(getActivity(), R.raw.applause);
        mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mp.stop();
                mp = null;
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void benchDialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Bench Press Goal: Complete!");
        mp = MediaPlayer.create(getActivity(), R.raw.applause);
        mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mp.stop();
                mp = null;
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void squatDialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Squat Goal: Complete!");
        mp = MediaPlayer.create(getActivity(), R.raw.applause);
        mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mp.stop();
                mp = null;
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void deadLiftDialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Dead Lift Goal: Complete!");
        mp = MediaPlayer.create(getActivity(), R.raw.applause);
        mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mp.stop();
                mp = null;
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void mileTimeDialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Mile Time Goal: Complete!");
        mp = MediaPlayer.create(getActivity(), R.raw.applause);
        mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mp.stop();
                mp = null;
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
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
        final TextView WeightGain = (TextView) v.findViewById(R.id.currentWeightGainGoal);
        final TextView MileTimeG = (TextView) v.findViewById(R.id.currentMileTimeGoal);
        final TextView currentW = (TextView) v.findViewById(R.id.currentWeight);
        final EditText changeBenchG = (EditText) v.findViewById(R.id.changeBenchGoal);
        final EditText changeSquatG = (EditText) v.findViewById(R.id.changeSquatGoal);
        final EditText changeDeadLiftG = (EditText) v.findViewById(R.id.changeDeadliftGoal);
        final EditText changeMileTimeG = (EditText) v.findViewById(R.id.changeMileTimeGoal);
        final EditText changeWeightG = (EditText) v.findViewById(R.id.changeWeightGoal);
        final EditText changeWeightGain = (EditText) v.findViewById(R.id.changeWeightGainGoal);


        ParseQuery Weightquery = ParseQuery.getQuery("WeightGoal");
        ParseQuery CurrentWeightquery = ParseQuery.getQuery("ProfileInfo");
        ParseQuery WeightGainquery = ParseQuery.getQuery("goalWeightGain");
        ParseQuery Benchquery = ParseQuery.getQuery("BenchGoal");
        ParseQuery Squatquery = ParseQuery.getQuery("SquatGoal");
        ParseQuery MileTimequery = ParseQuery.getQuery("MileTimeGoal");
        ParseQuery DeadLiftquery = ParseQuery.getQuery("DeadLiftGoal");
        Button setWeightG = (Button) v.findViewById(R.id.setWeight);
        Button setWeightGain = (Button) v.findViewById(R.id.setWeightG);
        Button setBenchG = (Button) v.findViewById(R.id.setBench);
        final Button setSquatG = (Button) v.findViewById(R.id.setSquat);
        Button setDeadLiftG = (Button) v.findViewById(R.id.setDeadLift);
        Button setMileTimeG = (Button) v.findViewById(R.id.setMileTime);


        ParseQuery MaxBench = ParseQuery.getQuery("MaxBench");
        MaxBench.whereExists("MaxBench");//setting constraints
        MaxBench.whereContains("username", ParseUser.getCurrentUser().getUsername());

        MaxBench.findInBackground(new FindCallback<ParseObject>() {
                                      public void done(List<ParseObject> objects, ParseException e) {

                                          if (e == null && objects.size() != 0) { //if objects size is not 0

                                              if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                                                  int x = (Integer) objects.get(objects.size() - 1).get("MaxBench");

                                                  main.bench = x;



                                              }

                                          }

                                      }


                                  }
        );
        ParseQuery MaxSquat = ParseQuery.getQuery("MaxSquat");
        MaxSquat.whereExists("MaxSquat");//setting constraints
        MaxSquat.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MaxSquat.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("MaxSquat");
                        //currentW.setText(Integer.toString(x));
                        //setSquat(x);
                        main.squat = x;
                       // Log.d("Q", "ddCurrentSquat " + main.squat + " dd ");


                    }
                }
            }

        });
        ParseQuery MaxDeadLift = ParseQuery.getQuery("MaxDeadLift");
        MaxDeadLift.whereExists("MaxDeadLift");//setting constraints
        MaxDeadLift.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MaxDeadLift.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("MaxDeadLift");
                        //currentW.setText(Integer.toString(x));
                        main.deadLift = x;



                    }
                }
            }

        });
        ParseQuery MaxMileTime = ParseQuery.getQuery("MaxMileTime");
        MaxMileTime.whereExists("MaxMileTime");//setting constraints
        MaxMileTime.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MaxMileTime.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("MaxMileTime");
                        //currentW.setText(Integer.toString(x));
                        main.mileTime = x;


                    }
                }
            }

        });

       /* CurrentWeightquery.whereExists("Weight");//setting constraints
        CurrentWeightquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        CurrentWeightquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("Weight");
                        //currentW.setText(Integer.toString(x));

                    }
                }
            }

        });*/

        Weightquery.whereExists("goalWeight");//setting constraints
        Weightquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        Weightquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0
                    Log.d("QAOD", "weight test success");
                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size()-1).get("goalWeight");
                        WeightG.setText("" + x);
                       Integer value = main.WeightGoalTest(x);
                       if(value == 1){
                           Log.d("QAOD", "congratsWEIGHTLOSS");
                           weightLossDialog();
                           //Toast.makeText(getActivity(), "Great Job!!!.", Toast.LENGTH_SHORT).show();

                       }
                        if(value == 2){
                           // Toast.makeText(getActivity(), "Almost!!!.", Toast.LENGTH_SHORT).show();
                           Log.d("QAOD", "not there yetWEIGHTLOSS");

                        }




                    }
                }
            }

        });

        WeightGainquery.whereExists("goalWeightGain");//setting constraints
        WeightGainquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        WeightGainquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0
                    Log.d("QAOD", "weight test success");
                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size()-1).get("goalWeightGain");
                        WeightGain.setText("" + x);
                        //main.bench = x;
                        Integer value = main.WeightGainGoalTest(x);
                        if(value == 1){
                            Log.d("QAOD", "congratsWEIGHTGAIN");
                            weightGainDialog();

                        }
                        if(value == 2){
                            Log.d("QAOD", "not there yetWEIGHTGAIN");
                           // Toast.makeText(getActivity(), "Almost Bench!!!.", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }

        });
        Benchquery.whereExists("BenchGoal");//setting constraints
        Benchquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        Benchquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("BenchGoal");
                        BenchG.setText("" + x);
                        Log.d("QAOD", "BENCHMAX" + main.bench);
                        Integer value = main.BenchGoalTest(x);
                        Log.d("QAOD", "BENCHMAXGOAL" + x);
                        if(value == 1){
                            Log.d("QAOD", "congratsBENCH");
                            benchDialog();

                        }
                        if(value == 2){
                            Log.d("QAOD", "not there yetBENCH");
                        }

                    }
                }
            }

        });

        Squatquery.whereExists("SquatGoal");//setting constraints
        Squatquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        Squatquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("SquatGoal");
                        SquatG.setText("" + x);
                        Log.d("QAOD", "SQUATMAX" + main.squat);
                       Integer value = main.SquatGoalTest(x);
                        Log.d("QAOD", "SQUATMAXGOAL" + x);
                        if(value == 1){
                            Log.d("QAOD", "congratsSQUAT");
                            squatDialog();

                        }
                        if(value == 2){
                            Log.d("QAOD", "not there yetSQUAT");
                        }


                    }
                }
            }

        });

        DeadLiftquery.whereExists("DeadLiftGoal");//setting constraints
        DeadLiftquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        DeadLiftquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("DeadLiftGoal");
                        DLG.setText("" + x);
                        Log.d("QAOD", "DEADLIFTMAX" + main.deadLift);
                       Integer value = main.DeadLiftGoalTest(x);
                        Log.d("QAOD", "DEADLIFTMAXGOAL" + x);
                        if(value == 1){
                            Log.d("QAOD", "congratsDEADLIFT");
                            deadLiftDialog();

                        }
                        if(value == 2){
                            Log.d("QAOD", "not there yetDEADLIFT");
                        }


                    }
                }
            }

        });

        MileTimequery.whereExists("MileTimeGoal");//setting constraints
        MileTimequery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MileTimequery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MileTimeGoal");
                        MileTimeG.setText("" + x);
                        Log.d("QAOD", "MILETIMEMAX" + main.mileTime);
                       Integer value = main.MileTimeGoalTest(x);
                        Log.d("QAOD", "MILETIMEMAXGOAL" + x);
                        if(value == 1){
                            Log.d("QAOD", "congratsMILEITME");
                            mileTimeDialog();

                        }
                        if(value == 2){
                            Log.d("QAOD", "not there yetMILETIME");
                        }


                    }
                }
            }

        });

        setWeightG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer weight;
                String weightS = changeWeightG.getText().toString();
                weight = Integer.valueOf(weightS);

                WeightG.setText("" + weight);

                String s = ParseUser.getCurrentUser().getUsername();

                main.WeightGoal(weight, s);

            }
        });

        setWeightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer weight;
                String weightS = changeWeightGain.getText().toString();
                weight = Integer.valueOf(weightS);

                WeightGain.setText("" + weight);

                String s = ParseUser.getCurrentUser().getUsername();
                main.WeightGainGoal(weight, s);
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
                main.MileTimeGoal(mileTime, s);
            }
        });


        return v;
    }



}
