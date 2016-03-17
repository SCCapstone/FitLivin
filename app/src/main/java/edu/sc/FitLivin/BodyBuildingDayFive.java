/******
 * Class 'BodyBuildingDayFive'
 *
 * Provides the workout for Day 5 of Body Building
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

public class BodyBuildingDayFive extends Fragment {

MediaPlayer mp;
    public BodyBuildingDayFive() {
        // Required empty public constructor
    }
    private AlertDialog.Builder dialogBuilder;
    //
    private void bodybuild5Dialog(){

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_body_building_day_five, container, false);
        ImageButton militaryPress = (ImageButton) v.findViewById(R.id.militaryPressImage);
        ImageButton sideLatRaise = (ImageButton) v.findViewById(R.id.sideLatImage);
        ImageButton shoulderPress = (ImageButton) v.findViewById(R.id.shoulderpressImage);
        getActivity().getActionBar()
                .setTitle("Day 5");

        militaryPress.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactmilitarypress);
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
        sideLatRaise.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactsidelatraise);
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
        shoulderPress.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(mp == null){
                                          mp = MediaPlayer.create(getActivity(), R.raw.fitfactshoulderpress);
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
        });**/
        Button complete = (Button) v.findViewById(R.id.completeDay5bb);//creates complete button
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

                                                Double x = (Double) objects.get(0).get("CurrentPoints");
                                                MainActivity main = new MainActivity();
                                                main.points = x;
                                                // main.bench = x;
                                                // currentPoints.setText("" +x);
                                                Double points = main.points;
                                                points = points + 50;//adds points for completed workout

                                                String s = ParseUser.getCurrentUser().getUsername();
                                                main.pointsData(points,s);
                                                bodybuild5Dialog();

                                            }

                                        }

                                    }


                                }
        );//

    }
});

        return v;//return
    }



}
