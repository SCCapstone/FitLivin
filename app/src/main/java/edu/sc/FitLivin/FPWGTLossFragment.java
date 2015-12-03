/******
 * Class 'FPWGTLossFragment'
 *
 * Provides the user with choices of day 1-5 for the WeightLoss Program
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

public class FPWGTLossFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fp__wgt__loss, container, false);
        Button btn = (Button) v.findViewById(R.id.backButtonWeightLoss);
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
        });

        Button day1 = (Button) v.findViewById(R.id.day1WeightLoss);
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightLossDayOne weightLossDayOne = new WeightLossDayOne();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, weightLossDayOne);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Button day2 = (Button) v.findViewById(R.id.day2WeightLoss);
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightLossDayTwo weightLossDayTwo = new WeightLossDayTwo();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, weightLossDayTwo);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button day3 = (Button) v.findViewById(R.id.day3WeightLoss);
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightLossDayThree weightLossDayThree = new WeightLossDayThree();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, weightLossDayThree);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Button day4 = (Button) v.findViewById(R.id.day4WeightLoss);
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightLossDayFour weightLossDayFour = new WeightLossDayFour();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, weightLossDayFour);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Button day5 = (Button) v.findViewById(R.id.day5WeightLoss);
        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightLossDayFive weightLossDayFive = new WeightLossDayFive();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, weightLossDayFive);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}
