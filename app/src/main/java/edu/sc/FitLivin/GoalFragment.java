/******
 * Class 'GoalFragment'
 *
 * Provides the user with the chance to add to their goals
 * Bench,Squat,Dead lift, Mile Run, Weight Loss, and Weight Gain.
 *
 */
        package edu.sc.FitLivin;

        import android.app.AlertDialog;
        import android.app.Fragment;
        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.content.DialogInterface;
        import android.graphics.Color;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.InputType;
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
    MainActivity main = new MainActivity();//main activity object

    private static final int TEXT_ID = 0;

    private AlertDialog.Builder dialogBuilder;

    /* * Method 'weightLossDialog'
     *
     *  Dialog builder for weight loss. notifies the user when a goal was completed.
     // Dispalys message and applause
     *
     */
    private void weightLossDialog(){
        dialogBuilder= new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Weight Loss Goal: Complete!");
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

    /* * Method 'weightGainDialog'
     *
     *  Dialog builder for weight gain. notifies the user when a goal was completed.
     // Dispalys message and applause
     *
     */
    private void weightGainDialog(){
        dialogBuilder= new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Weight Gain Goal: Complete!");
        main.mp = MediaPlayer.create(getActivity(), R.raw.applause);
        main.mp.start();// starts media player
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                main.mp.stop();//stops media player

                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    /* * Method 'benchDialog'
     *
     *  Dialog builder for bench press. notifies the user when a goal was completed.
     // Dispalys message and applause
     *
     */
    private void benchDialog(){
        dialogBuilder= new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Bench Press Goal: Complete!");
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

    /* * Method 'squatDialog'
     *
     *  Dialog builder for squat. notifies the user when a goal was completed.
     // Dispalys message and applause
     *
     */
    private void squatDialog(){
        dialogBuilder= new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
        dialogBuilder.setTitle("Great Job!!!!");
        dialogBuilder.setMessage("Squat Goal: Complete!");
        main.mp = MediaPlayer.create(getActivity(), R.raw.applause);
        main.mp.start();//starts media player
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                main.mp.stop();//stops media player

                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    /* * Method 'deadLiftDialog'
     *
     *  Dialog builder for deadlift. notifies the user when a goal was completed.
     // Dispalys message and applause
     *
     */
    private void deadLiftDialog(){
        dialogBuilder= new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
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

    /* * Method 'mileTimeDialog'
     *
     *  Dialog builder for miletime. notifies the user when a goal was completed.
     // Dispalys message and applause
     *
     */
    private void mileTimeDialog(){
        dialogBuilder= new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
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

        //intializing variables
        final  MainActivity main = new MainActivity();
        final TextView BenchG = (TextView) v.findViewById(R.id.currentBenchGoal);
        final TextView SquatG = (TextView) v. findViewById(R.id.currentSquatGoal);
        final TextView DLG = (TextView) v. findViewById(R.id.DeadLiftGoal);
        final TextView WeightG = (TextView) v.findViewById(R.id.currentWeightGoal);
        final TextView WeightGain = (TextView) v.findViewById(R.id.currentWeightGainGoal);
        final TextView MileTimeG = (TextView) v.findViewById(R.id.currentMileTimeGoal);
        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery Weightquery = ParseQuery.getQuery("WeightGoal");
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

        // querys parse database for points data on user
        ParseQuery queryuser = ParseUser.getQuery();
        queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
        ParseQuery Points = ParseQuery.getQuery("Points");
        Points.whereExists("CurrentPoints");//setting constraints
        Points.whereMatchesQuery("author", queryuser);
        Points.whereContains("username", ParseUser.getCurrentUser().getUsername());
        Points.orderByDescending("createdAt");// grabs the most recent data

        Points.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) { //if objects size is not 0

                                            if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                                                Double x = (Double) objects.get(0).get("CurrentPoints");
                                                main.points = x;


                                            }

                                        }

                                    }


                                }
        );//

        // querys parse database for weight data on user
        ParseQuery CurrentWeightquery = ParseQuery.getQuery("ProfileInfo");
        CurrentWeightquery.whereExists("Weight");//setting constraints
        CurrentWeightquery.orderByDescending("createdAt");
        CurrentWeightquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        // getst he most recent weight from the user
        CurrentWeightquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("Weight");
                        //currentW.setText(Integer.toString(x));
                        main.weightPro = x;

                    }

                }
            }

        });

        // querys parse database for bench data on user

        ParseQuery MaxBench = ParseQuery.getQuery("MaxBench");
        MaxBench.whereExists("MaxBench");//setting constraints
        MaxBench.whereMatchesQuery("author", queryuser);
        MaxBench.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MaxBench.orderByDescending("createdAt");// gets most recent data

        MaxBench.findInBackground(new FindCallback<ParseObject>() {
                                      public void done(List<ParseObject> objects, ParseException e) {
                                           //if object is not empty
                                          if (e == null && objects.size() != 0) { //if objects size is not 0
                                                    //gets the top user
                                              if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                                                  int x = (Integer) objects.get(0).get("MaxBench");
                                                  System.out.println("bench x = " + x);

                                                  main.bench = x;


                                              }

                                          }

                                      }


                                  }
        );

        // querys parse database for squat data on user
        ParseQuery MaxSquat = ParseQuery.getQuery("MaxSquat");
        MaxSquat.whereExists("MaxSquat");//setting constraints
        MaxSquat.whereMatchesQuery("author", queryuser);
        MaxSquat.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MaxSquat.orderByDescending("createdAt");//gets most recent data
        MaxSquat.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxSquat");//sets the top data set

                        main.squat = x;
                    }
                }
            }

        });
        // querys parse database for deadlift data on user
        ParseQuery MaxDeadLift = ParseQuery.getQuery("MaxDeadLift");// looks for maxdeadlift in database
        MaxDeadLift.whereExists("MaxDeadLift");//setting constraints
        MaxDeadLift.whereMatchesQuery("author", queryuser);
        MaxDeadLift.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MaxDeadLift.orderByDescending("createdAt");// gets most recent data first
        MaxDeadLift.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxDeadLift");

                        main.deadLift = x;


                    }
                }
            }

        });

        // querys parse database for miletime data on user
        ParseQuery MaxMileTime = ParseQuery.getQuery("MaxMileTime");
        MaxMileTime.whereExists("MaxMileTime");//setting constraints
        MaxMileTime.whereMatchesQuery("author", queryuser);
        MaxMileTime.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MaxMileTime.orderByDescending("createdAt");// gets data by date
        MaxMileTime.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
        // checks if object is null
                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {//gets top most data

                        int x = (Integer) objects.get(0).get("MaxMileTime");
                        main.mileTime = x;


                    }
                }
            }

        });


