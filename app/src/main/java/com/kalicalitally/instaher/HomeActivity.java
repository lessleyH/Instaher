package com.kalicalitally.instaher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kalicalitally.instaher.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    final FragmentManager fragmentManager = getSupportFragmentManager();

    // define your fragments here
    final Fragment HomeFeed = new HomeFragment();
    final Fragment Cam = new CameraFragment();
    final Fragment UserProfile = new ProfileFragment();

//    final Fragment Details= new DetailFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationView=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.profile:
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frag_home ,UserProfile).commit();
                            return true;
                        case R.id.home:
                            FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                            fragmentTransaction2.replace(R.id.frag_home, HomeFeed).commit();
                            return true;
                        case R.id.camera:
                            FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                            fragmentTransaction3.replace(R.id.frag_home, Cam).commit();
                            return true;
                    }

                    return false;
                }
            };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_home, HomeFeed).commit();

        BottomNavigationView navigation= (BottomNavigationView)findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(bottomNavigationView);

    }



//private void createPost(String description, ParseFile imageFile, ParseUser user){
//        //TODO: create and save post
//
//    final Post newPost = new Post();
//    newPost.setDescription(description);
//  //  newPost.setImage(imageFile);
//    newPost.setUser(user);
//
//    newPost.saveInBackground(new SaveCallback() {
//        @Override
//        public void done(ParseException e) {
//            if (e == null) {
//                Log.d("HomeActivity", "Create Post called and done :)!!");
//            } else {
//                e.printStackTrace();
//            }
//        }
//    });
//}
//
//
//private void loadTopPosts() {
//
//    final Post.Query postQuery = new Post.Query();
//    postQuery.getTop().withUser();
//
//    postQuery.findInBackground(new FindCallback<Post>() {
//        @Override
//        public void done(List<Post> objects, ParseException e) {
//            if (e == null) {
//                for (int i = 0; i < objects.size(); ++i) {
//                    Log.d("HomeActivity", " \n Hey Hey Post[" + i + "] = "
//                            + objects.get(i).getDescrption());
//                    Log.d("HomeActivity", "\n Hey Hey username = "
//                            + objects.get(i).getUser().getUsername());
//                }
//            } else {
//                e.printStackTrace();
//            }
//        }
//    });
//}
}