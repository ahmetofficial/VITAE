package com.project.ui.main.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.project.core.messagemodule.Message;
import com.project.restservice.ApiClient;
import com.project.ui.main.message.adapter.MessageAdapter;
import com.project.utils.WifiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {

    private String senderId;
    private String receiverId;
    private String conversationId;
    private String userId;
    private Message message;

    private EditText messageInputText;
    private RecyclerView recyclerView;
    private ArrayList<Message> messageList = new ArrayList();
    private MessageAdapter mAdapter;

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

        Intent myIntent = getIntent();
        senderId = myIntent.getStringExtra( "senderId" );
        receiverId = myIntent.getStringExtra( "receiverId" );
        conversationId = myIntent.getStringExtra( "conversationId" );
        userId = myIntent.getStringExtra( "userId" );

        message=new Message();
        message.setConversationId( conversationId );
        message.setSenderId( senderId );
        message.setReceiverId( receiverId );

        messageInputText = (EditText) findViewById( R.id.message_input );
        recyclerView = (RecyclerView) findViewById( R.id.message_recycler_view );
        getMessages(message);

        socket.connect();
        socket.on( "new message", handleIncomingMessages );

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getBaseContext() );
        recyclerView.setLayoutManager( mLayoutManager);
        recyclerView.setAdapter( mAdapter );

        ImageButton sendButton = (ImageButton) findViewById( R.id.send_button );
        sendButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        } );
    }


    private void getMessages(Message message) {
        try {
            ApiClient.messageApi().getMessages(message).enqueue( new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if (response.isSuccessful()) {
                        messageList = (ArrayList) response.body().getMessages();
                        mAdapter = new MessageAdapter( messageList, userId, getBaseContext() );
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
            Toast.makeText(getBaseContext() , e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void sendMessage() {
        String message = messageInputText.getText().toString().trim();
        messageInputText.setText( "" );
        JSONObject messageJson = new JSONObject();
        try {
            messageJson.put( "conversation_id", conversationId );
            messageJson.put( "sender_id", senderId );
            messageJson.put( "receiver_id", receiverId );
            messageJson.put( "message_text", message );
            messageJson.put( "sender_ip", WifiUtils.getIpAdress( getBaseContext() ) );
            socket.emit( "new message", messageJson );
        } catch (JSONException e) {

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    private void addMessage(String messageText, String senderId) {
        Message newMessage=new Message();
        newMessage.setMessageText( messageText );
        newMessage.setSenderId( senderId );
        messageList.add( newMessage );
        mAdapter = new MessageAdapter( messageList,userId, getBaseContext() );
        mAdapter.notifyDataSetChanged();
        scrollToBottom();
    }

    private void scrollToBottom() {
        recyclerView.scrollToPosition( mAdapter.getItemCount()-1 );
    }

    private Emitter.Listener handleIncomingMessages = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread( new Runnable() {
                @Override
                public void run() {
                    String messageText=args[0].toString(), senderId=args[1].toString();
                    addMessage(messageText,senderId);
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
