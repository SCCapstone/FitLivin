package edu.sc.FitLivin;

import com.parse.Parse;

/**
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
