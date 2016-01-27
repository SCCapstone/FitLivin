package edu.sc.FitLivin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by pkcho on 1/13/2016.
 * This class sets up a user to be entered into parse in a class called User
 */

public class SignupActivity extends Activity {

    private EditText usernameView;
    private EditText passwordView;
    private EditText passwordAgainView;
    private EditText emailView;
    private EditText phnumberView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        usernameView = (EditText) findViewById(R.id.username_in);
        passwordView = (EditText) findViewById(R.id.password_in);
        passwordAgainView = (EditText) findViewById(R.id.retypepasswrd_in);
        emailView = (EditText) findViewById(R.id.email_in);
        phnumberView = (EditText) findViewById(R.id.phonenumber_in);

        RadioButton maleRadioButton, femaleRadioButton;

        maleRadioButton = (RadioButton) findViewById(R.id.radio0);
        femaleRadioButton = (RadioButton) findViewById(R.id.radio1);
        if (maleRadioButton.isChecked() || femaleRadioButton.isChecked()) {
            Log.d("QAOD", "Gender is Selected");
        } else {
            Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
            Log.d("QAOD", "Gender is Null");
        }

        findViewById(R.id.email_signup_button_in).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Validate the sign up data
                boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder(getResources().getString(R.string.error_intro));
                if (isEmpty(usernameView)) {
                    validationError = true;
                    validationErrorMessage.append("enter your username");
                }
                if(isEmpty(emailView)){
                    validationError = true;
                    validationErrorMessage.append("enter an email");
                }
                if(isEmpty(phnumberView)){
                    validationError = true;
                    validationErrorMessage.append("enter your phone number");
                }
                if (isEmpty(passwordView)) {
                    if (validationError) {
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter a password");
                }
                if (!isMatching(passwordView, passwordAgainView)) {
                    if (validationError) {
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter the same password twice");
                }
                validationErrorMessage.append(".");

                // If there is a validation error, display the error
                if (validationError) {
                    Toast.makeText(SignupActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(SignupActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Signing up.  Please wait.");
                dlg.show();

                // Set up a new Parse user
                ParseUser user = new ParseUser();
                user.setUsername(usernameView.getText().toString());
                user.setPassword(passwordView.getText().toString());
                user.setEmail(emailView.getText().toString());
                user.put("phone", phnumberView.getText().toString());

                RadioButton maleRadioButton, femaleRadioButton;

                maleRadioButton = (RadioButton) findViewById(R.id.radio0);
                femaleRadioButton = (RadioButton) findViewById(R.id.radio1);
                if (maleRadioButton.isChecked()) {

                 user.put("gender","male");
                }
                if (femaleRadioButton.isChecked()) {
                    user.put("gender","female");
                }
                // Call the Parse signup method
                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            // Show the error message
                            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            // Start an intent for the dispatch activity
                            Intent intent = new Intent(SignupActivity.this, DispatchActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(EditText etText1, EditText etText2) {
        if (etText1.getText().toString().equals(etText2.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}
