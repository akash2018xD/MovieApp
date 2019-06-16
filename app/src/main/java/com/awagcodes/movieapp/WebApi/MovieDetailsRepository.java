package com.awagcodes.movieapp.WebApi;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.awagcodes.movieapp.Model.MovieDetails;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsRepository {

    private static MovieDetailsRepository movieDetailsRepository;

    public static MovieDetailsRepository getInstance(){
        if(movieDetailsRepository == null){
            movieDetailsRepository = new MovieDetailsRepository();
        }
        return movieDetailsRepository;
    }

    private ApiInterface apiInterface;

    private MovieDetailsRepository(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public MutableLiveData<MovieDetails> getMovieDetails(String api_key,String movie_id){
        final MutableLiveData<MovieDetails> movieDetail = new MutableLiveData<>();
        apiInterface.getMovieDetails(movie_id,api_key).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    movieDetail.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.e("message",t.getMessage());
                movieDetail.setValue(null);
            }
        });
        return movieDetail;
    }

}
