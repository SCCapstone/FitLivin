/******
 * Class 'NutritionCalFragment'
 *
 * Provides the user with the ability to calculate their needed calorie
 * to lose, maintain, or gain weight based on level of activity
 *
 */

package edu.sc.FitLivin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
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

        /****
         *  This block displays the amount of calories needed for a user when they
         *  are focused on weightloss. It checks the users gender and calculates by calling the
         *  caluclation methods.
         */
        weightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changes button text to green when clicked
                weightLoss.setTextColor(Color.GREEN);
                maintain.setTextColor(Color.WHITE);
                weightGain.setTextColor(Color.WHITE);

                //queries username information on gender
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
                            //if there are a male
                            //if user is a male, data is queried and low mod and high methods are called
                            //for weightLoss
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
                                                //calls low mod and high methods for weight loss
                                                Integer low = MaleWeightLossL(weight);
                                                Integer mod = MaleWeightLossM(weight);
                                                Integer high = MaleWeightLossH(weight);
                                                //sets the text
                                                lowCal.setText("" + low);
                                                modCal.setText("" + mod);
                                                highCal.setText("" + high);
                                            }
                                        }
                                    }

                                });

                            }
                            //if user is a female, data is queried and low mod and high methods are called
                            //for weight loss
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
                                                //sets the value for low, mod, and high
                                                Integer low = FemaleWeightLossL(weight);
                                                Integer mod = FemaleWeightLossM(weight);
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


        /****
         *  This block displays the amount of calories needed for a user when they
         *  are focused on maintain. It checks the users gender and calculates by calling the
         *  caluclation methods.
         */
        maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maintain.setTextColor(Color.GREEN);//sets button text to green
                weightLoss.setTextColor(Color.WHITE);
                weightGain.setTextColor(Color.WHITE);
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
                           //if user is a male, data is queried and low mod and high methods are called
                            //for weight maintain
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
                                                Integer low = MaleWeightMaintainL(weight);
                                                Integer mod = MaleWeightMaintainM(weight);
                                                Integer high = MaleWeightMaintainH(weight);
                                                lowCal.setText("" + low);
                                                modCal.setText("" + mod);
                                                highCal.setText("" + high);
                                            }
                                        }
                                    }

                                });

                            }
                            //if user is a female, data is queried and low mod and high methods are called
                            //for weight maintain
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
                                                Integer low = FemaleWeightMaintainL(weight);
                                                Integer mod = FemaleWeightMaintainM(weight);
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
        /****
         *  This block displays the amount of calories needed for a user when they
         *  are focused on weightgain. It checks the users gender and calculates by calling the
         *  caluclation methods.
         */
        weightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGain.setTextColor(Color.GREEN);
                maintain.setTextColor(Color.WHITE);
                weightLoss.setTextColor(Color.WHITE);
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
                            //if user is a male, data is queried and low mod and high methods are called
                            //for weight weightGain
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
                            //if user is a female, data is queried and low mod and high methods are called
                            //for weight weightGain
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

        return v;


    }

    /*****
     * 'MaleWeightLossL()'
     *
     * Sets the low activity calories for male weightLoss and returns it
     *
     */
    public Integer MaleWeightLossL (Integer weight){
        Integer weight1 = weight;
        Integer low = 17 * weight1 - 550;
        return low;
    }

    /*****
     * 'MaleWeightLossM()'
     *
     * Sets the mod activity calories for male weightLoss and returns it
     *
     */
    public Integer MaleWeightLossM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 19 * weight1 - 550;

        return mod;
    }
    /*****
     * 'MaleWeightLossH()'
     *
     * Sets the high activity calories for male weightLoss and returns it
     *
     */
    public Integer MaleWeightLossH (Integer weight){
        Integer weight1 = weight;
        Integer high = 23 * weight1 - 550;

        return high;
    }
    /*****
     * 'FemaleWeightLossL()'
     *
     * Sets the low activity calories for female weightLoss and returns it
     *
     */
    public Integer FemaleWeightLossL (Integer weight){
        Integer weight1 = weight;
        Integer low = 16 * weight1 - 550;

        return low;
    }
    /*****
     * 'FemaleWeightLossM()'
     *
     * Sets the mod activity calories for female weightLoss and returns it
     *
     */
    public Integer FemaleWeightLossM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 17 * weight1 - 550;

        return mod;
    }
    /*****
     * 'FemaleWeightLossH()'
     *
     * Sets the hgih activity calories for female weightLoss and returns it
     *
     */
    public Integer FemaleWeightLossH (Integer weight){
        Integer weight1 = weight;
        Integer high = 20 * weight1 - 550;

        return high;
    }
    /*****
     * 'MaleWeightMaintainL()'
     *
     * Sets the low activity calories for male weight maintain and returns it
     *
     */
    public Integer MaleWeightMaintainL (Integer weight){
        Integer weight1 = weight;
        Integer low = 17 * weight1;

        return low;
    }
    /*****
     * 'MaleWeightMaintainM()'
     *
     * Sets the mod activity calories for male weight maintain and returns it
     *
     */
    public Integer MaleWeightMaintainM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 19 * weight1;

        return mod;
    }
    /*****
     * 'MaleWeightMaintainH()'
     *
     * Sets the high activity calories for male weight maintain and returns it
     *
     */
    public Integer MaleWeightMaintainH (Integer weight){
        Integer weight1 = weight;
        Integer high = 23 * weight1;

        return high;
    }
    /*****
     * 'FemaleWeightMaintainL()'
     *
     * Sets the low activity calories for female weight maintain and returns it
     *
     */
    public Integer FemaleWeightMaintainL (Integer weight){
        Integer weight1 = weight;
        Integer low = 16 * weight1;

        return low;
    }
    /*****
     * 'FemaleWeightMaintainM()'
     *
     * Sets the mod activity calories for female weight maintain and returns it
     *
     */
    public Integer FemaleWeightMaintainM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 17 * weight1;

        return mod;
    }
    /*****
     * 'FemaleWeightMaintainH()'
     *
     * Sets the high activity calories for female weight maintain and returns it
     *
     */
    public Integer FemaleWeightMaintainH (Integer weight){
        Integer weight1 = weight;
        Integer high = 20 * weight1;

        return high;
    }
    /*****
     * 'MaleWeightGainL()'
     *
     * Sets the low activity calories for male weight gain and returns it
     *
     */
    public Integer MaleWeightGainL (Integer weight){
        Integer weight1 = weight;
        Integer low = 17 * weight1 +750;

        return low;
    }
    /*****
     * 'MaleWeightGainM()'
     *
     * Sets the mod activity calories for male weight gain and returns it
     *
     */
    public Integer MaleWeightGainM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 19 * weight1 +750;

        return mod;
    }
    /*****
     * 'MaleWeightGainH()'
     *
     * Sets the hgih activity calories for male weight gain and returns it
     *
     */
    public Integer MaleWeightGainH (Integer weight){
        Integer weight1 = weight;
        Integer high = 23 * weight1 +750;

        return high;
    }
    /*****
     * 'FemaleWeightGainL()'
     *
     * Sets the low activity calories for female weight gain and returns it
     *
     */
    public Integer FemaleWeightGainL (Integer weight){
        Integer weight1 = weight;
        Integer low = 16 * weight1 +750;

        return low;
    }
    /*****
     * 'FemaleWeightGainM()'
     *
     * Sets the mod activity calories for female weight gain and returns it
     *
     */
    public Integer FemaleWeightGainM (Integer weight){
        Integer weight1 = weight;
        Integer mod = 17 * weight1 +750;

        return mod;

    }
    /*****
     * 'FemaleWeightGainH()'
     *
     * Sets the high activity calories for female weight gain and returns it
     *
     */
    public Integer FemaleWeightGainH (Integer weight){
        Integer weight1 = weight;
        Integer high = 20 * weight1 +750;

        return high;
    }

}
