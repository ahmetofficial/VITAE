// Developer: Ahmet Kaymak
// Date: 14.03.2017

package com.project.uimodule.hospitalpage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.hospitalmodule.Hospital;
import com.project.restservice.ApiClient;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalProfileActivity extends AppCompatActivity{

    private ImageView profilePicture;
    private Bitmap bitmap;
    private TextView hospitalName, hospitalType, hospitalPhone, hospitalMail , hospitalTotalCount, hospitalOverallRate;
    private TextView hospitalTotalCountLabel, hospitalOverallRateLabel;
    private MaterialRatingBar hospitalRatingBar;
    public static int hospitalId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hospital);

        hospitalName= (TextView) findViewById( R.id.hospital_activity_hospital_name );
        hospitalName.setGravity( Gravity.CENTER_VERTICAL);
        hospitalType= (TextView) findViewById( R.id.hospital_activity_hospital_type );
        hospitalType.setGravity( Gravity.CENTER_VERTICAL);
        hospitalPhone= (TextView) findViewById( R.id.hospital_activity_hospital_phone );
        hospitalPhone.setGravity( Gravity.CENTER_VERTICAL);
        hospitalMail= (TextView) findViewById( R.id.hospital_activity_hospital_mail );
        hospitalMail.setGravity( Gravity.CENTER_VERTICAL);
        hospitalTotalCount= (TextView)findViewById( R.id.hospital_activity_total_vote_count );
        hospitalTotalCountLabel= (TextView)findViewById( R.id.hospital_activity_total_vote_count_label );
        hospitalOverallRate= (TextView)findViewById( R.id.hospital_activity_overall_rate );
        hospitalOverallRateLabel= (TextView)findViewById( R.id.hospital_activity_overall_rate_label );
        hospitalRatingBar= (MaterialRatingBar) findViewById( R.id.hospital_activity_rating_bar );

        try {
            profilePicture = (ImageView) findViewById( R.id.hospital_activity_profile_image_view );
            bitmap = BitmapFactory.decodeResource( getResources(), R.drawable.hospital_profile_picture );
            Bitmap circleBitmap = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888 );
            BitmapShader shader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
            Paint paint = new Paint();
            paint.setShader( shader );
            paint.setAntiAlias( true );
            Canvas c = new Canvas( circleBitmap );
            c.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint );
            profilePicture.setImageBitmap( circleBitmap );
        }catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
        fillHospitalAreas( hospitalId );
    }

    private void fillHospitalAreas(int hospitalId){
        try {
            ApiClient.hospitalApi().searchByHospitalId(hospitalId).enqueue( new Callback<Hospital>() {
                @Override
                public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                    if (response.isSuccessful()) {
                        Hospital h=response.body().getHospital();
                        hospitalName.setText( h.getHospitalName() );

                        if (h.getHospitalType() == 0) {
                            hospitalType.setText( getBaseContext().getResources().getString( R.string.state_hospital ));
                        } else {
                            hospitalType.setText( getBaseContext().getResources().getSystem().getString( R.string.private_hospital ));
                        }
                        hospitalMail.setText( h.getMail() );
                        hospitalPhone.setText( h.getPhoneNumber() );

                        hospitalTotalCountLabel.setText( getString( R.string.total_rating_count ) );
                        hospitalOverallRateLabel.setText( getString( R.string.overall_score ) );
                        hospitalTotalCount.setText( String.valueOf(h.getTotalVoteNumber()) );
                        hospitalOverallRate.setText( String.valueOf(h.getOverallScore()) );
                        float hospitalRating=(float) h.getOverallScore();
                        hospitalRatingBar.setRating( hospitalRating );
                    }
                }

                @Override
                public void onFailure(Call<Hospital> call, Throwable t) {
                    Log.e("UserTimeline", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
