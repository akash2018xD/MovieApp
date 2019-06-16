package com.awagcodes.movieapp.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.awagcodes.movieapp.View.Adapter.MoviePageAdapter;
import com.awagcodes.movieapp.ViewModel.MoviePageViewModel;
import com.awagcodes.movieapp.Model.Movie;
import com.awagcodes.movieapp.Model.MoviePage;
import com.awagcodes.movieapp.R;

import java.util.ArrayList;
import java.util.List;

public class MoviePageActivity extends AppCompatActivity implements MoviePageAdapter.MoviePageAdapterInterface, View.OnClickListener {

    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    private RecyclerView recyclerView_movie;
    private ProgressBar progress_movie_page;

    public MoviePageActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);
        init();
    }

    private void init() {
        recyclerView_movie = findViewById(R.id.recyclerView_movie);
        progress_movie_page = findViewById(R.id.progress_movie_page);
//        ImageView img_next = findViewById(R.id.img_next);
//        ImageView img_previous = findViewById(R.id.img_previous);
//        TextView tv_current_page = findViewById(R.id.tv_current_page);
//        img_next.setOnClickListener(this);
//        img_previous.setOnClickListener(this);
        int currentPage = 1;
        initViewModel(currentPage);
    }

    private void initViewModel(int pageNo) {
        recyclerView_movie.setVisibility(View.INVISIBLE);
        progress_movie_page.setVisibility(View.VISIBLE);

        MoviePageViewModel moviePageViewModel = ViewModelProviders.of(this).get(MoviePageViewModel.class);
        String pageNoToString = Integer.toString(pageNo);
        moviePageViewModel.init("e3b5f5e40e8cba8eba3a8e618ca8f2dd", "en-US", "popularity.desc", "false", "false", pageNoToString);
        moviePageViewModel.getMovieRepository().observe(this, new Observer<MoviePage>() {
            @Override
            public void onChanged(MoviePage moviePage) {
                List<Movie> movieList = moviePage.getResults();
                movieArrayList.addAll(movieList);
                setupRecyclerView();
            }
        });
    }

    private void setupRecyclerView() {
        MoviePageAdapter moviePageAdapter = new MoviePageAdapter(movieArrayList, this, this);
            recyclerView_movie.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_movie.setAdapter(moviePageAdapter);
            recyclerView_movie.setItemAnimator(new DefaultItemAnimator());
            recyclerView_movie.setNestedScrollingEnabled(true);
    }

    @Override
    public void onMovieClickListener(int position) {
        Log.e("position", position + "");
        Intent intent = new Intent(MoviePageActivity.this,MovieDetailsActivity.class);
        intent.putExtra("movie_id",movieArrayList.get(position).getId().toString());
        startActivity(intent);
    }

    @Override
    public void onViewLoadComplete() {
        recyclerView_movie.setVisibility(View.VISIBLE);
        progress_movie_page.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.img_next){
//
//        }else if(v.getId() == R.id.img_previous){
//
//        }
    }
}
