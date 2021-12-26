package com.callor.threedayday

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.callor.threedayday.data.LoginRepository
import com.callor.threedayday.databinding.ActivityMainBinding
import com.callor.threedayday.service.UserFile
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userFile: UserFile

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

//        val navController = findNavController()
        navController = findNavController(R.id.nav_host_fragment_activity_main)
//        val menuSets = setOf(
//            R.id.navigation_home, R.id.navigation_dashboard,
//            R.id.navigation_notifications, R.id.navigation_login
//        )

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            menuSets
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navView.setupWithNavController(navController)

        userFile = UserFile(filesDir.path)

//        Log.d("file path", filesDir.path)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_nav_menu, menu)
//        return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
        when (item?.itemId) {
            R.id.setting -> {
//                Toast.makeText(applicationContext, "세팅", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.navigation_setting)
//                return super.onOptionsItemSelected(item)
            }
            R.id.logout -> {
//                Toast.makeText(applicationContext, "로그아웃", Toast.LENGTH_SHORT).show()
//                userFile.remove("memo/test")
//                navController.navigate(R.id.navigation_home)
                setBottomNav(false)
//                userFile.remove("memo/test")
//                Handler(Looper.getMainLooper()).postDelayed(
//                    { navController.navigate(R.id.navigation_login) }, 100
//                )
                navController.popBackStack()
//                        navController.navigate(R.id.navigation_login)
//
            }
            else -> {}

        }
        return super.onOptionsItemSelected(item)
    }

    fun getFile(): UserFile {
        return userFile
    }

    fun setBottomNav(status: Boolean) {
        binding.navView.visibility = if (status) View.VISIBLE else View.GONE
        binding.toolbar.visibility = if (status) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
//        userFile.remove("memo/test")
    }
}