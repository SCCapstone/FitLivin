/******
 * Class 'MaxFragment'
 *
 * Provides the user with the chance to add to their max
 * for four main workouts. Bench,Squat,Dead lift, and Mile Run.
 *
 */

package edu.sc.FitLivin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_max, container, false);


        //initialize textviews in max xml
        final TextView userBench = (TextView) v.findViewById(R.id.userBench);
        final TextView userSquat = (TextView) v. findViewById(R.id.userSquat);
        final TextView userDL = (TextView) v. findViewById(R.id.userDeadlift);
        final TextView userTotal = (TextView) v.findViewById(R.id.userTotal);
        final TextView userMileTime = (TextView) v.findViewById(R.id.userTime);
        final EditText changeBench = (EditText) v.findViewById(R.id.changeBench);
        final EditText changeSquat = (EditText) v.findViewById(R.id.changeSquat);
        final EditText changeDeadLift = (EditText) v.findViewById(R.id.changeDeadlift);
        final EditText changeMileTime = (EditText) v.findViewById(R.id.changeMileTime);
        final EditText changeTotal = (EditText) v.findViewById(R.id.changeBig3);
        final  MainActivity main = new MainActivity();
        Button setBenchMax = (Button) v.findViewById(R.id.setBench);
        Button setSquatMax = (Button) v.findViewById(R.id.setSquat);
        Button setDeadLiftMax = (Button) v.findViewById(R.id.setDeadLift);
        Button setTotalMax = (Button) v.findViewById(R.id.setTotal);
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

                        int x = (Integer)objects.get(0).get("MaxBench");
                        userBench.setText("" + x);

                    }
                }
            }

        });

        SquatMaxquery.whereExists("MaxSquat");//setting constraints
        SquatMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        SquatMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null  && objects.size()!= 0 ) { //if objects size is not 0

                    if(objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername()))
                    {

                        int x = (Integer)objects.get(0).get("MaxSquat");
                        userSquat.setText("" + x);

                    }
                }
            }

        });
        DeadLiftMaxquery.whereExists("MaxDeadLift");//setting constraints
        DeadLiftMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        DeadLiftMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null  && objects.size()!= 0 ) { //if objects size is not 0

                    if(objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername()))
                    {

                        int x = (Integer)objects.get(0).get("MaxDeadLift");
                        userDL.setText("" + x);

                    }
                }
            }

        });
        BigThreeMaxquery.whereExists("MaxBigThree");//setting constraints
        BigThreeMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        BigThreeMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null  && objects.size()!= 0 ) { //if objects size is not 0

                    if(objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername()))
                    {

                        int x = (Integer)objects.get(0).get("MaxBigThree");
                        userTotal.setText("" + x);

                    }
                }
            }

        });
        MileTimeMaxquery.whereExists("MaxMileTime");//setting constraints
        MileTimeMaxquery.whereContains("username", ParseUser.getCurrentUser().getUsername());
        MileTimeMaxquery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null  && objects.size()!= 0 ) { //if objects size is not 0

                    if(objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername()))
                    {

                        int x = (Integer)objects.get(0).get("MaxMileTime");
                        userMileTime.setText("" + x);

                    }
                }
            }

        });




        setBenchMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer bench;
                String benchS = changeBench.getText().toString();
                bench = Integer.valueOf(benchS);

                userBench.setText("" + bench);

                String s = ParseUser.getCurrentUser().getUsername();
                main.BenchMax(bench, s);
            }
        });

        setSquatMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer squat;
                String squatS = changeSquat.getText().toString();
                squat = Integer.valueOf(squatS);

                userSquat.setText("" + squat);

                String s = ParseUser.getCurrentUser().getUsername();
                main.SquatMax(squat, s);
            }
        });
        setDeadLiftMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deadLift;
                String deadLiftS = changeDeadLift.getText().toString();
                deadLift = Integer.valueOf(deadLiftS);

                userDL.setText("" + deadLift);

                String s = ParseUser.getCurrentUser().getUsername();
                main.DeadLiftMax(deadLift, s);
            }
        });

        setTotalMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer total;
                String totalS = changeTotal.getText().toString();
                total = Integer.valueOf(totalS);

                userTotal.setText("" + total);

                String s = ParseUser.getCurrentUser().getUsername();
                main.BigThreeMax(total, s);
            }
        });

        setMileTimeMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer miletime;
                String mileitmeS = changeMileTime.getText().toString();
                miletime = Integer.valueOf(mileitmeS);

                userMileTime.setText("" + miletime);

                String s = ParseUser.getCurrentUser().getUsername();
                main.MileTimeMax(miletime, s);
            }
        });


       /* ParseQuery query = ParseQuery.getQuery("Max"); //getting query



        Button enterButton = (Button) v.findViewById(R.id.enterMax);

      enterButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Integer bench;
              Integer squat;
              Integer deadLift;
              Integer total;
              Integer mileTime;

              MainActivity main = new MainActivity();

              String ben = changeBench.getText().toString();
              bench = Integer.valueOf(ben);
              String sqa = changeSquat.getText().toString();
              squat = Integer.valueOf(sqa);
              String dea = changeDeadLift.getText().toString();
              deadLift = Integer.valueOf(dea);
              String tot = changeTotal.getText().toString();
              total = Integer.valueOf(tot);
              String mil = changeMileTime.getText().toString();
              mileTime = Integer.valueOf(mil);

              userBench.setText(""+bench);
              userSquat.setText(""+squat);
              userDL.setText(""+deadLift);
              userTotal.setText(""+total);
              userMileTime.setText(""+mileTime);

              String s = ParseUser.getCurrentUser().getUsername();
//

              main.MaxData(bench,squat,deadLift,total,mileTime,s);

          }
      });

         /*
          *On click method that exits out of max fragment and takes users back to the
          *menu. Gives functionality to the back button.
          */


        Button backBtn = (Button) v.findViewById(R.id.MaxBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageFragment fragment1 = new HomePageFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return v;
    }



}
