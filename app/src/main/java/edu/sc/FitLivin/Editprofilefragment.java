package edu.sc.FitLivin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by pkcho on 2/10/2016.
 */
public class Editprofilefragment extends Fragment {
    private EditText nametext;
    private EditText gendertext;
    private EditText phonetext;
    private EditText passwordtext;
    private CheckBox checkname;
    private CheckBox checkgender;
    private CheckBox checkphone;
    private CheckBox checkpass;
    private Button buttonsave;
    private String password = "Password";
    private String username = "username";
    private String phone = "phone";
    private String gender = "gender";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editprofile, container, false);
        nametext = (EditText)v.findViewById(R.id.editTextname);
        gendertext = (EditText)v.findViewById(R.id.editgender);
        passwordtext = (EditText)v.findViewById(R.id.editpasstext);
        phonetext = (EditText)v.findViewById(R.id.editphone);
        checkgender = (CheckBox)v.findViewById(R.id.checkboxgender);
        checkname = (CheckBox)v.findViewById(R.id.checkboxname);
        checkphone = (CheckBox)v.findViewById(R.id.checkBoxphone);
        checkpass = (CheckBox)v.findViewById(R.id.checkBoxpassword);
        buttonsave = (Button)v.findViewById(R.id.savebuttoneditprofile);
        final ParseUser curruser = ParseUser.getCurrentUser();

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(nametext)&& isEmptyCheckbox(checkname)){
                    Toast.makeText(getActivity(),"You must check the box to save the changes",Toast.LENGTH_SHORT).show();
                }
                else if(isEmpty(nametext)&&!isEmptyCheckbox(checkname)){
                    Toast.makeText(getActivity(),"You must entersomething",Toast.LENGTH_SHORT).show();
                }
                else if (!isEmpty(nametext) && !isEmptyCheckbox(checkname)){
                    curruser.setUsername(nametext.getText().toString());
                    issaved(username);
                }


                if(!isEmpty(gendertext) && isEmptyCheckbox(checkgender)){
                    Toast.makeText(getActivity(),"You must check the box to save the changes",Toast.LENGTH_SHORT).show();
                }else if (isEmpty(gendertext) && !isEmptyCheckbox(checkgender)){
                    Toast.makeText(getActivity(),"You must entersomething",Toast.LENGTH_SHORT).show();
                }else if(!isEmpty(gendertext) && !isEmptyCheckbox(checkgender)){
                    curruser.put("gender",gendertext.getText().toString());
                    issaved(gender);
                }
                if(!isEmpty(passwordtext) && isEmptyCheckbox(checkpass)){
                    Toast.makeText(getActivity(),"You must check the box to save the changes",Toast.LENGTH_SHORT).show();

                }else if (isEmpty(passwordtext) && !isEmptyCheckbox(checkpass)){
                    Toast.makeText(getActivity(),"You must entersomething",Toast.LENGTH_SHORT).show();

                }
                else if (!isEmpty(passwordtext) && !isEmptyCheckbox(checkpass)){
                    curruser.setPassword(passwordtext.getText().toString());
                    issaved(password);
                }
                if(!isEmpty(phonetext) && isEmptyCheckbox(checkphone)){
                    Toast.makeText(getActivity(),"You must check the box to save the changes",Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(phonetext)&& !isEmptyCheckbox(checkphone)){
                    Toast.makeText(getActivity(),"You must entersomething",Toast.LENGTH_SHORT).show();
                }
                else if (!isEmpty(phonetext) && !isEmptyCheckbox(checkphone)){
                    curruser.put("phone",phonetext.getText().toString());
                    issaved(phone);
                }

               curruser.saveInBackground(new SaveCallback() {
                   @Override
                   public void done(ParseException e) {
                       if(e != null){
                           Toast.makeText(getActivity(),"Cannot connect to the database, please try again later",Toast.LENGTH_SHORT).show();
                       }else{
                           ProfilePageFragment fragment = new ProfilePageFragment();
                           FragmentManager fm = getFragmentManager();
                           fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
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
    private boolean isEmptyCheckbox(CheckBox checkBox){
        if(checkBox.isChecked()){
            return true;
        }
        else {
            return false;
        }
    }
    private void issaved(String something){
        Toast.makeText(getActivity(),something+" has been updated",Toast.LENGTH_SHORT).show();
    }
}
