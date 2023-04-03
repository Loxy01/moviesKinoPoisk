package com.loxy01.test_start_new_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {

    ImageView poster;
    TextView title, movieDescriptionDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent getIntent = getIntent();

        poster = findViewById(R.id.MoviePosterDetail);
        title = findViewById(R.id.MovieNameDetail);
        movieDescriptionDetail = findViewById(R.id.MovieDescriptionDetail);

        String title_new_movie = getIntent.getStringExtra("title_new_movie");
        title.setText(title_new_movie);
        String urlPoster = getIntent.getStringExtra("posterUrl_new_movie");
        Picasso.get().load(urlPoster).into(poster);
        String description = getIntent.getStringExtra("description_new_movie");
        movieDescriptionDetail.setText(description);
    }
}