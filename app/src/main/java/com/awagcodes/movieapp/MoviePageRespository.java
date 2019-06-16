package com.awagcodes.movieapp;

import androidx.lifecycle.MutableLiveData;

import com.awagcodes.movieapp.Model.MoviePage;
import com.awagcodes.movieapp.WebApi.ApiClient;
import com.awagcodes.movieapp.WebApi.ApiInterface;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePageRespository {

    private static MoviePageRespository moviePageRespository;

    public static MoviePageRespository getInstance(){
        if(moviePageRespository == null){
            moviePageRespository = new MoviePageRespository();
        }
        return moviePageRespository;
    }

    private ApiInterface apiInterface;

    public MoviePageRespository(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public MutableLiveData<MoviePage> getMoviePage(String api_key, String language, String sort_by, String include_adult, String include_video, String page){
        final MutableLiveData<MoviePage> moviePage = new MutableLiveData<>();
        apiInterface.getMoviePage(api_key,language,sort_by,include_adult,include_video,page).enqueue(new Callback<MoviePage>() {
            @Override
            public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){

                    moviePage.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<MoviePage> call, Throwable t) {
                    moviePage.setValue(null);
            }
        });

        return moviePage;
    }

}
