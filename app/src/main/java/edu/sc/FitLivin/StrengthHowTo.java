/******
 * Class 'StrengthHowTo'
 *
 * Provides the user with directions on how to complete the workouts
 *
 */

package edu.sc.FitLivin;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class StrengthHowTo extends Fragment {



    public StrengthHowTo() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_strength_how_to, container, false);
    }



}
