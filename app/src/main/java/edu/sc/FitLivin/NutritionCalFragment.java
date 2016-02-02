/******
 * Class 'NutritionCalFragment'
 *
 * Provides the user with the ability to calculate their needed calorie
 * and protein intake.
 *
 */

package edu.sc.FitLivin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class NutritionCalFragment extends Fragment {
 public Integer weight = 10;


    public NutritionCalFragment() {

    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Integer test = 110;
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nutrition_cal, container, false);
        Button weightLoss = (Button) v.findViewById(R.id.wlossButton);
        Button maintain = (Button) v.findViewById(R.id.maintainButton);
        Button weightGain = (Button) v.findViewById(R.id.wGainButton);
        final TextView lowCal = (TextView) v.findViewById(R.id.lowCalories);
        final TextView modCal = (TextView) v.findViewById(R.id.modCalories);
        final TextView highCal = (TextView) v.findViewById(R.id.highCalories);

        weightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            ParseUser user = objects.get(0);
                            Log.d("QAOD", "success");
                            Log.d("Q", "dd " + objects.size() + "dd ");
                            String genderU = user.get("gender").toString();

                            Log.d("gender", genderU);
                            if (genderU.equalsIgnoreCase("male")) {
                                ParseQuery query1 = ParseQuery.getQuery("ProfileInfo"); //getting query
                                query1.whereExists("Weight");//setting constraints
                                query1.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query1.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) {
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(objects.size() - 1).get("Weight");
                                                Log.d("F", "weightM");
                                                Integer low = MaleWeightLoss(weight);
                                                Integer mod = MaleMaintain(weight);
                                                Integer high = 23 * weight - 550;
                                                lowCal.setText("" + low);
                                                modCal.setText("" + mod);
                                                highCal.setText("" + high);
                                            }
                                        }
                                    }

                                });

                            }
                            if (genderU.equalsIgnoreCase("female")) {
                                ParseQuery query2 = ParseQuery.getQuery("ProfileInfo"); //getting query
                                query2.whereExists("Weight");//setting constraints
                                query2.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query2.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) { //if objects size is not 0
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(objects.size() - 1).get("Weight");
                                                Log.d("F", "weightM");
                                                Integer low = 16 * weight - 550;
                                                Integer mod = 17 * weight - 550;
                                                Integer high = 20 * weight - 550;
                                                lowCal.setText("" + low);
                                                modCal.setText("" + mod);
                                                highCal.setText("" + high);
                                            }
                                        }
                                    }

                                });
                            }

                        } else {
                            Log.d("QAOD", "wrong");
                        }
                    }
                });

            }
        });

        maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            ParseUser user = objects.get(0);
                            Log.d("QAOD", "success");
                            Log.d("Q", "dd " + objects.size() + "dd ");
                            String genderU = user.get("gender").toString();

                            Log.d("gender", genderU);
                            if (genderU.equalsIgnoreCase("male")) {
                                ParseQuery query1 = ParseQuery.getQuery("ProfileInfo"); //getting query
                                query1.whereExists("Weight");//setting constraints
                                query1.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query1.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) {
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(objects.size() - 1).get("Weight");
                                                Log.d("F", "weightM");
                                                Integer low = 17 * weight;
                                                Integer mod = 19 * weight;
                                                Integer high = 23 * weight;
                                                lowCal.setText("" + low);
                                                modCal.setText("" + mod);
                                                highCal.setText("" + high);
                                            }
                                        }
                                    }

                                });

                            }
                            if (genderU.equalsIgnoreCase("female")) {
                                ParseQuery query2 = ParseQuery.getQuery("ProfileInfo"); //getting query
                                query2.whereExists("Weight");//setting constraints
                                query2.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query2.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) { //if objects size is not 0
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(objects.size() - 1).get("Weight");
                                                Log.d("F", "weightM");
                                                Integer low = 16 * weight;
                                                Integer mod = 17 * weight;
                                                Integer high = 20 * weight;
                                                lowCal.setText("" + low);
                                                modCal.setText("" + mod);
                                                highCal.setText("" + high);
                                            }
                                        }
                                    }

                                });
                            }

                        } else {
                            Log.d("QAOD", "wrong");
                        }
                    }
                });

            }
        });

        weightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            ParseUser user = objects.get(0);
                            Log.d("QAOD", "success");
                            Log.d("Q", "dd " + objects.size() + "dd ");
                            String genderU = user.get("gender").toString();

                            Log.d("gender", genderU);
                            if (genderU.equalsIgnoreCase("male")) {
                                ParseQuery query1 = ParseQuery.getQuery("ProfileInfo"); //getting query
                                query1.whereExists("Weight");//setting constraints
                                query1.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query1.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) {
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(objects.size() - 1).get("Weight");
                                                Log.d("F", "weightM");
                                                Integer low = 17 * weight + 750;
                                                Integer mod = 19 * weight + 750;
                                                Integer high = 23 * weight + 750;
                                                lowCal.setText("" + low);
                                                modCal.setText("" + mod);
                                                highCal.setText("" + high);
                                            }
                                        }
                                    }

                                });

                            }
                            if (genderU.equalsIgnoreCase("female")) {
                                ParseQuery query2 = ParseQuery.getQuery("ProfileInfo"); //getting query
                                query2.whereExists("Weight");//setting constraints
                                query2.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query2.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) { //if objects size is not 0
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(objects.size() - 1).get("Weight");
                                                Log.d("F", "weightM");
                                                Integer low = 16 * weight + 750;
                                                Integer mod = 17 * weight + 750;
                                                Integer high = 20 * weight + 750;
                                                lowCal.setText("" + low);
                                                modCal.setText("" + mod);
                                                highCal.setText("" + high);
                                            }
                                        }
                                    }

                                });
                            }

                        } else {
                            Log.d("QAOD", "wrong");
                        }
                    }
                });

            }
        });



        //Creates back button to go back to main page
        Button backBtn = (Button) v.findViewById(R.id.NutBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageFragment fragment1 = new HomePageFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);//replaces fragment with previous
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return v;


    }

    public Integer MaleWeightLoss(Integer weight){
        Integer weight1 = weight;
        Integer low = 17 * weight1 - 550;

        return low;
    }
    public Integer MaleMaintain(Integer weight){
        Integer weight1 = weight;
        Integer mod = 19 * weight1 - 550;

        return mod;
    }




}
