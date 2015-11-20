package edu.sc.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile_Page_Fragment extends Fragment {
private String name;
    public Profile_Page_Fragment() {
        // Required empty public constructor
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      Data data = new Data();
       String s = data.getName();

        View v = inflater.inflate(R.layout.fragment_profile__page, container, false);
      TextView newText = (TextView) v.findViewById(R.id.textView11);
        String ss = "dog";
       newText.setText(ss);

        Button btn2 = (Button) v.findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  EditText one = (EditText)v.findViewById(R.id.editText9);

                //Integer weight1 = Integer.parseInt(one.getText().toString());
               // MainActivity main = new MainActivity();
               // main.setWeight(weight1);

               /* BMICAL_Fragment fragment = new BMICAL_Fragment();
                EditText one = (EditText)v.findViewById(R.id.editText9);
                final Bundle bundle = new Bundle();
                Integer weight1 = Integer.parseInt(one.getText().toString());
                //String strValue = one.getText().toString();
                //OP.setText(Integer.toString(myNum);
                bundle.putInt("weight", weight1);
                fragment.setArguments(bundle);*/
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


       /* Fragment fragment = new Fragment();
        final Bundle bundle = new Bundle();
        bundle.putString("user_name", myusername);
        fragment.setArguments(bundle);
        // Inflate the layout for this fragment
    */    return v;
    }
    public String getName()
    {

        return this.name;
    }
    public void setName(String value)
    {

        this.name = value;
    }

}
