// Developer: Ahmet Kaymak
// Date: 04.03.2016

package com.project.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmetkaymak.vitae.R;
import com.project.ui.BaseActivity;
import com.project.utils.Typefaces;

public class SignUpMainActivity extends BaseActivity{

    private ImageView patient;
    private ImageView doctor;
    private ImageView hospital;
    private TextView patientText;
    private TextView doctorText;
    private TextView hospitalText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup_main );

        patient= (ImageView) findViewById( R.id.patient_icon );
        doctor= (ImageView) findViewById( R.id.doctor_icon);
        hospital= (ImageView) findViewById( R.id.hospital_icon );
        patientText = (TextView) findViewById( R.id.patient_text );
        doctorText = (TextView) findViewById( R.id.doctor_text );
        hospitalText = (TextView) findViewById( R.id.hospital_text );

        patientText.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
        doctorText.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
        hospitalText.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );

        patient.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getBaseContext(), SignUpUserActivity.class );
                intent.putExtra( "userTypeId",1 );
                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                getBaseContext().startActivity( intent );
            }
        } );

        doctor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getBaseContext(), SignUpUserActivity.class );
                intent.putExtra( "userTypeId",2 );
                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                getBaseContext().startActivity( intent );
            }
        } );

    }

}

