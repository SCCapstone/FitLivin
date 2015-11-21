package edu.sc.myapplication;

import android.app.Fragment;
import android.app.ListActivity;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{


    public static String name = "Rsand";
    private FragmentManager fm;
    private FragmentTransaction ft;
    public static Integer weight;
    public static Integer height;

    public static ParseObject profileInfo = new ParseObject("ProfileInfo");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "KkZRFZGjA2I8mKNyuBkqgunMsVCKiWA2YrLsR3w4", "8giIFo0DzUQVHwgsl0HkXzW12n2iGj8kU1vZd90f");

        final ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo22", "bar22");
       // testObject.put()
        testObject.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
        String objectID = testObject.getObjectId();
        query.getInBackground(objectID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                   // String name = testObject.getString("foo22");
                    //TextView newText = (TextView) findViewById(R.id.textView14);
                    //newText.setText(name);
                } else {


                }
            }

        });




       // ParseObject gameScore = new ParseObject("GameScore");
      //  gameScore.put("score", 1337);
       // gameScore.put("playerName", "Sean Plott");
       // gameScore.put("cheatMode", false);
      //  gameScore.saveInBackground();

        //Put fragment in container

       HomePageFragment firstFragment = new HomePageFragment();
      FragmentManager fm1 = getFragmentManager(); //or getFragmentManager() if you are not using support library.
      fm1.beginTransaction().add(R.id.container, firstFragment).addToBackStack(null).commit();
    }

    //  }

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

}
