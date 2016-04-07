/******
 * Class 'StrengthDayOne'
 *
 * Provides the workout for Day 1 of Strength Training
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


public class StrengthDayOne extends Fragment {

MediaPlayer mp;

    private AlertDialog.Builder dBuilder;


    private void StrengthD1dialog(){
        dBuilder = new AlertDialog.Builder(getActivity());
        dBuilder.setTitle("Congratulations!");
        dBuilder.setMessage("You earned 50 points!");
        dBuilder.setIcon(R.mipmap.ic_launcher);
        dBuilder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dBuilder.create();
        dBuilder.show();

    }


    public StrengthDayOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    /*
     *Creates the view of our fragment.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_strength_day_one, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Day1");
        ImageButton benchImage = (ImageButton) v.findViewById(R.id.closeGripBenchImage);
        ImageButton cableFlyImage = (ImageButton) v.findViewById(R.id.flies);
        ImageButton inclineDumbbell = (ImageButton) v.findViewById(R.id.incline);

        benchImage.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactbarbellbenchpress);
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
        cableFlyImage.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactcablefly);
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
        inclineDumbbell.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactinclinebenchpress);
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



        //Initializes back button for strength day 1
      /**  Button backBtn = (Button) v.findViewById(R.id.Sday1BACK);

            /*
             *On click method that exits out of Strength day one and takes users back to the
             *strength training menu. Gives functionality to the back button.
             */
      /**  backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPSTRTrainingFragment fragment1 = new FPSTRTrainingFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });**/

        Button complete = (Button) v.findViewById(R.id.completeDay1s);//creates complete button
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main1 = new MainActivity();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Date date = new Date();
                String time = sdf.format(date);
                main1.workoutPro(time,"Strength Day One: ");
                ParseQuery queryuser = ParseUser.getQuery();
                queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());

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
                                                        // main.bench = x;
                                                       // currentPoints.setText("" +x);
                                                        Double points = main.points;
                                                        points = points + 50;//adds points for completed workout

                                                        String s = ParseUser.getCurrentUser().getUsername();
                                                        main.pointsData(points,s);


                                                        StrengthD1dialog();
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
