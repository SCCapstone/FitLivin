package edu.sc.FitLivin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class GoalFragment extends Fragment {
    MediaPlayer mp;
    private static final int TEXT_ID = 0;

private AlertDialog.Builder dialogBuilder;
//
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

        ParseQuery queryuser = ParseUser.getQuery();
        ParseUser user = ParseUser.getCurrentUser();


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
        MaxBench.whereMatchesQuery("author", queryuser);

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

        queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser());
        MaxSquat.whereExists("MaxSquat");//setting constraints
        MaxSquat.whereMatchesQuery("author", queryuser);
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
        MaxDeadLift.whereMatchesQuery("author",queryuser);
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
        MaxMileTime.whereMatchesQuery("author", queryuser);
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
        Weightquery.whereMatchesQuery("author", queryuser);
        Weightquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0
                    Log.d("QAOD", "weight test success");
                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("goalWeight");
                        WeightG.setText("" + x);
                        Integer value = main.WeightGoalTest(x);
                        if (value == 1) {
                            Log.d("QAOD", "congratsWEIGHTLOSS");
                            //weightLossDialog();
                            //Toast.makeText(getActivity(), "Great Job!!!.", Toast.LENGTH_SHORT).show();

                        }
                        if (value == 2) {
                            // Toast.makeText(getActivity(), "Almost!!!.", Toast.LENGTH_SHORT).show();
                            Log.d("QAOD", "not there yetWEIGHTLOSS");

                        }


                    }
                }
            }

        });

        WeightGainquery.whereExists("goalWeightGain");//setting constraints
        WeightGainquery.whereMatchesQuery("author", queryuser);
        WeightGainquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0
                    Log.d("QAOD", "weight test success");
                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("goalWeightGain");
                        WeightGain.setText("" + x);
                        //main.bench = x;
                        Integer value = main.WeightGainGoalTest(x);
                        if (value == 1) {
                            Log.d("QAOD", "congratsWEIGHTGAIN");
                            // weightGainDialog();

                        }
                        if (value == 2) {
                            Log.d("QAOD", "not there yetWEIGHTGAIN");
                            // Toast.makeText(getActivity(), "Almost Bench!!!.", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }

        });
        Benchquery.whereExists("BenchGoal");//setting constraints
        Benchquery.whereMatchesQuery("author", queryuser);
        Benchquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("BenchGoal");
                        BenchG.setText("" + x);
                        Log.d("QAOD", "BENCHMAX" + main.bench);
                        Integer value = main.BenchGoalTest(x);
                        Log.d("QAOD", "BENCHMAXGOAL" + x);
                        if (value == 1) {
                            Log.d("QAOD", "congratsBENCH");
                            benchDialog();

                        }
                        if (value == 2) {
                            Log.d("QAOD", "not there yetBENCH");
                        }

                    }
                }
            }

        });

        Squatquery.whereExists("SquatGoal");//setting constraints
        Squatquery.whereMatchesQuery("author", queryuser);
        Squatquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("SquatGoal");
                        SquatG.setText("" + x);
                        Log.d("QAOD", "SQUATMAX" + main.squat);
                        Integer value = main.SquatGoalTest(x);
                        Log.d("QAOD", "SQUATMAXGOAL" + x);
                        if (value == 1) {
                            Log.d("QAOD", "congratsSQUAT");
                            //squatDialog();

                        }
                        if (value == 2) {
                            Log.d("QAOD", "not there yetSQUAT");
                        }


                    }
                }
            }

        });

        DeadLiftquery.whereExists("DeadLiftGoal");//setting constraints
        DeadLiftquery.whereMatchesQuery("author", queryuser);
        DeadLiftquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(objects.size() - 1).get("DeadLiftGoal");
                        DLG.setText("" + x);
                        Log.d("QAOD", "DEADLIFTMAX" + main.deadLift);
                        Integer value = main.DeadLiftGoalTest(x);
                        Log.d("QAOD", "DEADLIFTMAXGOAL" + x);
                        if (value == 1) {
                            Log.d("QAOD", "congratsDEADLIFT");
                            //deadLiftDialog();

                        }
                        if (value == 2) {
                            Log.d("QAOD", "not there yetDEADLIFT");
                        }


                    }
                }
            }

        });

        MileTimequery.whereExists("MileTimeGoal");//setting constraints
        MileTimequery.whereMatchesQuery("author",queryuser);
        MileTimequery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MileTimeGoal");
                        MileTimeG.setText("" + x);
                        Log.d("QAOD", "MILETIMEMAX" + main.mileTime);
                        Integer value = main.MileTimeGoalTest(x);
                        Log.d("QAOD", "MILETIMEMAXGOAL" + x);
                        if (value == 1) {
                            Log.d("QAOD", "congratsMILEITME");
                            //mileTimeDialog();

                        }
                        if (value == 2) {
                            Log.d("QAOD", "not there yetMILETIME");
                        }


                    }
                }
            }

        });

         setWeightG.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                 builder1.setTitle("Set Your Max");
                 builder1.setMessage("Enter Your Goal Wieght.");
                 final EditText input = new EditText(getActivity());
                 input.setId(TEXT_ID);
                 builder1.setView(input);
                 builder1.setCancelable(true);

                 builder1.setPositiveButton(
                         "SET",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 String value = input.getText().toString();
                                 Integer weight = Integer.valueOf(value);
                                 String s = ParseUser.getCurrentUser().getUsername();
                                 WeightG.setText("" + weight);
                                 main.WeightGoal(weight, s);
                                 dialog.cancel();
                             }
                         });

                 builder1.setNegativeButton(
                         "Cancel",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 dialog.cancel();
                             }
                         });

                 AlertDialog alert11 = builder1.create();
                 alert11.show();
             }
         });
        setWeightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Set Your Max");
                builder1.setMessage("Enter Your Goal Wieght.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String value = input.getText().toString();
                                Integer weight = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                WeightGain.setText("" + weight);
                                main.WeightGainGoal(weight, s);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        setBenchG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Set Your Max");
                builder1.setMessage("Enter Your Bench Goal.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String value = input.getText().toString();
                                Integer bench = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                BenchG.setText("" + bench);
                                main.BenchGoal(bench, s);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        setWeightG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Set Your Max");
                builder1.setMessage("Enter Your Goal Wieght.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String value = input.getText().toString();
                                Integer weight = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                WeightG.setText("" + weight);
                                main.WeightGoal(weight, s);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        setSquatG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Set Your Max");
                builder1.setMessage("Enter Your Squat Goal.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String value = input.getText().toString();
                                Integer squat = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                SquatG.setText("" + squat);
                                main.WeightGoal(squat, s);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        setDeadLiftG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Set Your Max");
                builder1.setMessage("Enter Your Dead Lift Goal.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String value = input.getText().toString();
                                Integer weight = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                DLG.setText("" + weight);
                                main.DeadLiftGoal(weight, s);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        setMileTimeG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Set Your Max");
                builder1.setMessage("Enter Your Mile Time Goal.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String value = input.getText().toString();
                                Integer time = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                MileTimeG.setText("" + time);
                                main.MileTimeGoal(time, s);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


        return v;
    }



}
