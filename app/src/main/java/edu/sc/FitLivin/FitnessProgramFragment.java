/******
 * Class 'FitnessProgramFragment'
 *
 * Allows the user to create a specific fitness program. They have
 * choices of WeightLoss, BodyBuilding, or StrengthTraining.
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

public class FitnessProgramFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflates layout for fragment
        View v = inflater.inflate(R.layout.fragment_fitness_program, container, false);
        Button btn = (Button) v.findViewById(R.id.StrengthTrainingButton);
        //sets action bar
        getActivity().getActionBar()
                .setTitle("Fitness Program");

        //sets button for the Strength Training fragment
        //Once clicked the button directs the app to the Strength training fragment
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPSTRTrainingFragment fragment = new FPSTRTrainingFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        //sets button for Bodybuilding fragment
        //Once clicked the button directs the app to the bodybuilding fragment
        Button btn2 = (Button) v.findViewById(R.id.BodyBuildingButton);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPBodyBuildingFragment fragment = new FPBodyBuildingFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        //sets button for Weightloss fragment
        //Once clicked the button directs the app to the weightloss exercise program
        Button btn3 = (Button) v.findViewById(R.id.WeightLossButton);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FPWGTLossFragment fragment = new FPWGTLossFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        // Inflate the layout for this fragment
      //  View v = inflater.inflate(R.layout.fragment_fitness_program, null);

        /**   Button backBtn = (Button) v.findViewById(R.id.FitProBack);
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
        });**/

        return v;


    }


}
