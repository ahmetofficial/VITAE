// Developer: Ahmet Kaymak
// Date: 19.04.2017

package com.project.ui.seach.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.project.core.usermodule.User;
import com.project.ui.doctor.DoctorActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorSearchAdapter extends RecyclerView.Adapter<DoctorSearchAdapter.MyViewHolder> {
    private List<User> users = new ArrayList<>();
    private static Context context;
    private String visitorUserId;

    public DoctorSearchAdapter(List<User> users, Context context, String visitorUserId) {
        this.users = users;
        this.context = context;
        this.visitorUserId = visitorUserId;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userId, similarityPercent;
        public CircleImageView userPhoto;
        private CardView userCardView;

        public MyViewHolder(final View view) {
            super( view );
            userName = (TextView) view.findViewById( R.id.search_item_user_name_text );
            userId = (TextView) view.findViewById( R.id.search_item_user_id_text );
            similarityPercent = (TextView) view.findViewById( R.id.search_item_user_similarity_percent );
            userPhoto = (CircleImageView) view.findViewById( R.id.search_item_user_profile_picture );
            userCardView = (CardView) view.findViewById( R.id.user_search_item );
        }

    }

    @Override
    public DoctorSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_search_user, parent, false );

        return new DoctorSearchAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final DoctorSearchAdapter.MyViewHolder holder, final int position) {
        try {
            User user = users.get( position );
            holder.userName.setText( user.getUserName() );
            holder.userId.setText( user.getUserId() );

            String photoId = user.getProfilePictureId();
            if (!photoId.equals( "" )) {
                String picturePath = "http://178.62.223.153:3000/images/" + photoId.charAt( 0 ) + "/"
                        + photoId.charAt( 1 ) + "/" + photoId.charAt( 2 ) + "/" + photoId.charAt( 3 ) + "/" + photoId.charAt( 4 ) + "/"
                        + photoId.charAt( 5 ) + "/" + photoId.charAt( 6 ) + "/" + photoId.charAt( 7 ) + "/" + photoId.charAt( 9 ) + "/"
                        + photoId.charAt( 10 ) + "/" + photoId.charAt( 11 ) + "/" + photoId.charAt( 12 ) + "/" + photoId + ".jpg";

                Glide.with( context )
                        .load( picturePath )
                        .into( holder.userPhoto );
            }else{
                Bitmap bitmap = BitmapFactory.decodeResource( context.getResources(), R.drawable.empty_profile );
                Bitmap circleBitmap = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888 );
                BitmapShader shader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
                Paint paint = new Paint();
                paint.setShader( shader );
                paint.setAntiAlias( true );
                Canvas c = new Canvas( circleBitmap );
                c.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint );
                holder.userPhoto.setImageBitmap( circleBitmap );
            }
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        holder.userCardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String visitedUserId = holder.userId.getText().toString();
                    Intent intent = new Intent( context, DoctorActivity.class );
                    intent.putExtra( "visitorId", visitorUserId );
                    intent.putExtra( "visitedId", visitedUserId );
                    context.startActivity( intent );
                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
