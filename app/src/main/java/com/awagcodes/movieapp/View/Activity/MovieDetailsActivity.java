package com.awagcodes.movieapp.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.awagcodes.movieapp.Model.MovieDetails;
import com.awagcodes.movieapp.Utils.NumberToWords;
import com.awagcodes.movieapp.ViewModel.MovieDetailViewModel;
import com.awagcodes.movieapp.R;
import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private String movie_id;

    private ImageView img_backdrop;
    private TextView tv_overview;
    private TextView tv_runtime;
    private TextView tv_genre_name;
    private TextView tv_vote_count;
    private TextView tv_vote_average;
    private TextView tv_language;
    private TextView tv_release_date;
    private TextView tv_budget;
    private TextView tv_revenue;
    private TextView tv_heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        Log.e("movie_id", movie_id);
        init();
    }

    private void init() {

        img_backdrop = findViewById(R.id.img_backdrop);
        tv_overview = findViewById(R.id.tv_overview);
        tv_runtime = findViewById(R.id.tv_runtime);
        tv_genre_name = findViewById(R.id.tv_genre_name);
        tv_vote_count = findViewById(R.id.tv_vote_count);
        tv_vote_average = findViewById(R.id.tv_vote_average);
        tv_language = findViewById(R.id.tv_language);
        tv_release_date = findViewById(R.id.tv_release_date);
        tv_budget = findViewById(R.id.tv_budget);
        tv_revenue = findViewById(R.id.tv_revenue);
        tv_heading = findViewById(R.id.tv_heading);
        ImageView img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(this);

        initViewModel();
    }

    private void initViewModel() {
        MovieDetailViewModel movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        movieDetailViewModel.init("e3b5f5e40e8cba8eba3a8e618ca8f2dd", movie_id);
        movieDetailViewModel.getMovieRepository().observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                fillViewData(movieDetails);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void fillViewData(MovieDetails movieDetails) {

        if (movieDetails.getBackdropPath() != null) {
            Glide.with(this).load("https://image.tmdb.org/t/p/w780/" + movieDetails.getBackdropPath())
                    .placeholder(R.mipmap.popcorn)
                    .error(R.mipmap.broken_image)
                    .centerInside()
                    .fitCenter()
                    .centerCrop()
                    .into(img_backdrop);
        }

        if (movieDetails.getOriginalTitle() != null) {
            tv_heading.setText(movieDetails.getOriginalTitle());
        } else tv_heading.setText("NA");

        if (movieDetails.getOverview() != null) {
            tv_overview.setText(movieDetails.getOverview());
        } else tv_overview.setText("NA");

        if (movieDetails.getRuntime() != null) {
            tv_runtime.setText(movieDetails.getRuntime() + " Minutes");
        } else tv_runtime.setText("NA");

        if (movieDetails.getGenres().get(0).getName() != null) {
            tv_genre_name.setText(movieDetails.getGenres().get(0).getName());
        } else tv_genre_name.setText("NA");

        if (movieDetails.getVoteCount() != null) {
            tv_vote_count.setText(movieDetails.getVoteCount() + " Votes");
        } else tv_vote_count.setText("NA");

        if (movieDetails.getVoteAverage() != null) {
            tv_vote_average.setText(Double.toString(movieDetails.getVoteAverage()));
        } else tv_vote_average.setText("NA");

        if (movieDetails.getSpokenLanguages().get(0).getIso6391() != null) {
            tv_language.setText(movieDetails.getSpokenLanguages().get(0).getIso6391().toUpperCase());
        } else tv_language.setText("NA");

        if (movieDetails.getReleaseDate() != null) {
            tv_release_date.setText(movieDetails.getReleaseDate());
        } else tv_release_date.setText("NA");

        if (movieDetails.getBudget() != null) {
            tv_budget.setText(NumberToWords.format(movieDetails.getBudget()));
        } else tv_budget.setText("NA");

        if (movieDetails.getRevenue() != null) {
            tv_revenue.setText(NumberToWords.format(movieDetails.getRevenue()));
        } else tv_revenue.setText("NA");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_back) {
            finish();
        }
    }
}
