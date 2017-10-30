// Developer: Ahmet Kaymak
// Date: 26.10.2017

package com.project.ui.signup.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.core.hospitalmodule.Clinic;
import com.project.core.hospitalmodule.Hospital;
import com.project.core.doctormodule.DoctorRegisterRequest;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDoctorSignUpThree extends Fragment {

    private String username;
    private String userId;
    private String mail;
    private String password;
    private int gender;
    private int bloodTypeId;
    private int hospitalId;
    private int clinicId;
    private Date birthday;
    private MaterialSpinner clinicSpinner;
    private MaterialSpinner hospitalSpinner;
    private Button createDoctor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View signUp = inflater.inflate( R.layout.activity_signup_doctor_three, container, false );

        clinicSpinner = (MaterialSpinner) signUp.findViewById( R.id.signUp_clinic_spinner );
        hospitalSpinner = (MaterialSpinner) signUp.findViewById( R.id.signUp_hospital_spinner );
        createDoctor = (Button) signUp.findViewById( R.id.create_profile );

        username="";
        userId="";
        mail="";
        password="";
        hospitalId=-1;
        clinicId=-1;

        //getting clinics into spinner
        try {
            ApiClient.hospitalApi().getAllClinics().enqueue( new Callback<Clinic>() {
                @Override
                public void onResponse(Call<Clinic> call, Response<Clinic> response) {
                    if (response.isSuccessful()) {
                        fillClinicSpinner( response.body().getClinics() );
                    }
                }

                @Override
                public void onFailure(Call<Clinic> call, Throwable t) {
                    Log.e( "UserHealthTree", t.getMessage() );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
        }

        //getting hospital into spinner
        try {
            ApiClient.hospitalApi().getAllHospitals().enqueue( new Callback<Hospital>() {
                @Override
                public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                    if (response.isSuccessful()) {
                        fillHospitalSpinner( response.body().getHospitals() );
                    }
                }

                @Override
                public void onFailure(Call<Hospital> call, Throwable t) {
                    Log.e( "UserHealthTree", t.getMessage() );
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
        }

        createDoctor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.isEmpty()){
                    Toast.makeText( getActivity(),getString( R.string.check_all_fields ),Toast.LENGTH_LONG );
                }else if(userId.isEmpty()){
                    Toast.makeText( getActivity(),getString( R.string.check_all_fields ),Toast.LENGTH_LONG );
                }
                else if(mail.isEmpty()){
                    Toast.makeText( getActivity(),getString( R.string.check_all_fields ),Toast.LENGTH_LONG );
                }
                else if(password.isEmpty()){
                    Toast.makeText( getActivity(),getString( R.string.check_all_fields ),Toast.LENGTH_LONG );
                }
                else if(hospitalId==-1){
                    Toast.makeText( getActivity(),getString( R.string.check_all_fields ),Toast.LENGTH_LONG );
                }
                else if(clinicId==-1){
                    Toast.makeText( getActivity(),getString( R.string.check_all_fields ),Toast.LENGTH_LONG );
                }
                else if(birthday!=null){
                    try {
                        DoctorRegisterRequest request=new DoctorRegisterRequest();
                        request.setUserId( userId );
                        request.setUserName( username );
                        request.setMail( mail );
                        request.setPassword( password );
                        request.setAboutMe( "" );
                        request.setProfilePictureId( "" );
                        request.setGender( gender );
                        request.setBloodTypeId( bloodTypeId );
                        request.setBirthday( birthday );
                        request.setHospitalId( hospitalId );
                        request.setClinicId( clinicId );
                        ApiClient.doctorApi().createDoctor(request).enqueue( new Callback<ServerResponse>() {
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

    public void setUserFields(String username,String userId,String mail,String password, int gender,int bloodTypeId,Date birthday){
        this.username=username;
        this.userId=userId;
        this.mail=mail;
        this.password=password;
        this.gender=gender;
        this.bloodTypeId=bloodTypeId;
        this.birthday=birthday;
    }

    private void fillHospitalSpinner(final ArrayList<Hospital> hospitals) {
        final List<String> hospitalList = new ArrayList();
        hospitalList.add( getString( R.string.select_hospital ) );
        for (int i = 0; i < hospitals.size(); i++) {
            hospitalList.add( hospitals.get( i ).getHospitalName() );
        }
        hospitalSpinner.setItems( hospitalList );
        hospitalSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (position != 0) {
                    hospitalId = hospitals.get( position - 1 ).getHospitalId();
                } else {
                    hospitalId = -1;
                }
            }
        } );
    }

    private void fillClinicSpinner(final ArrayList<Clinic> clinics) {
        final List<String> hospitalList = new ArrayList();
        hospitalList.add( getString( R.string.select_clinic) );
        for (int i = 0; i < clinics.size(); i++) {
            hospitalList.add( clinics.get( i ).getClinicName() );
        }
        clinicSpinner.setItems( hospitalList );
        clinicSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (position != 0) {
                    clinicId = clinics.get( position - 1 ).getClinicId();
                } else {
                    clinicId = -1;
                }
            }
        } );
    }

}

