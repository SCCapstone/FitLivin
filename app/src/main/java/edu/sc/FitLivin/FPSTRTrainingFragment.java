/******
 * Class 'FPSTRTrainingFragment'
 *
 * Provides the user with choices of day 1-5 for the Strength Training Program
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

public class FPSTRTrainingFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fp__str__training, container, false);
        Button btn = (Button) v.findViewById(R.id.backButtonStrength);

        Button day1 = (Button) v.findViewById(R.id.day1Strength);
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrengthDayOne fragment = new StrengthDayOne();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        Button day2 = (Button) v.findViewById(R.id.day2Strength);
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrengthDayTwo fragment = new StrengthDayTwo();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        Button day3 = (Button) v.findViewById(R.id.day3Strength);
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrengthDayThree fragment = new StrengthDayThree();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        Button day4 = (Button) v.findViewById(R.id.day4Strength);
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrengthDayFour fragment = new StrengthDayFour();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        Button day5 = (Button) v.findViewById(R.id.day5Strength);
        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrengthDayFive fragment = new StrengthDayFive();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
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
        // Inflate the layout for this fragment
        Button howTo = (Button) v.findViewById(R.id.howTo);
        howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrengthHowTo howTo = new StrengthHowTo();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, howTo);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return v;
    }



}
