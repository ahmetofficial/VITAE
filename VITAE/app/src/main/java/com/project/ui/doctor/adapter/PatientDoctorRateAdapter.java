// Developer: Ahmet Kaymak
// Date: 29.09.2017

package com.project.ui.doctor.adapter;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.project.core.diseasemodule.Disease;
import com.project.core.doctormodule.PatientDoctorRate;
import com.project.core.usermodule.User;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;

import io.github.rockerhieu.emojicon.EmojiconTextView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class PatientDoctorRateAdapter extends RecyclerView.Adapter<PatientDoctorRateAdapter.MyViewHolder> {
    private List<PatientDoctorRate> doctorRates = new ArrayList<>();
    private static Context context;
    private String visitorUserId;

    public PatientDoctorRateAdapter(String visitorUserId, List<PatientDoctorRate> doctorRates, Context context) {
        this.visitorUserId = visitorUserId;
        this.doctorRates = doctorRates;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView userPhoto;
        public TextView userName, ratingTimestamp;
        public EmojiconTextView comment;
        public MaterialRatingBar doctorRating;

        public MyViewHolder(final View view) {
            super( view );
            userPhoto = (CircleImageView) view.findViewById( R.id.user_review_profile_picture );
            userName = (TextView) view.findViewById( R.id.user_review_user_name );
            userName.setTypeface( Typefaces.getRobotoBold( context ) );
            ratingTimestamp = (TextView) view.findViewById( R.id.user_review_timestamp );
            ratingTimestamp.setTypeface( Typefaces.getRobotoLight( context ) );
            doctorRating = (MaterialRatingBar) view.findViewById( R.id.user_review_rating_bar );
            comment = (EmojiconTextView) view.findViewById( R.id.user_review_comment );
            comment.setTypeface( Typefaces.getRobotoMedium( context ) );
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_patient_doctor_review, parent, false );

        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        try {
            PatientDoctorRate rate = doctorRates.get( position );
            User patient=rate.getPatient();
            Disease disease=rate.getDisease();
            holder.userName.setText( patient.getUserName());
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                    rate.getCreatedAt().getTime(),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );

            holder.ratingTimestamp.setText( timeAgo );
            holder.doctorRating.setRating( (float) rate.getUserRate() );


            String photoId = patient.getProfilePictureId();
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

            if(rate.getUserComment()!=null){
                holder.comment.setText( rate.getUserComment() );
            }else{
                holder.comment.setVisibility( View.GONE );
            }
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public int getItemCount() {
        return doctorRates.size();
    }
}