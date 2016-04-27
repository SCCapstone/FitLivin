package edu.sc.FitLivin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.parse.ParseUser;

/*
*This class checks whether the user is logged in. If yes, then the user is directy taken to home screen
*/
public class DispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isNetworkAvailable(getApplicationContext())){ //cecking for internet connection
            if (ParseUser.getCurrentUser() != null) {
                // Start an intent for the logged in activity
                startActivity(new Intent(this, MainActivity.class)); //user is logged in
            } else {
                // Start and intent for the logged out activity
                startActivity(new Intent(this, SignUporLogin.class));//not logged in
            }
        }
        else {//tells the user the app is going to shutdown
            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {

                    Toast.makeText(getApplicationContext(),"The app will self destruct in: 5 seconds",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"You dont have internet!! Call an ISP now!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"The app will self destruct in: 1 second",Toast.LENGTH_SHORT).show();
                }

                public void onFinish() {
                    System.exit(1);
                }
            }.start();





        }


    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
