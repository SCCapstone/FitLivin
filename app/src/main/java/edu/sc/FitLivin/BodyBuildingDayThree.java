/******
 * Class 'BodyBuildingDayThree'
 *
 * Provides the workout for Day 3 of Body Building
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


public class BodyBuildingDayThree extends Fragment {
    //initialize media player for fit facts
    MediaPlayer mp;

    private AlertDialog.Builder dialogBuilder;
    /**
     * Complete button dialog to alert user that they have been awarded points
     */

    private void bodybuild3Dialog(){
        dialogBuilder= new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
        dialogBuilder.setTitle("Congratulations!");
        dialogBuilder.setMessage("You Earned 50 Points!");
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialogBuilder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }


    public BodyBuildingDayThree() {
        // Required empty public constructor
    }


    /**
     * Method for clicking on image and playing audio
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_body_building_day_three, container, false);
        //initialize fitfact buttons.
        ImageButton hammerCurl = (ImageButton) v.findViewById(R.id.hammerCurlImage);
        ImageButton preacherCurl = (ImageButton) v.findViewById(R.id.preacherCurlImage);
        ImageButton pushupClose = (ImageButton) v.findViewById(R.id.closegrippushupImage);
        getActivity().getActionBar()
                .setTitle("Day 3");
        //if hammer curl button is clicked, play the fitfact (as long as it is not already playing)
        hammerCurl.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfacthammercurl);
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
        /**
         *  if curl button is clicked, play the fitfact (as long as it is not already playing)
         */

        preacherCurl.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactpreachercurl);
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
        /**
         * if pushup button is clicked, play the fitfact (as long as it is not already playing)
         */

        pushupClose.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactclosegrippushup);
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

        Button complete = (Button) v.findViewById(R.id.completeDay3bb);//creates complete button
        /**
         * adds points to points page
         */

        complete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity main1 = new MainActivity();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Date date = new Date();
                String time = sdf.format(date);
                main1.workoutPro(time,"Body Building Day Three: ");
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

                                                        bodybuild3Dialog();

                                                    }

                                                }

                                            }


                                        }
                );

            }
        });

/**
        Button backBtn = (Button) v.findViewById(R.id.BBBack);//creates button
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

        return v;//return
    }



}
