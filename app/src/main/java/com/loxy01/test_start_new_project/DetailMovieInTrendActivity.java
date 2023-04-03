package com.loxy01.test_start_new_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailMovieInTrendActivity extends AppCompatActivity {

    ImageView poster;
    TextView title, movieDescriptionDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_in_trend);

        Intent getIntent = getIntent();

        poster = findViewById(R.id.MovieInTrendPosterDetail);
        title = findViewById(R.id.MovieInTrendNameDetail);
        movieDescriptionDetail = findViewById(R.id.MovieInTrendDescriptionDetail);

        String title_new_movie = getIntent.getStringExtra("title_in_trend");
        title.setText(title_new_movie);
        String urlPoster = getIntent.getStringExtra("posterUrl_in_trend");
        Picasso.get().load(urlPoster).into(poster);
        String description = getIntent.getStringExtra("description_in_trend");
        movieDescriptionDetail.setText(description);
    }
}