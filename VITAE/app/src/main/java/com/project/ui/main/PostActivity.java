// Developer: Ahmet Kaymak
// Date: 03.04.2017

package com.project.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.core.postmodule.UserPost;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;
import com.project.restservice.ServerResponseWithPhotoId;
import com.project.utils.FileUtils;
import com.project.utils.WifiUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private EditText postText;
    private Button sendPostButton;
    private ImageView uploadImageIcon;
    private ImageView postPhoto;
    public static String userId;

    private NotificationManager mNotifyManager;
    private Notification.Builder mBuilder;
    private int id = 1;
    private Uri uri;
    private int PICK_IMAGE_REQUEST = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_post );

            postText = (EditText) findViewById( R.id.activity_post_txt_post );
            sendPostButton = (Button) findViewById( R.id.activity_post_btn_post );
            uploadImageIcon = (ImageView) findViewById( R.id.upload_image_icon );
            postPhoto = (ImageView) findViewById( R.id.post_photo );
            InputMethodManager imm = (InputMethodManager) getSystemService( getBaseContext().INPUT_METHOD_SERVICE );
            imm.showSoftInput( postText, InputMethodManager.SHOW_IMPLICIT );


            if (checkPermissionREAD_EXTERNAL_STORAGE( this )) {
                //choosing photo for profile picture

                uploadImageIcon.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        //only images
                        intent.setType( "image/*" );
                        intent.setAction( Intent.ACTION_GET_CONTENT );
                        startActivityForResult( Intent.createChooser( intent, "Select Picture" ), PICK_IMAGE_REQUEST );
                    }
                } );
            }


            sendPostButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (file != null) {
                        if (!postText.getText().toString().equals( "" )) {
                            UserPost newPost = new UserPost();
                            newPost.setUser_id( userId );
                            newPost.setPost_text( postText.getText().toString() );
                            newPost.setUrl( null );
                            try {
                                newPost.setUserIp( WifiUtils.getIpAdress( getBaseContext() ) );
                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            }
                            RequestBody photoFile = RequestBody.create( MediaType.parse( getContentResolver().getType( uri ) ), file );
                            MultipartBody.Part photoPart = MultipartBody.Part.createFormData( "photo", "picture", photoFile );
                            uploadPhotoForUserPost( newPost, userId, photoPart, getBaseContext() );
                        }
                    } else {
                        if (!postText.getText().toString().equals( "" )) {
                            UserPost newPost = new UserPost();
                            newPost.setUser_id( userId );
                            newPost.setPost_text( postText.getText().toString() );
                            newPost.setUrl( null );
                            try {
                                newPost.setUserIp( WifiUtils.getIpAdress( getBaseContext() ) );
                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            }
                            createUserPost( userId, newPost );
                        }
                    }
                }

            } );

        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    public void uploadPhotoForUserPost(final UserPost userPost, final String userId, MultipartBody.Part photoPart, final Context context) {
        ApiClient.imageApi().uploadUserPostPhoto( userId, photoPart ).enqueue( new Callback<ServerResponseWithPhotoId>() {
            @Override
            public void onResponse(Call<ServerResponseWithPhotoId> call, Response<ServerResponseWithPhotoId> response) {
                if (response.isSuccessful()) {
                    String photoId=response.body().getPhotoId();
                    if(photoId!=null) {
                        addPhotoToUserPost( userId, userPost,photoId , context );
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponseWithPhotoId> call, Throwable t) {
                //Log.e( "UserTimeline", t.getMessage() );
                Toast.makeText( context, t.getMessage(), Toast.LENGTH_LONG ).show();
            }
        } );
    }

    private void addPhotoToUserPost(String userId, UserPost userPost, String photoId, final Context context) {
        ApiClient.postApi().createNewPostWithPhoto( userId, photoId, userPost ).enqueue( new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals( true )) ;
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                //Log.e( "UserTimeline", t.getMessage() );
                Toast.makeText( context, t.getMessage(), Toast.LENGTH_LONG ).show();
            }
        } );
    }

    private void createUserPost(String userId, UserPost userPost) {
        try {
            ApiClient.postApi().createNewPost( userId, userPost ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals( "true" )) {
                            startActivity( new Intent( PostActivity.this, MenuActivity.class ) );
                        }
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
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
                file = FileUtils.getFile( this, uri );
                Bitmap bitmap = null;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                try {
                    bitmap = BitmapFactory.decodeStream( new FileInputStream( file ), null, options );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                postPhoto.setImageBitmap( bitmap );
            } catch (Exception e) {
            }
        }
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
            postPhoto.setImageBitmap( circleBitmap );
        }
    }
}
