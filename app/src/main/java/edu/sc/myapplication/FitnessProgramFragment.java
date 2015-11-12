package edu.sc.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FitnessProgramFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fitness_program, container, false);
        Button btn = (Button) v.findViewById(R.id.StrengthTrainingButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FP_STR_TrainingFragment fragment = new FP_STR_TrainingFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.commit();
            }
        });

        Button btn2 = (Button) v.findViewById(R.id.BodyBuildingButton);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FP_Body_Building_Fragment fragment1 = new FP_Body_Building_Fragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.commit();
            }
        });
        // Inflate the layout for this fragment
      //  View v = inflater.inflate(R.layout.fragment_fitness_program, null);

        return v;


    }


}
