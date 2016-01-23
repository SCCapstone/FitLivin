package edu.sc.FitLivin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


/**
 * Created by pkcho on 1/22/2016.
 * could be useful for graph
 * must be passed in a list
 */
public class customWeightAdapter extends ParseQueryAdapter {

    public customWeightAdapter(Context context, Class clazz) {
        super(context, new QueryFactory<ParseObject>() {
            @Override
            public ParseQuery create() {
                ParseQuery query = ParseQuery.getQuery("ProfileInfo");
                query.whereEqualTo("author", ParseUser.getCurrentUser());
                return query;
            }
        });
    }
    public View getItemView(ParseObject object, View v, ViewGroup parent){
        if (v == null) {
            v = View.inflate(getContext(), R.layout.text_view, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image
        TextView weightText = (TextView) v.findViewById(R.id.texticon);
        ParseObject weightt = object.getParseObject("Weight");
        if (weightt != null) {
            weightText.setText(weightt.toString());
        }

        // Add the title view


        // Add a reminder of how long this item has been outstanding
        return v;

    }
}
