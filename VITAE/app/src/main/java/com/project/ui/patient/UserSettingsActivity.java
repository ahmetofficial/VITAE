// Developer: Ahmet Kaymak
// Date: 23.07.2017

package com.project.ui.patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.project.core.patientmodule.Patient;
import com.project.core.photomodule.Photo;
import com.project.core.usermodule.User;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.user.ChangeAboutMeFragment;
import com.project.ui.user.ChangeMailFragment;
import com.project.ui.user.ChangePasswordFragment;
import com.project.ui.user.ChangeUserNameFragment;
import com.project.utils.FileUtils;
import com.project.utils.Typefaces;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSettingsActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    private String userId;
    private int userTypeId;
    private CircleImageView profilePicture;

    private NotificationManager mNotifyManager;
    private Notification.Builder mBuilder;
    private int id = 1;
    private Uri uri;

    private TextView userName;
    private TextView userIde;
    private TextView aboutMe;
    private TextView password;
    private TextView mail;
    private TextView bloodAlarmPreference;
    private TextView similarUserSearchPreference;
    private ImageButton bloodAlarmSwitch;
    private ImageButton similarUserSearchSwitch;
    private boolean isBloodAlarmClicked;
    private boolean isSimilarUserSearchClicked;

    private ChangePasswordFragment changePasswordFragment;
    private ChangeUserNameFragment changeUserNameFragment;
    private ChangeAboutMeFragment changeAboutMeFragment;
    private ChangeMailFragment changeMailFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_patient_settings );

        Intent myIntent = getIntent();
        userId = myIntent.getStringExtra( "userId" );
        userTypeId = myIntent.getIntExtra( "userTypeId", 0 );
        userName = (TextView) findViewById( R.id.activity_patient_settings_user_name );
        userIde = (TextView) findViewById( R.id.activity_patient_settings_user_id );
        password = (TextView) findViewById( R.id.activity_patient_settings_password );
        aboutMe = (TextView) findViewById( R.id.activity_patient_settings_about_me );
        mail = (TextView) findViewById( R.id.activity_patient_settings_mail );
        profilePicture = (CircleImageView) findViewById( R.id.activity_patient_settings_profile_picture );
        bloodAlarmSwitch = (ImageButton) findViewById( R.id.blood_alarm_check_box );
        bloodAlarmPreference = (TextView) findViewById( R.id.blood_alarm_preference );
        bloodAlarmPreference.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
        similarUserSearchSwitch = (ImageButton) findViewById( R.id.similar_user_search_check_box );
        similarUserSearchPreference = (TextView) findViewById( R.id.similar_user_search_preference );
        similarUserSearchPreference.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );

        password.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if (!password.getText().toString().equals( "" )) {
                    FragmentManager fm = getSupportFragmentManager();
                    changePasswordFragment = new ChangePasswordFragment( userId, password.getText().toString() );
                    changePasswordFragment.show( fm, "Change Password" );
                }

            }
        } );

        userName.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if (!userName.getText().toString().equals( "" )) {
                    FragmentManager fm = getSupportFragmentManager();
                    changeUserNameFragment = new ChangeUserNameFragment( userId, userName.getText().toString() );
                    changeUserNameFragment.show( fm, "Change User Name" );
                }

            }
        } );

        aboutMe.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if (!userName.getText().toString().equals( "" )) {
                    FragmentManager fm = getSupportFragmentManager();
                    changeAboutMeFragment = new ChangeAboutMeFragment( userId, aboutMe.getText().toString() );
                    changeAboutMeFragment.show( fm, "Change About Me" );
                }

            }
        } );

        mail.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if (!userName.getText().toString().equals( "" )) {
                    FragmentManager fm = getSupportFragmentManager();
                    changeMailFragment = new ChangeMailFragment( userId, mail.getText().toString() );
                    changeMailFragment.show( fm, "Change Mail" );
                }

            }
        } );

        bloodAlarmSwitch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBloodAlarmClicked) {
                    isBloodAlarmClicked = false;
                    bloodAlarmSwitch.setImageDrawable( getResources().getDrawable( R.drawable.ic_close_white_18dp, getTheme() ) );
                    disableBloodAlarms( userId );
                } else {
                    isBloodAlarmClicked = true;
                    bloodAlarmSwitch.setImageDrawable( getResources().getDrawable( R.drawable.ic_check_white_24dp, getTheme() ) );
                    enableBloodAlarms( userId );
                }
            }
        } );

        similarUserSearchSwitch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSimilarUserSearchClicked) {
                    isSimilarUserSearchClicked = false;
                    similarUserSearchSwitch.setImageDrawable( getResources().getDrawable( R.drawable.ic_close_white_18dp, getTheme() ) );
                    disableSimilarUserSearch( userId );
                } else {
                    isSimilarUserSearchClicked = true;
                    similarUserSearchSwitch.setImageDrawable( getResources().getDrawable( R.drawable.ic_check_white_24dp, getTheme() ) );
                    enableSimilarUserSearch( userId );
                }
            }
        } );

        Typeface typeLight = Typeface.createFromAsset( getAssets(), "fonts/Roboto-Light.ttf" );
        userName.setTypeface( typeLight );
        userIde.setTypeface( typeLight );
        password.setTypeface( typeLight );
        aboutMe.setTypeface( typeLight );
        mail.setTypeface( typeLight );

        getPatientProfileInformations( userId, userTypeId );

        //making back arrow on toolbar
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setTitle( "" );


        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        if (checkPermissionREAD_EXTERNAL_STORAGE( this )) {
            //choosing photo for profile picture

            profilePicture.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    //only images
                    intent.setType( "image/*" );
                    intent.setAction( Intent.ACTION_GET_CONTENT );
                    startActivityForResult( Intent.createChooser( intent, "Select Picture" ), PICK_IMAGE_REQUEST );

                }
            } );
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mNotifyManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
            mBuilder = new Notification.Builder( this );
            mBuilder.setContentTitle( getString( R.string.upload ) )
                    .setContentText( getString( R.string.content_uploading ) )
                    .setSmallIcon( R.drawable.icon_upload );

            uri = data.getData();
            new UploaderNotification( uri ).execute();

            try {
                //image upload
                File file = FileUtils.getFile( this, uri );
                RequestBody photoFile = RequestBody.create( MediaType.parse( getContentResolver().getType( uri ) ), file );
                MultipartBody.Part photoPart = MultipartBody.Part.createFormData( "photo", "picture", photoFile );
                Photo.uploadProfilePhoto( userId, photoPart, getBaseContext() );
            } catch (Exception e) {
            }
        }
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission( context,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE )) {
                    showDialog( "External storage", context,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE );

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE );
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context, final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder( context );
        alertBuilder.setCancelable( true );
        alertBuilder.setTitle( "Permission necessary" );
        alertBuilder.setMessage( msg + " permission is necessary" );
        alertBuilder.setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions( (Activity) context,
                        new String[]{permission},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE );
            }
        } );
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText( this, "GET_ACCOUNTS Denied", Toast.LENGTH_SHORT ).show();
                }
                break;
            default:
                super.onRequestPermissionsResult( requestCode, permissions,
                        grantResults );
        }
    }

    private void getPatientProfileInformations(String userId, int userTypeId) {
        try {
            if (userTypeId == 1) {
                ApiClient.patientApi().getPatientProfileInformation( userId ).enqueue( new Callback<Patient>() {
                    @Override
                    public void onResponse(Call<Patient> call, Response<Patient> response) {
                        if (response.isSuccessful()) {
                            userName.setText( response.body().getUserName() );
                            userIde.setText( response.body().getUserId() );
                            password.setText( response.body().getPassword() );
                            aboutMe.setText( response.body().getAboutMe() );
                            mail.setText( response.body().getMail() );

                            if (response.body().getPatients().get( 0 ).getIsBloodAlarmNotificationOpen() == 0) {
                                isBloodAlarmClicked = false;
                                bloodAlarmSwitch.setImageDrawable( getResources().getDrawable( R.drawable.ic_close_white_18dp, getTheme() ) );
                            } else {
                                isBloodAlarmClicked = true;
                                bloodAlarmSwitch.setImageDrawable( getResources().getDrawable( R.drawable.ic_check_white_24dp, getTheme() ) );
                            }

                            if (response.body().getPatients().get( 0 ).getIsSimilarPatientSearchOpen() == 0) {
                                isSimilarUserSearchClicked = false;
                                similarUserSearchSwitch.setImageDrawable( getResources().getDrawable( R.drawable.ic_close_white_18dp, getTheme() ) );
                            } else {
                                isSimilarUserSearchClicked = true;
                                similarUserSearchSwitch.setImageDrawable( getResources().getDrawable( R.drawable.ic_check_white_24dp, getTheme() ) );
                            }
                            String photoId = response.body().getProfilePictureId();
                            if (!photoId.equals( "" )) {
                                String picturePath = "http://178.62.223.153:3000/images/" + photoId.charAt( 0 ) + "/"
                                        + photoId.charAt( 1 ) + "/" + photoId.charAt( 2 ) + "/" + photoId.charAt( 3 ) + "/" + photoId.charAt( 4 ) + "/"
                                        + photoId.charAt( 5 ) + "/" + photoId.charAt( 6 ) + "/" + photoId.charAt( 7 ) + "/" + photoId.charAt( 9 ) + "/"
                                        + photoId.charAt( 10 ) + "/" + photoId.charAt( 11 ) + "/" + photoId.charAt( 12 ) + "/" + photoId + ".jpg";

                                Glide.with( getBaseContext() )
                                        .load( picturePath )
                                        .into( profilePicture );
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<Patient> call, Throwable t) {
                        Log.e( "UserTimeline", t.getMessage() );
                        Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                } );
            } else if (userTypeId == 2) {
                ApiClient.userApi().getUserInformation( userId ).enqueue( new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            userName.setText( response.body().getUserName() );
                            userIde.setText( response.body().getUserId() );
                            password.setText( response.body().getPassword() );
                            aboutMe.setText( response.body().getAboutMe() );
                            mail.setText( response.body().getMail() );

                            String photoId = response.body().getProfilePictureId();
                            if (!photoId.equals( "" )) {
                                String picturePath = "http://178.62.223.153:3000/images/" + photoId.charAt( 0 ) + "/"
                                        + photoId.charAt( 1 ) + "/" + photoId.charAt( 2 ) + "/" + photoId.charAt( 3 ) + "/" + photoId.charAt( 4 ) + "/"
                                        + photoId.charAt( 5 ) + "/" + photoId.charAt( 6 ) + "/" + photoId.charAt( 7 ) + "/" + photoId.charAt( 9 ) + "/"
                                        + photoId.charAt( 10 ) + "/" + photoId.charAt( 11 ) + "/" + photoId.charAt( 12 ) + "/" + photoId + ".jpg";

                                Glide.with( getBaseContext() )
                                        .load( picturePath )
                                        .into( profilePicture );
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e( "UserTimeline", t.getMessage() );
                        Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                } );
            }

        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    public class UploaderNotification extends AsyncTask<Void, Integer, Integer> {

        private Uri uri;

        public UploaderNotification(Uri uri) {
            this.uri = uri;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBuilder.setProgress( 100, 0, false );
            mNotifyManager.notify( id, mBuilder.build() );
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mBuilder.setProgress( 100, values[0], false );
            mNotifyManager.notify( id, mBuilder.build() );
            super.onProgressUpdate( values );
        }

        @Override
        protected Integer doInBackground(Void... params) {
            int i;
            for (i = 0; i <= 100; i += 5) {
                publishProgress( Math.min( i, 100 ) );
                try {
                    // Sleep for 5 seconds
                    Thread.sleep( 2 * 1000 );
                } catch (InterruptedException e) {
                    Log.d( "TAG", "sleep failure" );
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute( result );
            mBuilder.setContentText( getResources().getString( R.string.photo_upload_succesfull ) );
            mBuilder.setProgress( 0, 0, false );
            mNotifyManager.notify( id, mBuilder.build() );
            //image set profile picture
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), uri );
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap circleBitmap = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888 );
            BitmapShader shader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
            Paint paint = new Paint();
            paint.setShader( shader );
            paint.setAntiAlias( true );
            Canvas c = new Canvas( circleBitmap );
            c.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint );
            profilePicture.setImageBitmap( circleBitmap );
        }
    }

    public void changeUserName(String userName) {
        this.userName.setText( userName );
    }

    private void enableBloodAlarms(String userId) {
        ApiClient.patientApi().enableBloodAlarms( userId ).enqueue( new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals( "true" )) {
                    }

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e( "UserTimeline", t.getMessage() );
                Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
            }
        } );
    }

    private void disableBloodAlarms(String userId) {
        ApiClient.patientApi().disableBloodAlarms( userId ).enqueue( new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals( "true" )) {
                    }

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e( "UserTimeline", t.getMessage() );
                Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
            }
        } );
    }

    private void enableSimilarUserSearch(String userId) {
        ApiClient.patientApi().enableSimilarUserSearch( userId ).enqueue( new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals( "true" )) {
                    }

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e( "UserTimeline", t.getMessage() );
                Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
            }
        } );
    }

    private void disableSimilarUserSearch(String userId) {
        ApiClient.patientApi().disableSimilarUserSearch( userId ).enqueue( new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals( "true" )) {
                    }

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e( "UserTimeline", t.getMessage() );
                Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
            }
        } );
    }

}
