// Developer: Ahmet Kaymak
// Date: 27.02.2017

package com.project.uimodule.main.healthtree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.project.generalhealthmodule.UserDiseaseHistory;
import com.project.generalhealthmodule.UserDrugUsageHistory;
import com.project.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ApiClient;
import com.project.uimodule.main.healthtree.disease.DiseaseAddActivity;
import com.project.uimodule.main.healthtree.disease.DiseaseDrugFragment;
import com.project.uimodule.main.healthtree.disease.DiseaseTreatmentFragment;
import com.project.uimodule.main.healthtree.drug.DrugAddActivity;
import com.project.uimodule.main.healthtree.treatment.TreatmentAddActivity;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHealthTree extends Fragment {

    private String userId;
    private DiseaseTreatmentFragment dialogDiseaseTreatmentFragment;
    private DiseaseDrugFragment dialogDiseaseDrugFragment;
    private DiseaseTreatmentFragment dialogTreatmentDrugFragment;

    //Disease cell fields
    private ArrayList<UserDiseaseHistory> userDiseaseHistoryList;
    private TextView diseaseCellUserDiseaseCount;
    private TextView diseaseCellDiseaseName;
    private TextView diseaseCellDiseaseId;
    private TextView diseaseCellDiseaseLevel;
    private TextView diseaseCellDiseaseState;
    private TextView diseaseCellDiseaseStartDate;
    private TextView diseaseCellDiseaseFinishDate;
    private TextView diseaseCellCountOfDrugs;
    private TextView diseaseCellCountOfTreatments;
    private MaterialSpinner diseaseSpinner;
    private Button diseaseCellAddDiseaseButton;
    private ImageView diseaseCellDrugIcon;
    private ImageView diseaseCellTreatmentIcon;

    //Treatment cell fields
    private ArrayList<UserTreatmentHistory> userTreatmentHistoryList;
    private MaterialSpinner treatmentSpinner;
    private TextView treatmentCellUserTreatmentCount;
    private TextView treatmentCellTreatmentName;
    private TextView treatmentCellAssociatedDisease;
    private TextView treatmentCellTreatmentStartDate;
    private TextView treatmentCellTreatmentFinishDate;
    private TextView treatmentCellCountOfDrugs;
    private TextView treatmentCellTreatmentId;
    private TextView treatmentCellDiseaseId;
    private Button treatmentCellAddTreatmentButton;
    private ImageView treatmentCellDrugIcon;

    //Drug cell fields
    private ArrayList<UserDrugUsageHistory> userDrugUsageHistoryList;
    private MaterialSpinner drugSpinner;
    private TextView drugCellUseraDrugUsageCount;
    private TextView drugCellDrugName;
    private TextView drugCellAssociatedDisease;
    private TextView drugCellAssociatedTreatment;
    private TextView drugCellDrugUsageStartDate;
    private TextView drugCellDrugUsageFinishDate;
    private Button drugCellAddDrugButton;

    public FragmentHealthTree(String userId) {
        this.userId = userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View healthTreeView = inflater.inflate( R.layout.fragment_health_tree, container, false );

        //Disease Cell
        diseaseSpinner = (MaterialSpinner) healthTreeView.findViewById( R.id.disease_spinner );
        diseaseCellDiseaseName = (TextView) healthTreeView.findViewById( R.id.disease_cell_disease_name_text );
        diseaseCellDiseaseId = (TextView) healthTreeView.findViewById( R.id.disease_cell_disease_id );
        diseaseCellUserDiseaseCount = (TextView) healthTreeView.findViewById( R.id.disease_cell_total_disease_number );
        diseaseCellDiseaseLevel = (TextView) healthTreeView.findViewById( R.id.disease_cell_level_of_disease_text );
        diseaseCellDiseaseState = (TextView) healthTreeView.findViewById( R.id.disease_cell_state_of_disease_text );
        diseaseCellDiseaseStartDate = (TextView) healthTreeView.findViewById( R.id.disease_cell_content_start_date );
        diseaseCellDiseaseFinishDate = (TextView) healthTreeView.findViewById( R.id.disease_cell_content_finish_date );
        diseaseCellCountOfDrugs = (TextView) healthTreeView.findViewById( R.id.disease_cell_number_of_drugs );
        diseaseCellCountOfTreatments = (TextView) healthTreeView.findViewById( R.id.disease_cell_number_of_treatments );
        diseaseCellAddDiseaseButton = (Button) healthTreeView.findViewById( R.id.disease_cell_add_disease_button );
        diseaseCellDrugIcon = (ImageView) healthTreeView.findViewById( R.id.disease_cell_drug_icon );
        diseaseCellTreatmentIcon = (ImageView) healthTreeView.findViewById( R.id.disease_cell_treatment_icon );

        diseaseCellDrugIcon.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                final FragmentManager fm = getFragmentManager();
                try {
                    ApiClient.userDrugUsageHistoryApi().getUserDrugUsageHistory( userId ).enqueue( new Callback<UserDrugUsageHistory>() {
                        @Override
                        public void onResponse(Call<UserDrugUsageHistory> call, Response<UserDrugUsageHistory> response) {
                            if (response.isSuccessful()) {
                                userDrugUsageHistoryList = response.body().getUserDrugUsageHistory();
                                ArrayList<UserDrugUsageHistory> diseaseDrugs=new ArrayList<UserDrugUsageHistory>();
                                for (int i=0; i<userDrugUsageHistoryList.size();i++){
                                    if(userDrugUsageHistoryList.get( i ).getDiseaseId().equals( diseaseCellDiseaseId.getText().toString() )){
                                        diseaseDrugs.add(userDrugUsageHistoryList.get( i ));
                                    }
                                }
                                dialogDiseaseDrugFragment= new DiseaseDrugFragment(diseaseDrugs);
                                dialogDiseaseDrugFragment.show( fm, "Disease Treatments" );
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
        } );

        diseaseCellTreatmentIcon.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                final FragmentManager fm = getFragmentManager();
                try {
                    ApiClient.userTreatmentHistoryApi().getDiseaseTreatmentHistory( userId, diseaseCellDiseaseId.getText().toString() ).enqueue( new Callback<UserTreatmentHistory>() {
                        @Override
                        public void onResponse(Call<UserTreatmentHistory> call, Response<UserTreatmentHistory> response) {
                            if (response.isSuccessful()) {
                                dialogDiseaseTreatmentFragment= new DiseaseTreatmentFragment(response.body().getUserTreatmentHistory());
                                dialogDiseaseTreatmentFragment.show( fm, "Disease Treatments" );
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
        } );


        //Treatment
        treatmentSpinner = (MaterialSpinner) healthTreeView.findViewById( R.id.treatment_spinner );
        treatmentCellTreatmentName = (TextView) healthTreeView.findViewById( R.id.treatment_cell_treatment_name_text );
        treatmentCellUserTreatmentCount = (TextView) healthTreeView.findViewById( R.id.treatment_cell_total_treatment_number );
        treatmentCellAssociatedDisease = (TextView) healthTreeView.findViewById( R.id.treatment_cell_associated_disease_text );
        treatmentCellTreatmentStartDate = (TextView) healthTreeView.findViewById( R.id.treatment_cell_treatment_start_date );
        treatmentCellTreatmentFinishDate = (TextView) healthTreeView.findViewById( R.id.treatment_cell_treatment_finish_date );
        treatmentCellCountOfDrugs = (TextView) healthTreeView.findViewById( R.id.treatment_cell_number_of_drugs );
        treatmentCellAddTreatmentButton = (Button) healthTreeView.findViewById( R.id.treatment_cell_add_treatment_button );
        treatmentCellDrugIcon = (ImageView) healthTreeView.findViewById( R.id.treatment_cell_drug_icon );
        treatmentCellTreatmentId = (TextView) healthTreeView.findViewById( R.id.treatment_cell_treatment_id );
        treatmentCellDiseaseId = (TextView) healthTreeView.findViewById( R.id.treatment_cell_disease_id );

        treatmentCellDrugIcon.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                final FragmentManager fm = getFragmentManager();
                try {
                    ApiClient.userDrugUsageHistoryApi().getTreatmentDrugUsageHistory( userId, treatmentCellDiseaseId.getText().toString() ,
                            Integer.valueOf(treatmentCellTreatmentId.getText().toString())).enqueue( new Callback<UserDrugUsageHistory>() {
                        @Override
                        public void onResponse(Call<UserDrugUsageHistory> call, Response<UserDrugUsageHistory> response) {
                            if (response.isSuccessful()) {
                                dialogDiseaseDrugFragment= new DiseaseDrugFragment(response.body().getUserDrugUsageHistory());
                                dialogDiseaseDrugFragment.show( fm, "Disease Treatments" );
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
        } );

        //Drugs
        drugSpinner = (MaterialSpinner) healthTreeView.findViewById( R.id.drug_spinner );
        drugCellUseraDrugUsageCount = (TextView) healthTreeView.findViewById( R.id.drug_cell_total_drug_number );
        drugCellDrugName = (TextView) healthTreeView.findViewById( R.id.drug_cell_drug_name_text );
        drugCellAssociatedDisease = (TextView) healthTreeView.findViewById( R.id.drug_cell_associated_disease_text );
        drugCellAssociatedTreatment = (TextView) healthTreeView.findViewById( R.id.drug_cell_associated_treatment_text );
        drugCellDrugUsageStartDate = (TextView) healthTreeView.findViewById( R.id.drug_cell_drug_usage_start_date );
        drugCellDrugUsageFinishDate = (TextView) healthTreeView.findViewById( R.id.drug_cell_drug_usage_finish_date );
        drugCellAddDrugButton = (Button) healthTreeView.findViewById( R.id.drug_cell_add_drug_button );

        try {

            final FoldingCell diseaseFoldingCell = (FoldingCell) healthTreeView.findViewById( R.id.disease_folding_cell );

            diseaseFoldingCell.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diseaseFoldingCell.toggle( false );
                }
            } );

            final FoldingCell treatmentFoldingCell = (FoldingCell) healthTreeView.findViewById( R.id.treatment_folding_cell );

            treatmentFoldingCell.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    treatmentFoldingCell.toggle( false );
                }
            } );

            final FoldingCell drugFoldingCell = (FoldingCell) healthTreeView.findViewById( R.id.drug_folding_cell );
            drugFoldingCell.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drugFoldingCell.toggle( false );
                }
            } );

            getUserDiseaseHistory( userId );
            getUserTreatmentHistory( userId );
            getUserDrugUsageHistory( userId );

            diseaseCellAddDiseaseButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DiseaseAddActivity.userId = userId;
                    startActivity( new Intent( getContext(), DiseaseAddActivity.class ) );
                }
            } );

            treatmentCellAddTreatmentButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TreatmentAddActivity.userId = userId;
                    startActivity( new Intent( getContext(), TreatmentAddActivity.class ) );
                }
            } );

            drugCellAddDrugButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrugAddActivity.userId = userId;
                    startActivity( new Intent( getContext(), DrugAddActivity.class ) );
                }
            } );

        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        return healthTreeView;
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
        if (userDiseaseHistoryList.size() != 0) {
            diseaseCellUserDiseaseCount.setText( String.valueOf( userDiseaseHistoryList.size() ) );
            final List<String> userDiseases = new ArrayList<String>();
            for (int i = 0; i < userDiseaseHistoryList.size(); i++) {
                userDiseases.add( userDiseaseHistoryList.get( i ).getDisease().getDiseaseName() );
            }
            diseaseSpinner.setItems( userDiseases );
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
        } else {
            drugCellDrugName.setText( R.string.drug_cell_welcome );
            diseaseCellUserDiseaseCount.setText( "0" );
            diseaseCellDiseaseName.setText( "-" );
            diseaseCellDiseaseStartDate.setText( "-" );
            diseaseCellDiseaseFinishDate.setText( "-" );
            diseaseCellDiseaseLevel.setText( "-" );
            diseaseCellDiseaseState.setText( "-" );
        }

        diseaseSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                diseaseCellDiseaseName.setText( userDiseaseHistoryList.get( position ).getDisease().getDiseaseName() );
                diseaseCellDiseaseId.setText( userDiseaseHistoryList.get( position ).getDisease().getDiseaseId() );
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
        if (userTreatmentHistoryList.size() != 0) {
            treatmentCellUserTreatmentCount.setText( String.valueOf( userTreatmentHistoryList.size() ) );
            final List<String> userTreatments = new ArrayList();
            for (int i = 0; i < userTreatmentHistoryList.size(); i++) {
                userTreatments.add( this.userTreatmentHistoryList.get( i ).getTreatment().getTreatmentName() );
            }
            treatmentSpinner.setItems( userTreatments );
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
            treatmentCellCountOfDrugs.setText( String.valueOf( userTreatmentHistoryList.get( 0 ).getCountOfDrugs() ) );
        } else {
            treatmentCellUserTreatmentCount.setText( String.valueOf( 0 ) );
        }

        treatmentSpinner.setOnItemSelectedListener( new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                treatmentCellTreatmentName.setText( userTreatmentHistoryList.get( position ).getTreatment().getTreatmentName() );
                treatmentCellAssociatedDisease.setText( userTreatmentHistoryList.get( position ).getDisease().getDiseaseName() );
                treatmentCellTreatmentId.setText( String.valueOf(userTreatmentHistoryList.get( position ).getTreatmentId()));
                treatmentCellDiseaseId.setText( userTreatmentHistoryList.get( position ).getDiseaseId());
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
                treatmentCellCountOfDrugs.setText( String.valueOf( userTreatmentHistoryList.get( position ).getCountOfDrugs() ) );
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
        if (userDrugUsageHistoryList.size() != 0) {
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
        } else {
            drugCellDrugName.setText( R.string.drug_cell_welcome );
            drugCellUseraDrugUsageCount.setText( "0" );
            drugCellDrugUsageStartDate.setText( "-" );
            drugCellDrugUsageFinishDate.setText( "-" );
            drugCellAssociatedDisease.setText( "-" );
            drugCellAssociatedTreatment.setText( "-" );
        }
    }

}
