/******
 * Class 'MainActivity'
 *
 * Initializes the Parse Database and first fragment layout. Stores variables
 * for the Database.
 *
 */

package edu.sc.FitLivin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import android.app.Activity;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{

    //Intializing varibles
    Integer value;
    public static String name;
    public ParseUser user;
    private FragmentManager fm;
    private FragmentTransaction ft;
    public static Integer weight;
    public static Integer weightPro = 0;
    public static Integer bench = 0;
    public static Integer benchD;
    public static Integer squat = 0;
    public static Integer deadLift = 0;
    public static Integer mileTime = 0;
    public static Integer height;
    public static Integer leader;
    public static String s;
    public static Integer y;
    public static Integer points = 0;
    public static Integer c = 0;
    public static Integer pointsL;
    private String objectID;
    public static MediaPlayer mp;
    public static Integer ExcTest;


    ListView listView;
    ArrayAdapter<String> listAdapter;
    String fragmentArray[] = {"Home Page", "Profile", "Points", "BMI", "Nutrition", "Goals", "Max", "Track Progress", "Fitness Program","Stop Watch"};
    DrawerLayout drawerLayout;


    String name1 = ParseUser.getCurrentUser().getUsername();
    ParseUser curruser = ParseUser.getCurrentUser();


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

    public static ParseObject weightGainParse = new ParseObject("goalWeightGain");
    ParseQuery<ParseObject> weightGainParseQuery = ParseQuery.getQuery("goalWeightGain");

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



     listView = (ListView)findViewById(R.id.listView);
       listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fragmentArray);
            listView.setAdapter(listAdapter);

            drawerLayout = (DrawerLayout) findViewById(R.id.drawerview);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Fragment fragment;
                    switch (position) {
                        case 0:
                            fragment = new HomePageFragment();
                            break;
                        case 1:
                            fragment = new ProfilePageFragment();
                            break;
                        case 2:
                            fragment = new PointsPageFragment();
                            break;
                        case 3:
                            fragment = new BMICAL_Fragment();
                            break;
                        case 4:
                            fragment = new NutritionCalFragment();
                            break;
                        case 5:
                            fragment = new GoalFragment();
                            break;
                        case 6:
                            fragment = new MaxFragment();
                            break;
                        case 7:
                            fragment = new TrackProgressFragment();
                            break;
                        case 8:
                            fragment = new FitnessProgramFragment();
                            break;
                        case 9:
                            fragment = new StopwatchFragment();
                            break;
                        default:
                            fragment = new HomePageFragment();
                    }
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
                    drawerLayout.closeDrawers();
                   /* HomePageFragment firstFragment = new HomePageFragment();
                    FragmentManager fm1 = getFragmentManager();
                    fm1.beginTransaction().add(R.id.container, firstFragment).addToBackStack(null).commit();*/
                }
            });


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
          /*  ParseQuery queryuser = ParseUser.getQuery();
            queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
            ParseQuery Points = ParseQuery.getQuery("Points");
            Points.whereExists("CurrentPoints");//setting constraints
            Points.whereMatchesQuery("author", queryuser);
            Points.whereContains("username", ParseUser.getCurrentUser().getUsername());
            Points.orderByDescending("createdAt");
            final ArrayList<String> arrayList5;
            arrayList5 = new ArrayList<String>();
            Points.findInBackground(new FindCallback<ParseObject>() {
                                        public void done(List<ParseObject> objects, ParseException e) {
                                            Integer p = 0;
                                            while( p != objects.size() - 1) {

                                                if (e == null && objects.size() != 0) { //if objects size is not 0

                                                    String x = (String) objects.get(p).get("username");
                                                    if(arrayList5.contains(x)){

                                                    }else {
                                                        arrayList5.add(x);
                                                        System.out.println("arrayList 5 "+x);
                                                    }
                                                }
                                                p++;
                                            }



                                        }


                                    }
            );
            String s = ParseUser.getCurrentUser().getUsername();
            if(!arrayList5.contains(s)){
                pointsData(0, s);
            }*/
            ParseQuery MaxBench = ParseQuery.getQuery("MaxBench");
            MaxBench.whereExists("MaxBench");//setting constraints
            MaxBench.orderByDescending("createdAt");
            MaxBench.whereContains("username", ParseUser.getCurrentUser().getUsername());

            MaxBench.findInBackground(new FindCallback<ParseObject>() {
                                          public void done(List<ParseObject> objects, ParseException e) {

                                              if (e == null && objects.size() != 0) { //if objects size is not 0

                                                  if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                                                      int x = (Integer) objects.get(0).get("MaxBench");

                                                      bench = x;


                                                  }

                                              }

                                          }


                                      }
            );
            ParseQuery MaxSquat = ParseQuery.getQuery("MaxSquat");
            MaxSquat.whereExists("MaxSquat");//setting constraints
            MaxSquat.orderByDescending("createdAt");
            MaxSquat.whereContains("username", ParseUser.getCurrentUser().getUsername());

            MaxSquat.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {

                    if (e == null && objects.size() != 0) { //if objects size is not 0

                        if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                            int x = (Integer) objects.get(0).get("MaxSquat");
                            //currentW.setText(Integer.toString(x));
                            //setSquat(x);
                            squat = x;
                        }
                    }
                }

            });
            ParseQuery MaxDeadLift = ParseQuery.getQuery("MaxDeadLift");
            MaxDeadLift.whereExists("MaxDeadLift");//setting constraints
            MaxDeadLift.orderByDescending("createdAt");
            MaxDeadLift.whereContains("username", ParseUser.getCurrentUser().getUsername());

            MaxDeadLift.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {

                    if (e == null && objects.size() != 0) { //if objects size is not 0

                        if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                            int x = (Integer) objects.get(0).get("MaxDeadLift");
                            //currentW.setText(Integer.toString(x));
                            deadLift = x;


                        }
                    }
                }

            });
            ParseQuery MaxMileTime = ParseQuery.getQuery("MaxMileTime");
            MaxMileTime.whereExists("MaxMileTime");//setting constraints
            MaxMileTime.orderByDescending("createdAt");
            MaxMileTime.whereContains("username", ParseUser.getCurrentUser().getUsername());

            MaxMileTime.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {

                    if (e == null && objects.size() != 0) { //if objects size is not 0

                        if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                            int x = (Integer) objects.get(0).get("MaxMileTime");
                            //currentW.setText(Integer.toString(x));
                            mileTime = x;


                        }
                    }
                }

            });

            //Adds the fragment for the layout
        PointsPageFragment firstFragment = new PointsPageFragment();
        FragmentManager fm1 = getFragmentManager();
        fm1.beginTransaction().add(R.id.container, firstFragment).addToBackStack(null).commit();

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        this.getActionBar()
                .setTitle("FitLivin");
        this.getActionBar().setDisplayUseLogoEnabled(true);
        this.getActionBar().setDisplayShowHomeEnabled(true);
        this.getActionBar().setIcon(R.mipmap.ic_launcher);

        this.getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#54e4d7")));

         String s = ParseUser.getCurrentUser().getUsername();
        Log.d("FUsername ", s);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.uName);
        menuItem .setTitle(s);


       // menuItem.setClickable(true);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            ParseUser.getCurrentUser().logOut();
            startActivity(new Intent(MainActivity.this, DispatchActivity.class));
            return true;
        }

        else if(id == R.id.uName){
            ProfilePageFragment fragment6 = new ProfilePageFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container, fragment6);
            ft.addToBackStack(null);
            ft.commit();
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
        profileInfo.put("randomValue", Math.random());
        profileInfo.put("UserP", user1);
        profileInfo.put("username", user1.getUsername());
        profileInfo.put("ObjectId", user1.getObjectId());



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
        max.put("deadlift", deadLift1);
        max.put("big3total", total1);
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

    public void pointsData(Integer points, String user) {
        Log.d("F", "pdata");
        Integer points1 = points;
        String username1 = user;

        //adds info to database
        pointsInfo.put("CurrentPoints",points1);
        pointsInfo.put("username", username1);
        pointsInfo.put("author",curruser);

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
        weightParse.put("author",curruser);

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

    public void WeightGainGoal(Integer weight, String user) {
        Integer weight1 = weight;

        // ParseUser user1 = user;
        String username1 = user;

        Log.d("F", "weightGainGoal!!!!!!!!!!!!!!!!!!");
        // adds info to database
        weightGainParse.put("goalWeightGain", weight1);
        weightGainParse.put("username", username1);
        weightGainParse.put("author",curruser);

        weightGainParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    objectID = weightGainParse.getObjectId();
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
        benchParse.put("author",curruser);

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
        squatParse.put("author",curruser);

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
        DeadLiftParse.put("author",curruser);

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
        MileTimeParse.put("author",curruser);

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
        MaxBenchParse.put("author",curruser);

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
        MaxSquatParse.put("author",curruser);

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
        MaxDeadLiftParse.put("author", curruser);

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
        MaxBigThreeParse.put("author",curruser);

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
        MaxMileTimeParse.put("author", curruser);

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
    public Integer WeightGoalTest(final Integer weightL){


          Integer currentw = weightPro;

          if (currentw <= weightL)
          {
              return 1;
          }
        else {

              return 2;
          }

    }
    public Integer WeightGainGoalTest(final Integer weightG){

        ParseQuery CurrentWeightquery = ParseQuery.getQuery("ProfileInfo");
        CurrentWeightquery.whereExists("Weight");//setting constraints
        CurrentWeightquery.orderByDescending("createdAt");
        CurrentWeightquery.whereContains("username", ParseUser.getCurrentUser().getUsername());


       //final  SaveData data = new SaveData();

        CurrentWeightquery.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                        int x = (Integer) objects.get(0).get("Weight");
                        //currentW.setText(Integer.toString(x));
                        setWeight(x);


                    }
                }
            }

        });

        //BenchGoalTest(1);
        Integer currentw = getWeight();
        if(currentw>=weightG){

            return 1;
        }else{

            return 2;
        }

    }
   public Integer BenchGoalTest(final Integer weightG) {


       Log.d("Q", "ddCurrentBenchMax2  " + bench + " dd ");

       Integer currentB = bench;

        if(currentB>=weightG){
            return 1;
        }else{
            return 2;
        }

    }


    public Integer SquatGoalTest(final Integer weightG){


        Integer currentS = squat;
        if(currentS>=weightG){
            return 1;
        }else{
            return 2;
        }

    }
   public Integer DeadLiftGoalTest(final Integer weightG){

       Integer currentD = deadLift;
        if(currentD>=weightG){
            return 1;
        }else{
            return 2;
        }

    }

    public Integer MileTimeGoalTest(final Integer weightG){

        Integer currentM = mileTime;
       if(currentM == 0){
           return 2;
       }
        if(currentM<=weightG){
            return 1;
        }else{
            return 2;
        }

    }
    public void myFancyMethod(View v) {
        BMICAL_Fragment fragment3 = new BMICAL_Fragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.container, fragment3).addToBackStack(null).commit();
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

    public Integer getC(){
        return c;
    }
    public void setC(Integer setC){
        this.c = setC;
    }
    public String getS(){
        return objectID;
    }
    public void setS(String sn){
        this.objectID = sn;
    }
   public void launchCamera(View view){
       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       startActivityForResult(intent, 1);
   }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
        }
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




