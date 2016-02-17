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
    private static final int TEXT_ID = 0;
    private static final int TEXT_ID1 = 0;
    private static final int TEXT_ID2 = 0;
    private static final int TEXT_ID3 = 0;
    private static final int TEXT_ID4 = 0;
    private static final int TEXT_ID5 = 0;

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
                          // weightLossDialog();
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
                           // weightGainDialog();

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

                        int x = (Integer) objects.get(objects.size()-1).get("BenchGoal");
                        BenchG.setText("" + x);
                        Log.d("QAOD", "BENCHMAX" + main.bench);
                        Integer value = main.BenchGoalTest(x);
                        Log.d("QAOD", "BENCHMAXGOAL" + x);
                        if(value == 1){
                            Log.d("QAOD", "congratsBENCH");
                           // benchDialog();

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

                        int x = (Integer) objects.get(objects.size()-1).get("SquatGoal");
                        SquatG.setText("" + x);
                        Log.d("QAOD", "SQUATMAX" + main.squat);
                       Integer value = main.SquatGoalTest(x);
                        Log.d("QAOD", "SQUATMAXGOAL" + x);
                        if(value == 1){
                            Log.d("QAOD", "congratsSQUAT");
                            //squatDialog();

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

                        int x = (Integer) objects.get(objects.size()-1).get("DeadLiftGoal");
                        DLG.setText("" + x);
                        Log.d("QAOD", "DEADLIFTMAX" + main.deadLift);
                       Integer value = main.DeadLiftGoalTest(x);
                        Log.d("QAOD", "DEADLIFTMAXGOAL" + x);
                        if(value == 1){
                            Log.d("QAOD", "congratsDEADLIFT");
                            //deadLiftDialog();

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
                            //mileTimeDialog();

                        }
                        if(value == 2){
                            Log.d("QAOD", "not there yetMILETIME");
                        }


                    }
                }
            }

        });
        final AlertDialog.Builder weightLoss = new AlertDialog.Builder(getActivity());
        weightLoss.setTitle("Change Weight Loss Goal");
        weightLoss.setMessage("Please Enter Your Goal:");

        // Use an EditText view to get user input.
        final EditText input = new EditText(getActivity());
        input.setId(TEXT_ID);
        weightLoss.setView(input);
        setWeightG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightLoss.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        Integer weightLoss = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        WeightG.setText("" + weightLoss);
                        main.WeightGoal(weightLoss, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = weightLoss.create();
                dialog.show();

            }
        });
        final AlertDialog.Builder weightGain= new AlertDialog.Builder(getActivity());
        weightGain.setTitle("Change Weight Gain Goal");
        weightGain.setMessage("Please Enter Your Goal:");

        // Use an EditText view to get user input.
        final EditText input1 = new EditText(getActivity());
        input1.setId(TEXT_ID1);
        weightGain.setView(input1);

        setWeightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGain.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input1.getText().toString();
                        Integer weightGain = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        WeightGain.setText("" + weightGain);
                        main.WeightGainGoal(weightGain, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = weightGain.create();
                dialog.show();
            }
        });
        final AlertDialog.Builder bench= new AlertDialog.Builder(getActivity());
        bench.setTitle("Change Bench Goal");
        bench.setMessage("Please Enter Your Goal:");

        // Use an EditText view to get user input.
        final EditText input2 = new EditText(getActivity());
        input2.setId(TEXT_ID2);
        bench.setView(input2);

        setBenchG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bench.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input2.getText().toString();
                        Integer benchg = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        BenchG.setText("" + benchg);
                        main.BenchGoal(benchg, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = bench.create();
                dialog.show();
            }
        });
        final AlertDialog.Builder squat = new AlertDialog.Builder(getActivity());
        squat.setTitle("Change Squat Goal");
        squat.setMessage("Please Enter Your Goal:");

        // Use an EditText view to get user input.
        final EditText input3 = new EditText(getActivity());
        input3.setId(TEXT_ID3);
        squat.setView(input3);
        setSquatG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                squat.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input3.getText().toString();
                        Integer squatg = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        SquatG.setText("" + squatg);
                        main.SquatGoal(squatg, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = squat.create();
                dialog.show();
            }
        });
        final AlertDialog.Builder deadlift = new AlertDialog.Builder(getActivity());
        deadlift.setTitle("Change Dead Lift Goal");
        deadlift.setMessage("Please Enter Your Goal:");

        // Use an EditText view to get user input.
        final EditText input4 = new EditText(getActivity());
        input4.setId(TEXT_ID4);
        deadlift.setView(input4);

        setDeadLiftG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deadlift.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input4.getText().toString();
                        Integer deadliftg = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        DLG.setText("" + deadliftg);
                        main.DeadLiftGoal(deadliftg, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = deadlift.create();
                dialog.show();
            }
        });
        final AlertDialog.Builder miletime = new AlertDialog.Builder(getActivity());
        miletime.setTitle("Change Mile Time Goal");
        miletime.setMessage("Please Enter Your Goal:");

        // Use an EditText view to get user input.
        final EditText input5 = new EditText(getActivity());
        input5.setId(TEXT_ID5);
        miletime.setView(input5);
        setMileTimeG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miletime.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input5.getText().toString();
                        Integer miletimeg = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        MileTimeG.setText("" + miletimeg);
                        main.MileTimeGoal(miletimeg, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = miletime.create();
                dialog.show();
            }
        });


        return v;
    }



}
