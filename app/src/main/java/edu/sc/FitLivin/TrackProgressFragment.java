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
    final ArrayList maxweight = new ArrayList<String>();
    final ArrayList maxsquats = new ArrayList<String>();
    final ArrayList maxbench = new ArrayList<String>();
    final ArrayList maxdeadlift = new ArrayList<String>();
    final ArrayList maxmiletime = new ArrayList<String>();
    ArrayAdapter<CharSequence> spinnerAdapter;

    public TrackProgressFragment() {

    }


    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_track_progress, container, false);
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
    private DataPoint[] WeightvsDategenerator(ArrayList<String> weight) {

        DataPoint[] values = new DataPoint[weight.size()];

            for (int i = 0; i < weight.size(); i++) {

                double x = i;

                double y = Double.parseDouble(weight.get(i));
                DataPoint v = new DataPoint(x, y);
                values[i] = v;

            }

        return values;
    }
    public void openAnotherGraph(int position){
        if (position == 0){
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


                            LineGraphSeries<DataPoint> mSeries1 = new LineGraphSeries<DataPoint>(WeightvsDategenerator(weight));
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
            Toast.makeText(getActivity(),"You selected this position: "+ position,Toast.LENGTH_SHORT).show();
        }
    }


}
