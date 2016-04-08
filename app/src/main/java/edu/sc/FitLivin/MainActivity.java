/******
 * Class 'MainActivity'
 *
 * Initializes the Parse Database and first fragment layout. Stores variables
 * for the Database.
 *
 */

package edu.sc.FitLivin;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends FragmentActivity{
    //
    //Intializing varibles
    Integer value;
    public static String name;
    public ParseUser user;
    private FragmentManager fm;
    private FragmentTransaction ft;
    public static Integer weight;
    public static Integer weightPro = 0;
    public static Integer bench = 0;
    public static Integer squat = 0;
    public static Integer deadLift = 0;
    public static Integer mileTime = 0;
    public static Integer height;
    public static Integer leader;
    public static String s;
    public static Double points = 0.0;
    public static Integer c = 0;
    private String objectID;
    public static MediaPlayer mp;
    public static Integer ExcTest;


    ListView listView;
    ArrayAdapter<String> listAdapter;
    String fragmentArray[] = {"Profile", "Points", "Nutrition", "Goals", "Max", "Track Progress", "Fitness Program","Stop Watch"};
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

    //public static ParseObject WorkoutParse = new ParseObject("WorkoutProgress");
    //  ParseQuery<ParseObject> WorkoutParseQuery = ParseQuery.getQuery("WorkoutProgress");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        listView = (ListView)findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1, fragmentArray){
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView ListItemShow = (TextView) view.findViewById(android.R.id.text1);
        ListItemShow.setTextColor(Color.parseColor("#ffffff"));
        return view;
    }
};
        listView.setAdapter(listAdapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment;
                switch (position) {

                    case 0:
                        fragment = new ProfilePageFragment();
                        break;
                    case 1:
                        fragment = new PointsPageFragment();
                        break;
                    case 2:
                        fragment = new NutritionCalFragment();
                        break;
                    case 3:
                        fragment = new GoalFragment();
                        break;
                    case 4:
                        fragment = new MaxFragment();
                        break;
                    case 5:
                        fragment = new TrackProgressFragment();
                        break;
                    case 6:
                        fragment = new FitnessProgramFragment();
                        break;
                    case 7:
                        fragment = new StopwatchFragment();
                        break;
                    default:
                        fragment = new HomePageFragment();
                }

                fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                drawerLayout.closeDrawers();

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
	        /* fm = getFragmentManager(); // or 'getSupportFragmentManager();'
	        int count = fm.getBackStackEntryCount();
	        System.out.println("count " + count);
	        for(int i = 0; i < count; ++i) {
	            System.out.println("framgnet " + i);
	            fm.popBackStack();
	        }*/
        //Adds the fragment for the layout
        PointsPageFragment firstFragment = new PointsPageFragment();
        fm= getFragmentManager();
        fm.beginTransaction().replace(R.id.container, firstFragment).addToBackStack(null).commit();

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        this.getActionBar()
                .setTitle("FitLivin");
        //this.getActionBar().setDisplayUseLogoEnabled(true);
        //this.getActionBar().setDisplayShowHomeEnabled(true);
        //this.getActionBar().setIcon(R.mipmap.ic_launcher);

        this.getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e70b0e")));

        String s = ParseUser.getCurrentUser().getUsername();
        Log.d("FUsername ", s);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.uName);
        MenuItem menuItem2 = menu.findItem(R.id.home);

        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                drawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
            }
        });
        menuItem .setTitle(s);


        // menuItem.setClickable(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
           System.out.println("Logout Count" +getFragmentManager().getBackStackEntryCount());
            ParseUser.getCurrentUser().logOut();
            startActivity(new Intent(MainActivity.this, DispatchActivity.class));
            return true;
        }

        if(id == R.id.uName){
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

    public void pointsData(Double points, String user) {
        Log.d("F", "pdata");
        Double points1 = points;
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
        ParseObject MaxBenchParse1 = new ParseObject("MaxBench");
        Integer bench1 = bench;

        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxBenchParse1.put("MaxBench", bench1);
        MaxBenchParse1.put("username", username1);
        MaxBenchParse1.put("author",curruser);

        MaxBenchParse1.saveInBackground();
    }

    public void SquatMax(Integer squat, String user) {
        ParseObject MaxSquatParse1 = new ParseObject("MaxSquat");
        Integer squat1 = squat;

        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxSquatParse1.put("MaxSquat", squat1);
        MaxSquatParse1.put("username", username1);
        MaxSquatParse1.put("author",curruser);

        MaxSquatParse1.saveInBackground();
    }

    public void DeadLiftMax(Integer deadLift, String user) {
        ParseObject MaxDeadLiftParse1 = new ParseObject("MaxDeadLift");
        Integer deadLift1 = deadLift;

        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxDeadLiftParse1.put("MaxDeadLift", deadLift1);
        MaxDeadLiftParse1.put("username", username1);
        MaxDeadLiftParse1.put("author", curruser);

        MaxDeadLiftParse1.saveInBackground();
    }


    public void MileTimeMax(Integer mileTime, String user) {
        ParseObject MaxMileTimeParse1 = new ParseObject("MaxMileTime");
        Integer mileTime1 = mileTime;        // ParseUser user1 = user;
        String username1 = user;
        // adds info to database
        MaxMileTimeParse1.put("MaxMileTime", mileTime1);
        MaxMileTimeParse1.put("username", username1);
        MaxMileTimeParse1.put("author", curruser);

        MaxMileTimeParse1.saveInBackground();
    }
    public void workoutPro(String date, String workout) {
        ParseObject WorkoutParse = new ParseObject("WorkoutProgress");
        String date1 = date;

        // ParseUser user1 = user;
        String workout1 = workout;
        // adds info to database
        WorkoutParse.put("workout", workout1);
        WorkoutParse.put("date", date1);
        WorkoutParse.put("author", curruser);
        WorkoutParse.saveInBackground();
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
        int count = fm.getBackStackEntryCount();
        System.out.println("count " + count);
        if(getFragmentManager().getBackStackEntryCount() <=1) {

            // finish();
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent a = new Intent(Intent.ACTION_MAIN);
                            a.addCategory(Intent.CATEGORY_HOME);
                            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(a);
                            // finish();

                        }
                    }).setNegativeButton("No", null).show();
        }

        else {
            getFragmentManager().popBackStack();

        }
    }




}





