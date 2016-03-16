package edu.sc.FitLivin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by pkcho on 2/10/2016.
 */
public class Editprofilefragment extends Fragment {
    private EditText nametext;
    private EditText phonetext;
    private EditText passwordtext;
    private ImageButton buttonsave;
    private EditText reenterpass;
    private ImageButton buttonexit;
    private String password = "Password";
    private String username = "username";
    private String phone = "phone";
    private ParseUser curruser = ParseUser.getCurrentUser();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editprofile, container, false);
        nametext = (EditText)v.findViewById(R.id.editTextname);
        passwordtext = (EditText)v.findViewById(R.id.editpasstext);
        phonetext = (EditText)v.findViewById(R.id.editphone);
        reenterpass = (EditText)v.findViewById(R.id.reditpassword);

        buttonsave = (ImageButton)v.findViewById(R.id.savebuttoneditprofile);
     //   backbutton = (Button)v.findViewById(R.id.Back);

        buttonexit = (ImageButton)v.findViewById(R.id.exiteditprofile);
        buttonexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String POPUP_TITLE="Attention!";
                final String POPUP_TEXT="Are You Sure?";

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle(POPUP_TITLE);
                alert.setMessage(POPUP_TEXT);


                // Set an EditText view to get user input
                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.VERTICAL);
                alert.setView(layout);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        ProfilePageFragment fragment = new ProfilePageFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                        // Do something with value!
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }

        });
        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder(getResources().getString(R.string.error_intro));

                if (!isEmpty(passwordtext) && !isEmpty(reenterpass)) {
                    if (!isMatching(reenterpass, passwordtext)) {

                            curruser.setPassword(reenterpass.getText().toString());

                    }
                    else {
                        Toast.makeText(getActivity(),"Enter the same password twice",Toast.LENGTH_SHORT).show();
                    }
                }
                if (!isEmpty(nametext)) {
                    curruser.setUsername(nametext.getText().toString());
                }
                if(!isEmpty(phonetext)){
                    curruser.put("phone", phonetext.getText().toString());
                }
                validationErrorMessage.append(".");

                // If there is a validation error, display the error
                if (validationError) {
                    Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(getActivity());
                dlg.setTitle("Please wait.");
                dlg.setMessage("Saving Information.");
                dlg.show();

                // Set up a new Parse user




                // Call the Parse signup method
                curruser.saveInBackground(new SaveCallback() {

                    @Override
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e == null) {                                                       // Show the error message
                            Toast.makeText(getActivity(), "Info Saved", Toast.LENGTH_LONG).show();
                            ProfilePageFragment fragment = new ProfilePageFragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                            // Do something with value!
                        } else {
                            Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });



        return v;
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


    private void issaved(String something){
        Toast.makeText(getActivity(),something+" has been updated",Toast.LENGTH_SHORT).show();
    }


}
