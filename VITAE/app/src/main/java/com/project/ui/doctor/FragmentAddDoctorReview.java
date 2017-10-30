package com.project.ui.doctor;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.core.doctormodule.PatientDoctorRate;
import com.project.core.generalhealthmodule.UserDiseaseHistory;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.utils.Typefaces;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAddDoctorReview extends DialogFragment {

    private String patientId;
    private String doctorId;
    private MaterialRatingBar hospitalRatingBar;
    private EditText ratingComment;
    private Button addDoctorRatingButton;
    private MaterialSpinner diseaseSpinner;
    private String diseaseId;

    public FragmentAddDoctorReview(String patientId, String doctorId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_add_hospital_rating, container, false );
        hospitalRatingBar = (MaterialRatingBar) rootView.findViewById( R.id.hospital_rating_bar );
        ratingComment = (EditText) rootView.findViewById( R.id.hospital_rating_comment );
        addDoctorRatingButton = (Button) rootView.findViewById( R.id.hospital_add_rating_button );
        diseaseSpinner = (MaterialSpinner) rootView.findViewById( R.id.disease_spinner );
        diseaseSpinner.setDropdownHeight( 80 );
        ratingComment.setTypeface( Typefaces.getRobotoLight( getContext() ) );

        try {
            ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( patientId ).enqueue( new Callback<UserDiseaseHistory>() {
                @Override
                public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                    if (response.isSuccessful()) {
                        fillDiseaseSpinner( response.body().getUserDiseaseHistories() );
                    }
                }

                @Override
                public void onFailure(Call<UserDiseaseHistory> call, Throwable t) {
                    Log.e( "UserHealthTree", t.getMessage() );
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        addDoctorRatingButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PatientDoctorRate rate = new PatientDoctorRate();
                rate.setPatientId( patientId );
                rate.setDoctorId( doctorId );
                rate.setDiseaseId( diseaseId );
                rate.setUserRate( (double) hospitalRatingBar.getRating() );
                rate.setUserComment( ratingComment.getText().toString() );

                if (diseaseId != null) {
                    try {
                        ApiClient.doctorApi().createPatientDoctorRates( rate ).enqueue( new Callback<ServerResponse>() {
                            @Override
                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getStatus().equals( "true" )) {
                                        Toast.makeText( getActivity(), getString( R.string.rate_is_succesful ), Toast.LENGTH_LONG ).show();
                                        getDialog().dismiss();
                                    } else {
                                        Toast.makeText( getActivity(), getString( R.string.something_went_wrong ), Toast.LENGTH_LONG ).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                Log.e( "UserHealthTree", t.getMessage() );
                                Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        } );
                    } catch (Exception e) {
                        Log.e( "UserHealthTree", e.getMessage() );
                        Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                } else {
                    Toast.makeText( getActivity(), getString( R.string.empty_disease ), Toast.LENGTH_LONG ).show();
                }
            }
        } );
        return rootView;
    }

    private void fillDiseaseSpinner(final ArrayList<UserDiseaseHistory> diseaseHistory) {
        final List<String> userDiseases = new ArrayList();
        userDiseases.add( getString( R.string.select_disease ) );
        for (int i = 0; i < diseaseHistory.size(); i++) {
            userDiseases.add( diseaseHistory.get( i ).getDisease().getDiseaseName() );
        }
        diseaseSpinner.setItems( userDiseases );
        diseaseSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (position != 0) {
                    diseaseId = diseaseHistory.get( position-1 ).getDiseaseId();
                }
            }
        } );
    }
}

