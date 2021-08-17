package com.callor.movies;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.callor.movies.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

//        content_main에 존재 : nav_host_fragment_content_main
        /*
        content_view.xml에 설정된 fragment view를
        NavController로 등록하여
        fragment 간의 이동을 쉽게 하겠다.
         */
        NavController navController
                = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        뒤로가기 버튼 등이 있을 때 처리를 쉽게 하기 위한 설정
//         ui에 뒤로가기 버튼이 생겼을 때 처리한다.
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
/*
ActionBar에 메뉴 등을 표현하기 위하여 작성되는 코드
res/menu/menu_main.xml 파일을 읽어서
ActionBar에 메뉴를 그리는 코드가 작성된다.
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        R.menu.menu_main불러와서 menu에 넣어라
//       여길 수정하려면 menu를 수정하면 된다.

        /*
        ActionBar에 구현된 검색창을 활성화 하기 위한 코드
        android.위젯.searchView를 사용하여 객체 생성
         */
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("영화 검색");

//        키보드에 검색 버튼을 클릭했을때
//        검색창이 활성화 되었을 때 실행되는 event
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

//            키보드의 검색 버튼 클릭
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("검색어",s);
                /*
                검색창에 입력한 검색어를 secondFragment에게 보내고
                SecondFragment에서는 전달받은 검색어를 사용하여
                Naver Open API 영화검색을 수행하여
                RecyclerView에 표시하기기
                */

                //검색창에 입력된 값이 있으면
                if( !s.isEmpty() ) {
                    /*
                    safe-args plugin을
                     프로젝트의 build.gradle에 설정하면
                    nav_Graph.xml에 선언된
                    fragment ID를 참조하기 위한
                    클래스가 자동으로 생성된다.

                    여기서 생성된 action 객체에 fragment에 전달할 데이터를 실어서 보낸다.

                    fragment에 전달할 데이터는 NavDirections 객체를 생성할때
                    매개변수로 전달한다
                     */
                    NavDirections action =
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(s);

                    // 생성된 action에 따라 화면에
                    // sectionFragment를 띄운다.
                    NavController controller = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
                    controller.navigate(action);
                }

                return false;
            }
//          검색창에 문자열을 입력할 때
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

/*
MainActivity 에 firstFragment가 열린 상태에서 ActionBar의 settings 메뉴를 클릭하면
firstFragment가 있던 공간에 SettingsFragment를 보여주기

1. content_main.xml의 fragment view를 NavController로 설정하고
2. NavController에 navigate를 실행하는데
이때 navGraph.xml에 설정된 Action을 사용하여 실행을 한다.
 */
    NavController controller =
            Navigation.findNavController(MainActivity.this,
                    R.id.nav_host_fragment_content_main);
    controller.navigate(R.id.action_FirstFragment_to_SettingsFragment);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
안드로이드의 물리적 버튼
원래 어플에 존재하는 버튼들을 의미(홈, 뒤로가기, 백그라운드목록)
위로가기,뒤로가기,어플종료,어플리스트 보기 등의 기능을 수행하는 활성화 시키는 event 핸들러

안드로이드 Up 버튼을 눌렀을 때 Navigation에 설정된 대로
뒤로가기를 수행하다가 가장 먼저 열린 화면에 도달했을 때
Up버튼을 누르면 어플을 종료하는 코드가 자동으로 실행된다.
 */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController =
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}