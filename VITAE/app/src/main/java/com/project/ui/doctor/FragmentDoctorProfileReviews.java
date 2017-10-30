// Developer: Ahmet Kaymak
// Date: 27.02.2016

package com.project.ui.doctor;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ahmetkaymak.vitae.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.project.core.doctormodule.Doctor;
import com.project.core.doctormodule.PatientDoctorRate;
import com.project.restservice.ApiClient;
import com.project.ui.doctor.adapter.PatientDoctorRateAdapter;
import com.project.utils.Typefaces;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDoctorProfileReviews extends Fragment implements OnChartValueSelectedListener {

    private ArrayList<PatientDoctorRate> rates = new ArrayList<>();
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayoutRates;
    private PatientDoctorRateAdapter mAdapter;
    private View profileView;
    private PieChart pieChart;
    private FloatingActionButton fab;
    private FragmentAddDoctorReview doctorReview;

    private ViewFlipper viewFlipper;
    private TextView dontFoundText;
    private String visitorUserId;
    private String visitedUserId;
    private String doctorName;
    private ImageView imageView;
    private TextView totalReviewCount;
    private TextView totalReviewCountText;

    public FragmentDoctorProfileReviews(String visitorUserId, String visitedUserId,String doctorName) {
        this.visitorUserId = visitorUserId;
        this.visitedUserId = visitedUserId;
        this.doctorName = doctorName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        profileView = inflater.inflate( R.layout.fragment_doctor_review, container, false );
        viewFlipper = (ViewFlipper) profileView.findViewById( R.id.view_flipper );
        dontFoundText = (TextView) profileView.findViewById( R.id.dont_found_text );
        recyclerView = (RecyclerView) profileView.findViewById( R.id.recycler_view );
        relativeLayoutRates = (RelativeLayout) profileView.findViewById( R.id.relative_layout_recyler_view );
        relativeLayoutRates.setBackgroundColor( getContext().getColor( R.color.colorPrimary ) );
        totalReviewCount = (TextView) profileView.findViewById( R.id.total_review_count);
        totalReviewCount.setTypeface( Typefaces.getLatoLight( getContext() ) );
        totalReviewCountText = (TextView) profileView.findViewById( R.id.total_review_count_text );
        totalReviewCountText.setTypeface( Typefaces.getLatoLight( getContext() ) );
        imageView= (ImageView) profileView.findViewById( R.id.imageView );
        fab = (FloatingActionButton) profileView.findViewById( R.id.fab_rating ) ;

        //Pie Chart
        pieChart = (PieChart) profileView.findViewById( R.id.doctor_rating_pie_chart );
        pieChart.setUsePercentValues( true );
        pieChart.getDescription().setEnabled( false );
        pieChart.setExtraOffsets( 5, 10, 5, 5 );
        pieChart.setDragDecelerationFrictionCoef( 0.95f );
        pieChart.setCenterTextTypeface( Typefaces.getRobotoLight( getContext() ) );
        pieChart.setCenterText( doctorName );
        pieChart.setDrawHoleEnabled( true );
        pieChart.setHoleColor( Color.WHITE );
        pieChart.setTransparentCircleColor( Color.WHITE );
        pieChart.setTransparentCircleAlpha( 110 );
        pieChart.setHoleRadius( 58f );
        pieChart.setTransparentCircleRadius( 61f );
        pieChart.setDrawCenterText( true );
        pieChart.setRotationAngle( 0 );
        pieChart.setRotationEnabled( true );
        pieChart.setHighlightPerTapEnabled( true );
        pieChart.setOnChartValueSelectedListener( this );
        setData( visitedUserId );
        pieChart.animateY( 1400, Easing.EasingOption.EaseInOutQuad );

        // entry label styling
        pieChart.setEntryLabelColor( Color.WHITE );
        pieChart.setEntryLabelTypeface( Typefaces.getRobotoBold( getContext() ) );
        pieChart.setEntryLabelTextSize( 15f );
        getDoctorReviews( visitedUserId );


        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                doctorReview = new FragmentAddDoctorReview( visitorUserId, visitedUserId );
                doctorReview.show( fragmentManager, "" );
            }
        } );

        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                doctorReview = new FragmentAddDoctorReview( visitorUserId, visitedUserId );
                doctorReview.show( fragmentManager, "" );
            }
        } );


        return profileView;
    }

    private void getDoctorReviews(String doctorId) {
        try {
            ApiClient.doctorApi().getPatientDoctorRates( doctorId ).enqueue( new Callback<PatientDoctorRate>() {
                @Override
                public void onResponse(Call<PatientDoctorRate> call, Response<PatientDoctorRate> response) {
                    if (response.isSuccessful()) {
                        rates = (ArrayList) response.body().getRates();
                        if (rates.size() == 0) {
                            viewFlipper.setDisplayedChild( 1 );
                            dontFoundText.setText( getString( R.string.reviews_are_important ) );
                        } else {
                            mAdapter = new PatientDoctorRateAdapter( visitorUserId, rates, getContext() );
                            recyclerView.setHasFixedSize( true );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getContext() );
                            recyclerView.setLayoutManager( mLayoutManager );
                            recyclerView.setItemAnimator( new DefaultItemAnimator() );
                            recyclerView.setAdapter( mAdapter );
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PatientDoctorRate> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }

    }

    private void setData(String doctorId) {

        final ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        try {
            ApiClient.doctorApi().getDoctorGeneralRatingParameters( doctorId ).enqueue( new Callback<Doctor>() {
                @Override
                public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                    if (response.isSuccessful()) {
                        entries.add( new PieEntry( (float) (response.body().getVote1Count()) ) );
                        entries.add( new PieEntry( (float) (response.body().getVote2Count()) ) );
                        entries.add( new PieEntry( (float) (response.body().getVote3Count()) ) );
                        entries.add( new PieEntry( (float) (response.body().getVote4Count()) ) );
                        entries.add( new PieEntry( (float) (response.body().getVote5Count()) ) );
                        totalReviewCountText.setText( String.valueOf(response.body().getTotalVoteNumber()) );

                        PieDataSet dataSet = new PieDataSet( entries,getString( R.string.patients_review ));

                        dataSet.setDrawIcons( false );
                        dataSet.setSliceSpace( 3f );
                        dataSet.setIconsOffset( new MPPointF( 0, 40 ) );
                        dataSet.setSelectionShift( 5f );

                        ArrayList<Integer> colors = new ArrayList<Integer>();
                        colors.add( getContext().getColor( R.color.color_rank_0_1 ) );
                        colors.add( getContext().getColor( R.color.color_rank_1_2 ) );
                        colors.add( getContext().getColor( R.color.color_rank_2_3 ) );
                        colors.add( getContext().getColor( R.color.color_rank_3_4 ) );
                        colors.add( getContext().getColor( R.color.color_rank_4_5 ) );

                        dataSet.setColors( colors );
                        PieData data = new PieData( dataSet );
                        data.setValueFormatter( new PercentFormatter() );
                        data.setValueTextSize( 10f );
                        data.setValueTextColor( getContext().getColor( R.color.white ) );
                        data.setValueTypeface( Typefaces.getRobotoBold( getContext() ) );
                        pieChart.setData( data );

                        // undo all highlights
                        pieChart.highlightValues( null );

                        pieChart.invalidate();

                    }
                }

                @Override
                public void onFailure(Call<Doctor> call, Throwable t) {
                    Log.e( "UserTimeline", t.getMessage() );
                    Toast.makeText( getContext(), t.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        } catch (Exception e) {
            Log.e( "UserTimeline", e.getMessage() );
            Toast.makeText( this.getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private SpannableString generateCenterSpannableText(String text) {

        SpannableString s = new SpannableString( text );
        s.setSpan( new RelativeSizeSpan( 1.7f ), 0, 14, 0 );
        s.setSpan( new StyleSpan( Typeface.NORMAL ), 14, s.length() - 15, 0 );
        s.setSpan( new ForegroundColorSpan( Color.GRAY ), 14, s.length() - 15, 0 );
        s.setSpan( new RelativeSizeSpan( .8f ), 14, s.length() - 15, 0 );
        s.setSpan( new StyleSpan( Typeface.ITALIC ), s.length() - 14, s.length(), 0 );
        s.setSpan( new ForegroundColorSpan( ColorTemplate.getHoloBlue() ), s.length() - 14, s.length(), 0 );
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
