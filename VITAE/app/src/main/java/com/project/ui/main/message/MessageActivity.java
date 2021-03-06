// Developer: Ahmet Kaymak
// Date: 22.02.2017

package com.project.ui.main.message;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.alexzh.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.project.core.messagemodule.Message;
import com.project.core.usermodule.User;
import com.project.restservice.ApiClient;
import com.project.ui.main.message.adapter.MessageAdapter;
import com.project.utils.Typefaces;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {

    private User sender;
    private User receiver;
    private String senderUserId;
    private String senderUserName;
    private String senderPhotoPath;
    private String receiverUserId;
    private String receiverUserName;
    private String receiverPhotoPath;
    private String conversationId;
    private Message message;

    private EditText messageInputText;
    private RecyclerView recyclerView;
    private ArrayList<Message> messageList = new ArrayList();
    private MessageAdapter mAdapter;
    private CircleImageView receiverPhoto;
    private TextView receiverIdText;
    private ImageView backButton;

    private Socket socket;

    {
        try {
            socket = IO.socket( ApiClient.BASE_URL );
        } catch (URISyntaxException e) {
            throw new RuntimeException( e );
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_message );


        sender = new User();
        receiver = new User();
        Intent myIntent = getIntent();
        senderUserId = myIntent.getStringExtra( "senderUserId" );
        senderUserName = myIntent.getStringExtra( "senderUserName" );
        senderPhotoPath = myIntent.getStringExtra( "senderPhotoPath" );
        receiverUserId = myIntent.getStringExtra( "receiverUserId" );
        receiverUserName = myIntent.getStringExtra( "receiverUserName" );
        receiverPhotoPath = myIntent.getStringExtra( "receiverPhotoPath" );
        conversationId = myIntent.getStringExtra( "conversationId" );

        sender.setUserId( senderUserId );
        sender.setUserName( senderUserName );
        sender.setProfilePictureId( senderPhotoPath );
        receiver.setUserId( receiverUserId );
        receiver.setUserName( receiverUserName );
        receiver.setProfilePictureId( receiverPhotoPath );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "" );

        message = new Message();
        message.setConversationId( conversationId );
        message.setSenderId( senderUserId );
        message.setReceiverId( receiverUserId );

        messageInputText = (EditText) findViewById( R.id.message_input );
        messageInputText.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
        recyclerView = (RecyclerView) findViewById( R.id.message_recycler_view );
        receiverIdText = (TextView) findViewById( R.id.receiver_id );
        receiverPhoto = (CircleImageView) findViewById( R.id.receiver_photo );
        backButton = (ImageView) findViewById( R.id.back_image );

        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        receiverIdText.setText( receiver.getUserName() );
        receiverIdText.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );
        if (!receiver.getProfilePictureId().equals( "" )) {
            Glide.with( getBaseContext() )
                    .load( receiver.getProfilePictureId() )
                    .into( receiverPhoto );
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource( getBaseContext().getResources(), R.drawable.empty_profile );
            Bitmap circleBitmap = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888 );
            BitmapShader shader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
            Paint paint = new Paint();
            paint.setShader( shader );
            paint.setAntiAlias( true );
            Canvas c = new Canvas( circleBitmap );
            c.drawCircle( bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint );
            receiverPhoto.setImageBitmap( circleBitmap );
        }
        getMessages( message, sender, receiver );

        socket.connect();
        socket.on( "new message", handleIncomingMessages );

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
        recyclerView.setLayoutManager( mLayoutManager );
        recyclerView.setAdapter( mAdapter );

        ImageButton sendButton = (ImageButton) findViewById( R.id.send_button );
        sendButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        } );
    }


    private void getMessages(Message message, final User sender, final User receiver) {
        try {
            ApiClient.messageApi().getMessages( message ).enqueue( new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if (response.isSuccessful()) {
                        messageList = (ArrayList) response.body().getMessages();
                        mAdapter = new MessageAdapter( messageList, sender, receiver, getBaseContext() );
                        recyclerView.setHasFixedSize( true );
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
                        recyclerView.setLayoutManager( mLayoutManager );
                        recyclerView.setItemAnimator( new DefaultItemAnimator() );
                        recyclerView.setAdapter( mAdapter );
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void sendMessage() {
        String message = messageInputText.getText().toString().trim();
        messageInputText.setText( "" );
        JSONObject messageJson = new JSONObject();
        try {
            addMessage( message,senderUserId);
            messageJson.put( "conversation_id", conversationId );
            messageJson.put( "sender_id", senderUserId );
            messageJson.put( "receiver_id", receiverUserId );
            messageJson.put( "message_text", message );
            //messageJson.put( "sender_ip", WifiUtils.getIpAdress( getBaseContext() ) );
            socket.emit( "new message", messageJson );
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void addMessage(String messageText, String senderId) {
        Message newMessage = new Message();
        newMessage.setMessageText( messageText );
        newMessage.setSenderId( senderId );
        messageList.add( newMessage );
        mAdapter = new MessageAdapter( messageList, receiver, sender, getBaseContext() );
        mAdapter.notifyDataSetChanged();
        scrollToBottom();
    }

    private void scrollToBottom() {
        recyclerView.scrollToPosition( mAdapter.getItemCount() - 1 );
    }

    private Emitter.Listener handleIncomingMessages = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread( new Runnable() {
                @Override
                public void run() {
                    String messageText = args[0].toString(), senderId = args[1].toString();
                    String receiverId=args[2].toString();
                    if(receiverId.equals( senderUserId )) {
                        addMessage( messageText, senderId );
                    }
                }
            } );
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }
}
