package com.callor.threedayday

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.callor.threedayday.data.model.User
import com.callor.threedayday.databinding.ActivityMainBinding
import com.callor.threedayday.service.UserFile
import com.callor.threedayday.ui.login.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.log

class MainActivity : AppCompatActivity(), LoginFragment.BottomNav {
    private lateinit var binding: ActivityMainBinding

    private lateinit var userFile:UserFile

//    private var userLogFile:String? = "";

//    public fun viewNav(status: Boolean) {
//        val _binding = ActivityMainBinding.inflate(layoutInflater)
//        if (status) binding.navView.visibility = View.INVISIBLE
//        else binding.navView.visibility = View.VISIBLE
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val menuSets = setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        )

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            menuSets
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        userFile = UserFile(filesDir.path)

//        if(login["isLogin"] == false) {
            navController.navigate(R.id.action_global_navigation_login)
//        }

        Log.d("file path", filesDir.path)

    }

    fun getFile():UserFile{
        return userFile
    }

    override fun setBottomNav(status: Boolean) {
        binding.navView.visibility = if(status) View.VISIBLE else View.GONE
    }

}