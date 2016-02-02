/******
 * Class 'TrackProgressFragment'
 *
 * Provides the user with the ability to Track their progress for BMI,
 * BENCH,SQUAT,DEAD LIFT, AND MILE RUN.
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class TrackProgressFragment extends Fragment {

    private ListView weightList;
    private customWeightAdapter customAdapter;
    final ArrayList weight = new ArrayList<String>();

    public TrackProgressFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_track_progress, container, false);
        //Creates back button to go back to main page
        final GraphView graph = (GraphView) v.findViewById(R.id.graph);



        ParseQuery query2 = ParseQuery.getQuery("ProfileInfo"); //getting query
        query2.whereExists("Weight");//setting constraints
        query2.whereExists("Height");//setting constraints
        query2.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    double start = objects.get(0).get("Weight").hashCode();
                    String starts = objects.get(0).get("Weight").toString();
                    //double end = objects.get(objects.size()).get("Weight").hashCode();
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{//new DataPoint(start,62)
                    });
                    series.appendData(new DataPoint(start,63),false,500);
                    graph.addSeries(series);
                    Log.d(starts, "done: ");
                }
            }


        });




        Button backBtn = (Button) v.findViewById(R.id.TPBack);
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

        weightList = (ListView) v.findViewById(R.id.listView2);

        final ListAdapter adapter;
        ParseQuery query = ParseQuery.getQuery("ProfileInfo"); //getting query
        query.whereExists("Weight");//setting constraints
        query.whereExists("Height");//setting constraints
        query.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                        for (int i = 0; i < objects.size(); i++) {
                            weight.add(objects.get(i).get("Weight").toString());
                        }


                        LineGraphSeries<DataPoint> mSeries1 = new LineGraphSeries<DataPoint>(generateData(weight));
                        mSeries1.setTitle("Weights");
                        graph.addSeries(mSeries1);
                        StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                        label.setHorizontalLabels(new String[]{"old", "middle", "new"});
                        label.setVerticalLabels(new String[]{"low", "middle", "high"});




                        graph.setTitle("Your Porgress");
                        graph.setTitleColor(0xff0000ff);
                       graph.getViewport().setMaxX(weight.size());
                        graph.getViewport().setBackgroundColor(0xffffffff);
                        graph.getLegendRenderer().setVisible(true);
                        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                        graph.setTitleTextSize(10);
                        graph.getViewport().isScrollable();
                        graph.getGridLabelRenderer().setLabelFormatter(label);


                        ArrayAdapter weightArrayAdapter =
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.text_view, weight);
                        weightList.setAdapter(weightArrayAdapter);


                    } else {
                        Toast.makeText(getActivity(),"Did not load",Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
        return v;

    }


    private DataPoint[] generateData(ArrayList<String> weight) {

        DataPoint[] values = new DataPoint[weight.size()];
        for (int i=0; i<weight.size(); i++) {
            double x = i;
            double y = Double.parseDouble(weight.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

}
