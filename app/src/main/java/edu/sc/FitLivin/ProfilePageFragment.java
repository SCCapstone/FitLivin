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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfilePageFragment extends Fragment {
    private Button editprofile;
    private ImageButton profileChangebutton;
    public ProfilePageFragment() {
        // Required empty public constructor
    }




    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile__page, container, false);

        final TextView heightText = (TextView) v.findViewById(R.id.heightView);
        final TextView weightText = (TextView) v.findViewById(R.id.weightView);
        final TextView VeiwName = (TextView) v.findViewById(R.id.name);
        final EditText editHeight = (EditText) v.findViewById(R.id.height);
        final EditText editWeight = (EditText) v.findViewById(R.id.weight);
        //final TextView currentName = (TextView) v.findViewById(R.id.CurrN);
        final TextView currentHeight = (TextView) v.findViewById(R.id.CurrH);
        final TextView currentWeight = (TextView) v.findViewById(R.id.CurrW);
        profileChangebutton = (ImageButton) v.findViewById(R.id.Profilepicview);








        ParseQuery query = ParseQuery.getQuery("ProfileInfo"); //getting query
        query.whereExists("Weight");//setting constraints
        query.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                        currentWeight.setText(objects.get(objects.size() - 1).get("Weight").toString()); //setting weight
                        currentHeight.setText(objects.get(objects.size() - 1).get("Height").toString()); //setting height
                        Log.d("F", "weight");
                    }
                } else {
                    Toast.makeText(getActivity(), "Error you must enter data first.", Toast.LENGTH_SHORT).show();
                }
            }

        });


        Button btn2 = (Button) v.findViewById(R.id.saveButtonProfile);
        VeiwName.setText(ParseUser.getCurrentUser().getUsername());
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                Integer height = MainActivity.height;
                Integer weight = MainActivity.weight;

                MainActivity main = new MainActivity();


                String h = editHeight.getText().toString();
                height = Integer.parseInt(h);
                String w = editWeight.getText().toString();
                weight = Integer.parseInt(w);
                currentHeight.setText(""+height);
                currentWeight.setText(""+weight);



                main.profileData(weight, height, ParseUser.getCurrentUser());


            }
        });

        Button btn = (Button) v.findViewById(R.id.profileBMIButton);
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
        editprofile = (Button) v.findViewById(R.id.editprofile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editprofilefragment fragment = new Editprofilefragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });



        return v;
    }


}
