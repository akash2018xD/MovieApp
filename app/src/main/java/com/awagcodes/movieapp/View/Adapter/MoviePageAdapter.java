package com.awagcodes.movieapp.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.awagcodes.movieapp.Model.Movie;
import com.awagcodes.movieapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class MoviePageAdapter extends RecyclerView.Adapter<MoviePageAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;
    private MoviePageAdapterInterface moviePageAdapterInterface;

    public MoviePageAdapter(List<Movie> movieList, Context context, MoviePageAdapterInterface moviePageAdapterInterface) {
        this.movieList = movieList;
        this.context = context;
        this.moviePageAdapterInterface = moviePageAdapterInterface;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_page_item,parent,false);
        return new MovieViewHolder(view, moviePageAdapterInterface);
    }

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w185/"+movie.getPosterPath())
                .override(120,120)
                .placeholder(R.mipmap.popcorn)
                .error(R.mipmap.broken_image)
                .fitCenter()
                .centerInside()
                .into(holder.img_movie_poster);
        holder.tv_original_title.setText(movie.getOriginalTitle());
        holder.tv_overview.setText(movie.getOverview());
        holder.tv_vote_average.setText(Double.toString(movie.getVoteAverage()));
        holder.tv_release_date.setText(movie.getReleaseDate());
        holder.tv_original_language.setText(movie.getOriginalLanguage().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public interface MoviePageAdapterInterface {
        void onMovieClickListener(int position);
        void onViewLoadComplete();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_movie_poster;
        TextView tv_original_title;
        TextView tv_overview;
        TextView tv_vote_average;
        TextView tv_release_date;
        TextView tv_original_language;
        CardView cardView_movie;
        MoviePageAdapterInterface moviePageAdapterInterface;

        MovieViewHolder(@NonNull View itemView, MoviePageAdapterInterface moviePageAdapterInterface) {
            super(itemView);

            img_movie_poster = itemView.findViewById(R.id.img_movie_poster);
            tv_original_title = itemView.findViewById(R.id.tv_original_title);
            tv_overview = itemView.findViewById(R.id.tv_overview);
            tv_vote_average = itemView.findViewById(R.id.tv_vote_average);
            tv_release_date = itemView.findViewById(R.id.tv_release_date);
            tv_original_language = itemView.findViewById(R.id.tv_original_language);
            cardView_movie = itemView.findViewById(R.id.cardView_movie);
            this.moviePageAdapterInterface = moviePageAdapterInterface;
            cardView_movie.setOnClickListener(this);
            moviePageAdapterInterface.onViewLoadComplete();
        }

        @Override
        public void onClick(View v) {
            moviePageAdapterInterface.onMovieClickListener(getAdapterPosition());
        }
    }
}
