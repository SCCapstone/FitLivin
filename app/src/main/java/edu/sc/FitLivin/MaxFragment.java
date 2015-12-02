/******
 * Class 'MaxFragment'
 *
 * Provides the user with the chance to add to their max
 * for four main workouts. Bench,Squat,Dead lift, and Mile Run.
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
import android.widget.TextView;


public class MaxFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_max, container, false);

        //initialize textviews in max xml
        final TextView userBench = (TextView) v.findViewById(R.id.userBench);
        final TextView userSquat = (TextView) v. findViewById(R.id.userSquat);
        final TextView userDL = (TextView) v. findViewById(R.id.userDeadlift);
        final TextView userTotal = (TextView) v.findViewById(R.id.userTotal);

        Integer userBench1 = 1;
        userBench.setText(""+userBench1);

        Integer userSquat1 = 2;
        userSquat.setText(""+userSquat1);

        Integer userDL1 = 3;
        userDL.setText(""+userDL1);

        Integer userTotal1 = 0;

         //sums userbench, usersquat, and userdl
        userTotal1 = userBench1 + userSquat1 + userDL1;
        //sets usertotal to usertotal1
        userTotal.setText(""+userTotal1);


        Button backBtn = (Button) v.findViewById(R.id.MaxBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
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



}
