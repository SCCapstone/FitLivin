package edu.sc.FitLivin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.PushService;
import com.parse.SaveCallback;

/**
 * Created by pkcho on 1/13/2016.
 * This class initializes parse and registers subclass.
 * We can add as many subclasses we want to this file
 * this will be initialized as soon as the app starts.
 */
public class Application extends android.app.Application{
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);//enables the database
        Parse.initialize(this, getString(R.string.parse_application_Id), getString(R.string.parse_application_Key));

    }



}
