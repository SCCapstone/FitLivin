package edu.sc.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FitnessProgramFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  View v = inflater.inflate(R.layout.fragment_fitness_program, null);

        return inflater.inflate(R.layout.fragment_fitness_program, container, false);
    }


}
