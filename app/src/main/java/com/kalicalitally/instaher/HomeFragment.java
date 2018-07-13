package com.kalicalitally.instaher;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalicalitally.instaher.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 */
public class HomeFragment extends Fragment {

    private SwipeRefreshLayout swipeContainer;
    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPost;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // once the network request has completed successfully.
                swipeContainer.setRefreshing(true);
                postAdapter.clear();
                loadTopPosts();
                swipeContainer.setRefreshing(false);

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        rvPost = (RecyclerView) view.findViewById(R.id.rvPosts);
        posts = new ArrayList<Post>();
        postAdapter = new PostAdapter(posts);



        rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPost.setAdapter(postAdapter);
        loadTopPosts();
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
                        posts.add(0,objects.get(i));
                        postAdapter.notifyDataSetChanged();
                    }

                } else {
                    e.printStackTrace();
                }
            }
        });
    }

}
