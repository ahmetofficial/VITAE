// Developer: Ahmet Kaymak
// Date: 23.07.2017

package com.project.ui.user;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;
import com.project.core.usermodule.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeAboutMeFragment extends DialogFragment {

    private String userId;
    private String aboutMe;
    private EditText aboutMeEditText;
    private TextView header;
    private Button changeButton;
    private ProgressBar progressBar;

    private User user;

    public ChangeAboutMeFragment(String userId, String aboutMe) {
        this.userId = userId;
        this.aboutMe = aboutMe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate( R.layout.fragment_change_about_me, container, false );
        getDialog().setTitle( getString( R.string.change_about_me ) );

        header = (TextView) rootView.findViewById( R.id.new_value_header );
        header.setText( getString( R.string.change_about_me ) );
        aboutMeEditText = (EditText) rootView.findViewById( R.id.new_value_edittext );
        aboutMeEditText.setText( aboutMe );
        changeButton = (Button) rootView.findViewById( R.id.new_value_button );
        progressBar = (ProgressBar) rootView.findViewById( R.id.new_value_progress_bar );

        Typeface typeLight = Typeface.createFromAsset( getActivity().getAssets(), "fonts/Roboto-Light.ttf" );
        aboutMeEditText.setTypeface( typeLight );

        changeButton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if (aboutMeEditText.getText().toString().trim().equals( aboutMe )) {
                }
                else if(aboutMeEditText.getText().toString().equals( "" )){
                    Toast.makeText( getContext(), getString( R.string.user_name_cannot_be_null ), Toast.LENGTH_LONG ).show();
                }else{
                    try {
                        user = new User();
                        user.setAboutMe(aboutMeEditText.getText().toString().trim() );
                        ApiClient.userApi().resetAboutMe( userId, user ).enqueue( new Callback<ServerResponse>() {
                            @Override
                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getStatus().equals( "true" )) {
                                        Toast.makeText( getContext(), getString( R.string.about_me_change_succesfull ), Toast.LENGTH_LONG ).show();
                                        aboutMe = aboutMeEditText.getText().toString();
                                        onDismiss( getDialog() );

                                    } else {
                                        Toast.makeText( getContext(), getString( R.string.about_me_change_unsuccesfull ), Toast.LENGTH_LONG ).show();
                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        } );
                    } catch (Exception e) {
                        Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }
            }
        } );

        return rootView;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss( getDialog() );
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss( getDialog() );
        }
    }
}
