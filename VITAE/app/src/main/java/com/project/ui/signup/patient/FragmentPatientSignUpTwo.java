// Developer: Ahmet Kaymak
// Date: 25.10.2017

package com.project.ui.signup.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.core.patientmodule.Patient;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.login.LoginActivity;
import com.project.utils.DatePickerFragment;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPatientSignUpTwo extends Fragment {

    private String username;
    private String userId;
    private String mail;
    private String password;
    private int gender;
    private int blood;
    private static Date birthday=new Date();
    private TextView birthdayText;
    public Button createPatient;
    private MaterialSpinner genderSpinner;
    private MaterialSpinner bloodSpinner;
    private DatePickerFragment newFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View signUp = inflater.inflate( R.layout.activity_signup_patient_two, container, false );

        username="";
        userId="";
        mail="";
        password="";
        birthdayText = (TextView) signUp.findViewById( R.id.txt_signup_birthday );
        createPatient = (Button) signUp.findViewById( R.id.create_profile );
        genderSpinner = (MaterialSpinner) signUp.findViewById( R.id.signUp_gender_spinner );
        bloodSpinner = (MaterialSpinner) signUp.findViewById( R.id.signUp_blood_spinner );

        newFragment = new DatePickerFragment( birthdayText, birthday );
        genderSpinner.setItems( getString( R.string.male ), getString( R.string.female ), getString( R.string.undefined ) );
        genderSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                gender = position;
            }
        } );

        bloodSpinner.setItems( getString( R.string.undefined ), "0 Rh+", "0 Rh-", "A Rh+", "A Rh-", "B Rh+", "B Rh-", "AB Rh+", "AB Rh-" );
        bloodSpinner.setDropdownHeight( 600 );
        bloodSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                blood = position;
            }
        } );


        birthdayText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFragment.show( getFragmentManager(), "Birthday" );
            }
        } );


        createPatient.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.isEmpty()){
                    Toast.makeText( getActivity(),"xyz",Toast.LENGTH_LONG );
                }else if(userId.isEmpty()){
                    Toast.makeText( getActivity(),"xyz",Toast.LENGTH_LONG );
                }
                else if(mail.isEmpty()){
                    Toast.makeText( getActivity(),"xyz",Toast.LENGTH_LONG );
                }
                else if(password.isEmpty()){
                    Toast.makeText( getActivity(),"xyz",Toast.LENGTH_LONG );
                }
                else if(birthday!=null){
                    try {
                        Patient patient = new Patient();
                        patient.setUserId( userId );
                        patient.setUserName( username );
                        patient.setMail( mail );
                        patient.setPassword( password );
                        patient.setAboutMe( "" );
                        patient.setProfilePictureId( "" );
                        patient.setGender( gender );
                        patient.setBloodTypeId( blood );
                        patient.setBirthday( newFragment.getDate() );
                        ApiClient.patientApi().createPatient(patient).enqueue( new Callback<ServerResponse>() {
                            @Override
                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getStatus().equals("true")) {
                                        Toast.makeText(getContext(), getContext().getString(R.string.user_succeffully_created), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        getContext().startActivity(intent);
                                    }else{
                                        Toast.makeText(getContext(), getContext().getString(R.string.user_cannot_created), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                Log.e("MenuActivity", t.getMessage());
                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText( getActivity(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }
            }
        } );

        return signUp;
    }

    public void setUserFields(String username,String userId,String mail,String password){
        this.username=username;
        this.userId=userId;
        this.mail=mail;
        this.password=password;
    }
}

