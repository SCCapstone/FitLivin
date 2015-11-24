package edu.sc.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BMICAL_Fragment extends Fragment {

    private Integer BMI_Weight;

    public BMICAL_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmical, container, false);

        final TextView newText = (TextView) v.findViewById(R.id.textView14);
        final TextView BMI = (TextView) v.findViewById(R.id.textView15);
        final TextView newText1 = (TextView) v.findViewById(R.id.textView11);
        newText.setText("" + MainActivity.height);
        newText1.setText("" + MainActivity.weight);
        BMI_Weight = MainActivity.weight;
        Button cal = (Button) v.findViewById(R.id.button4);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMI_Weight = BMI_Weight + 50;
                BMI.setText("" + BMI_Weight);
            }
        });

        Button btn = (Button) v.findViewById(R.id.button8);
        btn.setOnClickListener(new View.OnClickListener() {
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
    /* float bmiValue = calculateBMI(weight, height);

    private float calculateBMI(float weight, float height) {
        return (float) (weight * 4.88 / (height * height));
    }

    private interpretBMI(float bmiValue){
        if (bmiValue < 16) {
            return "Severely underweight";
        } else if (bmiValue < 18.5 ) {
            return "Underweight";
        } else if (bmiValue < 25) {
            return "Average weight"
        } else if (bmiValue < 30) {
            return "Overweight"
        } else {
            return "Sorry, you're obese";
        }
    }*/
}
