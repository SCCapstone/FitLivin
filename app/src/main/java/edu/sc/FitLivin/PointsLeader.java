package edu.sc.FitLivin;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.sc.FitLivin.R;

public class PointsLeader extends Fragment {

    MainActivity main = new MainActivity();
    Integer points;
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
        final ArrayList<String> arrayList2;
        arrayList2 = new ArrayList<String>();
        final ArrayList<Integer> arrayList3;
        arrayList3 = new ArrayList<Integer>();
        final ArrayAdapter<String> adapter;
        ListView list;
        list = (ListView) v.findViewById(R.id.listView3);
        final TextView currentPoints = (TextView) v.findViewById(R.id.leader);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList2);

        list.setAdapter(adapter);
        //MainActivity main = new MainActivity();

//




        return v;
    }




}
