/******
 * Class 'MainActivity'
 *
 * Initializes the Parse Database and first fragment layout. Stores variables
 * for the Database.
 *
 */

package edu.sc.FitLivin;

import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends FragmentActivity{

   //Intializing varibles
    public static String name;
    private FragmentManager fm;
    private FragmentTransaction ft;
    public static Integer weight;
    public static Integer height;
    public static String s;
    public static Integer points = 0;
    private String objectID;

    //Creates a parse object for the database
    //Creates a query object to query through the database
    public static ParseObject profileInfo = new ParseObject("ProfileInfo");
    ParseQuery<ParseObject> query = ParseQuery.getQuery("ProfileInfo");

    public static ParseObject pointsInfo = new ParseObject("Points");
    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Points");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);//enables the database

        //initializes the database store
        Parse.initialize(this, "KkZRFZGjA2I8mKNyuBkqgunMsVCKiWA2YrLsR3w4", "8giIFo0DzUQVHwgsl0HkXzW12n2iGj8kU1vZd90f");

        //initializes the query object for the Profile databse
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ProfileInfo");


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList, ParseException e) {
              //Uses a for loop to look through the database for specific info
                if (e == null) {
                    if (userList.size() > 0) {

                        for (int i = 0; i < userList.size(); i++) {
                            ParseObject p = userList.get(i);
                            name = p.getString("Username");
                            weight = p.getInt("Weight");
                            height = p.getInt("Height");
                        }
                    }

                } else {//else do nothing

                }
            }
        });

        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList2, ParseException e) {

                if (e == null) {
                    if (userList2.size() > 0) {

                        for (int i = 0; i < userList2.size(); i++) {
                            ParseObject p = userList2.get(i);

                            points = p.getInt("CurrentPoints");

                        }
                    }
                    //newText.setText(name);
                } else {

                }
            }
        });
       //Adds the fragment for the layout
       HomePageFragment firstFragment = new HomePageFragment();
       FragmentManager fm1 = getFragmentManager();
       fm1.beginTransaction().add(R.id.container, firstFragment).addToBackStack(null).commit();

    }

    /*****
     *
     * This method will allow for the Profile page to send name,
     * weight,and height to the database.
     *
     */
        public void profileData(String name, Integer weight, Integer height) {
        Log.d("F", "pdata");
            this.name = name;
            this.weight = weight;
            this.height = height;

        // adds info to database
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

    /*****
     *
     * Allows the user to send pints to the database
     *
     */

    public void pointsData(Integer points) {
        Log.d("F", "pdata");
        this.points = points;

        //adds info to database
        pointsInfo.put("CurrentPoints", this.points);

        pointsInfo.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = pointsInfo.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });


    }

    /***
     *
     * Getters and Setters for name, weight, and height
     */

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