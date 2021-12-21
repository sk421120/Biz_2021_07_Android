package com.callor.threedayday.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.callor.threedayday.MainActivity
import com.callor.threedayday.R
import com.callor.threedayday.data.LoginDataSource
import com.callor.threedayday.data.LoginRepository
import com.callor.threedayday.ui.login.LoginViewModel

/*
Java 에서는 모든 클래스는 기본적으로 상속을 받을 수 있다
Kotlin 에서는 모든 클래스는 기본적으로 상속이 불가능하다
open 키워드는 이 클래스를 누군가 상속하여 사용할 수 있도록 허용하는 코드
 */
open class AuthFragmentParent : Fragment() {

    //    private val loginViewModel: LoginViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = LoginViewModel(
                LoginRepository(dataSource = LoginDataSource())
            ) as T
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        val userId = loginViewModel.loginResult.value?.success?.displayName

//        userId 가 null( ?: ) 이면 noo 이라고 출력
//        null 이 아니면 userId 값을 출력
//        Log.d("Author", userId ?: "Noooo")

//        navigation_login 메뉴를 클릭한 것처럼
//        fragment 를 다른 fragment 로 redirection 하기
//        if (userId == null) {
//            findNavController().navigate(R.id.navigation_intro)
//        }

        val mainAct = activity as MainActivity
        val userFile = mainAct.getFile()

        if (!userFile.userLog()) {
            mainAct.setBottomNav(false)
            findNavController().navigate(R.id.navigation_login)
        }
    }

}