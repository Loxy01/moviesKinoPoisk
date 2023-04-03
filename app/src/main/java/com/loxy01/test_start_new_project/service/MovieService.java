package com.loxy01.test_start_new_project.service;

import com.loxy01.test_start_new_project.model.MovieInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("/v1/movie?page=1&limit=10&year=2023")
    Call<MovieInfo> getResultsNewMovies();

    @GET("/v1/movie?page=1&limit=10&top10=%21null")
    Call<MovieInfo> getResultsInTrendMovies();

    @GET("/v1/movie?page=1&limit=10&id=875215")
    Call<MovieInfo> getResultsMagiciansMovie();

}
