/******
 * Class 'BodyBuildingDayTwo'
 *
 * Provides the workout for Day 2 of Body Building
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


public class BodyBuildingDayTwo extends Fragment {

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
        ImageButton benchImage = (ImageButton) v.findViewById(R.id.benchImage);
        ImageButton dumbbellFlyImage = (ImageButton) v.findViewById(R.id.flyImage);
        ImageButton pushupImage = (ImageButton) v.findViewById(R.id.pushupImage);
       // mp = MediaPlayer.create(getActivity(), R.raw.fitfactbarbellbenchpressnew);
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


        return v;
        //retur

    }

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