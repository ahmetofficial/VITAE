// Developer: Ahmet Kaymak
// Date: 05.05.2017

package com.project.uimodule.userhealthtree.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lavie.users.R;
import com.project.generalhealthmodule.UserTreatmentHistory;
import com.project.restservice.ApiClient;
import com.project.uimodule.userhealthtree.TimelineRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimelineTreatmentAdapter extends RecyclerView.Adapter<TimelineTreatmentAdapter.MyViewHolder> {

    private Context context;
    private Resources res;
    private List<TimelineRow> treatmentList;
    private ArrayList<UserTreatmentHistory> userTreatmentHistoryList;
    private ArrayList<TimelineRow> timelineTreatmentRowsList = new ArrayList<>();
    private String userId;
    private String and;
    private float scale;

    public TimelineTreatmentAdapter(Context context, Resources res, List<TimelineRow> rowDataList, String userId, boolean orderTheList) {
        this.context = context;
        this.res = res;
        treatmentList = rowDataList;
        this.userId=userId;
        and = res.getString( R.string.and );
        scale = context.getResources().getDisplayMetrics().density;
        if (orderTheList)
            this.treatmentList = rearrangeByDate( (ArrayList<TimelineRow>) rowDataList );
        else
            this.treatmentList = rowDataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rowDate;
        private TextView rowTitle;
        private TextView rowDescription;
        private ImageView rowImage;
        private View rowUpperLine;
        private View rowLowerLine;
        private View backgroundView;
        private RelativeLayout diseaseButton;

        public MyViewHolder(final View view) {
            super( view );
            rowDate = (TextView) view.findViewById( R.id.timeline_disease_row_date );
            rowTitle = (TextView) view.findViewById( R.id.timeline_disease_row_title );
            rowDescription = (TextView) view.findViewById( R.id.timeline_disease_row_desc );
            rowImage = (ImageView) view.findViewById( R.id.timeline_disease_row_image );
            rowUpperLine = (View) view.findViewById( R.id.timeline_disease_row_upperLine );
            rowLowerLine = (View) view.findViewById( R.id.timeline_disease_row_lowerLine );
            backgroundView = (View) view.findViewById( R.id.timeline_disease_row_background );
            diseaseButton = (RelativeLayout) view.findViewById( R.id.timeline_disease_row_disease_info );
        }
    }

    @Override
    public TimelineTreatmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_timeline_disease_row, parent, false );
        return new TimelineTreatmentAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final TimelineTreatmentAdapter.MyViewHolder holder, final int position) {
        TimelineRow row = treatmentList.get( position );

        if (position == 0 && position == treatmentList.size() - 1) {
            holder.rowUpperLine.setVisibility( View.INVISIBLE );
            holder.rowLowerLine.setVisibility( View.INVISIBLE );
        } else if (position == 0) {
            int pixels = (int) (row.getBellowLineSize() * scale + 0.5f);

            holder.rowUpperLine.setVisibility( View.INVISIBLE );
            holder.rowLowerLine.setBackgroundColor( row.getBellowLineColor() );
            holder.rowLowerLine.getLayoutParams().width = pixels;
        } else if (position == treatmentList.size() - 1) {
            int pixels = (int) (treatmentList.get( position - 1 ).getBellowLineSize() * scale + 0.5f);

            holder.rowLowerLine.setVisibility( View.INVISIBLE );
            holder.rowUpperLine.setBackgroundColor( treatmentList.get( position - 1 ).getBellowLineColor() );
            holder.rowUpperLine.getLayoutParams().width = pixels;
        } else {
            int pixels = (int) (row.getBellowLineSize() * scale + 0.5f);
            int pixels2 = (int) (treatmentList.get( position - 1 ).getBellowLineSize() * scale + 0.5f);

            holder.rowLowerLine.setBackgroundColor( row.getBellowLineColor() );
            holder.rowUpperLine.setBackgroundColor( treatmentList.get( position - 1 ).getBellowLineColor() );
            holder.rowLowerLine.getLayoutParams().width = pixels;
            holder.rowUpperLine.getLayoutParams().width = pixels2;
        }


        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                row.getDate().getTime(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS );
        holder.rowDate.setText( timeAgo );

        if (row.getTitle() == null)
            holder.rowTitle.setVisibility( View.GONE );
        else
            holder.rowTitle.setText( row.getTitle() );
        if (row.getDescription() == null)
            holder.rowDescription.setVisibility( View.GONE );
        else
            holder.rowDescription.setText( row.getDescription() );


        if (row.getImage() != null) {
            holder.rowImage.setImageBitmap( row.getImage() );
        }

        int pixels = (int) (row.getImageSize() * scale + 0.5f);
        holder.rowImage.getLayoutParams().width = pixels;
        holder.rowImage.getLayoutParams().height = pixels;

        if (row.getBackgroundColor() == -1)
            holder.backgroundView.setBackground( null );
        else {
            if (row.getBackgroundSize() == -1) {
                holder.backgroundView.getLayoutParams().width = pixels;
                holder.backgroundView.getLayoutParams().height = pixels;
            } else {
                int BackgroundPixels = (int) (row.getBackgroundSize() * scale + 0.5f);
                holder.backgroundView.getLayoutParams().width = BackgroundPixels;
                holder.backgroundView.getLayoutParams().height = BackgroundPixels;
            }
            GradientDrawable background = (GradientDrawable) holder.backgroundView.getBackground();
            if (background != null) {
                background.setColor( row.getBackgroundColor() );
            }
        }
        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) holder.rowImage.getLayoutParams();
        marginParams.setMargins( 0, (int) (pixels / 2) * -1, 0, (pixels / 2) * -1 );

        holder.diseaseButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String diseaseId = treatmentList.get( position ).getId();
                    getTreatments(diseaseId);
                } catch (Exception e) {
                    Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return treatmentList.size();
    }

    private ArrayList<TimelineRow> rearrangeByDate(ArrayList<TimelineRow> objects) {
        if (objects.get( 0 ) == null || objects.get( 0 ).getDate() == null) return objects;
        int size = objects.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (objects.get( i ).getDate().compareTo( objects.get( j ).getDate() ) <= 0)
                    Collections.swap( objects, i, j );
            }
        }
        return objects;
    }

    private void getTreatments(String diseaseId){
        try {
            ApiClient.userTreatmentHistoryApi().getDiseaseTreatmentHistory( userId ,diseaseId).enqueue( new Callback<UserTreatmentHistory>() {
                @Override
                public void onResponse(Call<UserTreatmentHistory> call, Response<UserTreatmentHistory> response) {
                    if (response.isSuccessful()) {
                        userTreatmentHistoryList = response.body().getUserTreatmentHistory();
                        for (int i = 0; i < userTreatmentHistoryList.size(); i++) {
                            timelineTreatmentRowsList.add( new TimelineRow(
                                    String.valueOf(userTreatmentHistoryList.get( i ).getTreatmentId())
                                    ,userTreatmentHistoryList.get( i ).getTreatmentStartDate()
                                    ,userTreatmentHistoryList.get( i ).getTreatment().getTreatmentName()
                                    ,null
                                    , BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_treatment_green)
                                    , context.getResources().getColor( R.color.treatment_color_light ), 10, 50, -1, 50 ) );
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserTreatmentHistory> call, Throwable t) {
                    Log.e( "UserHealthTree", t.getMessage() );
                    Toast.makeText( context, t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserHealthTree", e.getMessage() );
            Toast.makeText( context, e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}

