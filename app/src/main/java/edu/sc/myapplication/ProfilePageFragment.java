package edu.sc.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

public class ProfilePageFragment extends Fragment {

    public ProfilePageFragment() {
        // Required empty public constructor
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     // Data data = new Data();
     //  String s = data.getName();

        View v = inflater.inflate(R.layout.fragment_profile__page, container, false);
        final TextView newText = (TextView) v.findViewById(R.id.textView25);
        final TextView heightText = (TextView) v.findViewById(R.id.heightView);
        final TextView weightText = (TextView) v.findViewById(R.id.weightView);
        final  EditText editName = (EditText) v.findViewById(R.id.name);
        final  EditText editHeight = (EditText) v.findViewById(R.id.height);
        final  EditText editWeight = (EditText) v.findViewById(R.id.weight);
        final TextView newText1 = (TextView) v.findViewById(R.id.ageView);

        String name = MainActivity.name;
        Integer height = MainActivity.height;
        Integer weight = MainActivity.weight;

        newText.setText(name);
        weightText.setText(""+weight);
        heightText.setText(""+ height);

        Button btn2 = (Button) v.findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String name = MainActivity.name;
                Integer height = MainActivity.height;
                Integer weight = MainActivity.weight;



               MainActivity main = new MainActivity();
                name = editName.getText().toString();
                //String obj = MainActivity.objectID;

                String h = editHeight.getText().toString();
                height = Integer.parseInt(h);
               String w = editWeight.getText().toString();
                weight = Integer.parseInt(w);
                newText.setText(name);
                weightText.setText(""+weight);
               heightText.setText(""+height);
                Integer sum = weight + 2;

                main.profileData(name,weight,height);
               String ee = main.getS();
               newText1.setText(ee);

            }
        });

        Button btn = (Button) v.findViewById(R.id.button7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMICAL_Fragment fragment1 = new BMICAL_Fragment();
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
