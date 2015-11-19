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

import com.parse.ParseObject;

public class BMICAL_Fragment extends Fragment {


    public BMICAL_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmical, container, false);
//MainActivity main = new MainActivity();
       // Bundle b = getArguments();
       // Integer x = b.getInt("weight");
        //TextView tvStyleName = (TextView)v.findViewById(R.id.textView15);
       // Integer x = main.getWeight();
       //tvStyleName.setText(String.valueOf(x));
       // Bundle args = getArguments();
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

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo2", "bar2");
        testObject.saveInBackground();

        // Inflate the layout for this fragment
        return v;
    }



}
