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
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        getActivity().getActionBar()
                .setTitle("Nutrition");
        final Button weightLoss = (Button) v.findViewById(R.id.wlossButton);
        final Button maintain = (Button) v.findViewById(R.id.maintainButton);
        final Button weightGain = (Button) v.findViewById(R.id.wGainButton);
        final TextView lowCal = (TextView) v.findViewById(R.id.lowCalories);
        final TextView modCal = (TextView) v.findViewById(R.id.modCalories);
        final TextView highCal = (TextView) v.findViewById(R.id.highCalories);

        weightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightLoss.setTextColor(Color.GREEN);
                maintain.setTextColor(Color.BLACK);
                weightGain.setTextColor(Color.BLACK);

                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                query.orderByDescending("createdAt");
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
                                query1.orderByDescending("createdAt");
                                query1.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query1.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) {
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(0).get("Weight");
                                                Log.d("F", "weightM");
                                                Integer low = MaleWeightLossL(weight);
                                                Integer mod = MaleWeightLossM(weight);
                                                Integer high = MaleWeightLossH(weight);
                                                //Integer high = 23 * weight - 550;
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
                                query2.orderByDescending("createdAt");
                                query2.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query2.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) { //if objects size is not 0
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(0).get("Weight");
                                                Log.d("F", "weightM");
                                                //Integer low = 16 * weight - 550;
                                                Integer low = FemaleWeightLossL(weight);
                                                //Integer mod = 17 * weight - 550;
                                                Integer mod = FemaleWeightLossM(weight);
                                                //Integer high = 20 * weight - 550;
                                                Integer high = FemaleWeightLossH(weight);
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
                maintain.setTextColor(Color.GREEN);
                weightLoss.setTextColor(Color.BLACK);
                weightGain.setTextColor(Color.BLACK);
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                query.orderByDescending("createdAt");
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
                                query1.orderByDescending("createdAt");
                                query1.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query1.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) {
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(0).get("Weight");
                                                Log.d("F", "weightM");
                                                //Integer low = 17 * weight;
                                                Integer low = MaleWeightMaintainL(weight);
                                                //Integer mod = 19 * weight;
                                                Integer mod = MaleWeightMaintainM(weight);
                                                //Integer high = 23 * weight;
                                                Integer high = MaleWeightMaintainH(weight);
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
                                query2.orderByDescending("createdAt");
                                query2.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query2.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) { //if objects size is not 0
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(0).get("Weight");
                                                Log.d("F", "weightM");
                                                //Integer low = 16 * weight;
                                                Integer low = FemaleWeightMaintainL(weight);
                                                //Integer mod = 17 * weight;
                                                Integer mod = FemaleWeightMaintainM(weight);
                                               // Integer high = 20 * weight;
                                                Integer high = FemaleWeightMaintainH(weight);
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
                weightGain.setTextColor(Color.GREEN);
                maintain.setTextColor(Color.BLACK);
                weightLoss.setTextColor(Color.BLACK);
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                query.orderByDescending("createdAt");
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
                                query1.orderByDescending("createdAt");
                                query1.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query1.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) {
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(0).get("Weight");
                                                Log.d("F", "weightM");
                                                //Integer low = 17 * weight + 750;
                                                Integer low = MaleWeightGainL(weight);
                                                //Integer mod = 19 * weight + 750;
                                                Integer mod = MaleWeightGainM(weight);
                                                //Integer high = 23 * weight + 750;
                                                Integer high = MaleWeightGainH(weight);
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
                                query2.orderByDescending("createdAt");
                                query2.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                                query2.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) { //if objects size is not 0
                                            if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                                                weight = (Integer) objects.get(0).get("Weight");
                                                Log.d("F", "weightM");
                                                //Integer low = 16 * weight + 750;
                                                Integer low = FemaleWeightGainL(weight);
                                                //Integer mod = 17 * weight + 750;
                                                Integer mod = FemaleWeightGainM(weight);
                                                //Integer high = 20 * weight + 750;
                                                Integer high = FemaleWeightGainH(weight);
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



        /**   //Creates back button to go back to main page
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
        });**/
        return v;


    }

    public Integer MaleWeightLossL (Integer weight){
        Integer weight1 = weight;
        Integer low = 17 * weight1 - 550;

        return low;
    }
    public Integer MaleWeightLossM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 19 * weight1 - 550;

        return mod;
    }

    public Integer MaleWeightLossH (Integer weight){
        Integer weight1 = weight;
        Integer high = 23 * weight1 - 550;

        return high;
    }

    public Integer FemaleWeightLossL (Integer weight){
        Integer weight1 = weight;
        Integer low = 16 * weight1 - 550;

        return low;
    }
    public Integer FemaleWeightLossM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 17 * weight1 - 550;

        return mod;
    }

    public Integer FemaleWeightLossH (Integer weight){
        Integer weight1 = weight;
        Integer high = 20 * weight1 - 550;

        return high;
    }
    public Integer MaleWeightMaintainL (Integer weight){
        Integer weight1 = weight;
        Integer low = 17 * weight1;

        return low;
    }
    public Integer MaleWeightMaintainM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 19 * weight1;

        return mod;
    }
    public Integer MaleWeightMaintainH (Integer weight){
        Integer weight1 = weight;
        Integer high = 23 * weight1;

        return high;
    }
    public Integer FemaleWeightMaintainL (Integer weight){
        Integer weight1 = weight;
        Integer low = 16 * weight1;

        return low;
    }
    public Integer FemaleWeightMaintainM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 17 * weight1;

        return mod;
    }
    public Integer FemaleWeightMaintainH (Integer weight){
        Integer weight1 = weight;
        Integer high = 20 * weight1;

        return high;
    }

    public Integer MaleWeightGainL (Integer weight){
        Integer weight1 = weight;
        Integer low = 17 * weight1 +750;

        return low;
    }

    public Integer MaleWeightGainM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 19 * weight1 +750;

        return mod;
    }

    public Integer MaleWeightGainH (Integer weight){
        Integer weight1 = weight;
        Integer high = 23 * weight1 +750;

        return high;
    }
    public Integer FemaleWeightGainL (Integer weight){
        Integer weight1 = weight;
        Integer low = 16 * weight1 +750;

        return low;
    }
    public Integer FemaleWeightGainM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 17 * weight1 +750;

        return mod;

    }
    public Integer FemaleWeightGainH (Integer weight){
        Integer weight1 = weight;
        Integer high = 20 * weight1 +750;

        return high;
    }

}
