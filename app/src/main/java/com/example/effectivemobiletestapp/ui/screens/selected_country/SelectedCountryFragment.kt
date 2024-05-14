package com.example.effectivemobiletestapp.ui.screens.selected_country

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentSelectedCountryBinding
import com.example.effectivemobiletestapp.ui.MainActivity

class SelectedCountryFragment : Fragment(R.layout.fragment_selected_country) {

    private var _binding: FragmentSelectedCountryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SelectedCountryViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSelectedCountryBinding.bind(view)

        initializeRouteFieldComponents()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun initializeRouteFieldComponents() {
        binding.backArrowIcon.setOnClickListener {
            (requireActivity() as MainActivity).popBackStack()
        }
        binding.crossIcon.setOnClickListener {
            (requireActivity() as MainActivity).popBackStack()
        }
    }

    companion object {
        fun newInstance() = SelectedCountryFragment()
    }
}