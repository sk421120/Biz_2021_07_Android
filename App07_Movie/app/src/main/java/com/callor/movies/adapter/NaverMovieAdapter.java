package com.callor.movies.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.callor.movies.databinding.FragmentSecondBinding;
import com.callor.movies.databinding.MovieItemViewBinding;
import com.callor.movies.model.MovieDTO;

import java.util.List;

public class NaverMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieDTO> movieList;

    public NaverMovieAdapter(List<MovieDTO> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieItemViewBinding movieBinding = MovieItemViewBinding.inflate(layoutInflater,
                parent,false);
        return new MovieViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder viewHolder = (MovieViewHolder) holder;
        MovieDTO movieDTO = movieList.get(position);
        MovieItemViewBinding mbinding = viewHolder.movieBinding;

        mbinding.movieItemTitle.setText(Html.fromHtml(movieDTO.getTitle(),
                Html.FROM_HTML_MODE_LEGACY));
        String strDirect = String.format("<b> Director : </b> %s",
                movieDTO.getDirector());
        mbinding.movieItemDirect.setText(Html.fromHtml(strDirect,
                Html.FROM_HTML_MODE_LEGACY));
        String strActor = String.format("<b> Actors : </b> %s",
                movieDTO.getActor());
        mbinding.movieItemActor.setText(Html.fromHtml(strActor,
                Html.FROM_HTML_MODE_LEGACY));

        Double intRating = Double.valueOf(movieDTO.getUserRating());
        String strRating = String.format("<b> Rating : </b> %3.2f",
                intRating);
        mbinding.movieItemRating.setText(Html.fromHtml(strRating,
                Html.FROM_HTML_MODE_LEGACY));
        /*
        glide를 사용하여 이미지 링크를 참조하여 이미지 표현하기
         */
        if (!movieDTO.getImage().isEmpty()) {
            Glide.with(mbinding.movieItemImage.getContext()).load(movieDTO.getImage())
                    .into(mbinding.movieItemImage);
        }
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public MovieItemViewBinding movieBinding;

        public MovieViewHolder(@NonNull MovieItemViewBinding movieBinding) {
            super(movieBinding.getRoot());
            this.movieBinding = movieBinding;
        }
    }
}
