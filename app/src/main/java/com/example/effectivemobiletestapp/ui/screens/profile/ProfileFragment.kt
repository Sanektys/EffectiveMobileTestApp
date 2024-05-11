package com.example.effectivemobiletestapp.ui.screens.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by lazy { ViewModelProvider(this).get(ProfileViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentProfileBinding.bind(view)

//        val textView: TextView = binding.textDashboard
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}