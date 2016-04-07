/******
 * Class 'ProfilePageFragment'
 *
 * Provides the user with the ability to enter their name, weight, and height
 *
 */

package edu.sc.FitLivin;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//import com.tutorialsface.roundimage.R;

public class ProfilePageFragment extends Fragment {

    ImageView imageView1;
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
        //getActivity().getActionBar()
               // .setTitle("Profile");
       MainActivity main = new MainActivity();
       ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Profile");

           p = (Button) v.findViewById(R.id.pic);

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });
        imageView1 = (CircleImageView) v.findViewById(R.id.profilepicview);
      //  Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.don);

        imageView1.setBackgroundColor(Color.TRANSPARENT);

        final TextView heightText = (TextView) v.findViewById(R.id.heightView);
        final TextView weightText = (TextView) v.findViewById(R.id.weightView);
        final TextView VeiwName = (TextView) v.findViewById(R.id.name);

        //final TextView currentName = (TextView) v.findViewById(R.id.CurrN);
        final Button takepic = (Button) v.findViewById(R.id.pic);
        final Button editprofileinfo = (Button) v.findViewById(R.id.editprofilebutton);
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

        //pic = (Button) v.findViewById(R.id.pic);






        ParseQuery query = ParseQuery.getQuery("ProfileInfo"); //getting query
        query.whereExists("Weight");//setting constraints
        query.whereContains("ObjectId", ParseUser.getCurrentUser().getObjectId());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() != 0) { //if objects size is not 0

                    if (objects.get(0).get("UserP").equals(ParseUser.getCurrentUser())) {
                        currentWeight.setText(objects.get(objects.size() - 1).get("Weight").toString()); //setting weight
                        currentHeight.setText(objects.get(objects.size() - 1).get("Height").toString()); //setting height
                        Log.d("F", "weight");
                    }
                } else {
                    Toast.makeText(getActivity(), "Error you must enter data first.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        editprofileinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editprofilefragment frag = new Editprofilefragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, frag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


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
                        float bmiValue = (float)Math.round(bmiV * 1000f) / 1000f;
                        if(bmiValue<18){
                            underBMI.setText(""+bmiValue);
                            normalBMI.setText("");
                            overBMI.setText("");
                            obeseBMI.setText("");
                        }
                        else if(bmiValue<25){
                            normalBMI.setText(""+bmiValue);
                            underBMI.setText("");
                            overBMI.setText("");
                            obeseBMI.setText("");
                        }
                        else if(bmiValue<30){
                            overBMI.setText(""+bmiValue);
                            underBMI.setText("");
                            normalBMI.setText("");
                            obeseBMI.setText("");
                        }
                        else{
                            obeseBMI.setText(""+bmiValue);
                            underBMI.setText("");
                            normalBMI.setText("");
                            overBMI.setText("");

                        }


                    }
                }
            }

        });
        setWeightPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


         final String POPUP_TITLE="Attributes";
        final String POPUP_TEXT="Please enter your weight and height";
        final String WEIGHT_HINT="Weight(lbs)";
        final String HEIGHT_HINT="Height(in)";



        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setTitle(POPUP_TITLE);
        alert.setMessage(POPUP_TEXT);

        // Set an EditText view to get user input
        final EditText WEIGHT = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
        WEIGHT.setHint(WEIGHT_HINT);
        final EditText HEIGHT = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);

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
                int w = Integer.parseInt(WEIGHT.getText().toString());
                int h = Integer.parseInt(HEIGHT.getText().toString());
                currentWeight.setText("" + w);
                currentHeight.setText("" + h);
                main.profileData(w, h, ParseUser.getCurrentUser());
                float bmiV = calculateBMI(w, h);
                String.format("%.3g%n", bmiV);
                float bmiValue = (float)Math.round(bmiV * 1000f) / 1000f;
                if(bmiValue<18){
                    underBMI.setText(""+bmiValue);
                    normalBMI.setText("");
                    overBMI.setText("");
                    obeseBMI.setText("");
                }
                else if(bmiValue<25){
                    normalBMI.setText(""+bmiValue);
                    underBMI.setText("");
                    overBMI.setText("");
                    obeseBMI.setText("");
                }
                else if(bmiValue<30){
                    overBMI.setText(""+bmiValue);
                    underBMI.setText("");
                    normalBMI.setText("");
                    obeseBMI.setText("");
                }
                else{
                    obeseBMI.setText(""+bmiValue);
                    underBMI.setText("");
                    normalBMI.setText("");
                    overBMI.setText("");

                }
                dialog.cancel();
                // Do something with value!
            }
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




      /*  editprofile = (Button) v.findViewById(R.id.editprofile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editprofilefragment fragment = new Editprofilefragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            }
        });*/
       /* final MainActivity main1 = new MainActivity();
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           main1.launchCamera(v);
            }
        });
*/
        /**
        takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CameraActivity.class));
            }
        });
         **/


        return v;
    }
private File getFile(){
    File folder = new File("sdcard/fitLivin_app");
    if(!folder.exists()){
        folder.mkdir();
    }
    File image_file = new File(folder, "FL_image.jpg");
            return image_file;
}
    public float calculateBMI(double weight, double height) {

        return (float) ((weight / (height * height)) * multiplier);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/fitLivin/FL_image.jpg";
        iv.setImageDrawable(Drawable.createFromPath(path));
    }
}
