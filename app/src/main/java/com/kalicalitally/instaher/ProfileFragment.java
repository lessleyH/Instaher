package com.kalicalitally.instaher;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.kalicalitally.instaher.model.Post;
import com.parse.FindCallback;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 *
 */
public class ProfileFragment extends Fragment {

    private Button SignOutBtn;

    static final int REQUEST_IMAGE_CAPTURE =1;


    public final String APP_TAG = "MyCustomApp";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";
    File photoFile;

    private ImageButton camPic;
    ImageView ivPreview;
    private Button postBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SignOutBtn = view.findViewById(R.id.logOut_btn);
        SignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
            }
        });
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(APP_TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview

                ivPreview.setImageBitmap(takenImage);

                postBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final File file = getPhotoFileUri(photoFileName);
                        final ParseFile pFile = new ParseFile(file);

                    }
                });
            } else { // Result was a failure
                Toast.makeText(getActivity(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }
//    private void loadTopPosts() {
//
//        final Post.Query postQuery = new Post.Query();
//        postQuery.getTop().withUser();
//
//        postQuery.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> objects, ParseException e) {
//                if (e == null) {
//                    for (int i = 0; i < objects.size(); ++i) {
//                        Log.d("HomeActivity", " \n Hey Hey Post[" + i + "] = "
//                                + objects.get(i).getDescrption());
//                        Log.d("HomeActivity", "\n Hey Hey username = "
//                                + objects.get(i).getUser().getUsername());
//                    }
//                } else {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
