package com.awagcodes.movieapp.WebApi;

import com.awagcodes.movieapp.Model.MovieDetails;
import com.awagcodes.movieapp.Model.MoviePage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie")
    Call<MoviePage> getMoviePage(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("sort_by") String sort_by,
            @Query("include_adult") String include_adult,
            @Query("include_video") String include_video,
            @Query("page") String page
    );

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(
            @Path("movie_id") String movie_id,
            @Query("api_key") String api_key
    );

}
