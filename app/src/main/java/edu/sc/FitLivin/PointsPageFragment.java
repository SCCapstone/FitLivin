/******
 * Class 'PointsPageFragment'
 *
 * Points will be accumulated for meeting goals. This page displays the points and position of the user.
 * There is a list showing all of the users and their points along with their position. This allows
 * our users to check their status of the the competition.
 *
 */

package edu.sc.FitLivin;

import android.app.Fragment;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PointsPageFragment extends Fragment {

    private String x;
    public PointsPageFragment() {
        // Required empty public constructor
    }

    private File imageFile;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_points_page, container, false);

        getActivity().getActionBar()
                .setTitle("Points");// sets the title of the action bar

       //initializing variables and objects
        MainActivity main = new MainActivity();
        final TextView currentPoints = (TextView) v.findViewById(R.id.PointsView);
        final TextView position = (TextView) v.findViewById(R.id.position);
        final String name1 = ParseUser.getCurrentUser().getUsername();
        final MainActivity main5 = new MainActivity();
        MainActivity main2 = new MainActivity();
        final ArrayList<String> arrayList;
        arrayList = new ArrayList<String>();
        final ArrayList<String> arrayList2;
        arrayList2 = new ArrayList<String>();
        final ArrayList<Double> arrayList3;
        arrayList3 = new ArrayList<Double>();
        final ArrayAdapter<String> adapter;
        final ListView list;
        list = (ListView) v.findViewById(R.id.listView4);

        // creates an adapter for the list
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, android.R.id.text1,arrayList2) {
        // changes view of the list with a color change
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView ListItemShow = (TextView) view.findViewById(android.R.id.text1);
            ListItemShow.setTextColor(Color.parseColor("#ffffff"));
            return view;
            }
        };

        list.setAdapter(adapter);
        adapter.clear();// clears adapter for new addition
        main2.leader = 1;
        // initializing map
        final HashMap<Double, String> myMap = new HashMap<Double, String>();

        //query database for points info
        ParseQuery Points2 = ParseQuery.getQuery("Points");
        Points2.whereExists("CurrentPoints");
        Points2.whereExists("username");//setting constraints
        Points2.orderByDescending("createdAt");// orders data by descending depending on date

        Points2.findInBackground(new FindCallback<ParseObject>() {
                                  // this section uses three arrayList for the data
                                    //sorted by user and points
                                    // they are then put into a map and sent to the list and adapter
                                     public void done(List<ParseObject> objects, ParseException e) {
                                         if (e == null) {
                                             MainActivity main = new MainActivity();
                                             main.leader = 0;
                                             while (main.leader != objects.size() - 1) {

                                                 if (e == null && objects.size() != 0) { //if objects size is not 0

                                                     ParseUser user1 = objects.get(main.leader).getParseUser("author");
                                                     //try catch to see if username exist
                                                     try {
                                                         x = user1.fetchIfNeeded().getUsername();
                                                     } catch (ParseException E) {

                                                     }
                                                     double y = (double) objects.get(main.leader).get("CurrentPoints");
                                                     if (arrayList.contains(x)) {

                                                     } else {
                                                         //adds username and points to list
                                                         //adds attributes to map
                                                         arrayList.add(x);
                                                         arrayList3.add(y);
                                                         myMap.put(y, x);

                                                     }
                                                 }
                                                 main.leader++;
                                             }

                                             Collections.sort(arrayList3);//sorts the arraylist by largest number

                                             Integer place = 1;
                                             //loop goes through arraylist
                                             for (Integer i = arrayList3.size() - 1; i >= 0; i--) {
                                                 Double pts = arrayList3.get(i);
                                                 String uname = myMap.get(pts);
                                                 if (uname.equalsIgnoreCase(name1)) {
                                                     position.setText("" + place);
                                                     int a = (int) Math.round(pts);
                                                     currentPoints.setText("" + a);
                                                 }

                                                 int b = (int) Math.round(pts);

                                                 String padded = uname;
                                                 //organizes data by using padding
                                                 if (place <= 9) {
                                                     String s = "  #" + place + ": " + b + " " + padded;
                                                     arrayList2.add(s);

                                                 } else {
                                                     String s = "#" + place + ": " + b + " " + padded;
                                                     arrayList2.add(s);

                                                 }
                                                 place++;
                                             }

                                             adapter.notifyDataSetChanged();

                                         } else {
                                             Toast.makeText(getActivity(), "You dont have internet!! Call an ISP now!", Toast.LENGTH_SHORT).show();
                                         }
                                     }


                                 }

        );

        return v;

    }


}
