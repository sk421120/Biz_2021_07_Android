package com.callor.movies.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.movies.adapter.NaverMovieAdapter;
import com.callor.movies.config.NaverAPI;
import com.callor.movies.databinding.FragmentSecondBinding;
import com.callor.movies.model.MovieDTO;
import com.callor.movies.model.NaverParent;
import com.callor.movies.service.NaverAPIService;
import com.callor.movies.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class NaverMovieServiceImplV1 implements NaverAPIService {

    private NaverMovieAdapter adapter;
    private FragmentSecondBinding secondBinding;

    public NaverMovieServiceImplV1(FragmentSecondBinding secondBinding) {
        this.secondBinding = secondBinding;
    }

    @Override
    public void getNaverMovie(String search) {

        /*
        Naver에 API 조회를 수행하기 위한 객체를 생성하기
         */
        Call<NaverParent> naverCall = RetrofitClient.getApiClient().getMovies(NaverAPI.NAVER_CLIENT_ID,
                NaverAPI.NAVER_CLIENT_SECRET, search, 1, 20);

        /*
        생성된 API 객체에 대하여 비동기 Call method를 선언하기
        Retrofit이 naver에 API 요청을 하고
        API 결과가 다다르면 (도착하면) 반응을 하는 method
         */
        naverCall.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                Log.d("Naver movies", response.toString());
                if(response.code() < 300){
                    NaverParent naverParent = response.body();
                    Log.d("movie data", naverParent.toString());

                    List<MovieDTO> movieList = naverParent.items;
                    adapter = new NaverMovieAdapter(movieList);

                    secondBinding.recyclerView.setAdapter(adapter);

                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(secondBinding.getRoot().getContext());
                    secondBinding.recyclerView.setLayoutManager(layoutManager);
                } else {
                    Log.d("movie error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });
    }
}
