
/******
 * Class 'ProfilePageFragment'
 * Provides the user with the ability to enter weight and height attributes. User
 * will have the ability to take a picture and set it as a profile picture.
 * The user will also be able to check their bmi and see where they stand on the
 * bmi chart.
 *
 */

package edu.sc.FitLivin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
public class ProfilePageFragment extends Fragment {

    CircleImageView imageView1;
    private static final int TEXT_ID = 0;
    private static final int TEXT_ID2 = 0;
    private float multiplier = 703;

    private Button editprofile;
    Button p;
    ImageView iv;
    static final int CAM_REQUEST = 1;
    private ImageButton profileChangebutton;
    public ProfilePageFragment() {
        // Required empty public constructor
    }




    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile__page, container, false);

        imageView1 = (CircleImageView) v.findViewById(R.id.profilepicview);


        imageView1.setBackgroundColor(Color.TRANSPARENT);
        imageView1.setOnClickListener(new View.OnClickListener() {
                       @Override public void onClick(View v) {
                startActivity(new Intent(getActivity(), CameraActivity.class));
                           }
                   });

        final TextView currentHeight = (TextView) v.findViewById(R.id.CurrH);
        final TextView currentWeight = (TextView) v.findViewById(R.id.CurrW);
        final TextView underBMI = (TextView) v.findViewById(R.id.underBmi);
        final TextView normalBMI = (TextView) v.findViewById(R.id.normalBmi);
        final TextView overBMI = (TextView) v.findViewById(R.id.overBmi);
        final TextView obeseBMI = (TextView) v.findViewById(R.id.obeseBmi);
        final TextView name = (TextView) v.findViewById(R.id.name);
        Button setWeightPro = (Button) v.findViewById(R.id.weightButton);
        String s = ParseUser.getCurrentUser().getUsername();
        name.setText(s);

        /*****
         *
         * This section queries the database and sets the weight height attributes
         *
         */
        ParseQuery query = ParseQuery.getQuery("ProfileInfo"); //getting query
        query.whereExists("Weight");//setting constraints
        query.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                        currentWeight.setText(objects.get(0).get("Weight").toString()); //setting weight
                        currentHeight.setText(objects.get(0).get("Height").toString()); //setting height
                        Log.d("F", "weight");
                    }
                } else {
                    Toast.makeText(getActivity(), "Error you must enter data first.", Toast.LENGTH_SHORT).show();
                }
            }

        });


        /*****
         *
         * This section queries the database and sets the weight height attibutes as floats for bmi calculation.
         * It then runs through and calculates the bmi and sets the edit text to the value
         *
         */

        ParseQuery query2 = ParseQuery.getQuery("ProfileInfo"); //getting query
        query2.whereExists("Weight");//setting constraints
        query2.whereExists("Height");//setting constraints
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        query2.orderByDescending("createdAt");
        query2.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                        float currweight = objects.get(0).get("Weight").hashCode(); //setting weight
                        float currheight = objects.get(0).get("Height").hashCode(); //setting height

                        float bmiV = calculateBMI(currweight, currheight);

                        float bmiValue = (float) Math.round(bmiV * 1000f) / 1000f;
                        if (bmiValue < 18) {
                            underBMI.setText("" + bmiValue);
                            normalBMI.setText("");
                            overBMI.setText("");
                            obeseBMI.setText("");
                        } else if (bmiValue < 25) {
                            normalBMI.setText("" + bmiValue);
                            underBMI.setText("");
                            overBMI.setText("");
                            obeseBMI.setText("");
                        } else if (bmiValue < 30) {
                            overBMI.setText("" + bmiValue);
                            underBMI.setText("");
                            normalBMI.setText("");
                            obeseBMI.setText("");
                        } else {
                            obeseBMI.setText("" + bmiValue);
                            underBMI.setText("");
                            normalBMI.setText("");
                            overBMI.setText("");

                        }


                    }
                }
            }

        });
        /*****
         *
         * This section is for the dialog alert box. When the set attributes button is clicked
         * the alert box pops up asking the user to enter weight and height. When the user enters
         * weight and height, they can click set and the attributes will be set if they are valid.
         *
         */
        setWeightPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String POPUP_TITLE = "Attributes";
                final String POPUP_TEXT = "Please enter your weight and height";
                final String WEIGHT_HINT = "Weight(lbs)";
                final String HEIGHT_HINT = "Height(in)";
                AlertDialog.Builder alert =  new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);
                alert.setTitle(POPUP_TITLE);
                alert.setMessage(POPUP_TEXT);
                final EditText WEIGHT = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                WEIGHT.setInputType(InputType.TYPE_CLASS_NUMBER);
                WEIGHT.setHint(WEIGHT_HINT);
                final EditText HEIGHT = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                HEIGHT.setInputType(InputType.TYPE_CLASS_NUMBER);
                HEIGHT.setHint(HEIGHT_HINT);
                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(WEIGHT);
                layout.addView(HEIGHT);
                alert.setView(layout);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        MainActivity main = new MainActivity();
                        final EditText input = new EditText(getActivity());
                        input.setInputType(InputType.TYPE_CLASS_NUMBER);
                        if (isEmpty(WEIGHT)==true || isEmpty(HEIGHT)==true)
                        {
                        }
                        else{


                            MainActivity m = new MainActivity();
                            m.ExcTest = 1;
                           // String value = input.getText().toString();
                            try {
                                Integer weight = Integer.parseInt(WEIGHT.getText().toString());
                                Integer height = Integer.parseInt(HEIGHT.getText().toString());
                            } catch (NumberFormatException e) {
                                m.ExcTest = 2;
                                Toast.makeText(getActivity(), "Invalid Weight", Toast.LENGTH_LONG)
                                        .show();

                            }
                            if (m.ExcTest == 1) {
                        int w = Integer.parseInt(WEIGHT.getText().toString());
                        int h = Integer.parseInt(HEIGHT.getText().toString());
                        currentWeight.setText("" + w);
                        currentHeight.setText("" + h);
                        main.profileData(w, h, ParseUser.getCurrentUser());
                        float bmiV = calculateBMI(w, h);
                        String.format("%.3g%n", bmiV);
                        float bmiValue = (float) Math.round(bmiV * 1000f) / 1000f;// calculates bmi
                     /*****
                     *
                     *
                     * It then runs through and calculates the bmi and sets the edit text to the value
                     * for the bmi chart.
                     *
                     */
                        if (bmiValue < 18) {
                            underBMI.setText("" + bmiValue);
                            normalBMI.setText("");
                            overBMI.setText("");
                            obeseBMI.setText("");
                        } else if (bmiValue < 25) {
                            normalBMI.setText("" + bmiValue);
                            underBMI.setText("");
                            overBMI.setText("");
                            obeseBMI.setText("");
                        } else if (bmiValue < 30) {
                            overBMI.setText("" + bmiValue);
                            underBMI.setText("");
                            normalBMI.setText("");
                            obeseBMI.setText("");
                        } else {
                            obeseBMI.setText("" + bmiValue);
                            underBMI.setText("");
                            normalBMI.setText("");
                            overBMI.setText("");
                        }}
                        dialog.cancel();
                        // Do something with value!
                    }}
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                        dialog.cancel();
                    }
                });

                alert.show();
            }
        });
        /*****
         *
         * This section queries the database for the picture to set as the profile picture
         *
         */
        ParseQuery queryPropic = ParseUser.getQuery();
        queryPropic.whereEqualTo("objectId",ParseUser.getCurrentUser().getObjectId());
        queryPropic.whereExists("Images");
        queryPropic.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if(e == null && objects.size() != 0){
                     try {
                        ParseFile profilepic  = objects.get(0).getParseFile("Images");
                        byte[] bm = profilepic.getData();
                        Bitmap bmprofile = BitmapFactory.decodeByteArray(bm,0,bm.length);
                        imageView1.setImageBitmap(bmprofile);
                     }catch (ParseException error){
                        e.printStackTrace();
                         }
                                   }
                else {
                     Toast.makeText(getActivity(),"Please set a profile picture",Toast.LENGTH_SHORT);
                    }
            }
        });



        return v;
    }
    /*****
     * 'calculateBMI'
     *
     * Takes in weight and height and calculates body mass index
     *
     */
    public float calculateBMI(double weight, double height) {

        return (float) ((weight / (height * height)) * multiplier);
    }
    /*****
     * 'isEmpty'
     *
     * Checks to see if edit text is empty
     *
     */
    public boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /*****
     * 'onActivityResult'
     *
     * As this page opens, the image for the profile picture will be set
     *
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/fitLivin/FL_image.jpg";
        iv.setImageDrawable(Drawable.createFromPath(path));
    }
}