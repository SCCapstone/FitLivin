/******
 * Class 'WeightLossDayFour'
 *
 * Provides the workout for Day 3 of WeigthLoss
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

public class WeightLossDayFour extends Fragment {

    MediaPlayer mp;

    public WeightLossDayFour() {
        // Required empty public constructor
    }
    private AlertDialog.Builder dBuilder;
    private void WeightLD4dialog(){
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
    /*
     *Creates the view of our fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Required
        View v = inflater.inflate(R.layout.fragment_weightloss_day_four, container, false);
        getActivity().getActionBar()
                .setTitle("Day 4");
        ImageButton jog = (ImageButton) v.findViewById(R.id.jogginImage);
        ImageButton rows = (ImageButton) v.findViewById(R.id.rowImage);
        ImageButton cardio = (ImageButton) v.findViewById(R.id.cardioImage);


        jog.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactjogging);
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
        rows.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactrowing);
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
        cardio.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactcardio);
                                          mp.start();
                                          Log.d("QAOD", "start");
                                      }
                                      else{
                                          mp.stop();
                                          mp = null;
                                          Log.d("QAOD", "stop");
                                      }

                                  }
                              });

        /**   Button backBtn = (Button) v.findViewById(R.id.WLBack);//creates button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPWGTLossFragment fragment1 = new FPWGTLossFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);//replaces previous fragment
                ft.addToBackStack(null);//adds to back stack
                ft.commit();//commits it
            }
        });**/

        //Add points to point page
        Button complete1 = (Button) v.findViewById(R.id.completeDay4w);
        complete1.setOnClickListener(new View.OnClickListener() {
            @Override
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
                                                        Double x = (Double) objects.get(0).get("CurrentPoints");
                                                        MainActivity main = new MainActivity();
                                                        main.points = x;
                                                        Double points = main.points;
                                                        points = points + 50;//adds points for completed workout
                                                        String s = ParseUser.getCurrentUser().getUsername();
                                                        main.pointsData(points, s);

                                                        WeightLD4dialog();
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