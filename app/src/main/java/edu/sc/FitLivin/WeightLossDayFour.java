/******
 * Class 'WeightLossDayFour'
 *
 * Provides the workout for Day 3 of WeigthLoss
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

public class WeightLossDayFour extends Fragment {

    MediaPlayer mp;

    public WeightLossDayFour() {
        // Required empty public constructor
    }

    /*
     *Creates the view of our fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Required
        View v = inflater.inflate(R.layout.fragment_weightloss_day_three, container, false);
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
        Button backBtn = (Button) v.findViewById(R.id.WLBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPWGTLossFragment fragment1 = new FPWGTLossFragment();
                FragmentManager fm = getFragmentManager(); //or getFragmentManager() if you are not using support library.
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Button complete = (Button) v.findViewById(R.id.completeDay4w);//creates complete button
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer points = MainActivity.points;
                points = points + 50;//adds points for completed workout
                MainActivity main = new MainActivity();
                main.pointsData(points);
            }
        });

        return v;
    }



}