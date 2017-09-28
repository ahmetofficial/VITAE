package com.project.utils;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarAction extends AsyncTask<Integer, Integer, String> {

    private ProgressBar progressBar;

    public ProgressBarAction(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Integer... params) {
        return "Task Completed.";
    }
    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility( View.GONE );
    }
    @Override
    protected void onPreExecute() {
        progressBar.setVisibility( View.INVISIBLE );
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setVisibility( View.VISIBLE );
    }
}