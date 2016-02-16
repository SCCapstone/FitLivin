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


        BenchMaxquery.whereExists("MaxBench");//setting constraints
        BenchMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        BenchMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null  && objects.size()!= 0 ) { //if objects size is not 0

                    if(objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername()))
                    {

                        int x = (Integer)objects.get(objects.size() - 1).get("MaxBench");
                        userBench.setText("" + x);

                    }
                }
            }

        });

        SquatMaxquery.whereExists("MaxSquat");//setting constraints
        SquatMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
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
        DeadLiftMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
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
        MileTimeMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
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

            final AlertDialog.Builder builderBench = new AlertDialog.Builder(getActivity());
            builderBench.setTitle("Set Your Max");
            builderBench.setMessage("Please Enter Your Bench Max:");

            // Use an EditText view to get user input.
            final EditText input = new EditText(getActivity());
            input.setId(TEXT_ID);
            builderBench.setView(input);
        setBenchMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderBench.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        Integer bench = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        userBench.setText("" + bench);
                        main.BenchMax(bench, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = builderBench.create();
                dialog.show();
            }
        });

            final AlertDialog.Builder builderSquat = new AlertDialog.Builder(getActivity());
            builderSquat.setTitle("Set Your Max");
            builderSquat.setMessage("Please Enter Your Squat Max:");

            // Use an EditText view to get user input.
            final EditText input1 = new EditText(getActivity());
            input1.setId(TEXT_ID1);
            builderSquat.setView(input1);
        setSquatMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderSquat.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input1.getText().toString();
                        Integer squat = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        userSquat.setText("" + squat);
                        main.SquatMax(squat, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = builderSquat.create();
                dialog.show();
            }
        });
            final AlertDialog.Builder builderDeadLift = new AlertDialog.Builder(getActivity());
            builderDeadLift.setTitle("Set Your Max");
            builderDeadLift.setMessage("Please Enter Your Dead Lift Max:");

            // Use an EditText view to get user input.
            final EditText input2 = new EditText(getActivity());
            input2.setId(TEXT_ID2);
            builderDeadLift.setView(input2);
        setDeadLiftMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderDeadLift.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input2.getText().toString();
                        Integer deadLift = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        userDL.setText("" + deadLift);
                        main.DeadLiftMax(deadLift, s);
                        //main.benchD = test;
                        return;
                    }
                });
                AlertDialog dialog = builderDeadLift.create();
                dialog.show();
            }
        });



            final AlertDialog.Builder builderMileTime = new AlertDialog.Builder(getActivity());
            builderMileTime.setTitle("Set Your Max");
            builderMileTime.setMessage("Please Enter Your Best Mile Time:");

            // Use an EditText view to get user input.
            final EditText input3 = new EditText(getActivity());
            input3.setId(TEXT_ID3);
            builderMileTime.setView(input3);
        setMileTimeMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderMileTime.setPositiveButton("SET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input3.getText().toString();
                        Integer mileTime = Integer.valueOf(value);
                        String s = ParseUser.getCurrentUser().getUsername();
                        userMileTime.setText("" + mileTime);
                        main.MileTimeMax(mileTime, s);
                        return;
                    }
                });
                AlertDialog dialog = builderMileTime.create();
                dialog.show();
            }
        });



        return v;
    }



}
