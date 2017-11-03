// Developer: Ahmet Kaymak
// Date: 30.04.2016

package com.project.ui.userhealthtree;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.project.core.generalhealthmodule.UserDiseaseHistory;
import com.project.core.generalhealthmodule.UserDrugUsageHistory;
import com.project.core.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ApiClient;
import com.project.ui.BaseActivity;
import com.project.utils.Typefaces;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHealthTreeActivity extends BaseActivity {

    public static String userId;
    private ExpandingList diseaseExpandingList;
    private ExpandingList treatmentExpandingList;
    private String diseaseStateArray[];
    private String diseaseLevelArray[];
    private ArrayList<UserDiseaseHistory> healthHistory;
    private ArrayList<UserDiseaseHistory> diseaseHistory;
    private ArrayList<UserTreatmentHistory> treatmentHistory;
    private ArrayList<UserDrugUsageHistory> drugUsageHistory;

    private HealthTreeCreatingDialogFragment creatingDialogFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_health_tree );
        diseaseExpandingList = (ExpandingList) findViewById( R.id.health_tree_expanding_list );
        diseaseStateArray = getResources().getStringArray( R.array.disease_state_array );
        diseaseLevelArray = getResources().getStringArray( R.array.disease_level_array );
        FragmentManager fm = getSupportFragmentManager();
        creatingDialogFragment = new HealthTreeCreatingDialogFragment();
        creatingDialogFragment.show( fm, "Loading" );
        createHealthTree( userId );
        creatingDialogFragment.dismiss();
    }

    private void createHealthTree(final String userId){
        try {
            ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( userId ).enqueue( new Callback<UserDiseaseHistory>() {
                @Override
                public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                    if (response.isSuccessful()) {
                        diseaseHistory = response.body().getUserDiseaseHistories();
                        orderDiseases( diseaseHistory );

                        ApiClient.userTreatmentHistoryApi().getUserTreatmentHistory( userId ).enqueue( new Callback<UserTreatmentHistory>() {
                            @Override
                            public void onResponse(Call<UserTreatmentHistory> call, Response<UserTreatmentHistory> response) {
                                if (response.isSuccessful()) {
                                    treatmentHistory = response.body().getUserTreatmentHistory();
                                    orderTreatments( treatmentHistory );

                                    ApiClient.userDrugUsageHistoryApi().getUserDrugUsageHistory( userId ).enqueue( new Callback<UserDrugUsageHistory>() {
                                        @Override
                                        public void onResponse(Call<UserDrugUsageHistory> call, Response<UserDrugUsageHistory> response) {
                                            if (response.isSuccessful()) {
                                                drugUsageHistory = response.body().getUserDrugUsageHistory();
                                                orderDrugUsages( drugUsageHistory );

                                                healthHistory = new ArrayList<>();
                                                for (int d = 0; d < diseaseHistory.size(); d++) {
                                                    UserDiseaseHistory disease = diseaseHistory.get( d );

                                                    ArrayList<UserTreatmentHistory> tHistory = new ArrayList<>();
                                                    for (int t = 0; t < treatmentHistory.size(); t++) {
                                                        UserTreatmentHistory treatment = treatmentHistory.get( t );
                                                        if (treatment.getDiseaseId().equals( disease.getDiseaseId() )) {

                                                            ArrayList<UserDrugUsageHistory> duHistory = new ArrayList<>();
                                                            for (int u = 0; u < drugUsageHistory.size(); u++) {
                                                                UserDrugUsageHistory drugUsage = drugUsageHistory.get( u );
                                                                if (drugUsage.getDiseaseId().equals( treatment.getDiseaseId() )
                                                                        && drugUsage.getTreatmentId() == treatment.getTreatmentId()) {
                                                                    duHistory.add( drugUsage );
                                                                }
                                                            }
                                                            treatment.setUserDrugUsageHistory( duHistory );
                                                            tHistory.add( treatment );
                                                        }
                                                    }
                                                    disease.setUserTreatmentHistory( tHistory );
                                                    healthHistory.add( disease );
                                                }

                                                for (int i = 0; i < healthHistory.size(); i++) {
                                                    addDiseaseItem( healthHistory.get( i ), R.color.disease_color, R.drawable.ic_plus_outline_white_24dp );
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UserDrugUsageHistory> call, Throwable t) {
                                            Log.e( "UserHealthTree", t.getMessage() );
                                            Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                                        }
                                    } );


                                }
                            }

                            @Override
                            public void onFailure(Call<UserTreatmentHistory> call, Throwable t) {
                                Log.e( "UserHealthTree", t.getMessage() );
                                Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        } );
                    }
                }

                @Override
                public void onFailure(Call<UserDiseaseHistory> call, Throwable t) {
                    Log.e( "UserHealthTree", t.getMessage() );
                    Toast.makeText( getBaseContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( getBaseContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private void addDiseaseItem(UserDiseaseHistory disease, int colorRes, int iconRes) {
        final ExpandingItem item = diseaseExpandingList.createNewItem( R.layout.expanding_layout_disease );

        if (item != null) {
            item.setIndicatorColorRes( colorRes );
            item.setIndicatorIconRes( iconRes );
            TextView diseaseDate = (TextView) item.findViewById( R.id.row_date );
            diseaseDate.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString( disease.getDiseaseStartDate().getTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
            diseaseDate.setText( timeAgo );
            TextView diseaseName = (TextView) item.findViewById( R.id.row_title );
            diseaseName.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );
            diseaseName.setText( disease.getDisease().getDiseaseName() );
            TextView diseaseState = (TextView) item.findViewById( R.id.row_desc );
            diseaseDate.setTypeface( Typefaces.getRobotoLight( getBaseContext() ) );
            diseaseState.setText( diseaseStateArray[disease.getDiseaseStateId()] );

            ExpandingList expandingList = (ExpandingList) item.findViewById( R.id.expanding_list );
            expandingList.setVisibility( View.GONE );


            item.createSubItems( disease.getUserTreatmentHistory().size() );

            for (int i = 0; i < item.getSubItemsCount(); i++) {
                final View diseaseView = item.getSubItemView( i );
                UserTreatmentHistory treatment = disease.getUserTreatmentHistory().get( i );
                addTreatmentItem( diseaseView, treatment, R.color.treatment_color, R.drawable.ic_test_tube_white_24dp );
            }
        }
    }

    private void addTreatmentItem(final View diseaseView, UserTreatmentHistory treatment, int colorRes, int iconRes) {
        treatmentExpandingList = (ExpandingList) diseaseView.findViewById( R.id.expanding_list );
        RelativeLayout row = (RelativeLayout) diseaseView.findViewById( R.id.row );
        row.setVisibility( View.GONE );
        final ExpandingItem item = treatmentExpandingList.createNewItem( R.layout.expanding_layout_treatment );
        if (item != null) {
            item.setIndicatorColorRes( colorRes );
            item.setIndicatorIconRes( iconRes );
            TextView treatmentDate = (TextView) item.findViewById( R.id.row_date );
            treatmentDate.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString( treatment.getTreatmentStartDate().getTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
            treatmentDate.setText( timeAgo );
            TextView treatmentName = (TextView) item.findViewById( R.id.row_title );
            treatmentName.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );
            treatmentName.setText( treatment.getTreatment().getTreatmentName() );

            item.createSubItems( treatment.getUserDrugUsageHistory().size() );

            for (int i = 0; i < item.getSubItemsCount(); i++) {
                final View treatmentView = item.getSubItemView( i );
                UserDrugUsageHistory drugUsage = treatment.getUserDrugUsageHistory().get( i );
                addDrugUsageItem( treatmentView, drugUsage, R.color.drug_color, R.drawable.ic_pill_white_24dp );
            }
        }
    }

    private void addDrugUsageItem(final View treatmentView, UserDrugUsageHistory drugUsage, int colorRes, int iconRes) {
        TextView treatmentDate = (TextView) treatmentView.findViewById( R.id.row_date );
        treatmentDate.setTypeface( Typefaces.getLatoLight( getBaseContext() ) );
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString( drugUsage.getDrugUsageStartDate().getTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
        treatmentDate.setText( timeAgo );
        TextView treatmentName = (TextView) treatmentView.findViewById( R.id.row_title );
        treatmentName.setTypeface( Typefaces.getRobotoBold( getBaseContext() ) );
        treatmentName.setText( drugUsage.getDrug().getCommercialName() );
    }

    public void orderDiseases(ArrayList<UserDiseaseHistory> diseaseHistory) {
        UserDiseaseHistory temp;
        for (int i = 1; i < diseaseHistory.size(); i++) {
            for (int j = 0; j < diseaseHistory.size() - i; j++) {
                if (diseaseHistory.get( j ).getDiseaseStartDate().getTime() > diseaseHistory.get( j + 1 ).getDiseaseStartDate().getTime()) {
                    temp = diseaseHistory.get( j );
                    diseaseHistory.set( j, diseaseHistory.get( j + 1 ) );
                    diseaseHistory.set( j + 1, temp );
                }
            }
        }
    }

    public void orderTreatments(ArrayList<UserTreatmentHistory> treatmentHistory) {
        UserTreatmentHistory temp;
        for (int i = 1; i < treatmentHistory.size(); i++) {
            for (int j = 0; j < treatmentHistory.size() - i; j++) {
                if (treatmentHistory.get( j ).getTreatmentStartDate().getTime() > treatmentHistory.get( j + 1 ).getTreatmentStartDate().getTime()) {
                    temp = treatmentHistory.get( j );
                    treatmentHistory.set( j, treatmentHistory.get( j + 1 ) );
                    treatmentHistory.set( j + 1, temp );
                }
            }
        }
    }

    public void orderDrugUsages(ArrayList<UserDrugUsageHistory> drugUsageHistory) {
        UserDrugUsageHistory temp;
        for (int i = 1; i < drugUsageHistory.size(); i++) {
            for (int j = 0; j < drugUsageHistory.size() - i; j++) {
                if (drugUsageHistory.get( j ).getDrugUsageStartDate().getTime() > drugUsageHistory.get( j + 1 ).getDrugUsageStartDate().getTime()) {
                    temp = drugUsageHistory.get( j );
                    drugUsageHistory.set( j, drugUsageHistory.get( j + 1 ) );
                    drugUsageHistory.set( j + 1, temp );
                }
            }
        }
    }
}
