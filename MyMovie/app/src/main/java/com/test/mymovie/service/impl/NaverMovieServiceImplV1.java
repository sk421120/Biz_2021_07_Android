package com.test.mymovie.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.mymovie.adapter.MovieViewAdapter;
import com.test.mymovie.config.NaverAPI;
import com.test.mymovie.model.NaverMovieDTO;
import com.test.mymovie.model.NaverParent;
import com.test.mymovie.service.RetrofitClient;
import com.test.mymovie.service.NaverService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class NaverMovieServiceImplV1 implements NaverService {

    private RecyclerView movieListView;

    public NaverMovieServiceImplV1(RecyclerView movieListView) {
        this.movieListView = movieListView;
    }

    @Override
    public void getMovies(String search) {
        Call<NaverParent> naverCall = RetrofitClient.getAPIClient().getMovies(
                NaverAPI.CLIENT_ID, NaverAPI.CLIENT_SECRET, search, 1, 10);

        naverCall.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                int resCode = response.code();
                if (resCode < 300) {
                    Log.d("Naver Movie List", response.body().toString());

                    List<NaverMovieDTO> movieList = response.body().items;
                    MovieViewAdapter viewAdapter = new MovieViewAdapter(movieList);

                    movieListView.setAdapter(viewAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                            movieListView.getContext());
                    movieListView.setLayoutManager(layoutManager);
                } else {
                    Log.d("Naver Error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {
                Log.d("Naver Failure", t.toString());

            }
        });
    }
}
