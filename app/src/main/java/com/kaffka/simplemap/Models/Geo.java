package com.kaffka.simplemap.Models;

// A simple model to encapsulate the Lat/Lon coordinates
final public class Geo {
    final private Double Latitude;
    final private Double Longitude;

    public Geo(Double latitude, Double longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }
}
