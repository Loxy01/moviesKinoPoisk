package com.loxy01.test_start_new_project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Doc {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("alternativeName")
    @Expose
    private Object alternativeName;
    @SerializedName("countries")
    @Expose
    private List<Country> countries;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("enName")
    @Expose
    private Object enName;
    @SerializedName("externalId")
    @Expose
    private ExternalId externalId;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;
    @SerializedName("movieLength")
    @Expose
    private Integer movieLength;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("names")
    @Expose
    private List<Name> names;
    @SerializedName("poster")
    @Expose
    private Poster poster;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("shortDescription")
    @Expose
    private Object shortDescription;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("votes")
    @Expose
    private Votes votes;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("logo")
    @Expose
    private Logo logo;
    @SerializedName("watchability")
    @Expose
    private Watchability watchability;
    @SerializedName("releaseYears")
    @Expose
    private List<ReleaseYear> releaseYears;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(Object alternativeName) {
        this.alternativeName = alternativeName;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getEnName() {
        return enName;
    }

    public void setEnName(Object enName) {
        this.enName = enName;
    }

    public ExternalId getExternalId() {
        return externalId;
    }

    public void setExternalId(ExternalId externalId) {
        this.externalId = externalId;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Integer getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(Integer movieLength) {
        this.movieLength = movieLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Object getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(Object shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Votes getVotes() {
        return votes;
    }

    public void setVotes(Votes votes) {
        this.votes = votes;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public Watchability getWatchability() {
        return watchability;
    }

    public void setWatchability(Watchability watchability) {
        this.watchability = watchability;
    }

    public List<ReleaseYear> getReleaseYears() {
        return releaseYears;
    }

    public void setReleaseYears(List<ReleaseYear> releaseYears) {
        this.releaseYears = releaseYears;
    }
}
