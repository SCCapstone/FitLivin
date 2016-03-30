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

import com.jjoe64.graphview.DefaultLabelFormatter;
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
import java.util.HashMap;
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

      /**  Button backBtn = (Button) v.findViewById(R.id.trackprogressBack);
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
        });**/

        getActivity().getActionBar()
                .setTitle("Track Progress");

        final ArrayList<String> arrayList2;
        arrayList2 = new ArrayList<String>();
        final ArrayList<String> arrayList3;
        arrayList3 = new ArrayList<String>();
        final ArrayList<String> arrayList5;
        arrayList5 = new ArrayList<String>();
        final ArrayAdapter<String> adapter1;
        final ListView list;
        list = (ListView) v.findViewById(R.id.listView3);


        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList5);
        list.setAdapter(adapter1);
        adapter1.clear();



       // final HashMap<String, String> myMap = new HashMap<String, String>();
        ParseQuery workout2 = ParseQuery.getQuery("WorkoutProgress");
        workout2.whereExists("workout");
        workout2.whereExists("date");//setting constraints
        workout2.orderByDescending("createdAt");

        workout2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                MainActivity main = new MainActivity();
                main.leader = 0;
                while (main.leader != objects.size() - 1) {

                    if (e == null && objects.size() != 0) { //if objects size is not 0
                        ParseUser curruser = ParseUser.getCurrentUser();
                        ParseUser user1 = objects.get(main.leader).getParseUser("author");

                        String w = (String) objects.get(main.leader).get("workout");
                        String d = (String) objects.get(main.leader).get("date");
                        if (user1 == curruser) {
                            System.out.println("workout test");
                            arrayList2.add(w);
                            arrayList3.add(d);
                            // adapter.notifyDataSetChanged();

                        }


                    }
                    main.leader++;
                }
                for (Integer i = 0; i < arrayList2.size()-1; i++) {
                    String data = (arrayList2.get(i)+" " + " "+arrayList3.get(i));
                    System.out.println(arrayList2.get(i)+" " + " "+arrayList3.get(i));
                    arrayList5.add(data);
                    System.out.println("size " + arrayList5.size());
                }
                adapter1.notifyDataSetChanged();
            }});
        //Creates back button to go back to main page
       graph = (GraphView) v.findViewById(R.id.graph);

        final Spinner gspinner = (Spinner) v.findViewById(R.id.spinner_dropdown);
        gspinner.setPrompt("Select your graph");
        spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.graphspinner,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gspinner.setAdapter(spinnerAdapter);
        gspinner.setBackgroundColor(Color.CYAN);
      gspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              openAnotherGraph(position);
              if(position == 0){
                  gspinner.setBackgroundColor(Color.YELLOW);
              }
              else if(position == 1){
                  gspinner.setBackgroundColor(Color.RED);
              }
              else if(position ==2){
                  gspinner.setBackgroundColor(Color.CYAN);
              }
              else if(position == 3){
                  gspinner.setBackgroundColor(Color.MAGENTA);
              }
              else if(position ==4 ){
                  gspinner.setBackgroundColor(Color.GREEN);
              }
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
                                weight.add(objects.get(objects.size()-1).get("Weight").toString());
                                dates.add(objects.get(objects.size()-1).getCreatedAt().getMonth());

                                Log.d(dates.get(i).toString(), "done: ");
                            }

                            if(weight.size() < 2){
                                Toast.makeText(getActivity().getApplicationContext(),"You need to add more data to graph",Toast.LENGTH_LONG).show();
                            }
                            else {


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


                                //graph.addSeries(mSeries1);
                                graph.addSeries(mSeries2);


                                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                                    @Override
                                    public String formatLabel(double value, boolean isValueX) {
                                        if (isValueX) {
                                            //for (int i = 0; i < dates.size(); i++) {
                                            //value = Double.parseDouble(dates.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            // }


                                        } else {

                                            //for (int i = 0; i < weight.size(); i++) {
                                            //value =  Double.parseDouble(weight.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            //}


                                        }

                                    }
                                });

                                if (weight.size() == 1) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"0", "Jan"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (weight.size() == 2) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (weight.size() == 3) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (weight.size() == 4) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March", "April"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                }

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

                                graph.getGridLabelRenderer().setGridColor(Color.WHITE);
                                graph.getViewport().setScrollable(true);
                                graph.getViewport().setScrollable(true);
                                graph.getLegendRenderer().isVisible();
                                graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                                graph.getLegendRenderer().setBackgroundColor(Color.WHITE);
                                graph.getGridLabelRenderer().setLabelHorizontalHeight(weight.size());
                                graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setGridColor(Color.GRAY);
                            }

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
                                maxBenchweight.add(objects.get(objects.size()-1).get("MaxBench").toString());
                                dates.add(objects.get(objects.size()-1).getCreatedAt());
                                Log.d(dates.get(i).toString(), "done: ");
                            }
                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, maxBenchweight);
                            weightList.setAdapter(weightArrayAdapter);

                            if(maxBenchweight.size() < 2){
                                Toast.makeText(getActivity().getApplicationContext(),"You need to add more data to graph",Toast.LENGTH_LONG).show();
                            }
                            else {

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


                                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                                    @Override
                                    public String formatLabel(double value, boolean isValueX) {
                                        if (isValueX) {
                                            //for (int i = 0; i < dates.size(); i++) {
                                            //value = Double.parseDouble(dates.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            // }


                                        } else {

                                            //for (int i = 0; i < weight.size(); i++) {
                                            //value =  Double.parseDouble(weight.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            //}


                                        }

                                    }
                                });

                                if (maxBenchweight.size() == 1) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"0", "Jan"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxBenchweight.size() == 2) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxBenchweight.size() == 3) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxBenchweight.size() == 4) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March", "April"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                }


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


                                graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setGridColor(Color.GRAY);


                            }


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
                                maxsquats.add(objects.get(objects.size()-1).get("MaxSquat").toString());
                                dates.add(objects.get(objects.size()-1).getCreatedAt());
                                Log.d(dates.get(i).toString(), "done: ");
                            }
                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, maxsquats);
                            weightList.setAdapter(weightArrayAdapter);

                            if(maxsquats.size() < 2){
                                Toast.makeText(getActivity().getApplicationContext(),"You need to add more data to graph",Toast.LENGTH_LONG).show();
                            }
                            else {
                                LineGraphSeries<DataPoint> mSeries3 = new LineGraphSeries<DataPoint>(Datagenerator(maxsquats));
                                LineGraphSeries<DataPoint> mSeries4 = new LineGraphSeries<DataPoint>(WeightGenerator(weight, dates));
                                mSeries3.setTitle("Max squats");
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
                                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                                    @Override
                                    public String formatLabel(double value, boolean isValueX) {
                                        if (isValueX) {
                                            //for (int i = 0; i < dates.size(); i++) {
                                            //value = Double.parseDouble(dates.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            // }


                                        } else {

                                            //for (int i = 0; i < weight.size(); i++) {
                                            //value =  Double.parseDouble(weight.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            //}


                                        }

                                    }
                                });

                                if (maxsquats.size() == 1) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"0", "Jan"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxsquats.size() == 2) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxsquats.size() == 3) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxsquats.size() == 4) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March", "April"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                }



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

                                graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setGridColor(Color.GRAY);


                            }

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
                                maxdeadlift.add(objects.get(objects.size()-1).get("MaxDeadLift").toString());
                                dates.add(objects.get(objects.size()-1).getCreatedAt());
                                Log.d(dates.get(i).toString(), "done: ");
                            }
                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, maxdeadlift);
                            weightList.setAdapter(weightArrayAdapter);
                            if(maxdeadlift.size() < 2){
                                Toast.makeText(getActivity().getApplicationContext(),"You need to add more data to graph",Toast.LENGTH_LONG).show();
                            }
                            else {

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
                                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                                    @Override
                                    public String formatLabel(double value, boolean isValueX) {
                                        if (isValueX) {
                                            //for (int i = 0; i < dates.size(); i++) {
                                            //value = Double.parseDouble(dates.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            // }


                                        } else {

                                            //for (int i = 0; i < weight.size(); i++) {
                                            //value =  Double.parseDouble(weight.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            //}


                                        }

                                    }
                                });

                                if (maxdeadlift.size() == 1) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"0", "Jan"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxdeadlift.size() == 2) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxdeadlift.size() == 3) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxdeadlift.size() == 4) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March", "April"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                }


                                graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.WHITE);
                                graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);



                                graph.getGridLabelRenderer().setGridColor(Color.WHITE);
                                graph.getViewport().setScrollable(true);
                                graph.getViewport().setScrollable(true);
                                graph.getLegendRenderer().isVisible();
                                graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);



                                graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setGridColor(Color.GRAY);


                            }

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
                                maxmiletime.add(objects.get(objects.size()-1).get("MaxMileTime").toString());
                                dates.add(objects.get(objects.size()-1).getCreatedAt());
                                Log.d(dates.get(i).toString(), "done: ");
                            }
                            ArrayAdapter weightArrayAdapter =
                                    new ArrayAdapter<String>(getActivity(),
                                            R.layout.text_view, maxmiletime);
                            weightList.setAdapter(weightArrayAdapter);
                            if(maxmiletime.size() < 2){
                                Toast.makeText(getActivity().getApplicationContext(),"You need to add more data to graph",Toast.LENGTH_LONG).show();
                            }
                            else {

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
                                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                                    @Override
                                    public String formatLabel(double value, boolean isValueX) {
                                        if (isValueX) {
                                            //for (int i = 0; i < dates.size(); i++) {
                                            //value = Double.parseDouble(dates.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            // }


                                        } else {

                                            //for (int i = 0; i < weight.size(); i++) {
                                            //value =  Double.parseDouble(weight.get(i).toString());
                                            return super.formatLabel(value, isValueX);
                                            //}


                                        }

                                    }
                                });

                                if (maxmiletime.size() == 1) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"0", "Jan"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxmiletime.size() == 2) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxmiletime.size() == 3) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                } else if (maxmiletime.size() == 4) {
                                    StaticLabelsFormatter label = new StaticLabelsFormatter(graph);
                                    label.setHorizontalLabels(new String[]{"Jan", "Feb", "March", "April"});
                                    graph.getGridLabelRenderer().setLabelFormatter(label);
                                }



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


                                graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                                graph.getGridLabelRenderer().setGridColor(Color.GRAY);


                            }


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
            double x = i;
            //double x = Double.parseDouble(dates.get(i).toString());
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
