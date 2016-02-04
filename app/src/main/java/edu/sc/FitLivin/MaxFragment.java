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
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


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
        final TextView userMileTime = (TextView) v.findViewById(R.id.userTime);
        final EditText changeBench = (EditText) v.findViewById(R.id.changeBench);
        final EditText changeSquat = (EditText) v.findViewById(R.id.changeSquat);
        final EditText changeDeadLift = (EditText) v.findViewById(R.id.changeDeadlift);
        final EditText changeMileTime = (EditText) v.findViewById(R.id.changeMileTime);
        final EditText changeTotal = (EditText) v.findViewById(R.id.changeBig3);

        ParseQuery query = ParseQuery.getQuery("Max"); //getting query



        Button enterButton = (Button) v.findViewById(R.id.enterMax);

      enterButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Integer bench;
              Integer squat;
              Integer deadLift;
              Integer total;
              Integer mileTime;

              MainActivity main = new MainActivity();

              String ben = changeBench.getText().toString();
              bench = Integer.valueOf(ben);
              String sqa = changeSquat.getText().toString();
              squat = Integer.valueOf(sqa);
              String dea = changeDeadLift.getText().toString();
              deadLift = Integer.valueOf(dea);
              String tot = changeTotal.getText().toString();
              total = Integer.valueOf(tot);
              String mil = changeMileTime.getText().toString();
              mileTime = Integer.valueOf(mil);

              userBench.setText(+bench);
              userSquat.setText(+squat);
              userDL.setText(+deadLift);
              userTotal.setText(+total);
              userMileTime.setText(+mileTime);

              String s = ParseUser.getCurrentUser().getUsername();


              main.MaxData(bench,squat,deadLift,total,mileTime,s);

          }
      });

         /*
          *On click method that exits out of max fragment and takes users back to the
          *menu. Gives functionality to the back button.
          */

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
