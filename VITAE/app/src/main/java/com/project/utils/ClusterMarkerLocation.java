package com.project.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterMarkerLocation implements ClusterItem {

    private LatLng position;

    public ClusterMarkerLocation( LatLng latLng ) {
        position = latLng;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public void setPosition( LatLng position ) {
        this.position = position;
    }
}