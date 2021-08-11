package com.callor.topnews.service.impl;

import java.util.List;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.topnews.adapter.NewsViewAdapter;
import com.callor.topnews.config.NaverAPI;
import com.callor.topnews.model.NaverNewsDTO;
import com.callor.topnews.model.NaverParent;
import com.callor.topnews.service.NaverRetrofitClient;
import com.callor.topnews.service.NaverService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverNewsServiceImplV1 implements NaverService {

    private RecyclerView newsListView;
    public NaverNewsServiceImplV1(RecyclerView newsListView) {
        this.newsListView = newsListView;
    }

    @Override
    public void getNews(String search) {
        Call<NaverParent> naverCall =
        NaverRetrofitClient.getAPIClient().getNews(NaverAPI.CLIENT_ID,
                NaverAPI.CLIENT_SECRET, search, 1, 15);

        naverCall.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                int resCode = response.code();
                if (resCode < 300 ){
                    Log.d("Naver News List", response.body().toString());

                    List<NaverNewsDTO> newsList = response.body().items;
                    NewsViewAdapter viewAdapter = new NewsViewAdapter(newsList);

                    newsListView.setAdapter(viewAdapter);

                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(newsListView.getContext());

                    newsListView.setLayoutManager(layoutManager);
                } else {
                    Log.d("Naver Error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });
    }
}
