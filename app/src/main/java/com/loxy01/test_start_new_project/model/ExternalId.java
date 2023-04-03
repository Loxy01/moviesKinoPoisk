package com.loxy01.test_start_new_project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalId {

    @SerializedName("kpHD")
    @Expose
    private Object kpHD;
    @SerializedName("imdb")
    @Expose
    private Object imdb;
    @SerializedName("tmdb")
    @Expose
    private Integer tmdb;

    public Object getKpHD() {
        return kpHD;
    }

    public void setKpHD(Object kpHD) {
        this.kpHD = kpHD;
    }

    public Object getImdb() {
        return imdb;
    }

    public void setImdb(Object imdb) {
        this.imdb = imdb;
    }

    public Integer getTmdb() {
        return tmdb;
    }

    public void setTmdb(Integer tmdb) {
        this.tmdb = tmdb;
    }

}