package edu.sc.FitLivin;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

import edu.sc.FitLivin.R;

public class PointsLeader extends Fragment {


    public PointsLeader() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_points_leader, container, false);

        getActivity().getActionBar()
                .setTitle("Leaderboard");

        final ArrayList<String> arrayList;
        arrayList = new ArrayList<String>();
        final ArrayAdapter<String> adapter;
        ListView list;
        list = (ListView) v.findViewById(R.id.listView3);
        final TextView currentPoints = (TextView) v.findViewById(R.id.leader);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrayList);

        list.setAdapter(adapter);
       MainActivity main = new MainActivity();
        main.leader = 1;


        ParseQuery Points = ParseQuery.getQuery("Points");
        Points.whereExists("CurrentPoints");
        Points.whereExists("username");//setting constraints
       Points.orderByDescending("createdAt");

        Points.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {
                                        MainActivity main = new MainActivity();
                                        main.leader = 0;
                                        while (main.leader != objects.size() - 1) {

                                            if (e == null && objects.size() != 0) { //if objects size is not 0
                                                String x = (String) objects.get(main.leader).get("username");
                                                if (arrayList.contains(x)) {

                                                } else {
                                                    arrayList.add(x);
                                                    adapter.notifyDataSetChanged();
                                                }


                                            }
                                            main.leader++;
                                        }
                                    }
                                }
        );
        return v;
    }


}
