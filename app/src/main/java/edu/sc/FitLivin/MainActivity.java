/******
 * Class 'MainActivity'
 *
 * Initializes the Parse Database and first fragment layout. Stores variables
 * for the Database.
 *
 */

package edu.sc.FitLivin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends FragmentActivity{

    //Intializing varibles
    public static String name;
    public ParseUser user;
    private FragmentManager fm;
    private FragmentTransaction ft;
    public static Integer weight;
    public static Integer height;
    public static String s;
    public static Integer points = 0;
    private String objectID;
    String name1 = ParseUser.getCurrentUser().getUsername();


    //Creates a parse object for the database
    //Creates a query object to query through the database
    public static ParseObject profileInfo = new ParseObject("ProfileInfo");
    ParseQuery<ParseObject> query = ParseQuery.getQuery("ProfileInfo");

    public static ParseObject max = new ParseObject("Max");
    ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Max");

    public static ParseObject pointsInfo = new ParseObject("Points");
    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Points");

    public static ParseObject weightParse = new ParseObject("WeightGoal");
    ParseQuery<ParseObject> weightParseQuery = ParseQuery.getQuery("WeightGoal");

    public static ParseObject benchParse = new ParseObject("BenchGoal");
    ParseQuery<ParseObject> benchParseQuery = ParseQuery.getQuery("BenchGoal");

    public static ParseObject squatParse = new ParseObject("SquatGoal");
    ParseQuery<ParseObject> squatParseQuery = ParseQuery.getQuery("SquatGoal");

    public static ParseObject DeadLiftParse = new ParseObject("DeadLiftGoal");
    ParseQuery<ParseObject> DeadLiftParseQuery = ParseQuery.getQuery("DeadLiftGoal");

    public static ParseObject MileTimeParse = new ParseObject("MileTimeGoal");
    ParseQuery<ParseObject> MileTimeParseQuery = ParseQuery.getQuery("MileTimeGoal");

    public static ParseObject MaxBenchParse = new ParseObject("MaxBench");
    ParseQuery<ParseObject> MaxBenchParseQuery = ParseQuery.getQuery("MaxBench");

    public static ParseObject MaxSquatParse = new ParseObject("MaxSquat");
    ParseQuery<ParseObject> MaxSquatParseQuery = ParseQuery.getQuery("MaxSquat");

    public static ParseObject MaxDeadLiftParse = new ParseObject("MaxDeadLift");
    ParseQuery<ParseObject> MaxDeadLiftParseQuery = ParseQuery.getQuery("MaxDeadLift");

    public static ParseObject MaxBigThreeParse = new ParseObject("MaxBigThree");
    ParseQuery<ParseObject> MaxBigThreeParseQuery = ParseQuery.getQuery("MaxBigThree");

    public static ParseObject MaxMileTimeParse = new ParseObject("MaxMileTime");
    ParseQuery<ParseObject> MaxMileTimeParseQuery = ParseQuery.getQuery("MaxMileTime");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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

                            name = p.getString("username");
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
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            ParseUser.getCurrentUser().logOut();
            startActivity(new Intent(MainActivity.this, DispatchActivity.class));
            return true;
        }
        return onOptionsItemSelected(item);

    }

    /*****
     *
     * This method will allow for the Profile page to send name,
     * weight,and height to the database.
     *
     */
    public void profileData(Integer weight, Integer height, ParseUser user1) {
        Log.d("F", "pdata");
        Integer weight1 = weight;
        Integer height1 = height;
        this.user = user1;

        // adds info to database
        profileInfo.put("Weight", weight1);
        profileInfo.put("Height", height1);
        profileInfo.put("randomValue",Math.random());
        profileInfo.put("UserP",user1);
        profileInfo.put("username",user1.getUsername());
        profileInfo.put("ObjectId",user1.getObjectId());



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

    public void MaxData(Integer bench, Integer squat, Integer deadLift, Integer total,Integer mileTime, String user) {
        Integer bench1 = bench;
        Integer squat1 = squat;
        Integer deadLift1 = deadLift;
        Integer total1 = total;
        Integer miletime1 = mileTime;
        // ParseUser user1 = user;
        String username1 = user;

        Log.d("F", "maxData!!!!!!!!!!!!!!!!!!");
        // adds info to database
        max.put("bench", bench1);
        max.put("squat", squat1);
        max.put("deadlift",deadLift1);
        max.put("big3total",total1);
        max.put("miletime",miletime1);
        max.put("username", username1);

        max.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = max.getObjectId();
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

    public void WeightGoal(Integer weight, String user) {
        Integer weight1 = weight;

        // ParseUser user1 = user;
        String username1 = user;

        Log.d("F", "weightGoal!!!!!!!!!!!!!!!!!!");
        // adds info to database
        weightParse.put("goalWeight", weight1);
        weightParse.put("username", username1);

        weightParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = weightParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });

    }

    public void BenchGoal(Integer bench, String user) {
        Integer bench1 = bench;

        // ParseUser user1 = user;
        String username1 = user;

        Log.d("F", "benchGoal!!!!!!!!!!!!!!!!!!");
        // adds info to database
        benchParse.put("BenchGoal", bench1);
        benchParse.put("username", username1);

        benchParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = benchParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });

    }

    public void SquatGoal(Integer squat, String user) {
        Integer squat1 = squat;

        // ParseUser user1 = user;
        String username1 = user;

        Log.d("F", "squatGoal!!!!!!!!!!!!!!!!!!");
        // adds info to database
        squatParse.put("SquatGoal", squat1);
        squatParse.put("username", username1);

        squatParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = squatParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });

    }

    public void DeadLiftGoal(Integer DL, String user) {
        Integer DL1 = DL;

        // ParseUser user1 = user;
        String username1 = user;

        Log.d("F", "DLGoal!!!!!!!!!!!!!!!!!!");
        // adds info to database
        DeadLiftParse.put("DeadLiftGoal", DL1);
        DeadLiftParse.put("username", username1);

        DeadLiftParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = DeadLiftParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });

    }

    public void MileTimeGoal(Integer mileTime, String user) {
        Integer mileTime1 = mileTime;

        // ParseUser user1 = user;
        String username1 = user;

        Log.d("F", "mileTimeGoal!!!!!!!!!!!!!!!!!!");
        // adds info to database
        MileTimeParse.put("MileTimeGoal", mileTime1);
        MileTimeParse.put("username", username1);

        MileTimeParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = MileTimeParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });

    }
    public void BenchMax(Integer bench, String user) {
        Integer bench1 = bench;

        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxBenchParse.put("MaxBench", bench1);
        MaxBenchParse.put("username", username1);

        MaxBenchParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = MaxBenchParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });
    }

    public void SquatMax(Integer squat, String user) {
        Integer squat1 = squat;

        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxSquatParse.put("MaxSquat", squat1);
        MaxSquatParse.put("username", username1);

        MaxSquatParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = MaxSquatParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });
    }

    public void DeadLiftMax(Integer deadLift, String user) {
        Integer deadLift1 = deadLift;

        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxDeadLiftParse.put("MaxDeadLift", deadLift1);
        MaxDeadLiftParse.put("username", username1);

        MaxDeadLiftParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = MaxDeadLiftParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });
    }

    public void BigThreeMax(Integer bigThree, String user) {
        Integer bigThree1 = bigThree;

        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxBigThreeParse.put("MaxBigThree", bigThree1);
        MaxBigThreeParse.put("username", username1);

        MaxBigThreeParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = MaxBigThreeParse.getObjectId();
                    setS(objectID);
                } else {
                    Log.d("F", "object failllll");
                }
            }
        });
    }

    public void MileTimeMax(Integer mileTime, String user) {
        Integer mileTime1 = mileTime;

        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxMileTimeParse.put("MaxMileTime", mileTime1);
        MaxMileTimeParse.put("username", username1);

        MaxMileTimeParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = MaxMileTimeParse.getObjectId();
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
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
            finish();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }

}




