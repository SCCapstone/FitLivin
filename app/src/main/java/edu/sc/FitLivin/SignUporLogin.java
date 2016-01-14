package edu.sc.FitLivin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by pkcho on 1/13/2016.
 * This class guides the user to a login or sign up screen
 */
public class SignUporLogin extends Activity {
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_signuporlogin);

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_signuporlogin);
        ((Button) findViewById(R.id.email_login_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent of the log in activity
                startActivity(new Intent(SignUporLogin.this, LoginActivity.class));
            }
        });
        ((Button) findViewById(R.id.email_signin_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent for the sign up activity
                startActivity(new Intent(SignUporLogin.this, SignupActivity.class));
            }
        });

    }

    public void forgotonclicklistener(View view) {
        Toast.makeText(getApplicationContext(),"Not created yet, have patience grasshopper.",Toast.LENGTH_SHORT).show();
    }
}
