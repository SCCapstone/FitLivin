package edu.sc.myapplication;

import android.app.Fragment;
import android.app.ListActivity;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity{

    private List<Note> posts;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Integer weight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "KkZRFZGjA2I8mKNyuBkqgunMsVCKiWA2YrLsR3w4", "8giIFo0DzUQVHwgsl0HkXzW12n2iGj8kU1vZd90f");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo22", "bar22");
       // testObject.put()
        testObject.saveInBackground();

        posts = new ArrayList<Note>();
        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, R.layout.list_item_layout, posts);
        setListAdapter(adapter);
        refreshPostList();

       // ParseObject gameScore = new ParseObject("GameScore");
      //  gameScore.put("score", 1337);
       // gameScore.put("playerName", "Sean Plott");
       // gameScore.put("cheatMode", false);
      //  gameScore.saveInBackground();

        //Put fragment in container

        HomePageFragment firstFragment = new HomePageFragment();
        FragmentManager fm1 = getFragmentManager(); //or getFragmentManager() if you are not using support library.
       // fm1.beginTransaction().add(R.id.container, firstFragment).addToBackStack(null).commit();
    }

    //  }
    private void refreshPostList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> postList, ParseException e) {
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    posts.clear();
                    for (ParseObject post : postList) {
                        Note note = new Note(post.getObjectId(), post.getString("title"), post.getString("content"));
                        posts.add(note);
                    }
                    ((ArrayAdapter<Note>) getListAdapter()).notifyDataSetChanged();
                } else {

                }
            }
        });
    }



}
