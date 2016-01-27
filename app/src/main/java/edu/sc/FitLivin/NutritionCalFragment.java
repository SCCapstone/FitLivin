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
        final TextView lowCal = (TextView) v.findViewById(R.id.lowCalories);
        final TextView modCal = (TextView) v.findViewById(R.id.modCalories);
        final TextView highCal = (TextView) v.findViewById(R.id.highCalories);
        ParseQuery query = ParseQuery.getQuery("ProfileInfo"); //getting query
        query.whereExists("Weight");//setting constraints
        query.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                        weight = (Integer) objects.get(objects.size() - 1).get("Weight");
                        Log.d("F", "weight1");
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


}
