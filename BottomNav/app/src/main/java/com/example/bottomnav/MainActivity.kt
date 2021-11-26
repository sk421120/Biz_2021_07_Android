package com.example.bottomnav

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bottomnav.databinding.ActivityMainBinding
import com.example.bottomnav.ui.login.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.BottomNav {

    //    binding 방식으로 activity 에 접근하기 위한 준비
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        activity.xml 파일을 확장하여 binding 객체로 변환하기
        binding = ActivityMainBinding.inflate(layoutInflater)
//        확장된 binding 을 처음 화면에 그리기
        setContentView(binding.root)

        /*
        ButtomNav 를 설정하는 부분

        변수선언하기 val(var) 변수명 : type = 초기화
        val navView = binding.navView 이렇게도 사용할 수 있는데

        kotlin 에서 변수를 선언할 때 type 이 명확하고 null 이 절대 아닐 경우는
        변수의 type 을 생략할 수 있다.
         */
        val navView: BottomNavigationView = binding.navView

//        fragment 를 NavControl 방식으로 제어하기 위하여 선언하기
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        /*---------------------------------------------
         BottomNav를 선택햇을 때
         화면에 해당하는 fragment를 띄우고
         띄워진 fragment가 어떤 것인 title bar에 제목을 표시하기 위한 절차
        ---------------------------------------------
         bottom_nav_menu.xml 에 선언된 메뉴 item 들을 Set type 의 데이터로 만들기
         set type의 데이터 : 중복되지 않는 단순한 collection type의 데이터 구조
         */
        val menuSets = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications
        )
//        AppBar 와 bottom_nav_menu.xml 과 연동하기
        val appBarConfiguration = AppBarConfiguration(
            menuSets
        )
//        Appbar 와 BottomNav를 연동하기 위한 준비
        setupActionBarWithNavController(navController, appBarConfiguration)
//        선언된 NavController 를 navView 에 부착하기
        navView.setupWithNavController(navController)

//        사용자 정보를 임시로 생성 login 칼럼을 false로
        val login = mapOf(
            "username" to "corgi",
            "password" to "12345",
            "nickname" to "Hong",
            "isLogin" to false
        )

        if (login["isLogin"] == false) {
            navController.navigate(R.id.action_global_navigation_login)
        }

    }

    override fun setBottomNav(status: Boolean) {
//        status가 true이면 BottomNav를 보이고 false이면 감춰라
        binding.navView.visibility = if (status) View.VISIBLE else View.GONE
    }
}