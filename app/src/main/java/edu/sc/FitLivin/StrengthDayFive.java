/******
 * Class 'StrengthDayFive'
 *
 * Provides the workout for Day 5 of Strength Training
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

import java.util.List;


public class StrengthDayFive extends Fragment {
MediaPlayer mp;

    private AlertDialog.Builder dBuilder;


    private void StrengthD5dialog(){
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




    public StrengthDayFive() {
        // Required empty public constructor
    }



    @Override
    /*
     *Creates the view of our fragment.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_strength_day_five, container, false);
        getActivity().getActionBar()
                .setTitle("Day 5");
        ImageButton benchImage = (ImageButton) v.findViewById(R.id.closeGripBenchImage);
        ImageButton squat = (ImageButton) v.findViewById(R.id.squatImage);
        ImageButton legpress = (ImageButton) v.findViewById(R.id.legpresspic);



        benchImage.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactbarbellbenchpressnew);
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

        squat.setOnClickListener(new View.OnClickListener() {
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

        legpress.setOnClickListener(new View.OnClickListener() {
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

        //Initializes back button for strength day 5
        Button backBtn = (Button) v.findViewById(R.id.Sday5BACK);

            /*
             *On click method that exits out of Strength day five and takes users back to the
             *strength training menu. Gives functionality to the back button.
             */
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPSTRTrainingFragment fragment1 = new FPSTRTrainingFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button complete = (Button) v.findViewById(R.id.completeDay5s);//creates complete button
        complete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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

                                                        int x = (Integer) objects.get(0).get("CurrentPoints");
                                                        MainActivity main = new MainActivity();
                                                        main.points = x;
                                                        // main.bench = x;
                                                        // currentPoints.setText("" +x);
                                                        Integer points = main.points;
                                                        points = points + 50;//adds points for completed workout

                                                        String s = ParseUser.getCurrentUser().getUsername();
                                                        main.pointsData(points,s);

                                                        StrengthD5dialog();

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
