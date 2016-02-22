/******
 * Class 'StrengthDayTwo'
 *
 * Provides the workout for Day 2 of Strength Training
 *
 */

package edu.sc.FitLivin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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


public class StrengthDayTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

MediaPlayer mp;




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
        ImageButton backSquat = (ImageButton) v.findViewById(R.id.squatImage);
        ImageButton legPress = (ImageButton) v.findViewById(R.id.legpresspic);
        ImageButton calfPress = (ImageButton) v.findViewById(R.id.calfpresspic);

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
        Button backBtn = (Button) v.findViewById(R.id.Sday2BACK);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /*
             *On click method that exits out of Strength day two and takes users back to the
             *strength training menu. Gives functionality to the back button.
             */
            public void onClick(View v) {
                FPSTRTrainingFragment fragment1 = new FPSTRTrainingFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        Button complete = (Button) v.findViewById(R.id.completeDay2s);//creates complete button
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery queryuser = ParseUser.getQuery();
                queryuser.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());

                ParseQuery Points = ParseQuery.getQuery("Points");
                Points.whereExists("CurrentPoints");//setting constraints
                Points.whereMatchesQuery("author", queryuser);

                Points.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> objects, ParseException e) {

                                                if (e == null && objects.size() != 0) { //if objects size is not 0

                                                    if (objects.get(0).get("username").equals(ParseUser.getCurrentUser().getUsername())) {

                                                        int x = (Integer) objects.get(objects.size() - 1).get("CurrentPoints");
                                                        MainActivity main = new MainActivity();
                                                        main.points = x;
                                                        // main.bench = x;
                                                        // currentPoints.setText("" +x);
                                                        Integer points = main.points;
                                                        points = points + 50;//adds points for completed workout

                                                        String s = ParseUser.getCurrentUser().getUsername();
                                                        main.pointsData(points,s);


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
