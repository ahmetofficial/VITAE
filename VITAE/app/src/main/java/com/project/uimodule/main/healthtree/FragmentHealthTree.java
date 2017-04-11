// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.uimodule.main.healthtree;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lavie.users.R;
import com.project.generalhealthmodule.UserDiseaseHistory;
import com.project.generalhealthmodule.UserDrugUsageHistory;
import com.project.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ApiClient;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHealthTree extends Fragment {

    private String userId;

    //Disease cell fields
    private ArrayList<UserDiseaseHistory> userDiseaseHistoryList;
    private TextView diseaseCellUserDiseaseCount;
    private TextView diseaseCellDiseaseName;
    private TextView diseaseCellDiseaseLevel;
    private TextView diseaseCellDiseaseState;
    private TextView diseaseCellDiseaseStartDate;
    private TextView diseaseCellDiseaseFinishDate;
    private TextView diseaseCellCountOfDrugs;
    private TextView diseaseCellCountOfTreatments;
    private MaterialSpinner diseaseSpinner;

    //Treatment cell fields
    private ArrayList<UserTreatmentHistory> userTreatmentHistoryList;
    private MaterialSpinner treatmentSpinner;
    private TextView treatmentCellUserTreatmentCount;
    private TextView treatmentCellTreatmentName;
    private TextView treatmentCellAssociatedDisease;
    private TextView treatmentCellTreatmentStartDate;
    private TextView treatmentCellTreatmentFinishDate;
    private TextView treatmentCellCountOfDrugs;

    //Drug cell fields
    private ArrayList<UserDrugUsageHistory> userDrugUsageHistoryList;
    private MaterialSpinner drugSpinner;
    private TextView drugCellUseraDrugUsageCount;
    private TextView drugCellDrugName;
    private TextView drugCellAssociatedDisease;
    private TextView drugCellAssociatedTreatment;
    private TextView drugCellDrugUsageStartDate;
    private TextView drugCellDrugUsageFinishDate;

    public FragmentHealthTree(String userId) {
        this.userId = userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View health_tree_view = inflater.inflate( R.layout.fragment_health_tree, container, false );

        //Disease Cell
        diseaseSpinner = (MaterialSpinner) health_tree_view.findViewById( R.id.disease_spinner );
        diseaseCellDiseaseName = (TextView) health_tree_view.findViewById( R.id.disease_cell_disease_name_text );
        diseaseCellUserDiseaseCount = (TextView) health_tree_view.findViewById( R.id.disease_cell_total_disease_number );
        diseaseCellDiseaseLevel = (TextView) health_tree_view.findViewById( R.id.disease_cell_level_of_disease_text );
        diseaseCellDiseaseState = (TextView) health_tree_view.findViewById( R.id.disease_cell_state_of_disease_text );
        diseaseCellDiseaseStartDate = (TextView) health_tree_view.findViewById( R.id.disease_cell_content_start_date );
        diseaseCellDiseaseFinishDate = (TextView) health_tree_view.findViewById( R.id.disease_cell_content_finish_date );
        diseaseCellCountOfDrugs = (TextView) health_tree_view.findViewById( R.id.disease_cell_number_of_drugs );
        diseaseCellCountOfTreatments = (TextView) health_tree_view.findViewById( R.id.disease_cell_number_of_treatments );

        //Treatment
        treatmentSpinner = (MaterialSpinner) health_tree_view.findViewById( R.id.treatment_spinner );
        treatmentCellTreatmentName = (TextView) health_tree_view.findViewById( R.id.treatment_cell_treatment_name_text );
        treatmentCellUserTreatmentCount = (TextView) health_tree_view.findViewById( R.id.treatment_cell_total_treatment_number );
        treatmentCellAssociatedDisease = (TextView) health_tree_view.findViewById( R.id.treatment_cell_associated_disease_text );
        treatmentCellTreatmentStartDate = (TextView) health_tree_view.findViewById( R.id.treatment_cell_treatment_start_date );
        treatmentCellTreatmentFinishDate = (TextView) health_tree_view.findViewById( R.id.treatment_cell_treatment_finish_date );
        treatmentCellCountOfDrugs = (TextView) health_tree_view.findViewById( R.id.treatment_cell_number_of_drugs );

        //Drugs
        drugSpinner = (MaterialSpinner) health_tree_view.findViewById( R.id.drug_spinner );
        drugCellUseraDrugUsageCount = (TextView) health_tree_view.findViewById( R.id.drug_cell_total_drug_number );
        drugCellDrugName = (TextView) health_tree_view.findViewById( R.id.drug_cell_drug_name_text );
        drugCellAssociatedDisease = (TextView) health_tree_view.findViewById( R.id.drug_cell_associated_disease_text );
        drugCellAssociatedTreatment = (TextView) health_tree_view.findViewById( R.id.drug_cell_associated_treatment_text );
        drugCellDrugUsageStartDate = (TextView) health_tree_view.findViewById( R.id.drug_cell_drug_usage_start_date );
        drugCellDrugUsageFinishDate = (TextView) health_tree_view.findViewById( R.id.drug_cell_drug_usage_finish_date );

        try {

            final FoldingCell diseaseFoldingCell = (FoldingCell) health_tree_view.findViewById( R.id.disease_folding_cell );

            diseaseFoldingCell.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diseaseFoldingCell.toggle( false );
                }
            } );

            final FoldingCell treatmentFoldingCell = (FoldingCell) health_tree_view.findViewById( R.id.treatment_folding_cell );

            treatmentFoldingCell.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    treatmentFoldingCell.toggle( false );
                }
            } );

            final FoldingCell drugFoldingCell = (FoldingCell) health_tree_view.findViewById( R.id.drug_folding_cell );
            drugFoldingCell.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drugFoldingCell.toggle( false );
                }
            } );

            getUserDiseaseHistory( userId );
            getUserTreatmentHistory( userId );
            getUserDrugUsageHistory( userId );

        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        return health_tree_view;
    }


    private void getUserDiseaseHistory(String userId) {
        try {
            ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( userId ).enqueue( new Callback<UserDiseaseHistory>() {
                @Override
                public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                    if (response.isSuccessful()) {
                        userDiseaseHistoryList = response.body().getUserDiseaseHistories();
                        fillDiseaseCell( userDiseaseHistoryList );
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
    }

    private void fillDiseaseCell(final ArrayList<UserDiseaseHistory> userDiseaseHistoryList) {
        final String[] diseaseLevel = getResources().getStringArray( R.array.disease_level_array );
        final String[] diseaseState = getResources().getStringArray( R.array.disease_state_array );
        diseaseCellUserDiseaseCount.setText( String.valueOf( userDiseaseHistoryList.size() ) );
        final List<String> userDiseases = new ArrayList<String>();
        for (int i = 0; i < userDiseaseHistoryList.size(); i++) {
            userDiseases.add( userDiseaseHistoryList.get( i ).getDisease().getDiseaseName() );
        }
        diseaseSpinner.setItems( userDiseases );

        if (userDiseaseHistoryList.size() != 0) {
            diseaseCellDiseaseName.setText( userDiseaseHistoryList.get( 0 ).getDisease().getDiseaseName() );
            diseaseCellDiseaseLevel.setText( diseaseLevel[userDiseaseHistoryList.get( 0 ).getDiseaseLevelId()] );
            diseaseCellDiseaseState.setText( diseaseState[userDiseaseHistoryList.get( 0 ).getDiseaseStateId()] );
            Date dateStart = userDiseaseHistoryList.get( 0 ).getDiseaseStartDate();
            CharSequence timeAgoStart = DateUtils.getRelativeTimeSpanString(
                    dateStart.getTime(),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
            diseaseCellDiseaseStartDate.setText( timeAgoStart );

            Date dateFinish = userDiseaseHistoryList.get( 0 ).getDiseaseFinishDate();
            if (dateFinish != null) {
                CharSequence timeAgoFinish = DateUtils.getRelativeTimeSpanString(
                        dateStart.getTime(),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                diseaseCellDiseaseFinishDate.setText( timeAgoFinish );
            } else {
                diseaseCellDiseaseFinishDate.setText( "∞" );
            }

            diseaseCellCountOfTreatments.setText( String.valueOf( userDiseaseHistoryList.get( 0 ).getCountOfTreatments() ) );
            diseaseCellCountOfDrugs.setText( String.valueOf( userDiseaseHistoryList.get( 0 ).getCountOfDrugs() ) );
        }

        diseaseSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                diseaseCellDiseaseName.setText( userDiseaseHistoryList.get( position ).getDisease().getDiseaseName() );
                diseaseCellDiseaseLevel.setText( diseaseLevel[userDiseaseHistoryList.get( position ).getDiseaseLevelId()] );
                diseaseCellDiseaseState.setText( diseaseState[userDiseaseHistoryList.get( position ).getDiseaseStateId()] );
                Date dateStart = userDiseaseHistoryList.get( position ).getDiseaseStartDate();
                CharSequence timeAgoStart = DateUtils.getRelativeTimeSpanString(
                        dateStart.getTime(),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                diseaseCellDiseaseStartDate.setText( timeAgoStart );

                Date dateFinish = userDiseaseHistoryList.get( position ).getDiseaseFinishDate();
                if (dateFinish != null) {
                    CharSequence timeAgoFinish = DateUtils.getRelativeTimeSpanString(
                            dateStart.getTime(),
                            System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                    diseaseCellDiseaseFinishDate.setText( timeAgoFinish );
                } else {
                    diseaseCellDiseaseFinishDate.setText( "∞" );
                }

                diseaseCellCountOfTreatments.setText( String.valueOf( userDiseaseHistoryList.get( position ).getCountOfTreatments() ) );
                diseaseCellCountOfDrugs.setText( String.valueOf( userDiseaseHistoryList.get( position ).getCountOfDrugs() ) );
            }
        } );
    }

    private void getUserTreatmentHistory(String userId) {
        try {
            ApiClient.userTreatmentHistoryApi().getUserTreatmentHistory( userId ).enqueue( new Callback<UserTreatmentHistory>() {
                @Override
                public void onResponse(Call<UserTreatmentHistory> call, Response<UserTreatmentHistory> response) {
                    if (response.isSuccessful()) {
                        userTreatmentHistoryList = response.body().getUserTreatmentHistory();
                        fillTreatmentCell( userTreatmentHistoryList );
                    }
                }

                @Override
                public void onFailure(Call<UserTreatmentHistory> call, Throwable t) {
                    Log.e( "UserHealthTree", t.getMessage() );
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void fillTreatmentCell(final ArrayList<UserTreatmentHistory> userTreatmentHistoryList) {
        treatmentCellUserTreatmentCount.setText( String.valueOf( userTreatmentHistoryList.size() ) );
        final List<String> userTreatments = new ArrayList();
        for (int i = 0; i < userTreatmentHistoryList.size(); i++) {
            userTreatments.add( this.userTreatmentHistoryList.get( i ).getTreatment().getTreatmentName() );
        }
        treatmentSpinner.setItems( userTreatments );

        if (userTreatmentHistoryList.size() != 0) {
            treatmentCellTreatmentName.setText( userTreatmentHistoryList.get( 0 ).getTreatment().getTreatmentName() );
            treatmentCellAssociatedDisease.setText( userTreatmentHistoryList.get( 0 ).getDisease().getDiseaseName() );
            Date dateStart = userTreatmentHistoryList.get( 0 ).getTreatmentStartDate();
            CharSequence timeAgoStart = DateUtils.getRelativeTimeSpanString(
                    dateStart.getTime(),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
            treatmentCellTreatmentStartDate.setText( timeAgoStart );

            Date dateFinish = userTreatmentHistoryList.get( 0 ).getTreatmentFinishDate();
            if (dateFinish != null) {
                CharSequence timeAgoFinish = DateUtils.getRelativeTimeSpanString(
                        dateStart.getTime(),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                treatmentCellTreatmentFinishDate.setText( timeAgoFinish );
            } else {
                treatmentCellTreatmentFinishDate.setText( "∞" );
            }
        }

        treatmentSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                treatmentCellTreatmentName.setText( userTreatmentHistoryList.get( position ).getTreatment().getTreatmentName() );
                treatmentCellAssociatedDisease.setText( userTreatmentHistoryList.get( position ).getDisease().getDiseaseName() );
                Date dateStart = userTreatmentHistoryList.get( position ).getTreatmentStartDate();
                CharSequence timeAgoStart = DateUtils.getRelativeTimeSpanString(
                        dateStart.getTime(),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                treatmentCellTreatmentStartDate.setText( timeAgoStart );

                Date dateFinish = userTreatmentHistoryList.get( position ).getTreatmentFinishDate();
                if (dateFinish != null) {
                    CharSequence timeAgoFinish = DateUtils.getRelativeTimeSpanString(
                            dateStart.getTime(),
                            System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                    treatmentCellTreatmentFinishDate.setText( timeAgoFinish );
                } else {
                    treatmentCellTreatmentFinishDate.setText( "∞" );
                }
            }
        } );
    }

    private void getUserDrugUsageHistory(String userId) {
        try {
            ApiClient.userDrugUsageHistoryApi().getUserDrugUsageHistory( userId ).enqueue( new Callback<UserDrugUsageHistory>() {
                @Override
                public void onResponse(Call<UserDrugUsageHistory> call, Response<UserDrugUsageHistory> response) {
                    if (response.isSuccessful()) {
                        userDrugUsageHistoryList = response.body().getUserDrugUsageHistory();
                        fillDrugCell( userDrugUsageHistoryList );
                    }
                }

                @Override
                public void onFailure(Call<UserDrugUsageHistory> call, Throwable t) {
                    Log.e( "UserHealthTree", t.getMessage() );
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void fillDrugCell(final ArrayList<UserDrugUsageHistory> userDrugUsageHistoryList) {
        if (userDrugUsageHistoryList != null) {
            drugCellUseraDrugUsageCount.setText( String.valueOf( userDrugUsageHistoryList.size() ) );
            final List<String> userDrugs = new ArrayList<String>();
            for (int i = 0; i < userDrugUsageHistoryList.size(); i++) {
                userDrugs.add( userDrugUsageHistoryList.get( i ).getDrug().getCommercialName() );
            }
            drugSpinner.setItems( userDrugs );

            if (userDrugUsageHistoryList.size() != 0) {
                drugCellDrugName.setText( userDrugUsageHistoryList.get( 0 ).getDrug().getCommercialName() );
                drugCellAssociatedDisease.setText( userDrugUsageHistoryList.get( 0 ).getDisease().getDiseaseName() );
                drugCellAssociatedTreatment.setText( userDrugUsageHistoryList.get( 0 ).getTreatment().getTreatmentName() );
                Date dateStart = userDrugUsageHistoryList.get( 0 ).getDrugUsageStartDate();
                CharSequence timeAgoStart = DateUtils.getRelativeTimeSpanString(
                        dateStart.getTime(),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                drugCellDrugUsageStartDate.setText( timeAgoStart );

                Date dateFinish = userDrugUsageHistoryList.get( 0 ).getDrugUsageFinishDate();
                if (dateFinish != null) {
                    CharSequence timeAgoFinish = DateUtils.getRelativeTimeSpanString(
                            dateStart.getTime(),
                            System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                    drugCellDrugUsageFinishDate.setText( timeAgoFinish );
                } else {
                    drugCellDrugUsageFinishDate.setText( "∞" );
                }
            }

            drugSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    drugCellDrugName.setText( userDrugUsageHistoryList.get( position ).getDrug().getCommercialName() );
                    drugCellAssociatedDisease.setText( userDrugUsageHistoryList.get( position ).getDisease().getDiseaseName() );
                    drugCellAssociatedTreatment.setText( userDrugUsageHistoryList.get( position ).getTreatment().getTreatmentName() );
                    Date dateStart = userDrugUsageHistoryList.get( position ).getDrugUsageStartDate();
                    CharSequence timeAgoStart = DateUtils.getRelativeTimeSpanString(
                            dateStart.getTime(),
                            System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                    drugCellDrugUsageStartDate.setText( timeAgoStart );

                    Date dateFinish = userDrugUsageHistoryList.get( position ).getDrugUsageFinishDate();
                    if (dateFinish != null) {
                        CharSequence timeAgoFinish = DateUtils.getRelativeTimeSpanString(
                                dateStart.getTime(),
                                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
                        drugCellDrugUsageFinishDate.setText( timeAgoFinish );
                    } else {
                        drugCellDrugUsageFinishDate.setText( "∞" );
                    }
                }
            } );
        }
        else{
            drugCellDrugName.setText(R.string.drug_cell_welcome);
            drugCellUseraDrugUsageCount.setText( "0" );
            drugCellDrugUsageStartDate.setText( "-" );
            drugCellDrugUsageFinishDate.setText( "-" );
            drugCellAssociatedDisease.setText( "-" );
            drugCellAssociatedTreatment.setText( "-" );
        }
    }
}
