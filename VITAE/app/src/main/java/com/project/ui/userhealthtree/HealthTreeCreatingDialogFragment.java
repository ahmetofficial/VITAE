package com.project.ui.userhealthtree;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmetkaymak.vitae.R;
import com.project.utils.Typefaces;

public class HealthTreeCreatingDialogFragment extends DialogFragment {

    private TextView loadingText;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_loading, container, false);
        loadingText = (TextView) rootView.findViewById( R.id.alert_text );
        progressBar =(ProgressBar) rootView.findViewById( R.id.progressBar2 ) ;
        progressBar.setVisibility(View.VISIBLE);
        loadingText.setTypeface( Typefaces.getRobotoMedium( getContext() ) );
        loadingText.setText( R.string.health_tree_creating );
        return rootView;
    }

}
