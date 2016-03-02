/******
 * Class 'TrackProgressFragment'
 *
 * Provides the user with the ability to Track their progress for BMI,
 * BENCH,SQUAT,DEAD LIFT, AND MILE RUN.
 *
 */

package edu.sc.FitLivin;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
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
    private GraphView graph;
    private customWeightAdapter customAdapter;
    final ArrayList weight = new ArrayList<String>();
    final ArrayList dates = new ArrayList<Date>();
    final ArrayList maxBenchweight = new ArrayList<String>();
    final ArrayList maxsquats = new ArrayList<String>();
    final ArrayList maxdeadlift = new ArrayList<String>();
    final ArrayList maxmiletime = new ArrayList<String>();
    final ArrayList weightGoal = new ArrayList<String>();
    final ArrayList squatgoal = new ArrayList<String>();
    ArrayAdapter<CharSequence> spinnerAdapter;

    public TrackProgressFragment() {

    }


    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ParseQuery userquery = ParseUser.getQuery();
        userquery.whereEqualTo("objectId",ParseUser.getCurrentUser().getObjectId());
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_track_progress, container, false);
        getActivity().getActionBar()
                .setTitle("Track Progress");
        //Creates back button to go back to main page
       graph = (GraphView) v.findViewById(R.id.graph);

        final Spinner gspinner = (Spinner) v.findViewById(R.id.spinner_dropdown);
        gspinner.setPrompt("Select your graph");
        spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.graphspinner,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gspinner.setAdapter(spinnerAdapter);
        gspinner.setBackgroundColor(Color.LTGRAY);
      gspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              openAnotherGraph(position);
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });




        weightList = (ListView) v.findViewById(R.id.listView2);


        final ListAdapter adapter;

        return v;

    }


    public void openAnotherGraph(int position){
        if (position == 0){
            weight.clear();
            weightList.setAdapter(null);
            graph.removeAllSeries();
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


                            LineGraphSeries<DataPoint> mSeries1 = new LineGraphSeries<DataPoint>(Datagenerator(weight));
                            LineGraphSeries<DataPoint> mSeries2 = new LineGraphSeries<DataPoint>(WeightGenerator(weight, dates));
                            mSeries2.setTitle("Dates");
                            mSeries1.setTitle("Weights");
                            mSeries1.setThickness(7);
                            mSeries2.setThickness(7);
                            mSeries2.setColor(Color.YELLOW);
                            mSeries1.setColor(Color.RED);
                            mSeries1.isDrawDataPoints();
                            mSeries1.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getActivity(), "this is the data point: " + dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });
                            mSeries2.setDrawDataPoints(true);
                            mSeries1.setDrawDataPoints(true);


                            graph.addSeries(mSeries1);
                            // graph.addSeries(mSeries2);


                            StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                            label.setHorizontalLabels(new String[]{"0", "Jan", "Feb", "March"});
                            label.setVerticalLabels(new String[]{"0", "100", "150", "200", "250"});
                           /* graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(getActivity(),dates.get()) {
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

*/

                            graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setLabelsSpace(2);

                            graph.getViewport().setMaxX(weight.size());
                            graph.getGridLabelRenderer().setGridColor(Color.WHITE);
                            graph.getViewport().setScrollable(true);
                            graph.getViewport().setScrollable(true);
                            graph.getLegendRenderer().isVisible();
                            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                            graph.getLegendRenderer().setBackgroundColor(Color.WHITE);

                            graph.getViewport().setMaxX(weight.size());

                            graph.getGridLabelRenderer().setLabelFormatter(label);
                            graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setGridColor(Color.GRAY);


                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, weight);
                            weightList.setAdapter(weightArrayAdapter);


                        } else {
                            Toast.makeText(getActivity(), "Did not load", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            });

        }
        else if (position == 1){
            maxBenchweight.clear();
            weightList.setAdapter(null);
            graph.removeAllSeries();
            ParseQuery query = ParseQuery.getQuery("MaxBench"); //getting query
            ParseQuery userquery = ParseUser.getQuery();
            userquery.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
            query.whereMatchesQuery("author",userquery);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null && objects.size() != 0) { //if objects size is not 0

                        if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {
                            for (int i = 0; i < objects.size(); i++) {
                                maxBenchweight.add(objects.get(i).get("MaxBench").toString());
                                dates.add(objects.get(i).getCreatedAt());
                                Log.d(dates.get(i).toString(), "done: ");
                            }
                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, maxBenchweight);
                            weightList.setAdapter(weightArrayAdapter);


                            LineGraphSeries<DataPoint> mSeries3 = new LineGraphSeries<DataPoint>(Datagenerator(maxBenchweight));
                            LineGraphSeries<DataPoint> mSeries4 = new LineGraphSeries<DataPoint>(WeightGenerator(weight, dates));
                            mSeries3.setTitle("Max bench");
                            mSeries3.setThickness(7);
                            mSeries4.setThickness(7);
                            mSeries3.setColor(Color.YELLOW);
                            mSeries4.setColor(Color.RED);
                            mSeries3.isDrawDataPoints();
                            mSeries3.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getActivity(), "this is the data point: " + dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });
                            mSeries4.setDrawDataPoints(true);
                            mSeries3.setDrawDataPoints(true);


                            graph.addSeries(mSeries3);
                            // graph.addSeries(mSeries2);


                            StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                            label.setHorizontalLabels(new String[]{"0", "Jan", "Feb", "March"});
                            label.setVerticalLabels(new String[]{"0", "100", "150", "200", "250"});


                            graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setLabelsSpace(2);

                            graph.getViewport().setMaxX(maxBenchweight.size());
                            graph.getGridLabelRenderer().setGridColor(Color.WHITE);
                            graph.getViewport().setScrollable(true);
                            graph.getViewport().setScrollable(true);
                            graph.getLegendRenderer().isVisible();
                            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                            graph.getLegendRenderer().setBackgroundColor(Color.WHITE);

                            graph.getViewport().setMaxX(maxBenchweight.size());

                            graph.getGridLabelRenderer().setLabelFormatter(label);
                            graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setGridColor(Color.GRAY);





                        } else {
                            Toast.makeText(getActivity(), "Did not load", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            });
        }
        else if (position == 2){
            maxsquats.clear();
            weightList.setAdapter(null);
            graph.removeAllSeries();
            ParseQuery query = ParseQuery.getQuery("MaxSquat"); //getting query
            ParseQuery userquery = ParseUser.getQuery();
            userquery.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
            query.whereMatchesQuery("author",userquery);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null && objects.size() != 0) { //if objects size is not 0

                        if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {
                            for (int i = 0; i < objects.size(); i++) {
                                maxsquats.add(objects.get(i).get("MaxSquat").toString());
                                dates.add(objects.get(i).getCreatedAt());
                                Log.d(dates.get(i).toString(), "done: ");
                            }
                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, maxsquats);
                            weightList.setAdapter(weightArrayAdapter);


                            LineGraphSeries<DataPoint> mSeries3 = new LineGraphSeries<DataPoint>(Datagenerator(maxsquats));
                            LineGraphSeries<DataPoint> mSeries4 = new LineGraphSeries<DataPoint>(WeightGenerator(weight, dates));
                            mSeries3.setTitle("Max bench");
                            mSeries3.setThickness(7);
                            mSeries4.setThickness(7);
                            mSeries3.setColor(Color.CYAN);
                            mSeries4.setColor(Color.RED);
                            mSeries3.isDrawDataPoints();
                            mSeries3.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getActivity(), "this is the data point: " + dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });
                            mSeries4.setDrawDataPoints(true);
                            mSeries3.setDrawDataPoints(true);


                            graph.addSeries(mSeries3);
                            // graph.addSeries(mSeries2);


                            StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                            label.setHorizontalLabels(new String[]{"0", "Jan", "Feb", "March"});
                            label.setVerticalLabels(new String[]{"0", "100", "150", "200", "250"});


                            graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setLabelsSpace(2);

                            graph.getViewport().setMaxX(maxsquats.size());
                            graph.getGridLabelRenderer().setGridColor(Color.WHITE);
                            graph.getViewport().setScrollable(true);
                            graph.getViewport().setScrollable(true);
                            graph.getLegendRenderer().isVisible();
                            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                            graph.getLegendRenderer().setBackgroundColor(Color.WHITE);

                            graph.getViewport().setMaxX(maxsquats.size());

                            graph.getGridLabelRenderer().setLabelFormatter(label);
                            graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setGridColor(Color.GRAY);





                        } else {
                            Toast.makeText(getActivity(), "Did not load", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            });
        }
        else if(position ==3){
            maxdeadlift.clear();
            weightList.setAdapter(null);
            graph.removeAllSeries();
            ParseQuery query = ParseQuery.getQuery("MaxDeadLift"); //getting query
            ParseQuery userquery = ParseUser.getQuery();
            userquery.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
            query.whereMatchesQuery("author",userquery);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null && objects.size() != 0) { //if objects size is not 0

                        if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {
                            for (int i = 0; i < objects.size(); i++) {
                                maxdeadlift.add(objects.get(i).get("MaxDeadLift").toString());
                                dates.add(objects.get(i).getCreatedAt());
                                Log.d(dates.get(i).toString(), "done: ");
                            }
                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, maxdeadlift);
                            weightList.setAdapter(weightArrayAdapter);


                            LineGraphSeries<DataPoint> mSeries3 = new LineGraphSeries<DataPoint>(Datagenerator(maxdeadlift));
                            LineGraphSeries<DataPoint> mSeries4 = new LineGraphSeries<DataPoint>(WeightGenerator(weight, dates));
                            mSeries3.setTitle("Max Deadlift");
                            mSeries3.setThickness(7);
                            mSeries4.setThickness(7);
                            mSeries3.setColor(Color.BLUE);
                            mSeries4.setColor(Color.RED);
                            mSeries3.isDrawDataPoints();
                            mSeries3.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getActivity(), "this is the data point: " + dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });
                            mSeries4.setDrawDataPoints(true);
                            mSeries3.setDrawDataPoints(true);


                            graph.addSeries(mSeries3);
                            // graph.addSeries(mSeries2);


                            StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                            label.setHorizontalLabels(new String[]{"0", "Jan", "Feb", "March"});
                            label.setVerticalLabels(new String[]{"0", "100", "150", "200", "250"});


                            graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setLabelsSpace(2);

                            graph.getViewport().setMaxX(maxdeadlift.size());
                            graph.getGridLabelRenderer().setGridColor(Color.WHITE);
                            graph.getViewport().setScrollable(true);
                            graph.getViewport().setScrollable(true);
                            graph.getLegendRenderer().isVisible();
                            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                            graph.getLegendRenderer().setBackgroundColor(Color.WHITE);

                            graph.getViewport().setMaxX(maxdeadlift.size());

                            graph.getGridLabelRenderer().setLabelFormatter(label);
                            graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setGridColor(Color.GRAY);





                        } else {
                            Toast.makeText(getActivity(), "Did not load", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            });

        }
        else if(position ==4){
            maxmiletime.clear();
            weightList.setAdapter(null);
            graph.removeAllSeries();
            ParseQuery query = ParseQuery.getQuery("MaxMileTime"); //getting query
            ParseQuery userquery = ParseUser.getQuery();
            userquery.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
            query.whereMatchesQuery("author",userquery);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null && objects.size() != 0) { //if objects size is not 0

                        if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {
                            for (int i = 0; i < objects.size(); i++) {
                                maxmiletime.add(objects.get(i).get("MaxMileTime").toString());
                                dates.add(objects.get(i).getCreatedAt());
                                Log.d(dates.get(i).toString(), "done: ");
                            }
                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, maxmiletime);
                            weightList.setAdapter(weightArrayAdapter);


                            LineGraphSeries<DataPoint> mSeries3 = new LineGraphSeries<DataPoint>(Datagenerator(maxmiletime));
                            LineGraphSeries<DataPoint> mSeries4 = new LineGraphSeries<DataPoint>(WeightGenerator(weight, dates));
                            mSeries3.setTitle("Max Mile Time");
                            mSeries3.setThickness(7);
                            mSeries4.setThickness(7);
                            mSeries3.setColor(Color.GREEN);
                            mSeries4.setColor(Color.RED);
                            mSeries3.isDrawDataPoints();
                            mSeries3.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getActivity(), "this is the data point: " + dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });
                            mSeries4.setDrawDataPoints(true);
                            mSeries3.setDrawDataPoints(true);


                            graph.addSeries(mSeries3);
                            // graph.addSeries(mSeries2);


                            StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                            label.setHorizontalLabels(new String[]{"0", "Jan", "Feb", "March"});
                            label.setVerticalLabels(new String[]{"0", "100", "150", "200", "250"});


                            graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
                            graph.getGridLabelRenderer().setLabelsSpace(2);

                            graph.getViewport().setMaxX(maxmiletime.size());
                            graph.getGridLabelRenderer().setGridColor(Color.WHITE);
                            graph.getViewport().setScrollable(true);
                            graph.getViewport().setScrollable(true);
                            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                            graph.getLegendRenderer().setBackgroundColor(Color.WHITE);

                            graph.getViewport().setMaxX(maxmiletime.size());

                            graph.getGridLabelRenderer().setLabelFormatter(label);
                            graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                            graph.getGridLabelRenderer().setGridColor(Color.GRAY);





                        } else {
                            Toast.makeText(getActivity(), "Did not load", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            });
        }
        else if(position ==5){

        }
        else if(position ==6){

        }
    }

    private DataPoint[] WeightGenerator(ArrayList<String> weight, ArrayList<Date> dates) {

        DataPoint[] values = new DataPoint[weight.size()];


        for (int i = 0; i < weight.size(); i++) {

            double x = dates.get(i).getMonth();
            Log.d(String.valueOf(dates.get(i).getDate()), "here is the time: ");
            double y = Double.parseDouble(weight.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;

        }
        return values;
    }
    private DataPoint[] Datagenerator(ArrayList<String> weight) {

        DataPoint[] values = new DataPoint[weight.size()];

        for (int i = 0; i < weight.size(); i++) {

            double x = i;

            double y = Double.parseDouble(weight.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;

        }

        return values;
    }







}
