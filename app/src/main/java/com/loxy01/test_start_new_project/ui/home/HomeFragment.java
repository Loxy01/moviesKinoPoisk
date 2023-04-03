package com.loxy01.test_start_new_project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loxy01.test_start_new_project.DetailMovieActivity;
import com.loxy01.test_start_new_project.DetailMovieInTrendActivity;
import com.loxy01.test_start_new_project.MainMoviesActivity;
import com.loxy01.test_start_new_project.R;
import com.loxy01.test_start_new_project.adapter.RecyclerViewInTrendMovieAdapter;
import com.loxy01.test_start_new_project.adapter.RecyclerViewNewMovieAdapter;
import com.loxy01.test_start_new_project.databinding.FragmentHomeBinding;
import com.loxy01.test_start_new_project.model.Doc;
import com.loxy01.test_start_new_project.model.MovieInfo;
import com.loxy01.test_start_new_project.service.MovieService;
import com.loxy01.test_start_new_project.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ArrayList<Doc> docResultArrayListNewMovies;
    private ArrayList<Doc> docResultArrayListInTrendMovies;
    RecyclerView newMovieRecyclerView;
    RecyclerViewNewMovieAdapter newMovieAdapter;

    RecyclerView inTrendMovieRecyclerView;
    RecyclerViewInTrendMovieAdapter inTrendMovieAdapter;
    ProgressBar progressBar;

    //Utils
    public static final String KEY_TITLE_NEW_MOVIE = "title_new_movie";
    public static final String KEY_POSTER_URL_NEW_MOVIE = "posterUrl_new_movie";
    public static final String KEY_DESCRIPTION_NEW_MOVIE = "description_new_movie";

    public static final String KEY_TITLE_IN_TREND = "title_in_trend";
    public static final String KEY_POSTER_URL_IN_TREND = "posterUrl_in_trend";
    public static final String KEY_DESCRIPTION_IN_TREND = "description_in_trend";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        Log.d("Fragments", "This is home fragment");
        progressBar = binding.progressBar;
        progressBar.setIndeterminate(true);
        setInTrendMovies();
        setNewMovies();
        return binding.getRoot();
    }

    private void setNewMovies() {
        MovieService service = RetrofitInstance.getMovieService();
        Call<MovieInfo> call2023 = service.getResultsNewMovies();

        call2023.enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                MovieInfo movieInfo = response.body();
                if(movieInfo != null){
                    docResultArrayListNewMovies = (ArrayList<Doc>) movieInfo.getDocs();
                    fillNewMoviesRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {

            }
        });
    }

    private void setInTrendMovies() {
        MovieService service = RetrofitInstance.getMovieService();
        Call<MovieInfo> callTrendMovies = service.getResultsInTrendMovies();

        callTrendMovies.enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {

                MovieInfo movieInfo = response.body();
                if(movieInfo != null) {
                    docResultArrayListInTrendMovies = (ArrayList<Doc>) movieInfo.getDocs();
                    Handler wait2seconds = new Handler();
                    wait2seconds.postDelayed(/*new Runnable*/() -> {
                        progressBar.setIndeterminate(false);
                        progressBar.setVisibility(View.GONE);
                    }, 500);
                    fillTrendMoviesRecyclerView();
                }

            }
            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {
                Log.d("fail", "fail " + call.request() + "\n " + t);
            }
        });
    }

    private void fillTrendMoviesRecyclerView() {
        inTrendMovieRecyclerView = binding.TrendMovieRecyclerView;
        inTrendMovieAdapter = new RecyclerViewInTrendMovieAdapter(docResultArrayListInTrendMovies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        inTrendMovieRecyclerView.setLayoutManager(layoutManager);
        inTrendMovieRecyclerView.setAdapter(inTrendMovieAdapter);
        inTrendMovieAdapter.setOnInTrendItemClickListener(this);
    }

    private void fillNewMoviesRecyclerView() {
        newMovieRecyclerView = binding.NewMovieRecyclerView;
        newMovieAdapter = new RecyclerViewNewMovieAdapter(docResultArrayListNewMovies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        newMovieRecyclerView.setLayoutManager(layoutManager);
        newMovieRecyclerView.setAdapter(newMovieAdapter);
        newMovieAdapter.setOnNewMovieItemClickListener(this);
    }

    public void onNewItemClick(int position) {
        Intent intent = new Intent(requireContext(), DetailMovieActivity.class);

        intent.putExtra(KEY_TITLE_NEW_MOVIE, docResultArrayListNewMovies.get(position).getName());
        intent.putExtra(KEY_POSTER_URL_NEW_MOVIE, docResultArrayListNewMovies.get(position).getPoster().getUrl());
        intent.putExtra(KEY_DESCRIPTION_NEW_MOVIE, docResultArrayListNewMovies.get(position).getDescription());

        startActivity(intent);
    }
    public void onInTrendItemClick(int position) {
        Intent intent = new Intent(requireContext(), DetailMovieInTrendActivity.class);

        intent.putExtra(KEY_TITLE_IN_TREND, docResultArrayListInTrendMovies.get(position).getName());
        intent.putExtra(KEY_POSTER_URL_IN_TREND, docResultArrayListInTrendMovies.get(position).getPoster().getUrl());
        intent.putExtra(KEY_DESCRIPTION_IN_TREND, docResultArrayListInTrendMovies.get(position).getDescription());

        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}