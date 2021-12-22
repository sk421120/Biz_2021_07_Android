package com.callor.threedayday.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.callor.threedayday.MainActivity
import com.callor.threedayday.R
import com.callor.threedayday.data.LoginRepository
import com.callor.threedayday.data.model.LoggedInUser
import com.callor.threedayday.databinding.ActivityMainBinding
import com.callor.threedayday.databinding.FragmentHomeBinding
import com.callor.threedayday.ui.AuthFragmentParent
import com.callor.threedayday.ui.login.LoginViewModel

class HomeFragment : AuthFragmentParent() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mainAct = activity as MainActivity
        mainAct?.setBottomNav(true)

        val textView: TextView = binding.textHome

//        setHasOptionsMenu(true)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
//        inflater.inflate(R.menu.top_nav_menu, menu)
        inflater.inflate(R.menu.top_nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
        return when(item.itemId) {
            R.id.setting -> {
//                findNavController().navigate(R.id.navigation_setting)
                Log.d("setting",item.toString())
                true
            }
            R.id.logout -> {
//                findNavController().navigate(R.id.navigation_login)
                Log.d("logout",item.toString())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}