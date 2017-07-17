// Developer: Ahmet Kaymak
// Date: 22.02.2017

package com.project.uimodule.main.timeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ahmetkaymak.vitae.R;
import com.project.postmodule.UserPost;
import java.util.ArrayList;
import java.util.List;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.MyViewHolder>  {
    private List<UserPost> userPosts =new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_id, post_text, timestamp, url;

        public MyViewHolder(View view) {
            super(view);
            user_id = (TextView) view.findViewById(R.id.postUserName);
            post_text = (TextView) view.findViewById(R.id.postText);
            timestamp = (TextView) view.findViewById(R.id.postTimestamp);
            url = (TextView) view.findViewById(R.id.postURL);
        }
    }

    public UserPostAdapter(List<UserPost> userPosts) {
        this.userPosts = userPosts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserPost userPost = userPosts.get(position);
        holder.user_id.setText(userPost.getUser_id());
        holder.post_text.setText(userPost.getPost_text());

        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                userPost.getCreated_at().getTime(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timestamp.setText(timeAgo);

        holder.url.setText(userPost.getUrl());
    }

    @Override
    public int getItemCount() {
        return userPosts.size();
    }
}
