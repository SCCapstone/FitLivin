/******
 * Class 'MaxFragment'
 *
 * Provides the user with the chance to add to their max
 * for four main workouts. Bench,Squat,Dead lift, and Mile Run.
 *
 */

package edu.sc.FitLivin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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


public class MaxFragment extends Fragment {

    private static final int TEXT_ID = 0;
    private static final int TEXT_ID1 = 0;
    private static final int TEXT_ID2 = 0;
    private static final int TEXT_ID3 = 0;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_max, container, false);



        //initialize textviews in max xml
        final TextView userBench = (TextView) v.findViewById(R.id.userBench);
        final TextView userSquat = (TextView) v. findViewById(R.id.userSquat);
        final TextView userDL = (TextView) v. findViewById(R.id.userDeadlift);
        final TextView userMileTime = (TextView) v.findViewById(R.id.userTime);

        final  MainActivity main = new MainActivity();

        Button setBenchMax = (Button) v.findViewById(R.id.setBench);
        Button setSquatMax = (Button) v.findViewById(R.id.setSquat);
        Button setDeadLiftMax = (Button) v.findViewById(R.id.setDeadLift);

        Button setMileTimeMax = (Button) v.findViewById(R.id.setMileTime);
        ParseQuery BenchMaxquery = ParseQuery.getQuery("MaxBench");
        ParseQuery SquatMaxquery = ParseQuery.getQuery("MaxSquat");
        ParseQuery DeadLiftMaxquery = ParseQuery.getQuery("MaxDeadLift");
        ParseQuery BigThreeMaxquery = ParseQuery.getQuery("MaxBigThree");
        ParseQuery MileTimeMaxquery = ParseQuery.getQuery("MaxMileTime");
            ParseQuery queryuser = ParseUser.getQuery();
            queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());


        BenchMaxquery.whereExists("MaxBench");//setting constraints
        BenchMaxquery.whereMatchesQuery("author", queryuser);
            BenchMaxquery.orderByDescending("createdAt");
        BenchMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxBench");
                        userBench.setText("" + x);

                    }
                }
            }

        });

        SquatMaxquery.whereExists("MaxSquat");//setting constraints
            SquatMaxquery.whereMatchesQuery("author", queryuser);
            SquatMaxquery.orderByDescending("createdAt");
        //SquatMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        SquatMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxSquat");
                        userSquat.setText("" + x);

                    }
                }
            }

        });
        DeadLiftMaxquery.whereExists("MaxDeadLift");//setting constraints
            DeadLiftMaxquery.whereMatchesQuery("author", queryuser);
            DeadLiftMaxquery.orderByDescending("createdAt");
        DeadLiftMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxDeadLift");
                        userDL.setText("" + x);

                    }
                }
            }

        });

        MileTimeMaxquery.whereExists("MaxMileTime");//setting constraints
            MileTimeMaxquery.whereMatchesQuery("author", queryuser);
            MileTimeMaxquery.orderByDescending("createdAt");
        MileTimeMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("MaxMileTime");
                        userMileTime.setText("" + x);

                    }
                }
            }

        });



            setBenchMax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("Set Your Max");
                    builder1.setMessage("Enter Your Bench Max.");
                    final EditText input = new EditText(getActivity());
                    input.setId(TEXT_ID);
                    builder1.setView(input);
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "SET",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Integer bench;
                                    MainActivity m = new MainActivity();
                                    m.ExcTest = 1;
                                    String value = input.getText().toString();
                                    try {
                                        bench = Integer.parseInt(value);
                                        Log.d("Q", "Is a number " + bench + " dd ");
                                    } catch (NumberFormatException e) {
                                        Log.d("Q", "Is not a number ");
                                        m.ExcTest = 2;
                                        Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                                .show();

                                    }
                                    if (m.ExcTest == 1) {
                                        Integer bench2 = Integer.valueOf(value);
                                        String s = ParseUser.getCurrentUser().getUsername();
                                        userBench.setText("" + bench2);
                                        main.BenchMax(bench2, s);
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

            setSquatMax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("Set Your Max");
                    builder1.setMessage("Enter Your Squat Max.");
                    final EditText input = new EditText(getActivity());
                    input.setId(TEXT_ID);
                    builder1.setView(input);
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "SET",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Integer squat;
                                    MainActivity m = new MainActivity();
                                    m.ExcTest = 1;
                                    String value = input.getText().toString();
                                    try {
                                        squat = Integer.parseInt(value);
                                        Log.d("Q", "Is a number " + squat + " dd ");
                                    } catch (NumberFormatException e) {
                                        Log.d("Q", "Is not a number ");
                                        m.ExcTest = 2;
                                        Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                                .show();

                                    }
                                    if (m.ExcTest == 1) {
                                        Integer squat2 = Integer.valueOf(value);
                                        String s = ParseUser.getCurrentUser().getUsername();
                                        userSquat.setText("" + squat2);
                                        main.SquatMax(squat2, s);
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

            setDeadLiftMax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("Set Your Max");
                    builder1.setMessage("Enter Your Dead Lift Max.");
                    final EditText input = new EditText(getActivity());
                    input.setId(TEXT_ID);
                    builder1.setView(input);
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "SET",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Integer dl;
                                    MainActivity m = new MainActivity();
                                    m.ExcTest = 1;
                                    String value = input.getText().toString();
                                    try {
                                        dl = Integer.parseInt(value);
                                        Log.d("Q", "Is a number " + dl + " dd ");
                                    } catch (NumberFormatException e) {
                                        Log.d("Q", "Is not a number ");
                                        m.ExcTest = 2;
                                        Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                                .show();

                                    }
                                    if (m.ExcTest == 1) {
                                    Integer deadLift = Integer.valueOf(value);
                                    String s = ParseUser.getCurrentUser().getUsername();
                                    userDL.setText("" + deadLift);
                                    main.DeadLiftMax(deadLift, s);
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

            setMileTimeMax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("Set Your Max");
                    builder1.setMessage("Enter Your Best Mile Time.");
                    final EditText input = new EditText(getActivity());
                    input.setId(TEXT_ID);
                    builder1.setView(input);
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "SET",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Integer mile;
                                    MainActivity m = new MainActivity();
                                    m.ExcTest = 1;
                                    String value = input.getText().toString();
                                    try {
                                        mile = Integer.parseInt(value);
                                        Log.d("Q", "Is a number " + mile + " dd ");
                                    } catch (NumberFormatException e) {
                                        Log.d("Q", "Is not a number ");
                                        m.ExcTest = 2;
                                        Toast.makeText(getActivity(), "Invalid Number", Toast.LENGTH_LONG)
                                                .show();

                                    }
                                    if (m.ExcTest == 1) {
                                        Integer miletime = Integer.valueOf(value);
                                        String s = ParseUser.getCurrentUser().getUsername();
                                        userMileTime.setText("" + miletime);
                                        main.MileTimeMax(miletime, s);
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



        return v;
    }



}
