// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.uimodule.main.healthtree.treatmentAdd;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.project.generalhealthmodule.UserDiseaseHistory;
import com.project.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;
import com.project.uimodule.main.MenuActivity;
import com.project.uimodule.main.healthtree.diseaseAdd.FragmentDiseaseAddTwo;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTreatmentAddThree extends Fragment {

    private View dialogView;
    private View treatmentInfo;
    private String userId;
    public static String diseaseName;
    public static String diseaseId;
    public static String treatmentName;
    public static int treatmentId;


    private TextView selectedDiseaseNameText;
    private TextView selectedTreatmentNameText;
    private EditText treatmentStartDateText;
    private Date treatmentStartDate;
    private Button treatmentSaveButton;

    public FragmentTreatmentAddThree(String userId) {
        this.userId = userId;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        treatmentInfo = inflater.inflate( R.layout.fragment_treatment_info, container, false );

        selectedDiseaseNameText = (TextView) treatmentInfo.findViewById( R.id.activity_treatment_add_disease_name_text );
        selectedTreatmentNameText = (TextView) treatmentInfo.findViewById( R.id.activity_treatment_add_treatment_name_text );
        treatmentStartDateText = (EditText) treatmentInfo.findViewById( R.id.activity_treatment_add_start_date_text );
        treatmentSaveButton = (Button) treatmentInfo.findViewById( R.id.activity_treatment_add_save_button );

        String alertDisease= getResources().getString( R.string.please_select_disease );
        String alertTreatment = getResources().getString( R.string.please_select_treatment );

        selectedDiseaseNameText.setText( alertDisease );
        selectedTreatmentNameText.setText( alertTreatment );

        treatmentStartDateText.setInputType( InputType.TYPE_NULL );
        treatmentStartDateText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class DatePickerFragment extends DialogFragment
                        implements DatePickerDialog.OnDateSetListener {

                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        // Use the current date as the default date in the picker
                        final Calendar c = Calendar.getInstance();
                        int year = c.get( Calendar.YEAR ) - 2000;
                        int month = c.get( Calendar.MONTH );
                        int day = c.get( Calendar.DAY_OF_MONTH );
                        return new DatePickerDialog( getActivity(), this, year, month, day );
                    }

                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        treatmentStartDateText.setText( day + "." + month + "." + year );
                        treatmentStartDate = new Date( year-1900, month, day );
                    }
                }

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show( getFragmentManager(), getResources().getString( R.string.treatment_start_date ) );

            }
        } );

        treatmentSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTreatmentToDiseaseHistory();
            }
        });

        return treatmentInfo;
    }

    public void fillDiseaseName(String alertDisease,String diseaseName) {
        if (diseaseName != null) {
            selectedDiseaseNameText.setText( diseaseName );
            selectedDiseaseNameText.setTextColor( R.color.darkText );
        } else {
            selectedDiseaseNameText.setText( alertDisease );
            selectedDiseaseNameText.setTextColor( R.color.treatment_color );
        }
    }

    public void fillTreatmentName(String alertTreatment,String treatmentName) {
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
            UserTreatmentHistory userTreatmentHistory=new UserTreatmentHistory();
            userTreatmentHistory.setUserId( userId );
            userTreatmentHistory.setDiseaseId( diseaseId );
            userTreatmentHistory.setTreatmentId( treatmentId );
            userTreatmentHistory.setTreatmentStartDate( treatmentStartDate );

            ApiClient.userTreatmentHistoryApi().createUserTreatmentHistory( userTreatmentHistory ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if(response.body().getStatus().equals( "true" )){
                            updateTreatmentCountsOfUserDiseaseHistory();
                        }else{
                            Toast.makeText( getContext(), getResources().getString( R.string.something_went_wrong ), Toast.LENGTH_LONG ).show();
                            FragmentTreatmentAddThree.diseaseName=null;
                            FragmentTreatmentAddThree.diseaseId=null;
                            FragmentTreatmentAddThree.treatmentName=null;
                            FragmentTreatmentAddThree.treatmentId=0;
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

    private void updateTreatmentCountsOfUserDiseaseHistory(){
        try {
            UserDiseaseHistory userDiseaseHistory=new UserDiseaseHistory();
            userDiseaseHistory.setUserId( userId );
            userDiseaseHistory.setDiseaseId( diseaseId );
            final String alertDisease = getResources().getString( R.string.please_select_disease );
            final String alertTreatment = getResources().getString( R.string.please_select_disease );
            ApiClient.userDiseaseHistoryApi().updateUserDiseaseHistoryTreatmentCount( userDiseaseHistory ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if(response.body().getStatus().equals( "true" )){
                            Toast.makeText( getContext(), getResources().getString( R.string.treatment_succesfull_saved ), Toast.LENGTH_LONG ).show();
                            FragmentDiseaseAddTwo.diseaseName=null;
                            FragmentDiseaseAddTwo.diseaseId=null;
                            selectedDiseaseNameText.setText( alertDisease );
                            selectedTreatmentNameText.setText( alertTreatment );
                            startActivity( new Intent( getActivity(), MenuActivity.class ) );
                        }else{
                            Toast.makeText( getContext(), getResources().getString( R.string.something_went_wrong ), Toast.LENGTH_LONG ).show();
                            FragmentDiseaseAddTwo.diseaseName=null;
                            FragmentDiseaseAddTwo.diseaseId=null;
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
}
