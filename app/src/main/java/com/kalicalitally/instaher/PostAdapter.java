package com.kalicalitally.instaher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.kalicalitally.instaher.model.Post;

import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

        private List<Post> mPosts;
        Context context;
        //pass in twt array into constructor
        public PostAdapter(List<Post>posts){
            mPosts= posts;

        }
        //for each row, inflate the layout and cache references into viewHolder layout

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View tweetView = inflater.inflate(R.layout.item_post, parent, false);
            ViewHolder viewHolder = new ViewHolder(tweetView);
            return viewHolder;
        }

        //bind the values based on the pos of the element

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            //get the data according to pos
            Post post = mPosts.get(position);

            //populate the views according to this data
            holder.usernameTop.setText(post.getUser().getUsername());
            holder.timeStamp.setText(post.getRelativeTimeAgo());
            holder.descriptions.setText(post.getDescrption());

            GlideApp.with(context).load(post.getImage().getUrl())
                    .into(holder.postImg);
        }
        @Override
        public int getItemCount(){
            return mPosts.size();
        }

        //create viewHolder class

        public static class ViewHolder extends  RecyclerView.ViewHolder{
            public ImageView postImg;
            public ImageView likes;
            public ImageView comment;
            public ImageView userTop;
            public ImageView dM;
            public ImageView extraMenu;
            public ImageView book;
            public TextView views;
            public TextView usernameTop;
            public TextView usernameBottom;
            public TextView comments;
            public TextView descriptions;
            public TextView userB;
            public TextView addComment;
            public TextView timeStamp;

            public ViewHolder(View itemView){
                super(itemView);

                //perform findViewById lookups

                postImg = (ImageView) itemView.findViewById(R.id.ivPost);
                userTop = (ImageView) itemView.findViewById(R.id.ivUserTop);
                views = (TextView) itemView.findViewById(R.id.tvViews);
                usernameTop = (TextView) itemView.findViewById(R.id.tvUsernameTop);
                userB = (TextView) itemView.findViewById(R.id.tvUsernameBottom);
                descriptions = (TextView) itemView.findViewById(R.id.tvDescriptions);
                timeStamp = (TextView) itemView.findViewById(R.id.tvTimeStamp);

            }
        }
        // Clean all elements of the recycler
        public void clear() {
            mPosts.clear();
            notifyDataSetChanged();
        }

        // Add a list of items -- change to type used
        public void addAll(List<Post> list) {
            mPosts.addAll(list);
            notifyDataSetChanged();
        }
    }
