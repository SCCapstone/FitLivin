/******
 * Class 'StrengthDayTwo'
 *
 * Provides the workout for Day 2 of Strength Training
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


public class StrengthDayTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//initialize media player for fit facts
MediaPlayer mp;
//builder for the dialog
    private AlertDialog.Builder dBuilder;

//alerts the use when they complete a workout and tells them how many points theyve earned
    private void StrengthD2dialog(){
        dBuilder = new AlertDialog.Builder(getActivity());
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


    public StrengthDayTwo() {
        // Required empty public constructor
    }



    @Override
    /*
     *Creates the view of our fragment.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_strength_day_two, container, false);
        getActivity().getActionBar()
                .setTitle("Day 2");
        //here we initialize the buttons for the fit facts feature
        ImageButton backSquat = (ImageButton) v.findViewById(R.id.squatImage);
        ImageButton legPress = (ImageButton) v.findViewById(R.id.legpresspic);
        ImageButton calfPress = (ImageButton) v.findViewById(R.id.calfpresspic);
//if backSquat button is clicked, play the fitfact (as long as it is not already playing)
      backSquat.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactbarbellbacksquat);
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
        //if leg press button is clicked, play the fitfact (as long as it is not already playing)
        legPress.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactlegpress);
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
        //if calf press button is clicked, play the fitfact (as long as it is not already playing)
        calfPress.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactcalfpress);
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


        //Initializes back button for strength day 2
        /**    Button backBtn = (Button) v.findViewById(R.id.Sday2BACK);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /*
             *On click method that exits out of Strength day two and takes users back to the
             *strength training menu. Gives functionality to the back button.
             */
        /**      public void onClick(View v) {
                FPSTRTrainingFragment fragment1 = new FPSTRTrainingFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });**/

//complete button adds points to points page and records date.
        Button complete = (Button) v.findViewById(R.id.completeDay2s);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main1 = new MainActivity();
                //sets the format of the date here
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Date date = new Date();
                String time = sdf.format(date);
                main1.workoutPro(time, "Strength Day Two: ");
                ParseQuery queryuser = ParseUser.getQuery();
                queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());

                ParseQuery Points = ParseQuery.getQuery("Points");
                Points.whereExists("CurrentPoints");//constraints
                Points.whereMatchesQuery("author", queryuser);
                Points.orderByDescending("createdAt");

                Points.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> objects, ParseException e) {
                                                if (e == null && objects.size() != 0) {
                                                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {
                                                        Double x = (Double) objects.get(0).get("CurrentPoints");
                                                        MainActivity main = new MainActivity();
                                                        main.points = x;
                                                        Double points = main.points;
                                                        points = points + 50;
                                                        String s = ParseUser.getCurrentUser().getUsername();
                                                        main.pointsData(points, s);

                                                        StrengthD2dialog();
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
