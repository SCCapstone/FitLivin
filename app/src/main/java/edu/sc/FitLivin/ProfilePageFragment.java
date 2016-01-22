/******
 * Class 'ProfilePageFragment'
 *
 * Provides the user with the ability to enter their name, weight, and height
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
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

public class ProfilePageFragment extends Fragment {

    public ProfilePageFragment() {
        // Required empty public constructor
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile__page, container, false);
       // final TextView newText = (TextView) v.findViewById(R.id.textView25);
        final TextView heightText = (TextView) v.findViewById(R.id.heightView);
        final TextView weightText = (TextView) v.findViewById(R.id.weightView);
        final TextView VeiwName = (TextView) v.findViewById(R.id.name);
        final EditText editHeight = (EditText) v.findViewById(R.id.height);
        final EditText editWeight = (EditText) v.findViewById(R.id.weight);
        //final TextView currentName = (TextView) v.findViewById(R.id.CurrN);
        final TextView currentHeight = (TextView) v.findViewById(R.id.CurrH);
        final TextView currrentWeight = (TextView) v.findViewById(R.id.CurrW);


        //String name = MainActivity.name;
        Integer height = MainActivity.height;
        Integer weight = MainActivity.weight;

        //currentName.setText(name);
        currrentWeight.setText("" + weight);
        currentHeight.setText("" + height);

        Button btn2 = (Button) v.findViewById(R.id.saveButtonProfile);
        VeiwName.setText(ParseUser.getCurrentUser().getUsername());
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //String name = MainActivity.name;
                Integer height = MainActivity.height;
                Integer weight = MainActivity.weight;

                MainActivity main = new MainActivity();
                //name = VeiwName.getText().toString();

                String h = editHeight.getText().toString();
                height = Integer.parseInt(h);
                String w = editWeight.getText().toString();
                weight = Integer.parseInt(w);
                //currentName.setText(name);

                currrentWeight.setText("" + weight);
                currentHeight.setText("" + height);

                main.profileData( weight, height);


            }
        });

        Button btn = (Button) v.findViewById(R.id.profileBMIButton);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BMICAL_Fragment fragment1 = new BMICAL_Fragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button backBtn = (Button) v.findViewById(R.id.profilePageBackButton);
        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
