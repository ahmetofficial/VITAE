// Developer: Ahmet Kaymak
// Date: 25.10.2017

package com.project.ui.signup.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ahmetkaymak.vitae.R;

public class FragmentPatientSignUpOne extends Fragment {

    private EditText usernameText;
    private EditText userIdText;
    private EditText mailText;
    private EditText passwordText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View signUp = inflater.inflate( R.layout.activity_signup_patient_one, container, false );

        usernameText = (EditText) signUp.findViewById( R.id.txt_signup_userName );
        userIdText = (EditText) signUp.findViewById( R.id.txt_signup_userId );
        mailText = (EditText) signUp.findViewById( R.id.txt_signup_mail );
        passwordText = (EditText) signUp.findViewById( R.id.txt_signup_password );

        return signUp;
    }

    public String getUsername() {
        return usernameText.getText().toString().trim();
    }

    public String getUserId() {
        return userIdText.getText().toString().trim();
    }

    public String getMail() {
        return mailText.getText().toString().trim();
    }

    public String getPassword() {
        return passwordText.getText().toString().trim();
    }
}

