/******
 * Class 'PointsPageFragment'
 *
 * Points will be accumulated for meeting goals. This page displays the points.
 *
 */

package edu.sc.FitLivin;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
                .setTitle("Points");



        final TextView currentPoints = (TextView) v.findViewById(R.id.PointsView);
        final TextView position = (TextView) v.findViewById(R.id.position);
        final String name1 = ParseUser.getCurrentUser().getUsername();
        final MainActivity main5 = new MainActivity();




              //Creates back button to go back to home page

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


        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList2);

        list.setAdapter(adapter);
        adapter.clear();
        //MainActivity main = new MainActivity();
        main2.leader = 1;

        final HashMap<Double, String> myMap = new HashMap<Double, String>();
        ParseQuery Points2 = ParseQuery.getQuery("Points");
        Points2.whereExists("CurrentPoints");
        Points2.whereExists("username");//setting constraints
        Points2.orderByDescending("createdAt");

        Points2.findInBackground(new FindCallback<ParseObject>() {
                                     public void done(List<ParseObject> objects, ParseException e) {
                                         MainActivity main = new MainActivity();
                                         main.leader = 0;
                                         while (main.leader != objects.size() - 1) {

                                             if (e == null && objects.size() != 0) { //if objects size is not 0

                                                 ParseUser user1 = objects.get(main.leader).getParseUser("author");
                                                 try {
                                                     x = user1.fetchIfNeeded().getUsername();
                                                 } catch (ParseException E) {

                                                 }
                                                 Double y = (Double) objects.get(main.leader).get("CurrentPoints");

                                                 if (arrayList.contains(x)) {
                                                     System.out.println("Sign up points3 " + y);
                                                 } else {
                                                     arrayList.add(x);
                                                     arrayList3.add(y);
                                                     myMap.put(y, x);
                                                     // adapter.notifyDataSetChanged();

                                                 }


                                             }
                                             main.leader++;
                                         }


                                         Collections.sort(arrayList3);

                                         // String str = new String(charArray);
                                         Integer place = 1;
                                         for (Integer i = arrayList3.size() - 1; i >= 0; i--) {
                                             Double pts = arrayList3.get(i);
                                             String uname = myMap.get(pts);
                                             if (uname.equalsIgnoreCase(name1)) {
                                                 position.setText("" + place);
                                                 int a = (int) Math.round(pts);
                                                 currentPoints.setText("" + a);
                                             }
                                             // myMap.remove(pts);
                                             uname += " ";
                                             Integer space = 19 - uname.length();
                                             int b = (int) Math.round(pts);
                                             for (Integer y = 0; y < space; y++) {
                                                 uname += ".";
                                             }
                                             // System.out.println(uname);
                                             String padded = uname;
                                             if (place <= 9) {
                                                 String s = "  " + place + ".) " + padded + " " + b;
                                                 //System.out.println(s);
                                                 arrayList2.add(s);
                                             } else {
                                                 String s = place + ".) " + padded + " " + b;
                                                 //System.out.println(s);
                                                 arrayList2.add(s);
                                             }
                                             place++;
                                         }

                                         adapter.notifyDataSetChanged();

                                     }

                                 }

        );

        return v;

    }


}
