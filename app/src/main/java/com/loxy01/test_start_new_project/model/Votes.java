package com.loxy01.test_start_new_project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Votes {

    @SerializedName("kp")
    @Expose
    private Integer kp;
    @SerializedName("imdb")
    @Expose
    private Integer imdb;
    @SerializedName("filmCritics")
    @Expose
    private Integer filmCritics;
    @SerializedName("russianFilmCritics")
    @Expose
    private Integer russianFilmCritics;
    @SerializedName("await")
    @Expose
    private Integer await;

    public Integer getKp() {
        return kp;
    }

    public void setKp(Integer kp) {
        this.kp = kp;
    }

    public Integer getImdb() {
        return imdb;
    }

    public void setImdb(Integer imdb) {
        this.imdb = imdb;
    }

    public Integer getFilmCritics() {
        return filmCritics;
    }

    public void setFilmCritics(Integer filmCritics) {
        this.filmCritics = filmCritics;
    }

    public Integer getRussianFilmCritics() {
        return russianFilmCritics;
    }

    public void setRussianFilmCritics(Integer russianFilmCritics) {
        this.russianFilmCritics = russianFilmCritics;
    }

    public Integer getAwait() {
        return await;
    }

    public void setAwait(Integer await) {
        this.await = await;
    }
}
