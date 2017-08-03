// Developer: Ahmet Kaymak
// Date: 23.07.2017

package com.project.uimodule.user;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;
import com.project.usermodule.User;
import com.project.utilitiesmodule.ProgressBarAction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordFragment extends DialogFragment {

    private String userId;
    private String oldPass;
    private EditText oldPassword;
    private EditText newPassword;
    private EditText newPasswordAgain;
    private Button changePasswordButton;
    private ProgressBar progressBar;

    private User user;

    public ChangePasswordFragment(String userId, String oldPass) {
        this.userId = userId;
        this.oldPass = oldPass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate( R.layout.fragment_change_password, container, false );
        getDialog().setTitle( getString( R.string.change_password ) );

        oldPassword = (EditText) rootView.findViewById( R.id.old_password_edit_text );
        newPassword = (EditText) rootView.findViewById( R.id.new_password_edit_text );
        newPasswordAgain = (EditText) rootView.findViewById( R.id.new_password_again_edit_text );
        changePasswordButton = (Button) rootView.findViewById( R.id.change_password_button );
        progressBar = (ProgressBar) rootView.findViewById( R.id.change_password_progress_bar );

        changePasswordButton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if (oldPassword.getText().toString().trim().equals( oldPass )
                        && newPassword.getText().toString().trim().equals( newPasswordAgain.getText().toString().trim() )
                        && !newPassword.getText().toString().trim().equals( "" )) {
                    new ProgressBarAction(progressBar).execute(10);
                    try {
                        user = new User();
                        user.setPassword( newPasswordAgain.getText().toString().trim() );
                        ApiClient.userApi().resetPassword( userId, user ).enqueue( new Callback<ServerResponse>() {
                            @Override
                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                if (response.isSuccessful()) {

                                    if (response.body().getStatus().equals( "true" )) {
                                        Toast.makeText( getContext(), getString( R.string.password_change_succesfull ), Toast.LENGTH_LONG ).show();
                                        oldPass = newPassword.getText().toString();
                                        onDismiss( getDialog() );

                                    } else {
                                        Toast.makeText( getContext(), getString( R.string.password_change_unsuccesfull ), Toast.LENGTH_LONG ).show();
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
                } else if (!oldPassword.getText().toString().trim().equals( oldPass )) {
                    Toast.makeText( getActivity(), getString( R.string.enter_old_password_correctly ), Toast.LENGTH_LONG ).show();
                    oldPassword.setText( "" );
                } else if (!newPassword.getText().toString().trim().equals( newPasswordAgain.getText().toString().trim() )) {
                    Toast.makeText( getActivity(), getString( R.string.new_passwords_arent_same ), Toast.LENGTH_LONG ).show();
                    newPassword.setText( "" );
                    newPasswordAgain.setText( "" );
                } else if (newPassword.getText().toString().trim().equals( oldPass )) {
                    Toast.makeText( getActivity(), getString( R.string.new_password_same_with_old ), Toast.LENGTH_LONG ).show();
                    newPassword.setText( "" );
                    newPasswordAgain.setText( "" );
                } else {
                    Toast.makeText( getActivity(), getString( R.string.new_password_cannot_null ), Toast.LENGTH_LONG ).show();
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
