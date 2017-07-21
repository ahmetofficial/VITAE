// Developer: Ahmet Kaymak
// Date: 30.04.2016

package com.project.uimodule.userhealthtree;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmetkaymak.vitae.R;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.project.generalhealthmodule.UserDiseaseHistory;
import com.project.restservice.ApiClient;
import com.project.uimodule.BaseActivity;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHealthTreeActivity extends BaseActivity {

    public static String userId;
    private ExpandingList mExpandingList;
    private String diseaseStateArray[];
    private String diseaseLevelArray[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_health_tree );
        mExpandingList = (ExpandingList) findViewById( R.id.health_tree_expanding_list );
        diseaseStateArray=getResources().getStringArray(R.array.disease_state_array);
        diseaseLevelArray=getResources().getStringArray(R.array.disease_level_array);

        try {
            ApiClient.userDiseaseHistoryApi().getUserDiseaseHistory( userId ).enqueue( new Callback<UserDiseaseHistory>() {
                @Override
                public void onResponse(Call<UserDiseaseHistory> call, Response<UserDiseaseHistory> response) {
                    if (response.isSuccessful()) {
                        for (int i=0;i<response.body().getUserDiseaseHistories().size();i++){
                            addItem(response.body().getUserDiseaseHistories().get( i ).getDisease().getDiseaseName(),
                                    response.body().getUserDiseaseHistories().get( i ).getDiseaseStartDate(),
                                    diseaseStateArray[response.body().getUserDiseaseHistories().get( i ).getDiseaseStateId()],
                                    new String[]{},
                                    R.color.disease_color,
                                    R.drawable.icon_disease_white
                                    );
                        }
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


    private void addItem(String name, Date date, String state, String[] subItems, int colorRes, int iconRes) {
        final ExpandingItem item = mExpandingList.createNewItem( R.layout.expanding_layout );

        if (item != null) {
            item.setIndicatorColorRes( colorRes );
            item.setIndicatorIconRes( iconRes );
            TextView diseaseDate= (TextView) item.findViewById(R.id.timeline_disease_row_date);
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString( date.getTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
            diseaseDate.setText(timeAgo);
            TextView diseaseName= (TextView) item.findViewById(R.id.timeline_disease_row_title);
            diseaseName.setText(name);
            ((TextView) item.findViewById(R.id.timeline_disease_row_desc)).setText(state);

            item.createSubItems( subItems.length );
            for (int i = 0; i < item.getSubItemsCount(); i++) {
                final View view = item.getSubItemView( i );
                configureSubItem( item, view, subItems[i] );
            }
        }
    }

    private void configureSubItem(final ExpandingItem item, final View view, String subTitle) {
        ((TextView) view.findViewById( R.id.sub_title )).setText( subTitle );
    }

}
