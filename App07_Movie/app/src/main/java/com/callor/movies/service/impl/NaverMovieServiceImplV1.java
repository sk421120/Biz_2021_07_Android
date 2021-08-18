package com.callor.movies.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.movies.adapter.NaverMovieAdapter;
import com.callor.movies.config.NaverAPI;
import com.callor.movies.model.MovieDTO;
import com.callor.movies.model.NaverParent;
import com.callor.movies.service.NaverApiService;
import com.callor.movies.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class NaverMovieServiceImplV1 implements NaverApiService {

    protected RecyclerView movieRecyclerView;

    public NaverMovieServiceImplV1(RecyclerView movieRecyclerView) {
        this.movieRecyclerView = movieRecyclerView;
    }

    @Override
    public void getNaverData(String search) {
        Call<NaverParent> naverCall =
        RetrofitClient.getApiClient().getMovie(
                NaverAPI.NAVER_CLIENT_ID, NaverAPI.NAVER_CLIENT_SECRET,
                search, 1, 20);

        naverCall.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                Log.d("movie data",response.toString());
                if ( response.code() < 300 ){
                    List<MovieDTO> movieList = response.body().items;
                    NaverMovieAdapter adapter = new NaverMovieAdapter(movieList);

                    movieRecyclerView.setAdapter(adapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                            movieRecyclerView.getContext());
                    movieRecyclerView.setLayoutManager(layoutManager);
                } else {
                    Log.d("movie data error",response.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });
    }
}
