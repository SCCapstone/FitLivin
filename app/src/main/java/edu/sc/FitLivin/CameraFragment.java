package edu.sc.FitLivin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by pkcho on 3/23/2016.
 */
public class CameraFragment extends Fragment {
    public static final String TAG = "CameraFragment";


    private Camera camera;
    private SurfaceView surfaceView;
    private ParseFile photoFile;
    private ImageButton photoButton;


    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_camera, parent, false);
        photoButton = (ImageButton) v.findViewById(R.id.camera_photo_button);

        if (camera == null) {
            try {
                camera = android.hardware.Camera.open() ;
                Camera.Parameters params = camera.getParameters();
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                camera.setParameters(params);

                photoButton.setEnabled(true);
            } catch (Exception e) {
                Log.e(TAG, "No camera with exception: " + e.getMessage());
                photoButton.setEnabled(false);
                Toast.makeText(getActivity(), "No camera detected",
                        Toast.LENGTH_LONG).show();
            }
        }


        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (camera == null)
                    return;
                camera.takePicture(new Camera.ShutterCallback() {

                    @Override
                    public void onShutter() {
                        // nothing to do
                    }

                }, null, new Camera.PictureCallback() {

                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        saveScaledPhoto(data);
                    }

                });

            }
        });
        surfaceView = (SurfaceView) v.findViewById(R.id.camera_surface_view);
        SurfaceHolder holder = surfaceView.getHolder();


        holder.addCallback(new SurfaceHolder.Callback() {

            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (camera != null) {
                        camera.setDisplayOrientation(90);
                        camera.setPreviewDisplay(holder);
                        camera.startPreview();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error setting up preview", e);
                }
            }

            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                // nothing to do here
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                // nothing here
            }

        });

        return v;
    }
    private void saveScaledPhoto(byte[] data) {

        // Resize photo from camera byte array
        Bitmap imageProfile = BitmapFactory.decodeByteArray(data, 0, data.length);
        final Bitmap imageProfileScaled = Bitmap.createScaledBitmap(imageProfile, 500, 500
                * imageProfile.getHeight() / imageProfile.getWidth(), false);

        // Override Android default landscape orientation and save portrait
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        final Bitmap rotatedScaledprofileImage = Bitmap.createBitmap(imageProfileScaled, 0,
                0, imageProfileScaled.getWidth(), imageProfileScaled.getHeight(),
                matrix, true);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        rotatedScaledprofileImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);


        byte[] scaledData = bos.toByteArray();



        // Save the scaled image to Parse
        ParseUser user = ParseUser.getCurrentUser();
        photoFile = new ParseFile("Picture.jpg", scaledData);
        user.put("Images",photoFile);
        user.saveInBackground(new SaveCallback() {

            @Override
            public void done(com.parse.ParseException e) {
                if (e != null) {
                    Toast.makeText(getActivity(),
                            "Error saving: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),
                            "Photo Saved",
                            Toast.LENGTH_LONG).show();
                    ProfilePageFragment frag = new ProfilePageFragment();
                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack("ProfilePageFragment",
                            FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.container, frag);
                    ft.addToBackStack(null);
                    ft.commit();
                    imageProfileScaled.recycle();
                }
            }


        });
    }

    public void onResume() {
        super.onResume();
        if (camera == null) {
            try {
                camera = Camera.open();
                photoButton.setEnabled(true);
            } catch (Exception e) {
                Log.i(TAG, "No camera: " + e.getMessage());
                photoButton.setEnabled(false);
                Toast.makeText(getActivity(), "No camera detected",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    public void onPause() {
        if (camera != null) {
            camera.stopPreview();

            camera.release();

        }
        super.onPause();
    }
      public void onBackPressed() {
         Toast.makeText(getActivity(),"you must take a picture",Toast.LENGTH_SHORT).show();
     }

}
