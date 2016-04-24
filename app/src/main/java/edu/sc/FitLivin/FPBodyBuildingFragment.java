/******
 * Class 'FPBodyBuildingFragment'
 *
 * Provides the user with choices of day 1-5 for the BodyBuilding Program
 *
 */

package edu.sc.FitLivin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FPBodyBuildingFragment extends Fragment {
    

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fp__body__building, container, false);
        //sets action bar
        getActivity().getActionBar()
                .setTitle("Body Building");

        /**    Button btn = (Button) v.findViewById(R.id.backBodyBuilding);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FitnessProgramFragment fragment1 = new FitnessProgramFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });**/
        //sets button for Bodybuilding day 1 fragment
        //Once clicked the button directs the app to the bodybuilding day 1 fragment
        Button day1 = (Button) v.findViewById(R.id.day1BodyBuilding);
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayOne fragment = new BodyBuildingDayOne();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        //sets button for Bodybuilding day 2 fragment
        //Once clicked the button directs the app to the bodybuilding day 2 fragment
        Button day2 = (Button) v.findViewById(R.id.day2BodyBuilding);
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayTwo fragment = new BodyBuildingDayTwo();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        //sets button for Bodybuilding day 3 fragment
        //Once clicked the button directs the app to the bodybuilding day 3 fragment
        Button day3 = (Button) v.findViewById(R.id.day3BodyBuilding);
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayThree fragment = new BodyBuildingDayThree();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        //sets button for Bodybuilding day 4 fragment
        //Once clicked the button directs the app to the bodybuilding day 4 fragment
        Button day4 = (Button) v.findViewById(R.id.day4BodyBuilding);
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayFour fragment = new BodyBuildingDayFour();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();

            }
        });
        //sets button for Bodybuilding day 5 fragment
        //Once clicked the button directs the app to the bodybuilding day 5 fragment
        Button day5 = (Button) v.findViewById(R.id.day5BodyBuilding);
        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayFive fragment = new BodyBuildingDayFive();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        //sets button for Bodybuilding how to fragment
        //Once clicked the button directs the app to the bodybuilding how to fragment
        // Inflate the layout for this fragment
        Button howTo = (Button) v.findViewById(R.id.howTo);
        howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingHowto howTo = new BodyBuildingHowto();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, howTo);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return v;
    }
}