package com.callor.library.service;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.library.adapter.BookAdapter;
import com.callor.library.config.Naver;
import com.callor.library.databinding.FragmentFirstBinding;
import com.callor.library.model.BookDTO;
import com.callor.library.model.NaverParent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
// Ctrl + Alt + O import 정리

public class NaverAPIServiceV1 {

    private NaverParent naverParent = null;
    private BookAdapter bookAdapter;
    FragmentFirstBinding binding;

    public NaverAPIServiceV1(BookAdapter bookAdapter,
                             FragmentFirstBinding binding) {
        this.bookAdapter = bookAdapter;
        this.binding = binding;
    }

    // 3. 생성된 Connection을 통하여 데이터를 가져오고 필요한 데이터를
    // parsing하여 books 객체를 담기
    public void getNaverBooks(String search){

        // 아래의 코드를 실행하면 Retrofit 설정된 값을 기준으로 naver에 요청을 수행한다
        // 이때 이 코드는 비동기 방식으로 작동이 된다다
       Call<NaverParent> books = RetrofitClient.getClient()
                .getNaverBook(Naver.CLIENT_ID,Naver.CLIENT_SECRET,
                        search,10,1);
       /*
       Retrofit 은 API요청을 비동기 방식으로 수행을 한다
       일반적으로 Network이나 외부 다른 장치와 데이터를 주고 받을 때는
       대부분의 코드를 비동기, 또는 thread 방식으로 사용한다

       동기방식
       요청수행 ==> 결과가 return 되어 올때까지 대기

       비동기방식
       요청수행 ==> 결과가 return 되든 말든 다른일 수행하기
       결과가 return되면 그 결과를 수신하여 처리할 event handler를 작성해야 한다다
        */

        //Retrofit event 핸들러 작성
        books.enqueue(new Callback<NaverParent>(){

            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                // return된 restponse 객체 확인
                Log.d("Naver Res Return", response.toString());

                int resCode = response.code();

                if(resCode < 300) {
                    naverParent = response.body();
                    Log.d("Naver Return", naverParent.toString());

                    List<BookDTO> bookDTOList = naverParent.items;
                    bookAdapter = new BookAdapter(bookDTOList);
                    binding.bookListView.setAdapter(bookAdapter);

                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(binding.getRoot().getContext());
                    binding.bookListView.setLayoutManager(layoutManager);
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {
                Log.d("Error!",naverParent.toString());
            }
        });
       Log.d("Naver Books", books.toString());
    }



}
