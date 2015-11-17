package edu.sc.myapplication;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Integer weight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Put fragment in container

        HomePageFragment firstFragment = new HomePageFragment();
        FragmentManager fm1 = getFragmentManager(); //or getFragmentManager() if you are not using support library.
        fm1.beginTransaction().add(R.id.container, firstFragment).addToBackStack(null).commit();
    }

    //  }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void setWeight(Integer weight1) {
        this.weight = weight1;
    }
    public Integer getWeight() {
        return weight;
    }

}
