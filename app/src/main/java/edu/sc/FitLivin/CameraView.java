package edu.sc.FitLivin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This class is a back up Camera and is depricated from the app.
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private Context mContext;
    public CameraView(Context context, Camera camera){
        super(context);
        mContext = context;

        mCamera = camera;
        mCamera.setDisplayOrientation(90);
        //get the holder and set this class as the callback, so we can get camera data here
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

    }

    private void saveScaledPhoto(byte[] data) {
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
        ParseFile images = new ParseFile("Images",scaledData);
        ParseUser user = ParseUser.getCurrentUser();
        user.put("Images", scaledData);

        user.saveInBackground();
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try{
            //when the surface is created, we can set the camera to draw images in this surfaceholder
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();

        } catch (IOException e) {
            Log.d("ERROR", "Camera error on surfaceCreated " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        //before changing the application orientation, you need to stop the preview, rotate and then start it again
        if(mHolder.getSurface() == null)//check if the surface is ready to receive camera data
            return;

        try{
            mCamera.stopPreview();
        } catch (Exception e){
            //this will happen when you are trying the camera if it's not running
        }

        //now, recreate the camera preview
        try{
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("ERROR", "Camera error on surfaceChanged " + e.getMessage());
        }
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //our app has only one screen, so we'll destroy the camera in the surface
        //if you are unsing with more screens, please move this code your activity
        mCamera.stopPreview();
        mCamera.release();
    }
}
