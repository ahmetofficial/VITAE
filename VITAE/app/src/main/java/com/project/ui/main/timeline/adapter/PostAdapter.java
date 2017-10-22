// Developer: Ahmet Kaymak
// Date: 22.02.2017

package com.project.ui.main.timeline.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.project.core.postmodule.UserPost;
import com.project.core.postmodule.UserPostLike;
import com.project.restservice.ApiClient;
import com.project.restservice.serverresponse.ServerResponse;
import com.project.ui.patient.PatientActivity;
import com.project.utils.Typefaces;
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
        public TextView userId, postText, timestamp, url, postInteractionInformation;
        public ImageView postPhoto;
        public LikeButton likebutton;
        public CircleImageView profilePicture;
        public ImageView dotsVertical;

        public MyViewHolder(View view) {
            super( view );
            profilePicture = (CircleImageView) view.findViewById( R.id.postProfilePicture );
            userId = (TextView) view.findViewById( R.id.postUserName );
            postText = (TextView) view.findViewById( R.id.postText );
            timestamp = (TextView) view.findViewById( R.id.postTimestamp );
            postPhoto = (ImageView) view.findViewById( R.id.postPhoto );
            url = (TextView) view.findViewById( R.id.postURL );
            postInteractionInformation = (TextView) view.findViewById( R.id.postInteractionInformation );
            likebutton = (LikeButton) view.findViewById( R.id.postLike );
            dotsVertical = (ImageView) view.findViewById( R.id.dots_vertical );
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
        holder.userId.setText( userPost.getUserId() );
        holder.postText.setText( userPost.getPostText() );
        holder.postText.setTypeface( Typefaces.getRobotoLight( context ) );

        if(!userId.equals( userPost.getUserId() )){
            holder.dotsVertical.setVisibility( View.GONE );
        }

        final int[] likeCount = {userPost.getLikeCount()};
        final int commentCount = userPost.getCommentCount();
        holder.postInteractionInformation.setText( String.valueOf( likeCount[0] ) + " "
                + context.getString( R.string.like_count ) + ", " + String.valueOf( commentCount )
                + " " + context.getString( R.string.comment_count ) );
        holder.postInteractionInformation.setTypeface( Typefaces.getRobotoBold( context ) );

        String photoId = userPost.getUser().getProfilePictureId();
        if (!photoId.equals( "" )) {
            String picturePath = "http://178.62.223.153:3000/images/" + photoId.charAt( 0 ) + "/"
                    + photoId.charAt( 1 ) + "/" + photoId.charAt( 2 ) + "/" + photoId.charAt( 3 ) + "/" + photoId.charAt( 4 ) + "/"
                    + photoId.charAt( 5 ) + "/" + photoId.charAt( 6 ) + "/" + photoId.charAt( 7 ) + "/" + photoId.charAt( 9 ) + "/"
                    + photoId.charAt( 10 ) + "/" + photoId.charAt( 11 ) + "/" + photoId.charAt( 12 ) + "/" + photoId + ".jpg";

            Glide.with( context )
                    .load( picturePath )
                    .apply(new RequestOptions()
                            .fitCenter() // keep memory usage low by fitting into (w x h) [optional]
                            .dontAnimate()
                    )
                    .into( holder.profilePicture );
        }else{
            Bitmap bitmap = BitmapFactory.decodeResource( context.getResources(), R.drawable.empty_profile );
            Bitmap circleBitmap = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888 );
            BitmapShader shader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
            Paint paint = new Paint();
            paint.setShader( shader );
            paint.setAntiAlias( true );
            Canvas c = new Canvas( circleBitmap );
            c.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint );
            holder.profilePicture.setImageBitmap( circleBitmap );
        }

        if (userPost.getPostLikes() != null) {
            for (int i = 0; i < userPost.getPostLikes().size(); i++) {
                if (userPost.getPostLikes().get( i ).getUserId().equals( userId )) {
                    holder.likebutton.setLiked( true );
                    break;
                }
            }
        } else {
            holder.likebutton.setLiked( false );
        }

        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                userPost.getCreatedAt().getTime(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
        holder.timestamp.setText( timeAgo );
        holder.url.setText( userPost.getUrl() );

        if (userPost.getUserPostHavePhotos().size() != 0) {
            String postPhotoId = userPost.getUserPostHavePhotos().get( 0 ).getPhotoId();
            String picturePath = "http://178.62.223.153:3000/images/" + postPhotoId.charAt( 0 ) + "/"
                    + postPhotoId.charAt( 1 ) + "/" + postPhotoId.charAt( 2 ) + "/" + postPhotoId.charAt( 3 ) + "/" + postPhotoId.charAt( 4 ) + "/"
                    + postPhotoId.charAt( 5 ) + "/" + postPhotoId.charAt( 6 ) + "/" + postPhotoId.charAt( 7 ) + "/" + postPhotoId.charAt( 9 ) + "/"
                    + postPhotoId.charAt( 10 ) + "/" + postPhotoId.charAt( 11 ) + "/" + postPhotoId.charAt( 12 ) + "/" + postPhotoId + ".jpg";
            Glide.with( context )
                    .load( picturePath )
                    .apply( new RequestOptions()
                            .fitCenter() // keep memory usage low by fitting into (w x h) [optional]
                            .dontAnimate()
                            .centerCrop()
                            .dontTransform()
                    )
                    .into( holder.postPhoto );
        } else {
            holder.postPhoto.setVisibility( View.GONE );
        }

        holder.profilePicture.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (userPost.getUserId().equals( userId )) {

                    } else {
                        Intent intent = new Intent( context, PatientActivity.class );
                        intent.putExtra( "visitorId", userId );
                        intent.putExtra( "visitedId", userPost.getUserId() );
                        context.startActivity( intent );
                    }
                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );

        holder.likebutton.setOnLikeListener( new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                try {
                    likeCount[0] += 1;
                    holder.likebutton.setLiked( true );
                    holder.postInteractionInformation.setText( String.valueOf( likeCount[0] ) + " "
                            + context.getString( R.string.like_count ) + ", " + String.valueOf( commentCount )
                            + " " + context.getString( R.string.comment_count ) );
                    like( userPost.getPostId(), userId );
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                try {
                    likeCount[0] -= 1;
                    holder.likebutton.setLiked( false );
                    holder.postInteractionInformation.setText( String.valueOf( likeCount[0] ) + " "
                            + context.getString( R.string.like_count ) + ", " + String.valueOf( commentCount )
                            + " " + context.getString( R.string.comment_count ) );
                    unlike( userPost.getPostId(), userId );
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

            }
        } );
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
