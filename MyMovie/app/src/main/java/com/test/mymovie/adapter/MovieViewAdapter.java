package com.test.mymovie.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.mymovie.databinding.MovieItemViewBinding;
import com.test.mymovie.model.NaverMovieDTO;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter {

    protected List<NaverMovieDTO> movieList;

    public MovieViewAdapter(List<NaverMovieDTO> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        MovieItemViewBinding viewBinding = MovieItemViewBinding
                .inflate(layoutInflater, parent, false);

        return new MovieViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder viewHolder = (MovieViewHolder) holder;

        MovieItemViewBinding movieBinding = viewHolder.viewBinding;

        NaverMovieDTO movieDTO = movieList.get(position);

        Spanned movieTitle = Html.fromHtml(movieDTO.getTitle(), Html.FROM_HTML_MODE_LEGACY);
//        movieBinding.movieItemTitle.setText(movieDTO.getTitle());
        movieBinding.movieItemTitle.setText(movieTitle);

        if(!movieDTO.getImage().isEmpty()){
            movieBinding.movieItemImage.setBackground(null);
            Picasso.get().load(movieDTO.getImage()).into(movieBinding.movieItemImage);
        }
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public MovieItemViewBinding viewBinding;

        public MovieViewHolder(@NonNull MovieItemViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}
