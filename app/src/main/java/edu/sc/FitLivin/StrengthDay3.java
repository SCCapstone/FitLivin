/******
 * Class 'StrengthDay3'
 *
 * Provides the workout for Day 3 of Strength Training
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


public class StrengthDay3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match




    public StrengthDay3() {
        // Required empty public constructor
    }



    @Override
    /*
     *Creates the view of our fragment.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_strength_day3, container, false);
        //Initializes backbutton for strength day 3
        Button backBtn = (Button) v.findViewById(R.id.Sday3BACK);

             /*
             *On click method that exits out of Strength day three and takes users back to the
             *strength training menu. Gives functionality to the back button.
             */
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPSTRTrainingFragment fragment1 = new FPSTRTrainingFragment();
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
