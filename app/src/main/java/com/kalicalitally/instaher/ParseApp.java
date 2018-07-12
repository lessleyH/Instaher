package com.kalicalitally.instaher;

import android.app.Application;

import com.kalicalitally.instaher.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration config = new Parse.Configuration.Builder(this)
                .applicationId("snapple")
                .clientKey("HiHelloHolaMerhabaMerhabanGutentag")
                .server("http://instaher.herokuapp.com/parse")
                .build();

        Parse.initialize(config);
    }
}
