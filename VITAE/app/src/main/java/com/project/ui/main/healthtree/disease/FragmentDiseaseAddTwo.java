// Developer: Ahmet Kaymak
// Date: 24.04.2017

package com.project.ui.main.healthtree.disease;

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

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.ahmetkaymak.vitae.R;
import com.project.core.generalhealthmodule.UserDiseaseHistory;
import com.project.restservice.ApiClient;
import com.project.restservice.serverresponse.ServerResponse;
import com.project.ui.main.MenuActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDiseaseAddTwo extends Fragment {

    private View dialogView;
    private View diseaseInfo;
    private String userId;
    public static String diseaseName;
    public static String diseaseId;

    private MaterialSpinner diseaseAddDialogLevelSpinner;
    private MaterialSpinner diseaseAddDialogStateSpinner;
    private TextView selectedDiseaseNameText;
    private EditText diseaseStartDateText;
    private Date diseaseStartDate;
    private Button diseaseSaveButton;

    public FragmentDiseaseAddTwo(String userId) {
        this.userId = userId;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        diseaseInfo = inflater.inflate( R.layout.fragment_disease_info, container, false );
        dialogView = inflater.inflate( R.layout.fragment_recyclerview, null );
        diseaseAddDialogLevelSpinner = (MaterialSpinner) diseaseInfo.findViewById( R.id.activity_disease_add_level_spinner );
        diseaseAddDialogStateSpinner = (MaterialSpinner) diseaseInfo.findViewById( R.id.activity_disease_add_state_spinner );
        diseaseSaveButton = (Button) diseaseInfo.findViewById( R.id.activity_disease_add_save_button );
        diseaseStartDateText = (EditText) diseaseInfo.findViewById( R.id.activity_disease_start_date_text );
        diseaseStartDateText.setInputType( InputType.TYPE_NULL );

        selectedDiseaseNameText = (TextView) diseaseInfo.findViewById( R.id.activity_disease_add_name_text );
        String alert = getResources().getString( R.string.please_select_disease );
        selectedDiseaseNameText.setText( alert );
        selectedDiseaseNameText.setTextColor( R.color.disease_color );

        String[] levelArray = getResources().getStringArray( R.array.disease_level_array );
        String[] stateArray = getResources().getStringArray( R.array.disease_state_array );
        List<String> llevel = new ArrayList<>();
        for (int i = 0; i < levelArray.length; i++) {
            llevel.add( levelArray[i] );
        }

        List<String> slevel = new ArrayList<>();
        for (int i = 0; i < stateArray.length; i++) {
            slevel.add( stateArray[i] );
        }

        diseaseAddDialogLevelSpinner.setItems( llevel );
        diseaseAddDialogStateSpinner.setItems( slevel );

        diseaseStartDateText.setOnClickListener( new View.OnClickListener() {
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
                        diseaseStartDateText.setText( day + "." + month + "." + year );
                        diseaseStartDate = new Date( year-1900, month, day );
                    }
                }

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show( getFragmentManager(), getResources().getString( R.string.disease_start_date ) );

            }
        } );

        diseaseSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDiseaseToDiseaseHistory();
            }
        });

        return diseaseInfo;
    }

    public void fillDiseaseName() {
        if (diseaseName != null) {
            selectedDiseaseNameText.setText( diseaseName );
            selectedDiseaseNameText.setTextColor( R.color.primaryDarkText );
        } else {
            String alert = getResources().getString( R.string.please_select_disease );
            selectedDiseaseNameText.setText( alert );
            selectedDiseaseNameText.setTextColor( R.color.disease_color );
        }
    }

    private void addDiseaseToDiseaseHistory() {
        try {
            UserDiseaseHistory userDiseaseHistory=new UserDiseaseHistory();
            userDiseaseHistory.setUserId( userId );
            userDiseaseHistory.setDiseaseId( diseaseId );
            userDiseaseHistory.setDiseaseStartDate( diseaseStartDate );
            userDiseaseHistory.setDiseaseLevelId( diseaseAddDialogLevelSpinner.getSelectedIndex() );
            userDiseaseHistory.setDiseaseStateId( diseaseAddDialogStateSpinner.getSelectedIndex() );

            int x=0;
            ApiClient.userDiseaseHistoryApi.createUserDiseaseHistory( userDiseaseHistory ).enqueue( new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()) {
                        if(response.body().getStatus().equals( "true" )){
                            Toast.makeText( getContext(), getResources().getString( R.string.disease_succesfull_saved ), Toast.LENGTH_LONG ).show();
                            FragmentDiseaseAddTwo.diseaseName=null;
                            FragmentDiseaseAddTwo.diseaseId=null;
                            String alert = getResources().getString( R.string.please_select_disease );
                            selectedDiseaseNameText.setText( alert );

                            startActivity( new Intent( getActivity(), MenuActivity.class ) );
                        }else{
                            Toast.makeText( getContext(), getResources().getString( R.string.something_went_wrong ), Toast.LENGTH_LONG ).show();
                            FragmentDiseaseAddTwo.diseaseName=null;
                            FragmentDiseaseAddTwo.diseaseId=null;
                            String alert = getResources().getString( R.string.please_select_disease );
                            startActivity( new Intent( getActivity(), MenuActivity.class ) );
                            selectedDiseaseNameText.setText( alert );
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
