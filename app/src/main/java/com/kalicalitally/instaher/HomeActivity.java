package com.kalicalitally.instaher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kalicalitally.instaher.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

//image path
    private EditText descriptionInput;
    private Button createBttn;
    private Button refreshBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        descriptionInput = findViewById(R.id.etDescription);
        createBttn = findViewById(R.id.btnCreate);
        refreshBttn = findViewById(R.id.btnRefresh);

        createBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description = descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();
                    //add the image later

                //TODO: this is still hard coded
                final File file = new File("yo ");

                final ParseFile parseFile = new ParseFile(file);

                createPost(description, parseFile, user);
            }
        });

        refreshBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTopPosts();
            }
        });



        loadTopPosts();
    }
private void createPost(String description, ParseFile imageFile, ParseUser user){
        //TODO: create and save post

    final Post newPost = new Post();
    newPost.setDescription(description);
    newPost.setImage(imageFile);
    newPost.setUser(user);


}


private void loadTopPosts() {

    final Post.Query postQuery = new Post.Query();
    postQuery.getTop().withUser();

    postQuery.findInBackground(new FindCallback<Post>() {
        @Override
        public void done(List<Post> objects, ParseException e) {
            if (e == null) {
                for (int i = 0; i < objects.size(); ++i) {
                    Log.d("HomeActivity", " \n Hey Hey Post[" + i + "] = "
                            + objects.get(i).getDescrption());
                    Log.d("HomeActivity", "\n Hey Hey username = "
                            + objects.get(i).getUser().getUsername());
                }
            } else {
                e.printStackTrace();
            }
        }
    });
}
}
