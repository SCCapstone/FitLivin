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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class PointsPageFragment extends Fragment {


    public PointsPageFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_points_page, container, false);

        final TextView currentPoints = (TextView) v.findViewById(R.id.PointsView);

        ParseQuery queryuser = ParseUser.getQuery();
        queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());

        ParseQuery Points = ParseQuery.getQuery("Points");
        Points.whereExists("CurrentPoints");//setting constraints
        Points.whereMatchesQuery("author", queryuser);

        Points.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        if (e == null && objects.size() != 0) { //if objects size is not 0

                                            if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                                                int x = (Integer) objects.get(objects.size() - 1).get("CurrentPoints");

                                                // main.bench = x;
                                                currentPoints.setText("" +x);


                                            }

                                        }

                                    }


                                }
        );

       /* Integer points;
        //Calls main class
        MainActivity main = new MainActivity();
        // sets points to static points in main class
        points = MainActivity.points;

        main.pointsData(points);
        //sets the text view to points
        currentPoints.setText("" + points);
*/
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



        return v;

    }
}
