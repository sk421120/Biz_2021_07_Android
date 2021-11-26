package com.callor.threedayday

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.callor.threedayday.databinding.ActivityMainBinding
import com.callor.threedayday.databinding.FragmentLoginBinding
import com.callor.threedayday.ui.login.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.BottomNav {
    private lateinit var binding: ActivityMainBinding

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

//        if(login["isLogin"] == false) {
            navController.navigate(R.id.action_global_navigation_login)
//        }

    }

    override fun setBottomNav(status: Boolean) {
        binding.navView.visibility = if(status) View.VISIBLE else View.GONE
    }

}