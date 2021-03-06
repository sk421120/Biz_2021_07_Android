package com.callor.threedayday

import android.app.AlertDialog
import android.content.Context
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
import android.content.DialogInterface
import android.os.Process
import com.google.android.material.dialog.MaterialAlertDialogBuilder


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
//                Toast.makeText(applicationContext, "??????", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.navigation_setting)
//                return super.onOptionsItemSelected(item)
            }
            R.id.logout -> {
//                Toast.makeText(applicationContext, "????????????", Toast.LENGTH_SHORT).show()
//                userFile.remove("memo/test")
//                navController.navigate(R.id.navigation_home)
//                setBottomNav(false)
//                userFile.remove("memo/test")
//                Handler(Looper.getMainLooper()).postDelayed(
//                    { navController.navigate(R.id.navigation_login) }, 100
//                )
//                navController.popBackStack()
//                navController.navigate(R.id.navigation_login)
//                val fragmentId = navController.backStack.last
//                navController.navigate(R.id.action_global_navigation_login)
//                navController.navigate(R.id.navigation_intro)
//                val alertDialogBuilder = AlertDialog.Builder(this)
//                alertDialogBuilder.setTitle("???????????? ??????")
//                alertDialogBuilder
//                    .setMessage("??????????????? ????????? ?????????????")
//                    .setCancelable(false)
//                    .setPositiveButton("??????",
//                        DialogInterface.OnClickListener { dialog, id -> // ??????????????? ????????????
//                            val pid = Process.myPid()
//                            Process.killProcess(pid) //?????????????????????
//                            finish()
//                        })
//                    .setNegativeButton("??????",
//                        DialogInterface.OnClickListener { dialog, id -> // ?????????????????? ????????????
//                            dialog.cancel()
//                        })

                // ??????????????? ??????
//                val alertDialog: AlertDialog = alertDialogBuilder.create()

                // ??????????????? ????????????
//                alertDialog.show()
                alertdialog(this)
            }
            else -> {}

        }
        return super.onOptionsItemSelected(item)
    }

    fun alertdialog(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("???????????? ??????")
        alertDialogBuilder.setMessage("??????????????? ???????????????????")
            .setCancelable(false)
            .setPositiveButton("????????????",
                DialogInterface.OnClickListener { dialog, id ->
                    userFile.remove("memo/test")
                    val pid = Process.myPid()
                    Process.killProcess(pid)
                    finish()
                })
            .setNegativeButton("??????",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

        val alertDialog = alertDialogBuilder.create()

        alertDialog.show()
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