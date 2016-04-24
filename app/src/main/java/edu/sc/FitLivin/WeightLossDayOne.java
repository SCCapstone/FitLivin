/******
 * Class 'WeightLossDayOne'
 *
 * Provides the workout for Day 1 of WeigthLoss
 *
 */

package edu.sc.FitLivin;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class WeightLossDayOne extends Fragment {

    //initialize media player for fit facts
    MediaPlayer mp;

    public WeightLossDayOne() {
        // Required empty public constructor
    }

//builder for the dialog
    private AlertDialog.Builder dBuilder;

//alerts the use when they complete a workout and tells them how many points theyve earned
    private void WeightLD1dialog(){
        dBuilder = new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
        //the congrats message
        dBuilder.setTitle("Congratulations!");
        //amount of points earned
        dBuilder.setMessage("You earned 50 points!");
        dBuilder.setIcon(R.mipmap.ic_launcher);
        //continue dialog box option
        dBuilder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dBuilder.create();
        dBuilder.show();

    }
    /*
     *Creates the view of our fragment.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_weightloss_day_one, container, false);
        getActivity().getActionBar()
                .setTitle("Day 1");
        //here we initialize the buttons for the fit facts feature
        ImageButton running = (ImageButton) v.findViewById(R.id.runningImage);
        ImageButton lunges = (ImageButton) v.findViewById(R.id.lungesImage);
        ImageButton jumprope = (ImageButton) v.findViewById(R.id.jumpRopeImage);
//if running button is clicked, play the fitfact (as long as it is not already playing)
        running.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactrunning);
                                          mp.start();
                                          Log.d("QAOD", "start");
                                      }
                                      else{
                                          mp.stop();
                                          mp = null;
                                          Log.d("QAOD", "stop");
                                      }

                                  }
                              }
        );
        //if lunges button is clicked, play the fitfact (as long as it is not already playing)
        lunges.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactlunges);
                                          mp.start();
                                          Log.d("QAOD", "start");
                                      }
                                      else{
                                          mp.stop();
                                          mp = null;
                                          Log.d("QAOD", "stop");
                                      }

                                  }
                              }
        );
        //if jumprope button is clicked, play the fitfact (as long as it is not already playing)
        jumprope.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactjumprope);
                                          mp.start();
                                          Log.d("QAOD", "start");
                                      }
                                      else{
                                          mp.stop();
                                          mp = null;
                                          Log.d("QAOD", "stop");
                                      }

                                  }
                              }
        );





        //complete button adds points to points page and records date.
        Button complete1 = (Button) v.findViewById(R.id.completeDay1w);
        complete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main1 = new MainActivity();
                System.out.println("Workout Day 1 1");
                //sets the format of the date here
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Date date = new Date();
                String time = sdf.format(date);
                main1.workoutPro(time,"Weight Loss Day One: ");
                System.out.println("Workout Day 1 2");
                ParseQuery queryuser = ParseUser.getQuery();
                queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
//here points are updated
                ParseQuery Points = ParseQuery.getQuery("Points");
                Points.whereExists("CurrentPoints");//setting constraints
                Points.whereMatchesQuery("author", queryuser);
                Points.orderByDescending("createdAt");

                Points.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> objects, ParseException e) {
                                                if (e == null && objects.size() != 0) { //if objects size is not 0
                                                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {
                                                        Double x = (Double) objects.get(0).get("CurrentPoints");
                                                        MainActivity main = new MainActivity();
                                                        main.points = x;
                                                        Double points = main.points;
                                                        points = points + 50;//adds points for completed workout
                                                        String s = ParseUser.getCurrentUser().getUsername();
                                                        main.pointsData(points, s);
                                                        WeightLD1dialog();
                                                    }

                                                }

                                            }


                                        }


                );

            }
        });

        return v;
    }



}
