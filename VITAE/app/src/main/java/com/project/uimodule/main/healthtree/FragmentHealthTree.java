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
        diseaseSpinner = (MaterialSpinner) health_tree_view.findViewById( R.id.disease_spinner );
        diseaseCellDiseaseName = (TextView) health_tree_view.findViewById( R.id.disease_cell_disease_name_text );
        diseaseCellUserDiseaseCount = (TextView) health_tree_view.findViewById( R.id.disease_cell_total_disease_number );
        diseaseCellDiseaseLevel = (TextView) health_tree_view.findViewById( R.id.disease_cell_level_of_disease_text );
        diseaseCellDiseaseState = (TextView) health_tree_view.findViewById( R.id.disease_cell_state_of_disease_text );
        diseaseCellDiseaseStartDate = (TextView) health_tree_view.findViewById( R.id.disease_cell_content_start_date );
        diseaseCellDiseaseFinishDate = (TextView) health_tree_view.findViewById( R.id.disease_cell_content_finish_date );
        diseaseCellCountOfDrugs= (TextView) health_tree_view.findViewById( R.id.disease_cell_number_of_drugs );
        diseaseCellCountOfTreatments= (TextView) health_tree_view.findViewById( R.id.disease_cell_number_of_treatments );

        try {
            final FoldingCell drugFoldingCell = (FoldingCell) health_tree_view.findViewById( R.id.drug_folding_cell );
            drugFoldingCell.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drugFoldingCell.toggle( false );
                }
            } );

            final FoldingCell diseaseFoldingCell = (FoldingCell) health_tree_view.findViewById( R.id.disease_folding_cell );

            diseaseFoldingCell.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diseaseFoldingCell.toggle( false );
                }
            } );

            fillUserDiseaseHistory( userId );

        } catch (Exception e) {
            Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }

        return health_tree_view;
    }

    private void fillUserDiseaseHistory(String userId) {
        final String[] diseaseLevel = getResources().getStringArray( R.array.disease_level_array );
        final String[] diseaseState = getResources().getStringArray( R.array.disease_state_array );
        try {
            ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( userId ).enqueue( new Callback<UserDiseaseHistory>() {
                @Override
                public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                    if (response.isSuccessful()) {

                        userDiseaseHistoryList = response.body().getUserDiseaseHistories();
                        diseaseCellUserDiseaseCount.setText( String.valueOf( userDiseaseHistoryList.size() ) );
                        final List<String> userDiseases = new ArrayList<String>();
                        for (int i = 0; i < userDiseaseHistoryList.size(); i++) {
                            userDiseases.add( userDiseaseHistoryList.get( i ).getDisease().getDiseaseName() );
                        }
                        diseaseSpinner.setItems( userDiseases );

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

                                diseaseCellCountOfTreatments.setText(String.valueOf(userDiseaseHistoryList.get(position).getCountOfTreatments()));
                                diseaseCellCountOfDrugs.setText(String.valueOf(userDiseaseHistoryList.get(position).getCountOfDrugs()));
                            }
                        } );
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

}
