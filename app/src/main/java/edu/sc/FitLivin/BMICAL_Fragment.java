/******
 * Class 'BMICAL_Fragment'
 *
 * Allows the user to calculate their Body Mass Index
 *
 */

package edu.sc.FitLivin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
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

public class BMICAL_Fragment extends Fragment {

   // private Integer BMI_Weight;
    private float multiplier = 703;

    public BMICAL_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmical, container, false);

        final TextView CurrH = (TextView) v.findViewById(R.id.CurrHeight);
        final TextView BMI = (TextView) v.findViewById(R.id.DisplayBMI);
        final TextView CurrW = (TextView) v.findViewById(R.id.CurrWeight);

       CurrH.setText("" + MainActivity.height);
       CurrW.setText("" + MainActivity.weight);
        //BMI_Weight = MainActivity.weight;
        Button cal = (Button) v.findViewById(R.id.CalculateBMI);

        ParseQuery query = ParseQuery.getQuery("ProfileInfo"); //getting query
        query.whereExists("Weight");//setting constraints
        query.whereExists("Height");//setting constraints
        query.orderByDescending("createdAt");
        query.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null  && objects.size()!= 0 ) { //if objects size is not 0

                    if(objects.get(0).get("UserP").equals(ParseUser.getCurrentUser()))
                    {
                        int x = (Integer)objects.get(0).get("Weight");

                        CurrW.setText(objects.get(0).get("Weight").toString()); //setting weight
                        CurrH.setText(objects.get(0).get("Height").toString()); //setting height
                    }
                }
            }

        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // BMI_Weight = BMI_Weight + 50;
                //BMI.setText("" + BMI_Weight);



                ParseQuery query = ParseQuery.getQuery("ProfileInfo"); //getting query
                query.whereExists("Weight");//setting constraints
                query.whereExists("Height");//setting constraints
                query.orderByDescending("createdAt");
                query.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null  && objects.size()!= 0 ) { //if objects size is not 0

                            if(objects.get(0).get("UserP").equals(ParseUser.getCurrentUser()))
                            {
                                float currweight = objects.get(0).get("Weight").hashCode(); //setting weight
                                float currheight = objects.get(0).get("Height").hashCode(); //setting height

                                float bmiValue = calculateBMI(currweight, currheight);
                                if (bmiValue < 16) {
                                    BMI.setText("BMI: " + bmiValue + ". You are severely underweight. Get help!");
                                } else if (bmiValue < 18.5) {
                                    BMI.setText("BMI: " + bmiValue + ". You are underweight. Eat something!");
                                } else if (bmiValue < 25) {
                                    BMI.setText("BMI: " + bmiValue + ". You are average. Keep doing you.");
                                } else if (bmiValue < 30) {
                                    BMI.setText("BMI: " + bmiValue + ". You are overweight. Go for a run.");
                                } else {
                                    BMI.setText("BMI: " + bmiValue + ". You are obese. Get a hold of yourself!");
                                }

                            }
                        }
                    }

                });




            }
        });

        Button btn = (Button) v.findViewById(R.id.BmiBack);
        btn.setOnClickListener(new View.OnClickListener() {
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

    public float calculateBMI(double weight, double height) {
        return (float) ((weight / (height * height)) * multiplier);
    }
}
