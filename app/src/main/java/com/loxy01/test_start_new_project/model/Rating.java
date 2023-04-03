package com.loxy01.test_start_new_project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    @Expose
    private Double kp;
    @SerializedName("imdb")
    @Expose
    private Double imdb;
    @SerializedName("filmCritics")
    @Expose
    private Double filmCritics;
    @SerializedName("russianFilmCritics")
    @Expose
    private Double russianFilmCritics;
    @SerializedName("await")
    @Expose
    private Double await;

    public Double getKp() {
        return kp;
    }

    public void setKp(Double kp) {
        this.kp = kp;
    }

    public Double getImdb() {
        return imdb;
    }

    public void setImdb(Double imdb) {
        this.imdb = imdb;
    }

    public Double getFilmCritics() {
        return filmCritics;
    }

    public void setFilmCritics(Double filmCritics) {
        this.filmCritics = filmCritics;
    }

    public Double getRussianFilmCritics() {
        return russianFilmCritics;
    }

    public void setRussianFilmCritics(Double russianFilmCritics) {
        this.russianFilmCritics = russianFilmCritics;
    }

    public Double getAwait() {
        return await;
    }

    public void setAwait(Double await) {
        this.await = await;
    }
}
