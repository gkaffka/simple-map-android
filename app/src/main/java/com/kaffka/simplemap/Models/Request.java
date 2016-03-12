package com.kaffka.simplemap.Models;

// The model that would become, after serialization, the body our API request
final public class Request {

    final private String tipoBusca;
    final private String latitude;
    final private String longitude;
    final private String subTipos;
    final private String textoBusca;

    public Request(String tipoBusca, String latitude, String longitude, String subTipos, String textoBusca) {
        this.tipoBusca = tipoBusca;
        this.latitude = latitude;
        this.longitude = longitude;
        this.subTipos = subTipos;
        this.textoBusca = textoBusca;
    }

    public String getTipoBusca() {
        return tipoBusca;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getSubTipos() {
        return subTipos;
    }

    public String getTextoBusca() {
        return textoBusca;
    }
}