// querys parse database for goalweight data on user
        Weightquery.whereExists("goalWeight");//setting constraints
        Weightquery.whereMatchesQuery("author", queryuser);
        Weightquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        Weightquery.orderByDescending("createdAt");
        Weightquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0
                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {
                        int x = (Integer) objects.get(0).get("goalWeight");
                        if (x == 0) {// if text field is empty we want to set the text to nothing
                            WeightG.setText("");
                        } else {// if text field is not 0 set the number
                            WeightG.setText("" + x);

                        }
/*
 This section adds points by calling the pointsData method from the main. It also calls the dialog
 */
                        if (x > 0) {
                            Integer value = main.WeightGoalTest(x);
                            System.out.println("value 2 " + value);
                            if (value == 1) {
                                Double points = main.points;// sets points to double
                                points = points + 100;
                                main.points = points;
                                String s = ParseUser.getCurrentUser().getUsername();
                                main.pointsData(points, s);
                                weightLossDialog();
                                main.WeightGoal(0, s);
                                WeightG.setText("");


                            }
                            if (value == 2) {
                                Log.d("QAOD", "not there yetWEIGHTloss");

                                // Toast.makeText(getActivity(), "Almost Bench!!!.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }

        });

        WeightGainquery.whereExists("goalWeightGain");//setting constraints
        WeightGainquery.whereMatchesQuery("author", queryuser);
        WeightGainquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
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
                        /*
 This section adds points by calling the pointsData method from the main. It also calls the dialog
 */
                        if (x > 0) {
                            Integer value = main.WeightGainGoalTest(x);
                            if (value == 1) {
                                Log.d("QAOD", "congratsWEIGHTGAIN");
                                Double points = main.points;
                                points = points + 100;
                                main.points = points;
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
        Benchquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
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
                        /*
 This section adds points by calling the pointsData method from the main. It also calls the dialog
 */
                        if (x > 0) {
                            Log.d("QAOD", "BENCHMAX" + main.bench);
                            Integer value = main.BenchGoalTest(x);
                            Log.d("QAOD", "BENCHMAXGOAL" + x);
                            if (value == 1) {
                                Log.d("QAOD", "congratsBENCH");
                                Double points = main.points;
                                points = points + 100;
                                main.points = points;
                                String s = ParseUser.getCurrentUser().getUsername();
                                main.pointsData(points, s);
                                benchDialog();
                                main.BenchGoal(0, s);
                                BenchG.setText("");

                            }
                           else if (value == 2) {
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
                        /*
 This section adds points by calling the pointsData method from the main. It also calls the dialog
 */
                        if (x > 0) {
                            Log.d("QAOD", "SQUATMAX" + main.squat);
                            Integer value = main.SquatGoalTest(x);
                            Log.d("QAOD", "SQUATMAXGOAL" + x);
                            if (value == 1) {
                                Log.d("QAOD", "congratsSQUAT");
                                Double points = main.points;
                                points = points + 100;
                                main.points = points;
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
                            Integer value = main.DeadLiftGoalTest(x);


                            /*
 This section adds points by calling the pointsData method from the main. It also calls the dialog
 */
                            if (value == 1) {
                                Log.d("QAOD", "congratsDEADLIFT");
                                Double points = main.points;
                                points = points + 100;
                                main.points = points;
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
                        /*
 This section adds points by calling the pointsData method from the main. It also calls the dialog
 */
                        if (x > 0) {
                            Log.d("QAOD", "MILETIMEMAX" + main.mileTime);
                            Integer value = main.MileTimeGoalTest(x);
                            Log.d("QAOD", "MILETIMEMAXGOAL" + x);
                            if (value == 1) {
                                Log.d("QAOD", "congratsMILEITME");
                                Double points = main.points;
                                points = points + 100;
                                main.points = points;
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
/*
 This section is for the dialog builder. When we want to set a goal, the set button is clicked which pops up the dialog alert box.
 The user can enter the value of the goal. They can then click set or cancel.
 */
        setWeightG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 =  new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Goal Weight.");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);//makes sure it is a value number
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
                                    dialog.cancel();// close dialog
                                }
                            }
                        });
// if cancel is clicked it closes
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

        /*
 This section is for the dialog builder. When we want to set a goal, the set button is clicked which pops up the dialog alert box.
 The user can enter the value of the goal. They can then click set or cancel.
 */
        setWeightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 =  new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Goal Weight.");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);// makes sure it is a valid number
                builder1.setView(input);
                builder1.setCancelable(true);

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
        // if cancel is clicked
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
/*
 This section is for the dialog builder. When we want to set a goal, the set button is clicked which pops up the dialog alert box.
 The user can enter the value of the goal. They can then click set or cancel.
 */
        setBenchG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 =  new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Bench Goal.");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
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

/*
 This section is for the dialog builder. When we want to set a goal, the set button is clicked which pops up the dialog alert box.
 The user can enter the value of the goal. They can then click set or cancel.
 */
        setSquatG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Squat Goal.");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
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
/*
 This section is for the dialog builder. When we want to set a goal, the set button is clicked which pops up the dialog alert box.
 The user can enter the value of the goal. They can then click set or cancel.
 */
        setDeadLiftG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 =  new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Dead Lift Goal.");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
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
        /*
 This section is for the dialog builder. When we want to set a goal, the set button is clicked which pops up the dialog alert box.
 The user can enter the value of the goal. They can then click set or cancel.
 */
        setMileTimeG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 =  new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder1.setTitle("Set Your Goal");
                builder1.setMessage("Enter Your Mile Time Goal.");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
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
