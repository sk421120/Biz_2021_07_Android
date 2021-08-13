package com.test.mymovie.service;

import com.test.mymovie.config.NaverAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit getInstance() {
        return new Retrofit.Builder().baseUrl(NaverAPI.NAVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static NaverRetrofit getAPIClient(){
        return getInstance().create(NaverRetrofit.class);
    }
}
