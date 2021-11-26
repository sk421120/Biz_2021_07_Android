package com.example.bottomnav.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
//    fragment.xml 을 binding 방식으로 사용하기 위한 선언
    private var _binding: FragmentHomeBinding? = null

//    _binding 을 Android, MainActicity 등에서 사용할 수 있도록 하는 Setter() method
//    setter method는 내부에서 선언된 _binding 변수를
//    immutable( 불변) 객체로 return 한다
//    !! : null 이; 아닌경우만 return 하라
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

//        fragment.xml을 확장하여 사용할 준비
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

//        return 시 반드시 immutable 객체로 return 하기
//        return _binding.root 절대 안 됨!!!!!!
        return binding.root
    }

//    사용이 끝난 _binding 변수를 메모리에서 해제하기
//    화면에 보이지 않는 fragment가 내부 메모리를 차지하고 있을 수 있기 때문에
//    사용이 끝난 fragment는 메모리에서 해제 해주어야 한다.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}