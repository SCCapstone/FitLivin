/******
 * Class 'StrengthDayOne'
 *
 * Provides the workout for Day 1 of Strength Training
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


public class StrengthDayOne extends Fragment {

MediaPlayer mp;
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
        ImageButton benchImage = (ImageButton) v.findViewById(R.id.closeGripBenchImage);
        ImageButton cableFlyImage = (ImageButton) v.findViewById(R.id.flies);
        ImageButton inclineDumbbell = (ImageButton) v.findViewById(R.id.incline);

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
        Button backBtn = (Button) v.findViewById(R.id.Sday1BACK);

            /*
             *On click method that exits out of Strength day one and takes users back to the
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

        Button complete = (Button) v.findViewById(R.id.completeDay1s);//creates complete button
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
