// Developer: Ahmet Kaymak
// Date: 05.05.2017

package com.project.uimodule.userhealthtree.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.ahmetkaymak.vitae.R;
import com.project.uimodule.userhealthtree.TimelineRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimelineDiseaseAdapter extends RecyclerView.Adapter<TimelineDiseaseAdapter.MyViewHolder> {

    private Context context;
    private Resources res;
    private List<TimelineRow> diseaseList;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private String and;
    private float scale;

    public TimelineDiseaseAdapter(Context context, Resources res, List<TimelineRow> diseaseList, boolean orderTheList) {
        this.context = context;
        this.res = res;
        this.diseaseList = diseaseList;
        and = res.getString( R.string.and );
        scale = context.getResources().getDisplayMetrics().density;
        if (orderTheList)
            this.diseaseList = rearrangeByDate( (ArrayList<TimelineRow>) diseaseList );
        else
            this.diseaseList = diseaseList;

        for (int i = 0; i < diseaseList.size(); i++) {
            expandState.append( i, false );
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rowDate;
        private TextView rowTitle;
        private TextView rowDescription;
        private ImageView rowImage;
        private View rowUpperLine;
        private View rowLowerLine;
        private View backgroundView;
        private LinearLayout diseaseButton;
        private ExpandableLinearLayout expandableLayout;

        public MyViewHolder(final View view) {
            super( view );
            rowDate = (TextView) view.findViewById( R.id.timeline_disease_row_date );
            rowTitle = (TextView) view.findViewById( R.id.timeline_disease_row_title );
            rowDescription = (TextView) view.findViewById( R.id.timeline_disease_row_desc );
            rowImage = (ImageView) view.findViewById( R.id.timeline_disease_row_image );
            rowUpperLine = (View) view.findViewById( R.id.timeline_disease_row_upperLine );
            rowLowerLine = (View) view.findViewById( R.id.timeline_disease_row_lowerLine );
            backgroundView = (View) view.findViewById( R.id.timeline_disease_row_background );
            diseaseButton = (LinearLayout) view.findViewById( R.id.timeline_disease_row_button );
            expandableLayout = (ExpandableLinearLayout) view.findViewById( R.id.timeline_disease_expandableLayout );
        }
    }

    @Override
    public TimelineDiseaseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_timeline_disease_row, parent, false );
        return new TimelineDiseaseAdapter.MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final TimelineDiseaseAdapter.MyViewHolder holder, final int position) {
        final TimelineRow row = diseaseList.get( position );

        if (position == 0 && position == diseaseList.size() - 1) {
            holder.rowUpperLine.setVisibility( View.INVISIBLE );
            holder.rowLowerLine.setVisibility( View.INVISIBLE );
        } else if (position == 0) {
            int pixels = (int) (row.getBellowLineSize() * scale + 0.5f);

            holder.rowUpperLine.setVisibility( View.INVISIBLE );
            holder.rowLowerLine.setBackgroundColor( row.getBellowLineColor() );
            holder.rowLowerLine.getLayoutParams().width = pixels;
        } else if (position == diseaseList.size() - 1) {
            int pixels = (int) (diseaseList.get( position - 1 ).getBellowLineSize() * scale + 0.5f);

            holder.rowLowerLine.setVisibility( View.INVISIBLE );
            holder.rowUpperLine.setBackgroundColor( diseaseList.get( position - 1 ).getBellowLineColor() );
            holder.rowUpperLine.getLayoutParams().width = pixels;
        } else {
            int pixels = (int) (row.getBellowLineSize() * scale + 0.5f);
            int pixels2 = (int) (diseaseList.get( position - 1 ).getBellowLineSize() * scale + 0.5f);

            holder.rowLowerLine.setBackgroundColor( row.getBellowLineColor() );
            holder.rowUpperLine.setBackgroundColor( diseaseList.get( position - 1 ).getBellowLineColor() );
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

        //Expandable View
        holder.setIsRecyclable( false );
        holder.expandableLayout.setInRecyclerView( true );
        holder.expandableLayout.setBackgroundColor( ContextCompat.getColor( context, R.color.white ) );
        holder.expandableLayout.setInterpolator( Utils.createInterpolator( Utils.LINEAR_OUT_SLOW_IN_INTERPOLATOR ) );
        holder.expandableLayout.setExpanded( expandState.get( position ) );
        holder.expandableLayout.initLayout();

        holder.expandableLayout.setListener( new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                expandState.put( position, true );
            }

            @Override
            public void onPreClose() {
                expandState.put( position, false );
            }
        } );

        holder.diseaseButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton( holder.expandableLayout );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return diseaseList.size();
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

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }
}

