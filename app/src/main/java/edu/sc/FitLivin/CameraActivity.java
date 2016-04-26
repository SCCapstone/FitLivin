package edu.sc.FitLivin;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.desmond.squarecamera.ImageUtility;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

/**
 * Author: Parth Choksi.
 * This class uses the squarecamera library and gets result and passes it onto CameraView
 */
public class CameraActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 0;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private Point mSize;
    private EditText titleText;
    private Button Done;
    private Intent newdata;
    private Button SaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcamera);



        Done = (Button)findViewById(R.id.Done);






        Display display = getWindowManager().getDefaultDisplay();
        mSize = new Point();
        display.getSize(mSize);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//getting data as intent

        Done = (Button)findViewById(R.id.Done);

        if (resultCode != RESULT_OK) return;

        if (requestCode == REQUEST_CAMERA) {
            final Uri photoUri = data.getData();//storinf the data as Uri
            ParseUser user = ParseUser.getCurrentUser();
            // Get the bitmap in according to the width of the device
            final Bitmap bitmap = ImageUtility.decodeSampledBitmapFromPath(photoUri.getPath(), mSize.x, mSize.x);
            ((ImageView) findViewById(R.id.image)).setImageBitmap(bitmap);
            Done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        if(photoUri != null){//checking if uri not equal to null
                            final ProgressDialog dlg = new ProgressDialog(CameraActivity.this);
                            dlg.setTitle("Please wait.");
                            dlg.setMessage("Saving Picture.  Please wait.");
                            dlg.show();
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);//compressing the image and storing in background
                            byte[] byteArray = stream.toByteArray();
                            ParseFile photofile = new ParseFile("Picture.jpg", byteArray);
                            ParseUser curruser = ParseUser.getCurrentUser();
                            curruser.put("Images",photofile);
                            curruser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {

                                    if (e == null){
                                        dlg.dismiss();
                                        Toast.makeText(CameraActivity.this, "Profile Picture set", Toast.LENGTH_SHORT).show();
                                        startActivityForResult(new Intent(CameraActivity.this, MainActivity.class), 0);
                                    }
                                    else {
                                        Toast.makeText(CameraActivity.this, "Error..please check your connection", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(CameraActivity.this, "Please take a picture first", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception error){
                        error.printStackTrace();
                    }
                }
            });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void requestForCameraPermission(View view) {//requesting persmission
        final String permission = Manifest.permission.CAMERA;
        if (ContextCompat.checkSelfPermission(CameraActivity.this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(CameraActivity.this, permission)) {
                showPermissionRationaleDialog("Test", permission);
            } else {
                requestForPermission(permission);
            }
        } else {
            launch();
        }
    }

    private void showPermissionRationaleDialog(final String message, final String permission) {
        new AlertDialog.Builder(CameraActivity.this)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CameraActivity.this.requestForPermission(permission);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    private void requestForPermission(final String permission) {
        ActivityCompat.requestPermissions(CameraActivity.this, new String[]{permission}, REQUEST_CAMERA_PERMISSION);
    }

    private void launch() {//launching the camera
        Intent startCustomCameraIntent = new Intent(this, com.desmond.squarecamera.CameraActivity.class);
        startActivityForResult(startCustomCameraIntent, REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                final int numOfRequest = grantResults.length;
                final boolean isGranted = numOfRequest == 1
                        && PackageManager.PERMISSION_GRANTED == grantResults[numOfRequest - 1];
                if (isGranted) {
                    launch();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public  byte[] scaledData(byte[] data){//seperate method to compress the image
        // Resize photo from camera byte array
        Bitmap imageProfile = BitmapFactory.decodeByteArray(data, 0, data.length);
        final Bitmap imageProfileScaled = Bitmap.createScaledBitmap(imageProfile, 500, 500
                * imageProfile.getHeight() / imageProfile.getWidth(), false);



        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imageProfileScaled.compress(Bitmap.CompressFormat.JPEG, 100, bos);


        byte[] scaledData = bos.toByteArray();
        return scaledData;
    }
}