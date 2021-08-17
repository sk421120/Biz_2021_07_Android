package com.callor.movies;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.callor.movies.databinding.FragmentSecondBinding;
import com.callor.movies.service.NaverAPIService;
import com.callor.movies.service.impl.NaverMovieServiceImplV1;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        // Activity에서 보내준 데이터를 fragment에서 받기
        if( getArguments() != null) {

            // 전달받은 데이터가 어떤 형태로 전송되어 오는지 확인하기
            Log.d("get args", getArguments().toString());

            /*
            전달받은 변수중에 movie_search 변수가 있으면 데이터를 getter하여 movie_text에 담아주고
            만약 데이터가 없으면 기본값으로 "No"라는 문자열을 movie_text에 담아달라
             */
            String movie_text = getArguments().getString("movie_search",
                    "No");

            Log.d("search text",movie_text);

            NaverAPIService naverAPIService = new NaverMovieServiceImplV1(binding);
            naverAPIService.getNaverMovie(movie_text);
        }
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}