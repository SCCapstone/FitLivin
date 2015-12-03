/******
 * Class 'WeightLossDayFour'
 *
 * Provides the workout for Day 3 of WeigthLoss
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

public class WeightLossDayFour extends Fragment {


    public WeightLossDayFour() {
        // Required empty public constructor
    }

    /*
     *Creates the view of our fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Required
        View v = inflater.inflate(R.layout.fragment_weightloss_day3, container, false);
        Button backBtn = (Button) v.findViewById(R.id.WLBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPWGTLossFragment fragment1 = new FPWGTLossFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        return v;
    }



}