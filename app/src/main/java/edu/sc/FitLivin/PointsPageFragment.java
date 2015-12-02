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

public class PointsPageFragment extends Fragment {

    public PointsPageFragment() {
        // Required empty public constructor
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_points_page, container, false);

        final TextView currrentPoints = (TextView) v.findViewById(R.id.PointsView);

       // Integer points = MainActivity.points;
        Integer points;

        //currrentPoints.setText("" + points);

        MainActivity main = new MainActivity();

        points = MainActivity.points;

        main.pointsData(points);
        currrentPoints.setText("" + points);

        Button backBtn = (Button) v.findViewById(R.id.PointsBack);
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
