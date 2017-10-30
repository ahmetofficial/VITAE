// Developer: Ahmet Kaymak
// Date: 13.10.2017

package com.project.ui.location;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.core.generalhealthmodule.BloodAlarm;
import com.project.core.hospitalmodule.Hospital;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.utils.Typefaces;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCreateBloodAlarm extends DialogFragment {

    private String userId;
    private TextView bloodAlarmHeader;
    private TextView selectedHospitalName;
    private TextView createBloodAlarm;
    private EditText contactNumber;
    private MaterialSpinner bloodSpinner;
    private MaterialSpinner hospitalSpinner;
    private BubbleSeekBar alarmLevel;
    private ViewFlipper viewFlipper;
    private int bloodTypeId;
    private int hospitalId;

    public FragmentCreateBloodAlarm(String userId) {
        this.userId = userId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_add_blood_alarm, container, false );
        getDialog().setTitle( getString( R.string.my_treatments ) );

        viewFlipper = (ViewFlipper) rootView.findViewById( R.id.view_flipper );
        viewFlipper.setDisplayedChild( 0 );
        selectedHospitalName = (TextView) rootView.findViewById( R.id.selected_hospital_name );
        contactNumber = (EditText) rootView.findViewById( R.id.contact_number );
        createBloodAlarm = (TextView) rootView.findViewById( R.id.create_blood_alarm );
        hospitalSpinner = (MaterialSpinner) rootView.findViewById( R.id.hospital_spinner );
        bloodSpinner = (MaterialSpinner) rootView.findViewById( R.id.blood_spinner );
        bloodAlarmHeader = (TextView) rootView.findViewById( R.id.blood_alarm );
        bloodAlarmHeader.setTypeface( Typefaces.getRobotoBold( getContext() ) );
        alarmLevel = (BubbleSeekBar) rootView.findViewById( R.id.alarm_level );
        alarmLevel.setElevation( 1 );

        bloodTypeId = -1;
        hospitalId = -1;

        bloodSpinner.setItems( getString( R.string.select_blood_type ), "0 Rh+", "0 Rh-", "A Rh+", "A Rh-", "B Rh+", "B Rh-", "AB Rh+", "AB Rh-" );
        bloodSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (position != 0) {
                    bloodTypeId = position;
                } else {
                    bloodTypeId = -1;
                }
            }
        } );

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
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        createBloodAlarm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int alertLevel = alarmLevel.getProgress();
                String phoneNumber = contactNumber.getText().toString().trim();
                if (bloodTypeId != -1 && hospitalId != -1 &&
                        ((phoneNumber.trim().length() == 10 && phoneNumber.substring( 0, 2 ).equals( "53" )) || (phoneNumber.equals( "" )))) {
                    BloodAlarm bloodAlarm = new BloodAlarm();
                    bloodAlarm.setUserId( userId );
                    bloodAlarm.setBloodTypeId( bloodTypeId );
                    bloodAlarm.setHospitalId( hospitalId );
                    bloodAlarm.setAlarmLevel( alertLevel );
                    if (!phoneNumber.equals( "" )) {
                        bloodAlarm.setContactNumber( phoneNumber );
                    }
                    try {
                        ApiClient.bloodApi().createBloodAlert( bloodAlarm ).enqueue( new Callback<ServerResponse>() {
                            @Override
                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                if (response.isSuccessful()) {
                                    viewFlipper.setDisplayedChild( 1 );
                                }
                            }

                            @Override
                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                Log.e( "UserHealthTree", t.getMessage() );
                                Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        } );
                    } catch (Exception e) {
                        Log.e( "UserHealthTree", e.getMessage() );
                        Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    } catch (NoSuchMethodError e) {
                        Log.e( "UserHealthTree", e.getMessage() );
                    }
                } else if (phoneNumber.trim().length() != 10 || !phoneNumber.substring( 0, 2 ).equals( "53" )) {
                    Toast.makeText( getContext(), getString( R.string.enter_acceptable_phone_number ), Toast.LENGTH_LONG ).show();
                }
            }
        } );

        return rootView;
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
                    selectedHospitalName.setVisibility( View.VISIBLE );
                    selectedHospitalName.setText( hospitals.get( position - 1 ).getHospitalName() );
                } else {
                    hospitalId = -1;
                    selectedHospitalName.setVisibility( View.GONE );
                    selectedHospitalName.setText( "" );
                }
            }
        } );
    }


}