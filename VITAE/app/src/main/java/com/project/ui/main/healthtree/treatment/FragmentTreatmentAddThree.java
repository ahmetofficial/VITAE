// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.ui.main.healthtree.treatment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.core.generalhealthmodule.UserDiseaseHistory;
import com.project.core.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ApiClient;
import com.project.restservice.serverResponse.ServerResponse;
import com.project.ui.main.MenuActivity;
import com.project.ui.main.healthtree.disease.FragmentDiseaseAddTwo;
import com.project.utils.DatePickerFragment;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTreatmentAddThree extends Fragment {

    private View dialogView;
    private View treatmentInfo;
    private String userId;
    private int userTypeId;
    public static String diseaseName;
    public static String diseaseId;
    public static String treatmentName;
    public static int treatmentId;


    private TextView selectedDiseaseNameText;
    private TextView selectedTreatmentNameText;
    private TextView treatmentStartDateText;
    private Date treatmentStartDate=new Date();
    private Button treatmentSaveButton;
    private DatePickerFragment newFragment;

    public FragmentTreatmentAddThree(String userId, int userTypeId) {
        this.userId = userId;
        this.userTypeId = userTypeId;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( "" );

        treatmentInfo = inflater.inflate( R.layout.fragment_treatment_info, container, false );

        selectedDiseaseNameText = (TextView) treatmentInfo.findViewById( R.id.activity_treatment_add_disease_name_text );
        selectedTreatmentNameText = (TextView) treatmentInfo.findViewById( R.id.activity_treatment_add_treatment_name_text );
        treatmentStartDateText = (TextView) treatmentInfo.findViewById( R.id.activity_treatment_add_start_date_text );
        treatmentSaveButton = (Button) treatmentInfo.findViewById( R.id.activity_treatment_add_save_button );
        newFragment = new DatePickerFragment( treatmentStartDateText, treatmentStartDate );

        String alertDisease = getResources().getString( R.string.please_select_disease );
        String alertTreatment = getResources().getString( R.string.please_select_treatment );

        selectedDiseaseNameText.setText( alertDisease );
        selectedTreatmentNameText.setText( alertTreatment );

        treatmentStartDateText.setInputType( InputType.TYPE_NULL );
        treatmentStartDateText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFragment.show( getFragmentManager(), getResources().getString( R.string.treatment_start_date ) );

            }
        } );

        treatmentSaveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTreatmentToDiseaseHistory();
            }
        } );

        return treatmentInfo;
    }

    public void fillDiseaseName(String alertDisease, String diseaseName) {
        if (diseaseName != null) {
            selectedDiseaseNameText.setText( diseaseName );
            selectedDiseaseNameText.setTextColor( R.color.darkText );
        } else {
            selectedDiseaseNameText.setText( alertDisease );
            selectedDiseaseNameText.setTextColor( R.color.treatment_color );
        }
    }

    public void fillTreatmentName(String alertTreatment, String treatmentName) {
        if (treatmentName != null) {
            selectedTreatmentNameText.setText( treatmentName );
            selectedTreatmentNameText.setTextColor( R.color.darkText );
        } else {
            selectedTreatmentNameText.setText( alertTreatment );
            selectedTreatmentNameText.setTextColor( R.color.treatment_color );
        }
    }

    private void addTreatmentToDiseaseHistory() {
        try {
            UserTreatmentHistory userTreatmentHistory = new UserTreatmentHistory();
            userTreatmentHistory.setUserId( userId );
            userTreatmentHistory.setDiseaseId( diseaseId );
            userTreatmentHistory.setTreatmentId( treatmentId );
            userTreatmentHistory.setTreatmentStartDate( newFragment.getDate() );

            ApiClient.userTreatmentHistoryApi().createUserTreatmentHistory( userTreatmentHistory ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals( "true" )) {
                            updateTreatmentCountsOfUserDiseaseHistory();
                        } else {
                            Toast.makeText( getContext(), getResources().getString( R.string.something_went_wrong ), Toast.LENGTH_LONG ).show();
                            FragmentTreatmentAddThree.diseaseName = null;
                            FragmentTreatmentAddThree.diseaseId = null;
                            FragmentTreatmentAddThree.treatmentName = null;
                            FragmentTreatmentAddThree.treatmentId = 0;
                            String alertDisease = getResources().getString( R.string.please_select_disease );
                            String alertTreatment = getResources().getString( R.string.please_select_treatment );
                            selectedDiseaseNameText.setText( alertDisease );
                            selectedTreatmentNameText.setText( alertTreatment );
                            startActivity( new Intent( getActivity(), MenuActivity.class ) );
                        }
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void updateTreatmentCountsOfUserDiseaseHistory() {
        try {
            UserDiseaseHistory userDiseaseHistory = new UserDiseaseHistory();
            userDiseaseHistory.setUserId( userId );
            userDiseaseHistory.setDiseaseId( diseaseId );
            final String alertDisease = getResources().getString( R.string.please_select_disease );
            final String alertTreatment = getResources().getString( R.string.please_select_disease );
            ApiClient.userDiseaseHistoryApi().updateUserDiseaseHistoryTreatmentCount( userDiseaseHistory ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals( "true" )) {
                            Toast.makeText( getContext(), getResources().getString( R.string.treatment_succesfull_saved ), Toast.LENGTH_LONG ).show();
                            FragmentDiseaseAddTwo.diseaseName = null;
                            FragmentDiseaseAddTwo.diseaseId = null;
                            selectedDiseaseNameText.setText( alertDisease );
                            selectedTreatmentNameText.setText( alertTreatment );
                            Intent intent = new Intent( getContext(), MenuActivity.class );
                            MenuActivity.userId = userId;
                            intent.putExtra( "userId", userId );
                            intent.putExtra( "userTypeId", Integer.valueOf( userTypeId ) );
                            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                            startActivity( intent );
                        } else {
                            Toast.makeText( getContext(), getResources().getString( R.string.something_went_wrong ), Toast.LENGTH_LONG ).show();
                            FragmentDiseaseAddTwo.diseaseName = null;
                            FragmentDiseaseAddTwo.diseaseId = null;
                            selectedDiseaseNameText.setText( alertDisease );
                            selectedTreatmentNameText.setText( alertTreatment );
                            Intent intent = new Intent( getContext(), MenuActivity.class );
                            MenuActivity.userId = userId;
                            intent.putExtra( "userId", userId );
                            intent.putExtra( "userTypeId", Integer.valueOf( userTypeId ) );
                            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                            startActivity( intent );
                        }
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
