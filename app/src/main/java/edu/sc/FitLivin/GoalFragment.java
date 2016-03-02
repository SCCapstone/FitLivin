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
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class GoalFragment extends Fragment {
   MainActivity main = new MainActivity();

    private static final int TEXT_ID = 0;

private AlertDialog.Builder dialogBuilder;
//
    private void weightLossDialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Weight Loss Goal: Complete!");
        main.mp = null;
        main.mp = MediaPlayer.create(getActivity(), R.raw.applause);
        main.mp.start();
       // mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // mp = null;
                dialog.dismiss();
                main.mp.stop();
            }


        });
        //mp = null;

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void weightGainDialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Weight Gain Goal: Complete!");
        main.mp=null;
        main.mp = MediaPlayer.create(getActivity(), R.raw.applause);
        main.mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                main.mp.stop();

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
        main.mp.stop();
        main.mp = MediaPlayer.create(getActivity(), R.raw.applause);
        main.mp.start();
       // mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                main.mp.stop();

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
        main.mp = MediaPlayer.create(getActivity(), R.raw.applause);
        main.mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                main.mp.stop();

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
        main.mp = MediaPlayer.create(getActivity(), R.raw.applause);
        main.mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                main.mp.stop();

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
       main.mp = MediaPlayer.create(getActivity(), R.raw.applause);
        main.mp.start();
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                main.mp.stop();

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
        getActivity().getActionBar()
                .setTitle("Goals");
        final  MainActivity main = new MainActivity();



        final TextView BenchG = (TextView) v.findViewById(R.id.currentBenchGoal);
        final TextView SquatG = (TextView) v. findViewById(R.id.currentSquatGoal);
        final TextView DLG = (TextView) v. findViewById(R.id.currentDeadLiftGoal);
        final TextView WeightG = (TextView) v.findViewById(R.id.currentWeightGoal);
        final TextView WeightGain = (TextView) v.findViewById(R.id.currentWeightGainGoal);
        final TextView MileTimeG = (TextView) v.findViewById(R.id.currentMileTimeGoal);


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

        ParseQuery queryuser = ParseUser.getQuery();
        queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());

        ParseQuery MaxBench = ParseQuery.getQuery("MaxBench");
        MaxBench.whereExists("MaxBench");//setting constraints
        MaxBench.whereMatchesQuery("author", queryuser);
        MaxBench.orderByDescending("createdAt");

        MaxBench.findInBackground(new FindCallback<ParseObject>() {
                                      public void done(List<ParseObject> objects, ParseException e) {

                                          if (e == null && objects.size() != 0) { //if objects size is not 0

                                              if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                                                  int x = (Integer) objects.get(0).get("MaxBench");

                                                  main.bench = x;


                                              }

                                          }

                                      }


                                  }
        );
        ParseQuery MaxSquat = ParseQuery.getQuery("MaxSquat");
        MaxSquat.whereExists("MaxSquat");//setting constraints
       MaxSquat.whereMatchesQuery("author", queryuser);
        MaxSquat.orderByDescending("createdAt");
        MaxSquat.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxSquat");
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
        MaxDeadLift.whereMatchesQuery("author", queryuser);
        MaxDeadLift.orderByDescending("createdAt");
        MaxDeadLift.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxDeadLift");
                        //currentW.setText(Integer.toString(x));
                        main.deadLift = x;


                    }
                }
            }

        });
        ParseQuery MaxMileTime = ParseQuery.getQuery("MaxMileTime");
        MaxMileTime.whereExists("MaxMileTime");//setting constraints
        MaxMileTime.whereMatchesQuery("author", queryuser);
        MaxMileTime.orderByDescending("createdAt");
        MaxMileTime.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxMileTime");
                        //currentW.setText(Integer.toString(x));
                        main.mileTime = x;


                    }
                }
            }

        });


        Weightquery.whereMatchesQuery("author", queryuser);
        Weightquery.orderByDescending("createdAt");
        Weightquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0
                    Log.d("QAOD", "weight test success");

                    int x = (Integer) objects.get(0).get("goalWeight");
                    if (x == 0) {
                        WeightG.setText("");
                    } else {
                        WeightG.setText("" + x);
                    }

                    //main.bench = x;
                    if (x > 0) {
                        Integer value = main.WeightGoalTest(x);
                        if (value == 1) {
                            Log.d("QAOD", "congratsWEIGHTLOSS");
                            Integer points = MainActivity.points;
                            points = points + 100;
                            MainActivity.points = points;
                            String s = ParseUser.getCurrentUser().getUsername();
                            main.pointsData(points, s);
                            weightLossDialog();
                            main.WeightGoal(0, s);
                            WeightG.setText("");

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
        WeightGainquery.orderByDescending("createdAt");
        WeightGainquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0
                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("goalWeightGain");
                        if (x == 0) {
                            WeightGain.setText("");
                        } else {
                            WeightGain.setText("" + x);
                        }

                        //main.bench = x;
                        if (x > 0) {
                            Integer value = main.WeightGainGoalTest(x);
                            if (value == 1) {
                                Log.d("QAOD", "congratsWEIGHTGAIN");
                                Integer points = MainActivity.points;
                                points = points + 100;
                                MainActivity.points = points;
                                String s = ParseUser.getCurrentUser().getUsername();
                                main.pointsData(points, s);
                                weightGainDialog();
                                main.WeightGainGoal(0, s);
                                WeightGain.setText("");


                            }
                            if (value == 2) {
                                Log.d("QAOD", "not there yetWEIGHTGAIN");
                                // Toast.makeText(getActivity(), "Almost Bench!!!.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }

        });
        Benchquery.whereExists("BenchGoal");//setting constraints
        Benchquery.whereMatchesQuery("author", queryuser);
        Benchquery.orderByDescending("createdAt");
        Benchquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("BenchGoal");
                        if (x == 0) {
                            BenchG.setText("");
                        } else {
                            BenchG.setText("" + x);
                        }
                        if (x > 0) {
                            Log.d("QAOD", "BENCHMAX" + main.bench);
                            Integer value = main.BenchGoalTest(x);
                            Log.d("QAOD", "BENCHMAXGOAL" + x);
                            if (value == 1) {
                                Log.d("QAOD", "congratsBENCH");
                                Integer points = MainActivity.points;
                                points = points + 100;
                                MainActivity.points = points;
                                String s = ParseUser.getCurrentUser().getUsername();
                                main.pointsData(points, s);
                                benchDialog();
                                main.BenchGoal(0, s);
                                BenchG.setText("");

                            }
                            if (value == 2) {
                                Log.d("QAOD", "not there yetBENCH");
                            }

                        }
                    }
                }
            }

        });

        Squatquery.whereExists("SquatGoal");//setting constraints
        Squatquery.whereMatchesQuery("author", queryuser);
        Squatquery.orderByDescending("createdAt");
        Squatquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("SquatGoal");
                        if (x == 0) {
                            SquatG.setText("");
                        } else {
                            SquatG.setText("" + x);
                        }
                        if (x > 0) {
                            Log.d("QAOD", "SQUATMAX" + main.squat);
                            Integer value = main.SquatGoalTest(x);
                            Log.d("QAOD", "SQUATMAXGOAL" + x);
                            if (value == 1) {
                                Log.d("QAOD", "congratsSQUAT");
                                Integer points = MainActivity.points;
                                points = points + 100;
                                MainActivity.points = points;
                                String s = ParseUser.getCurrentUser().getUsername();
                                main.pointsData(points, s);
                                squatDialog();
                                main.SquatGoal(0, s);
                                SquatG.setText("");

                            }
                            if (value == 2) {
                                Log.d("QAOD", "not there yetSQUAT");
                            }


                        }
                    }
                }
            }

        });

        DeadLiftquery.whereExists("DeadLiftGoal");//setting constraints
        DeadLiftquery.whereMatchesQuery("author", queryuser);
        DeadLiftquery.orderByDescending("createdAt");
        DeadLiftquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("DeadLiftGoal");
                        if (x == 0) {
                            DLG.setText("");
                        } else {
                            DLG.setText("" + x);
                        }
                        if (x > 0) {
                            Log.d("QAOD", "DEADLIFTMAX" + main.deadLift);
                            Integer value = main.DeadLiftGoalTest(x);
                            Log.d("QAOD", "DEADLIFTMAXGOAL" + x);

                            if (value == 1) {
                                Log.d("QAOD", "congratsDEADLIFT");
                                Integer points = MainActivity.points;
                                points = points + 100;
                                MainActivity.points = points;
                                String s = ParseUser.getCurrentUser().getUsername();
                                main.pointsData(points, s);
                                deadLiftDialog();
                                main.DeadLiftGoal(0, s);
                                DLG.setText("");


                            }
                            if (value == 2) {
                                Log.d("QAOD", "not there yetDEADLIFT");
                            }


                        }
                    }
                }
            }

        });

        MileTimequery.whereExists("MileTimeGoal");//setting constraints
        MileTimequery.whereMatchesQuery("author", queryuser);
        MileTimequery.orderByDescending("createdAt");
        MileTimequery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MileTimeGoal");
                        if (x == 0) {
                            MileTimeG.setText("");
                        } else {
                            MileTimeG.setText("" + x);
                        }
                        if (x > 0) {
                            Log.d("QAOD", "MILETIMEMAX" + main.mileTime);
                            Integer value = main.MileTimeGoalTest(x);
                            Log.d("QAOD", "MILETIMEMAXGOAL" + x);
                            if (value == 1) {
                                Log.d("QAOD", "congratsMILEITME");
                                Integer points = MainActivity.points;
                                points = points + 100;
                                MainActivity.points = points;
                                String s = ParseUser.getCurrentUser().getUsername();
                                main.pointsData(points, s);
                                mileTimeDialog();
                                main.MileTimeGoal(0, s);
                                MileTimeG.setText("");

                            }
                            if (value == 2) {
                                Log.d("QAOD", "not there yetMILETIME");
                            }


                        }
                    }
                }
            }

        });

        setWeightG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Goal Weight.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Integer weight;
                                MainActivity m = new MainActivity();
                                m.ExcTest = 1;
                                String value = input.getText().toString();
                                try {
                                    weight = Integer.parseInt(value);
                                    Log.d("Q", "Is a number " + weight + " dd ");
                                } catch (NumberFormatException e) {
                                    Log.d("Q", "Is not a number ");
                                    m.ExcTest = 2;
                                    Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                            .show();

                                }
                                if (m.ExcTest == 1) {
                                    Integer weight2 = Integer.valueOf(value);
                                    String s = ParseUser.getCurrentUser().getUsername();
                                    if (weight2 == 0) {
                                        WeightG.setText("");
                                    } else {


                                        WeightG.setText("" + weight2);
                                    }
                                    main.WeightGoal(weight2, s);
                                    dialog.cancel();
                                }
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
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Goal Weight.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);//

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Integer weight;
                                String value = input.getText().toString();
                                MainActivity m = new MainActivity();
                                m.ExcTest = 1;
                                try {
                                    weight = Integer.valueOf(value);
                                } catch (NumberFormatException e) {
                                    m.ExcTest = 2;
                                    Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                            .show();


                                }

                                if (m.ExcTest == 1) {
                                    Integer weight2 = Integer.valueOf(value);
                                    String s = ParseUser.getCurrentUser().getUsername();


                                    if (weight2 == 0) {
                                        WeightGain.setText("");
                                    } else {


                                        WeightGain.setText("" + weight2);
                                    }
                                    main.WeightGainGoal(weight2, s);
                                    dialog.cancel();
                                }
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
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Bench Goal.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Integer bench;
                                String value = input.getText().toString();
                                MainActivity m = new MainActivity();
                                m.ExcTest = 1;

                                try {
                                    bench = Integer.valueOf(value);

                                } catch (NumberFormatException e) {
                                    m.ExcTest = 2;
                                    Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                            .show();
                                }
                                if (m.ExcTest == 1) {
                                    Integer bench2 = Integer.valueOf(value);
                                    String s = ParseUser.getCurrentUser().getUsername();


                                    if (bench2 == 0) {
                                        BenchG.setText("");
                                    } else {
                                        BenchG.setText("" + bench2);
                                    }
                                    main.BenchGoal(bench2, s);
                                    dialog.cancel();
                                }
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
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Squat Goal.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Integer squat;
                                String value = input.getText().toString();
                                MainActivity m = new MainActivity();
                                m.ExcTest = 1;
                                try {
                                    squat = Integer.valueOf(value);

                                } catch (NumberFormatException e) {
                                    m.ExcTest = 2;
                                    Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                            .show();
                                }
                                if (m.ExcTest == 1) {
                                Integer squat2 = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                if (squat2 == 0) {
                                    SquatG.setText("");
                                } else {


                                    SquatG.setText("" + squat2);
                                }
                                main.SquatGoal(squat2, s);
                                dialog.cancel();
                            }}
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
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Dead Lift Goal.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Integer dl;
                                String value = input.getText().toString();
                                MainActivity m = new MainActivity();
                                m.ExcTest = 1;
                                try {
                                    dl = Integer.valueOf(value);

                                } catch (NumberFormatException e) {
                                    m.ExcTest = 2;
                                    Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                            .show();
                                }
                                if (m.ExcTest == 1) {
                                Integer weight = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                if (weight == 0) {
                                    DLG.setText("");
                                } else {


                                    DLG.setText("" + weight);
                                }
                                main.DeadLiftGoal(weight, s);
                                dialog.cancel();
                            }}
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
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Mile Time Goal.");
                final EditText input = new EditText(getActivity());
                input.setId(TEXT_ID);
                builder1.setView(input);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Integer mile;
                                String value = input.getText().toString();
                                MainActivity m = new MainActivity();
                                m.ExcTest = 1;
                                try {
                                    mile = Integer.valueOf(value);

                                } catch (NumberFormatException e) {
                                    m.ExcTest = 2;
                                    Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                            .show();
                                }
                                if (m.ExcTest == 1) {
                                Integer time = Integer.valueOf(value);
                                String s = ParseUser.getCurrentUser().getUsername();
                                if (time == 0) {
                                    MileTimeG.setText("");
                                } else {


                                    MileTimeG.setText("" + time);
                                }
                                main.MileTimeGoal(time, s);
                                dialog.cancel();
                            }}
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
