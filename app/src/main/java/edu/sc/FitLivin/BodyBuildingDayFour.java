/******
 * Class 'BodyBuildingDayFour'
 *
 * Provides the workout for Day 4 of Body Building
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


public class BodyBuildingDayFour extends Fragment {
   //initialize media player for fit facts
    MediaPlayer mp;


    private AlertDialog.Builder dialogBuilder;
    //alerts user that they have completed workout and awarded points
    private void bodybuild4Dialog(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Congratulations!");
        dialogBuilder.setMessage("You Earned 50 Points!");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialog.show();
    }



    public BodyBuildingDayFour() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_body_building_day_four, container, false);
        //intialize buttons
        ImageButton deadlift = (ImageButton) v.findViewById(R.id.deadliftImage);
        ImageButton pullup = (ImageButton) v.findViewById(R.id.pullupImage);
        ImageButton dbrow = (ImageButton) v.findViewById(R.id.dumbbellRowImage);
        getActivity().getActionBar()
                .setTitle("Day 4");
        //if DEADLIFT button is clicked, play the fitfact (as long as it is not already playing)
        deadlift.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      //prevents double clicking and overriding sound.
                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactdeadlift);
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
        //if pullup button is clicked, play the fitfact (as long as it is not already playing)
        pullup.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      //prevents double clicking and overriding sound.
                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactpullup);
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
        //if dumbbell row button is clicked, play the fitfact (as long as it is not already playing)
        dbrow.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      //prevents double clicking and overriding sound.
                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactdumbbellrow);
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


        /** Button backBtn = (Button) v.findViewById(R.id.BBBack);//creates button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPBodyBuildingFragment fragment1 = new FPBodyBuildingFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);//replaces previous fragment
                ft.addToBackStack(null);//adds to back stack
                ft.commit();//commits it
            }
        });**/

        Button complete = (Button) v.findViewById(R.id.completeDay4bb);//creates complete button
        //records points and date for points page
        complete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity main1 = new MainActivity();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Date date = new Date();
                String time = sdf.format(date);
                main1.workoutPro(time,"Body Building Day Four: ");
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

                                                        bodybuild4Dialog();

                                                    }

                                                }

                                            }


                                        }
                );

            }
        });


        return v;//return
    }




}
