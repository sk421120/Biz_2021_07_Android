package com.callor.threedayday.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.callor.threedayday.R
import com.callor.threedayday.data.LoginRepository
import com.callor.threedayday.data.model.LoggedInUser
import com.callor.threedayday.databinding.ActivityMainBinding
import com.callor.threedayday.databinding.FragmentHomeBinding
import com.callor.threedayday.ui.login.LoginViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var loginViewModel: LoginViewModel

//    private lateinit var mainBinding: ActivityMainBinding

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

        val textView: TextView = binding.textHome
        val id: TextView = binding.textId
        val pw: TextView = binding.textPw
        val loggedInUser = loginViewModel.viewUser()
//        if (loggedInUser)
        id.text = loggedInUser?.userId
        pw.text = loggedInUser?.nikname.toString()
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}