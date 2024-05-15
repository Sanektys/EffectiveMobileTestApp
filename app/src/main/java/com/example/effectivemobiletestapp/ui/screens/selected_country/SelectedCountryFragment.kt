package com.example.effectivemobiletestapp.ui.screens.selected_country

import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import androidx.fragment.app.Fragment
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentSelectedCountryBinding
import com.example.effectivemobiletestapp.ui.MainActivity
import com.example.effectivemobiletestapp.ui.utils.DatePickerDialogBuilder

class SelectedCountryFragment : Fragment(R.layout.fragment_selected_country) {

    private var _binding: FragmentSelectedCountryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SelectedCountryViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSelectedCountryBinding.bind(view)

        initializeRouteFieldComponents()
        initializeFiltersGroup()

        binding.allTicketsButton.setOnClickListener {
            (requireActivity() as MainActivity).openAllTicketsScreen()
        }
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
        binding.swapCountriesIcon.setOnClickListener {
            val temp = binding.countryFrom.text
            binding.countryFrom.text = binding.countryTo.text
            binding.countryTo.text = temp
        }
    }

    private fun initializeFiltersGroup() {
        binding.dateChip.apply {
            fun setDate(dateInMillis: Long) {
                val dateFormat = DateFormat.format("d LLL, E", dateInMillis)
                text = dateFormat
            }
            if (text.isEmpty()) {
                setDate(System.currentTimeMillis())
            }

            setOnClickListener {
                DatePickerDialogBuilder.buildAndShow(
                    this@SelectedCountryFragment.requireContext(),
                    childFragmentManager,
                    R.string.date_picker_title
                ) { dateInMillis ->
                    setDate(dateInMillis)
                }
            }
        }

        binding.wayBackChip.setOnClickListener {
            DatePickerDialogBuilder.buildAndShow(
                this.requireContext(),
                childFragmentManager,
                R.string.date_picker_title) {}
        }
    }
}