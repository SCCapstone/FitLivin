package edu.sc.myapplication;

import android.app.Fragment;
import android.app.ListActivity;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{


    public static String name;

    private FragmentManager fm;
    private FragmentTransaction ft;
    public static Integer weight;
    public static Integer height;
    public static String s;
    private String objectID;




    public static ParseObject profileInfo = new ParseObject("ProfileInfo");
    ParseQuery<ParseObject> query = ParseQuery.getQuery("ProfileInfo");


    //TextView newText = (TextView) findViewById(R.id.textView22);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView newText = (TextView) findViewById(R.id.textView22);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "KkZRFZGjA2I8mKNyuBkqgunMsVCKiWA2YrLsR3w4", "8giIFo0DzUQVHwgsl0HkXzW12n2iGj8kU1vZd90f");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("ProfileInfo");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {

                if (e == null) {
                    if (userList.size() > 0) {

                        for (int i = 0; i < userList.size(); i++) {
                            ParseObject p = userList.get(i);
                            name = p.getString("Username");
                            weight = p.getInt("Weight");
                            height = p.getInt("Height");
                        }
                    }
                           newText.setText(name);
                } else {

                }
            }
        });


      HomePageFragment firstFragment = new HomePageFragment();
       FragmentManager fm1 = getFragmentManager(); //or getFragmentManager() if you are not using support library.
       fm1.beginTransaction().add(R.id.container, firstFragment).addToBackStack(null).commit();

    }
        // Enable Local Datastore.
        public void profileData(String name, Integer weight, Integer height) {
        Log.d("F", "pdata");
            this.name = name;
            this.weight = weight;
            this.height = height;

       profileInfo.put("Username", name);
        profileInfo.put("Weight", weight);
        profileInfo.put("Height", height);
       profileInfo.saveInBackground(new SaveCallback() {
           @Override
           public void done(ParseException e) {
               if (e == null) {
                   objectID = profileInfo.getObjectId();
                   setS(objectID);
               } else {
                   Log.d("F", "object failllll");
               }
           }
       });


    }


public String getName(){
    return name;
}
    public void setName(String setName){
        this.name = setName;
    }
    public Integer getWeight(){
        return weight;
    }
    public void setWeight(Integer setWeight){
        this.weight = setWeight;
    }
    public Integer getHeight(){
        return height;
    }
    public void setHeight(Integer setHeight){
        this.height = setHeight;
    }
    public String getS(){
        return objectID;
    }
    public void setS(String sn){
        this.objectID = sn;
    }

}
