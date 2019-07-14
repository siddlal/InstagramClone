package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
//        Parse.initialize(new Parse.Configuration.Builder(this)
//                .applicationId("BfoEGInTjieB8xbyE0oXrJ1uU6dsDnZOOF0fVPdN")
//                // if defined
//                .clientKey("3SRn6W1xALlmLZb2CvsZBtZAi8kioBdJBFIFrRZ5")
//                .server("http://parseapi.back4app.com/")
//                .build()





    }
}
