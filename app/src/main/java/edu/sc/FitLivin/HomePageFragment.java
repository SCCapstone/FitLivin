/******
 * Class 'HomePageFragment'
 *
 * They user is able to choose Points, Profile, Max, BMI Calculator,
 * Nutrition Calculator, or FitnessProgram.
 *
 */

package edu.sc.FitLivin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomePageFragment extends Fragment{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("T", "ONcREATE");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("T", "ONcREATEVIEW");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_page, container, false);
        getActivity().getActionBar()
                .setTitle("Home");
        //Sets button for fitnessProgram
        //Sets onClick listener to display fragment
        Button btn = (Button) v.findViewById(R.id.fitnessProgramButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FitnessProgramFragment fragment = new FitnessProgramFragment();
                FragmentManager fm = getFragmentManager();
               fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        //Sets button for Bmi Page
        //Sets onClick listener to display fragment
        Button btn3 = (Button) v.findViewById(R.id.bmiButton);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMICAL_Fragment fragment3 = new BMICAL_Fragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment3).addToBackStack(null).commit();
            }
        });
        //Sets button for Nutrition Page
        //Sets onClick listener to display fragment
        Button btn4 = (Button) v.findViewById(R.id.nutritionButton);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NutritionCalFragment fragment4 = new NutritionCalFragment();
                FragmentManager fm1 = getFragmentManager();
                fm1.beginTransaction().replace(R.id.container, fragment4).addToBackStack(null).commit();
            }
        });
        //Sets button for Points Page
        //Sets onClick listener to display fragment
        Button btn5 = (Button) v.findViewById(R.id.pointsButton);
        btn5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                PointsPageFragment fragment5 = new PointsPageFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container, fragment5).addToBackStack(null).commit();

            }
        });
        //Sets button for Profile Page
        //Sets onClick listener to display fragment
        Button btn6 = (Button) v.findViewById(R.id.profileButton);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfilePageFragment fragment6 = new ProfilePageFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment6);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        //Sets button for trackProgress page
        //Sets onClick listener to display fragment
        Button btn22 = (Button) v.findViewById(R.id.trackProgressButton);
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackProgressFragment fragment22 = new TrackProgressFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment22);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        //Sets button for Max page
        //Sets onClick listener to display fragment
        Button maxBtn = (Button) v.findViewById(R.id.MaxButton);
        maxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaxFragment maxFrag = new MaxFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, maxFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

                Button btng = (Button) v.findViewById(R.id.goalButton);
                btng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoalFragment goal = new GoalFragment();
                        FragmentManager fm1 = getFragmentManager();
                        fm1.beginTransaction().replace(R.id.container, goal).addToBackStack(null).commit();
                    }
                });

        Button stopWatch  = (Button) v.findViewById(R.id.StopWatchButton);
        stopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StopwatchFragment stop = new StopwatchFragment();
                FragmentManager fm1 = getFragmentManager();
                fm1.beginTransaction().replace(R.id.container, stop).addToBackStack(null).commit();
            }
        });


                return v;

            }

        }


