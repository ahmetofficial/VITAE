package com.project.uimodule.userhealthtree.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lavie.users.R;
import com.project.uimodule.userhealthtree.TimelineDiseaseRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TimelineDiseaseAdapter extends ArrayAdapter<TimelineDiseaseRow> {

    private Context context;
    private Resources res;
    private List<TimelineDiseaseRow> RowDataList;
    private String AND;


    public TimelineDiseaseAdapter(Context context, int resource, ArrayList<TimelineDiseaseRow> objects, boolean orderTheList) {
        super(context, resource, objects);
        this.context = context;
        res = context.getResources();
        AND = res.getString( R.string.and);
        if (orderTheList)
            this.RowDataList = rearrangeByDate(objects);
        else
            this.RowDataList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TimelineDiseaseRow row = RowDataList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate( R.layout.item_timeline_disease_row, null);

        TextView rowId = (TextView) view.findViewById( R.id.timeline_disease_row_disease_id );
        TextView rowDate = (TextView) view.findViewById(R.id.timeline_disease_row_date );
        TextView rowTitle = (TextView) view.findViewById(R.id.timeline_disease_row_title );
        TextView rowDescription = (TextView) view.findViewById(R.id.timeline_disease_row_desc );
        ImageView rowImage = (ImageView) view.findViewById(R.id.timeline_disease_row_image );
        View rowUpperLine = (View) view.findViewById(R.id.timeline_disease_row_upperLine );
        View rowLowerLine = (View) view.findViewById(R.id.timeline_disease_row_lowerLine );


        final float scale = getContext().getResources().getDisplayMetrics().density;


        if (position == 0 && position == RowDataList.size()-1) {
            rowUpperLine.setVisibility( View.INVISIBLE);
            rowLowerLine.setVisibility( View.INVISIBLE);
        }
        else if(position==0) {
            int pixels = (int) (row.getBellowLineSize() * scale + 0.5f);

            rowUpperLine.setVisibility( View.INVISIBLE);
            rowLowerLine.setBackgroundColor(row.getBellowLineColor());
            rowLowerLine.getLayoutParams().width = pixels;
        }
        else if(position == RowDataList.size()-1) {
            int pixels = (int) (RowDataList.get(position-1).getBellowLineSize() * scale + 0.5f);

            rowLowerLine.setVisibility( View.INVISIBLE);
            rowUpperLine.setBackgroundColor(RowDataList.get(position-1).getBellowLineColor());
            rowUpperLine.getLayoutParams().width = pixels;
        }
        else {
            int pixels = (int) (row.getBellowLineSize() * scale + 0.5f);
            int pixels2 = (int) (RowDataList.get(position-1).getBellowLineSize() * scale + 0.5f);

            rowLowerLine.setBackgroundColor(row.getBellowLineColor());
            rowUpperLine.setBackgroundColor(RowDataList.get(position-1).getBellowLineColor());
            rowLowerLine.getLayoutParams().width = pixels;
            rowUpperLine.getLayoutParams().width = pixels2;
        }


        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                row.getDate().getTime(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        rowDate.setText(timeAgo);

        if (row.getTitle() == null)
            rowTitle.setVisibility( View.GONE);
        else
            rowTitle.setText(row.getTitle());

        if (row.getId() == null)
            rowId.setVisibility( View.GONE);
        else
            rowId.setText(row.getId());

        if (row.getDescription() == null)
            rowDescription.setVisibility( View.GONE);
        else
            rowDescription.setText(row.getDescription());



        if (row.getImage() !=null) {
                rowImage.setImageBitmap(row.getImage());
        }

        int pixels = (int) (row.getImageSize() * scale + 0.5f);
        rowImage.getLayoutParams().width = pixels;
        rowImage.getLayoutParams().height = pixels;

        View backgroundView =  view.findViewById(R.id.timeline_disease_row_background );
        if (row.getBackgroundColor() == -1)
            backgroundView.setBackground(null);
        else {
            if (row.getBackgroundSize() == -1) {
                backgroundView.getLayoutParams().width = pixels;
                backgroundView.getLayoutParams().height = pixels;
            } else {
                int BackgroundPixels = (int) (row.getBackgroundSize() * scale + 0.5f);
                backgroundView.getLayoutParams().width = BackgroundPixels;
                backgroundView.getLayoutParams().height = BackgroundPixels;
            }
            GradientDrawable background = (GradientDrawable) backgroundView.getBackground();
            if (background != null) {
                background.setColor(row.getBackgroundColor());
            }
        }


        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) rowImage.getLayoutParams();
        marginParams.setMargins(0, (int) (pixels/2)*-1, 0, (pixels/2)*-1);


        return view;
    }

    private void appendPastTime(StringBuilder s, long timespan, int nameId, long timespanNext, int nameNextId) {
        s.append(res.getQuantityString(nameId, (int) timespan, timespan ));
        if (timespanNext > 0) {
            s.append(' ').append(AND).append(' ');
            s.append(res.getQuantityString(nameNextId, (int) timespanNext, timespanNext));
        }
    }

    private ArrayList<TimelineDiseaseRow> rearrangeByDate (ArrayList<TimelineDiseaseRow> objects) {
        if(objects.get(0) == null || objects.get(0).getDate() == null ) return objects;
        int size = objects.size();
        for (int i = 0; i< size-1; i++) {
            for (int j = i+1; j < size ; j++) {
                if (objects.get(i).getDate().compareTo(objects.get(j).getDate()) <= 0)
                    Collections.swap(objects, i, j);
            }

        }
        return objects;
    }

}
