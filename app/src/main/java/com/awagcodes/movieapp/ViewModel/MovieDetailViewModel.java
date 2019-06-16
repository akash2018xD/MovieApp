package com.awagcodes.movieapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.awagcodes.movieapp.WebApi.MovieDetailsRepository;
import com.awagcodes.movieapp.Model.MovieDetails;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<MovieDetails> mutableLiveData;

    public void init(String api_key,String movie_id){
        if(mutableLiveData != null){
            return;
        }
        MovieDetailsRepository movieDetailsRepository = MovieDetailsRepository.getInstance();
        mutableLiveData = movieDetailsRepository.getMovieDetails(api_key,movie_id);
    }

    public LiveData<MovieDetails> getMovieRepository(){
        return mutableLiveData;
    }

}
