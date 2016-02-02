/******
 * Class 'PointsPageFragment'
 *
 * Points will be accumulated for meeting goals. This page displays the points.
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
import android.widget.TextView;

public class PointsPageFragment extends Fragment {

    public PointsPageFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_points_page, container, false);

        final TextView currentPoints = (TextView) v.findViewById(R.id.PointsView);

        Integer points;
        //Calls main class
        MainActivity main = new MainActivity();
        // sets points to static points in main class
        points = MainActivity.points;

        main.pointsData(points);
        //sets the text view to points
        currentPoints.setText("" + points);

        //Creates back button to go back to home page
        Button backBtn = (Button) v.findViewById(R.id.PointsBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageFragment fragment1 = new HomePageFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);//replaces fragment with previous
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        String regex = "-?\\d+(\\.\\d+)?";

        if (points.toString().matches(regex))  {
            Log.i("points value", "Numeric value received. Test successful");
            //flag=1;
        } else {
            //flag=-1;
            Log.i("points value", "Non Numeric. Something is wrong.");
        }

        return v;

    }
}
