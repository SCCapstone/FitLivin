/******
 * Class 'BodyBuildingDayOne'
 *
 * Provides the workout for Day 1 of Body Building
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


public class BodyBuildingDayOne extends Fragment {


    public BodyBuildingDayOne() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_body_building_one, container, false);
        Button backBtn = (Button) v.findViewById(R.id.BBBack);//creates buttons
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPBodyBuildingFragment fragment1 = new FPBodyBuildingFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);//replaces previous fragment
                ft.addToBackStack(null);//adds to the back stack
                ft.commit();//commits it
            }
        });

        Button completeBtn = (Button) v.findViewById(R.id.completeDay1bb);//creates complete button
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer points = MainActivity.points;
                points = points + 50;//adds points for completed workout
                MainActivity main = new MainActivity();
                main.pointsData(points);
            }
        });


        return v;//return

    }




}
