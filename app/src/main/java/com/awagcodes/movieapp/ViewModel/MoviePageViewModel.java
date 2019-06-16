package com.awagcodes.movieapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.awagcodes.movieapp.Model.MoviePage;
import com.awagcodes.movieapp.MoviePageRespository;

public class MoviePageViewModel extends ViewModel {

    private MutableLiveData<MoviePage> mutableLiveData;

    public void init(String api_key,String language,String sort_by,String include_adult,String include_video,String page){
        if(mutableLiveData != null){
            return;
        }
        MoviePageRespository moviePageRespository = MoviePageRespository.getInstance();
        mutableLiveData = moviePageRespository.getMoviePage(api_key,language,sort_by,include_adult,include_video,page);
    }

    public LiveData<MoviePage> getMovieRepository(){
        return mutableLiveData;
    }

}
