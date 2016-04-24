/******
 * Class 'BodyBuildingDayTwo'
 *
 * Provides the workout for Day 2 of Body Building
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

import static android.content.DialogInterface.*;


public class BodyBuildingDayTwo extends Fragment {

    private AlertDialog.Builder dialogBuilder;



    //dialog for day 2 completion. alerts user of their 50 points.
    private void bodybuild2Dialog(){


            dialogBuilder= new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
            dialogBuilder.setTitle("Congratulations!");
            dialogBuilder.setMessage("You Earned 50 Points!");
            dialogBuilder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = dialogBuilder.create();
            dialogBuilder.setIcon(R.mipmap.ic_launcher);
            dialog.show();


    }
//initialize media player for fit facts
MediaPlayer mp;
    public BodyBuildingDayTwo() {
        // Required empty public constructor
    }

private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_body_building_two, container, false);
        //initialize buttons
        ImageButton benchImage = (ImageButton) v.findViewById(R.id.benchImage);
        ImageButton dumbbellFlyImage = (ImageButton) v.findViewById(R.id.flyImage);
        ImageButton pushupImage = (ImageButton) v.findViewById(R.id.pushupImage);
        getActivity().getActionBar()
                .setTitle("Day 2");
       // mp = MediaPlayer.create(getActivity(), R.raw.fitfactbarbellbenchpressnew);

        //if bench button is clicked, play the fitfact (as long as it is not already playing)
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
        //if fly button is clicked, play the fitfact (as long as it is not already playing)
        dumbbellFlyImage.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if (mp == null) {
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactdumbbellfly);
                                          mp.start();
                                          Log.d("QAOD", "start");
                                      } else {
                                          mp.stop();
                                          mp = null;
                                          Log.d("QAOD", "stop");
                                      }

                                  }
                              }
        );
        //if pushup button is clicked, play the fitfact (as long as it is not already playing)
        pushupImage.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    if (mp == null) {
                                                        mp = MediaPlayer.create(getActivity(), R.raw.fitfactpushup);
                                                        mp.start();
                                                        Log.d("QAOD", "start");
                                                    } else {
                                                        mp.stop();
                                                        mp = null;
                                                        Log.d("QAOD", "stop");
                                                    }

                                                }
                                            }
        );
        //adds points to points page
        Button complete = (Button) v.findViewById(R.id.completeDay2bb);//creates complete button
        complete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity main1 = new MainActivity();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Date date = new Date();
                String time = sdf.format(date);
                main1.workoutPro(time,"Body Building Day Two: ");
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

                                                        bodybuild2Dialog();

                                                    }

                                                }

                                            }


                                        }
                );

            }
        });

        /**    Button backBtn = (Button) v.findViewById(R.id.BBBack);//creates button
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
        });
**/

        return v;
        //retur

    }
//prevents double clicks and overriding sound.
public void onBackPressed(){
    if (mp!=null){
        mp.pause();
        mp.stop();
        }
    super.onPause();

}

    /*public void benchImageButton(View v){
        MediaPlayer mp = MediaPlayer.create(v.getContext(), R.raw.fitfactbarbellbenchbress);
        mp.start();
        **/
    }