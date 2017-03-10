// Developer: Ahmet Kaymak
// Date: 05.03.2016

package com.project.uimodule.signup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lavie.users.R;

public class FragmentOne extends Fragment {

    private EditText txt_username;
    private EditText txt_userId;
    private EditText txt_mail;
    private EditText txt_password;
    private String username;
    private String userId;
    private String mail;
    private String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View signUp1 = inflater.inflate( R.layout.fragment_signup_one, container, false );
        txt_username = (EditText) signUp1.findViewById( R.id.txt_signup_userName );
        txt_userId = (EditText) signUp1.findViewById( R.id.txt_signup_userId );
        txt_mail = (EditText) signUp1.findViewById( R.id.txt_signup_mail );
        txt_password = (EditText) signUp1.findViewById( R.id.txt_signup_password );

        userId = txt_userId.getText().toString().trim();
        username = txt_username.getText().toString().trim();
        mail = txt_mail.getText().toString().trim();
        password = txt_password.getText().toString().trim();

        return signUp1;
    }

}

