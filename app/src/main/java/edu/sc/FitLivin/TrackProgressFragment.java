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

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrackProgressFragment extends Fragment {

    private ListView weightList;

    private customWeightAdapter customAdapter;
    final ArrayList weight = new ArrayList<String>();
    final ArrayList dates = new ArrayList<Date>();

    public TrackProgressFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_track_progress, container, false);
        //Creates back button to go back to main page
        final GraphView graph = (GraphView) v.findViewById(R.id.graph);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " €";
                }
            }
        });

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
                            dates.add(objects.get(i).getCreatedAt());
                            Log.d(dates.get(i).toString(), "done: ");
                        }


                        LineGraphSeries<DataPoint> mSeries1 = new LineGraphSeries<DataPoint>(generatenewData(weight));
                        LineGraphSeries<DataPoint> mSeries2 = new LineGraphSeries<DataPoint>(generateData(weight,dates));
                        mSeries2.setTitle("Dates");
                        mSeries1.setTitle("Weights");
                        mSeries1.setThickness(7);
                        mSeries2.setThickness(7);
                        mSeries1.isDrawDataPoints();
                        graph.addSeries(mSeries1);
                        graph.addSeries(mSeries2);
                        StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                        label.setDynamicLabelFormatter(new LabelFormatter() {
                            @Override
                            public String formatLabel(double value, boolean isValueX) {
                                if (isValueX) {
                                    // show normal x values
                                    return formatLabel(value, isValueX);
                                } else {
                                    // show currency for y values
                                    return formatLabel(value, isValueX) + " €";
                                }
                            }

                            @Override
                            public void setViewport(Viewport viewport) {
                                    graph.getViewport();
                            }
                        });
                        label.setHorizontalLabels(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "Novermber", "December"});
                        label.setVerticalLabels(new String[]{"low", "middle", "high"});




                        graph.setTitle("Your Progress");
                        graph.setTitleTextSize(15);

                        graph.setTitleColor(0xffffffff);
                       graph.getViewport().setMaxX(weight.size());
                        graph.getViewport().setBackgroundColor(0xffffffff);
                        graph.getLegendRenderer().setVisible(true);
                        graph.setHorizontalScrollBarEnabled(true);
                        graph.scrollTo(1, 1);
                        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                        graph.setTitleTextSize(10);
                        graph.getViewport().setMaxX(weight.size());
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


    private DataPoint[] generateData(ArrayList<String> weight,ArrayList<Date> dates) {

        DataPoint[] values = new DataPoint[weight.size()];
        for (int i=0; i<weight.size(); i++) {

            double x =  dates.get(i).getDate();
            Log.d(String.valueOf(dates.get(i).getDate()), "here is the time: ");
            double y =  Double.parseDouble(weight.get(i))/2.2;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;

        }
        return values;
    }
    private DataPoint[] generatenewData(ArrayList<String> weight) {

        DataPoint[] values = new DataPoint[weight.size()];
        for (int i=0; i<weight.size(); i++) {

            double x =  i;

            double y =  Double.parseDouble(weight.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;

        }
        return values;
    }

}
