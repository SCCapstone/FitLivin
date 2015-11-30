package edu.sc.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FPBodyBuildingFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fp__body__building, container, false);


        Button btn = (Button) v.findViewById(R.id.backBodyBuilding);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FitnessProgramFragment fragment1 = new FitnessProgramFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button day1 = (Button) v.findViewById(R.id.day1BodyBuilding);
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayOne bodyB1 = new BodyBuildingDayOne();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, bodyB1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Button day2 = (Button) v.findViewById(R.id.day2BodyBuilding);
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayTwo bodyB2 = new BodyBuildingDayTwo();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, bodyB2);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button day3 = (Button) v.findViewById(R.id.day3BodyBuilding);
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayThree bodyB3 = new BodyBuildingDayThree();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, bodyB3);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Button day4 = (Button) v.findViewById(R.id.day4BodyBuilding);
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayFour bodyB4 = new BodyBuildingDayFour();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, bodyB4);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Button day5 = (Button) v.findViewById(R.id.day5BodyBuilding);
        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyBuildingDayFive bodyB5 = new BodyBuildingDayFive();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, bodyB5);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}