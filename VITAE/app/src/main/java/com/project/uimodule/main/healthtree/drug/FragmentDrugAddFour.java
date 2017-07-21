// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.uimodule.main.healthtree.drug;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
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
import com.project.generalhealthmodule.UserDrugUsageHistory;
import com.project.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ApiClient;
import com.project.restservice.ServerResponse;
import com.project.uimodule.main.MenuActivity;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDrugAddFour extends Fragment {

    private View drugInfo;
    private String userId;
    public static String diseaseName;
    public static String diseaseId;
    public static String treatmentName;
    public static int treatmentId;
    public static String drugName;
    public static int drugId;

    private TextView selectedDiseaseNameText;
    private TextView selectedTreatmentNameText;
    private TextView selectedDrugNameText;
    private EditText drugStartDateText;
    private Date drugStartDate;
    private Button drugSaveButton;

    public FragmentDrugAddFour(String userId) {
        this.userId = userId;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        drugInfo = inflater.inflate( R.layout.fragment_drug_info, container, false );

        selectedDiseaseNameText = (TextView) drugInfo.findViewById( R.id.activity_drug_add_disease_name_text );
        selectedTreatmentNameText = (TextView) drugInfo.findViewById( R.id.activity_drug_add_treatment_name_text );
        selectedDrugNameText = (TextView) drugInfo.findViewById( R.id.activity_drug_add_drug_name_text );
        drugStartDateText = (EditText) drugInfo.findViewById( R.id.activity_drug_add_start_date_text );
        drugSaveButton = (Button) drugInfo.findViewById( R.id.activity_drug_add_save_button );

        String alertDisease= getResources().getString( R.string.please_select_disease );
        String alertTreatment = getResources().getString( R.string.please_select_treatment );
        String alertDrug = getResources().getString( R.string.please_select_drug);

        selectedDiseaseNameText.setText( alertDisease );
        selectedTreatmentNameText.setText( alertTreatment );
        selectedDrugNameText.setText( alertDrug );

        drugStartDateText.setInputType( InputType.TYPE_NULL );
        drugStartDateText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class DatePickerFragment extends DialogFragment
                        implements DatePickerDialog.OnDateSetListener {

                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        final Calendar c = Calendar.getInstance();
                        int year = c.get( Calendar.YEAR ) - 2000;
                        int month = c.get( Calendar.MONTH );
                        int day = c.get( Calendar.DAY_OF_MONTH );
                        return new DatePickerDialog( getActivity(), this, year, month, day );
                    }

                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        drugStartDateText.setText( day + "." + month + "." + year );
                        drugStartDate = new Date( year-1900, month, day );
                    }
                }

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show( getFragmentManager(), getResources().getString( R.string.treatment_start_date ) );

            }
        } );

        drugSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTreatmentToDiseaseHistory();
            }
        });

        return drugInfo;
    }

    public void fillDiseaseName(String alertDisease,String diseaseName) {
        if (diseaseName != null) {
            selectedDiseaseNameText.setText( diseaseName );
            selectedDiseaseNameText.setTextColor( R.color.darkText );
        } else {
            selectedDiseaseNameText.setText( alertDisease );
            selectedDiseaseNameText.setTextColor( R.color.drug_color );
        }
    }

    public void fillTreatmentName(String alertTreatment,String treatmentName) {
        if (treatmentName != null) {
            selectedTreatmentNameText.setText( treatmentName );
            selectedTreatmentNameText.setTextColor( R.color.darkText );
        } else {
            selectedTreatmentNameText.setText( alertTreatment );
            selectedTreatmentNameText.setTextColor( R.color.drug_color );
        }
    }

    public void fillDrugName(String alertDrug,String drugName) {
        if (treatmentName != null) {
            selectedDrugNameText.setText( drugName );
            selectedDrugNameText.setTextColor( R.color.darkText );
        } else {
            selectedDrugNameText.setText( alertDrug );
            selectedDrugNameText.setTextColor( R.color.drug_color );
        }
    }

    private void addTreatmentToDiseaseHistory() {
        try {
            UserDrugUsageHistory userDrugUsageHistory=new UserDrugUsageHistory();
            userDrugUsageHistory.setUserId( userId );
            userDrugUsageHistory.setDiseaseId( diseaseId );
            userDrugUsageHistory.setTreatmentId( treatmentId );
            userDrugUsageHistory.setDrugId( drugId );
            userDrugUsageHistory.setDrugUsageStartDate( drugStartDate );

            ApiClient.userDrugUsageHistoryApi().createUserDrugUsageHistory( userDrugUsageHistory ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if(response.body().getStatus().equals( "true" )){
                            updateDrugsCountsOfUserDiseaseHistory();
                            updateDrugsCountsOfUserTreatmentHistory();
                        }else{
                            Toast.makeText( getContext(), getResources().getString( R.string.something_went_wrong ), Toast.LENGTH_LONG ).show();
                            FragmentDrugAddFour.diseaseName=null;
                            FragmentDrugAddFour.diseaseId=null;
                            FragmentDrugAddFour.treatmentName=null;
                            FragmentDrugAddFour.drugName=null;
                            String alertDisease = getResources().getString( R.string.please_select_disease );
                            String alertTreatment = getResources().getString( R.string.please_select_treatment );
                            String alertDrug = getResources().getString( R.string.please_select_drug );
                            selectedDiseaseNameText.setText( alertDisease );
                            selectedTreatmentNameText.setText( alertTreatment );
                            selectedDrugNameText.setText( alertDrug );
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

    private void updateDrugsCountsOfUserDiseaseHistory(){
        try {
            UserDiseaseHistory userDiseaseHistory=new UserDiseaseHistory();
            userDiseaseHistory.setUserId( userId );
            userDiseaseHistory.setDiseaseId( diseaseId );
            ApiClient.userDiseaseHistoryApi().updateUserDiseaseHistoryDrugCount( userDiseaseHistory ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

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

    private void updateDrugsCountsOfUserTreatmentHistory(){
        try {
            UserTreatmentHistory userTreatmentHistory=new UserTreatmentHistory();
            userTreatmentHistory.setUserId( userId );
            userTreatmentHistory.setDiseaseId( diseaseId );
            userTreatmentHistory.setTreatmentId( treatmentId );
            final String alertDisease = getResources().getString( R.string.please_select_disease );
            final String alertTreatment = getResources().getString( R.string.please_select_treatment );
            final String alertDrug = getResources().getString( R.string.please_select_drug );
            ApiClient.userTreatmentHistoryApi().updateUserTreatmentHistoryDrugCount( userTreatmentHistory ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.body().getStatus().equals( "true" )){
                        Toast.makeText( getContext(), getResources().getString( R.string.drug_succesfull_saved ), Toast.LENGTH_LONG ).show();
                        FragmentDrugAddFour.diseaseName=null;
                        FragmentDrugAddFour.drugName=null;
                        FragmentDrugAddFour.treatmentName=null;
                        startActivity( new Intent( getActivity(), MenuActivity.class ) );
                        selectedDiseaseNameText.setText( alertDisease );
                        selectedTreatmentNameText.setText( alertTreatment );
                        selectedDrugNameText.setText( alertDrug );

                    }else{
                        Toast.makeText( getContext(), getResources().getString( R.string.something_went_wrong ), Toast.LENGTH_LONG ).show();
                        FragmentDrugAddFour.diseaseName=null;
                        FragmentDrugAddFour.drugName=null;
                        FragmentDrugAddFour.treatmentName=null;
                        startActivity( new Intent( getActivity(), MenuActivity.class ) );
                        selectedDiseaseNameText.setText( alertDisease );
                        selectedTreatmentNameText.setText( alertTreatment );
                        selectedDrugNameText.setText( alertDrug );
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
