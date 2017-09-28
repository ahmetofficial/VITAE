// Developer: Ahmet Kaymak
// Date: 22.02.2017

package com.project.ui.main.timeline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.project.core.postmodule.UserPost;
import com.project.core.postmodule.UserPostLike;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;
import com.project.utils.WifiUtils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private List<UserPost> userPosts = new ArrayList<>();
    private String userId;
    private Context context;

    public PostAdapter(List<UserPost> userPosts, String userId, Context context) {
        this.userPosts = userPosts;
        this.userId = userId;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_id, post_text, timestamp, url, likeCount, commentCount;
        public LikeButton likebutton;

        public MyViewHolder(View view) {
            super( view );
            user_id = (TextView) view.findViewById( R.id.postUserName );
            post_text = (TextView) view.findViewById( R.id.postText );
            timestamp = (TextView) view.findViewById( R.id.postTimestamp );
            url = (TextView) view.findViewById( R.id.postURL );
            likeCount = (TextView) view.findViewById( R.id.postLikeCount );
            commentCount = (TextView) view.findViewById( R.id.postCommentCount );
            likebutton = (LikeButton) view.findViewById( R.id.postLike );
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_post, parent, false );

        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final UserPost userPost = userPosts.get( position );
        holder.user_id.setText( userPost.getUser_id() );
        holder.post_text.setText( userPost.getPost_text() );
        holder.commentCount.setText( String.valueOf( userPost.getCommentCount() ) );
        holder.likeCount.setText( String.valueOf( userPost.getLikeCount() ) );

        if (userPost.getPostLikes() != null) {
            for (int i = 0; i < userPost.getPostLikes().size(); i++) {
                if (userPost.getPostLikes().get( i ).getUserId().equals( userId )) {
                    holder.likebutton.setLiked( true );
                    break;
                }
            }
        }else{
            holder.likebutton.setLiked( false );
        }

        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                userPost.getCreatedAt().getTime(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
        holder.timestamp.setText( timeAgo );
        holder.url.setText( userPost.getUrl() );

        holder.likebutton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                try {
                    like( userPost.getPostId(), userId );
                    holder.likebutton.setLiked( true );
                    holder.likeCount.setText( String.valueOf(Integer.valueOf(holder.likeCount.getText().toString())+1) );
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                try {
                    unlike( userPost.getPostId(), userId );
                    holder.likebutton.setLiked( false );
                    holder.likeCount.setText( String.valueOf(Integer.valueOf(holder.likeCount.getText().toString())-1) );
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return userPosts.size();
    }

    public void unlike(String postId, String userId) throws UnknownHostException {
        UserPostLike userPostLike = new UserPostLike();
        userPostLike.setUserIp( WifiUtils.getIpAdress( context ) );
        userPostLike.setUserId( userId );
        userPostLike.setPostId( postId );
        try {
            ApiClient.postApi().unlike( userPostLike ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {

                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    public void like(String postId, String userId) throws UnknownHostException {
        UserPostLike userPostLike = new UserPostLike();
        userPostLike.setUserId( userId );
        userPostLike.setPostId( postId );
        try {
            ApiClient.postApi().like( userPostLike ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {

                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
