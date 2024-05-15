package com.example.effectivemobiletestapp.ui.screens.selected_country

import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentSelectedCountryBinding
import com.example.effectivemobiletestapp.ui.MainActivity
import com.example.effectivemobiletestapp.ui.screens.all_tickets_list.AllTicketsListFragment
import com.example.effectivemobiletestapp.ui.utils.DatePickerDialogBuilder
import kotlinx.coroutines.launch


class SelectedCountryFragment : Fragment(R.layout.fragment_selected_country) {

    private var _binding: FragmentSelectedCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this).get( SelectedCountryViewModel::class.java )}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSelectedCountryBinding.bind(view)

        viewModel.setCountryFrom(arguments?.getString(KEY_COUNTRY_FROM) ?: "")
        viewModel.setCountryTo(arguments?.getString(KEY_COUNTRY_TO) ?: "")

        initializeRouteFieldComponents()
        initializeFiltersGroup()

        binding.allTicketsButton.setOnClickListener {
            openAllTicketsListScreen()
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
            val temp = viewModel.countryFrom.value
            viewModel.setCountryFrom(viewModel.countryTo.value)
            viewModel.setCountryTo(temp)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countryFrom.collect { value ->
                binding.countryFrom.text = value
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countryTo.collect { value ->
                binding.countryTo.text = value
            }
        }
    }

    private fun initializeFiltersGroup() {
        binding.dateChip.apply {
            setOnClickListener {
                DatePickerDialogBuilder.buildAndShow(
                    this@SelectedCountryFragment.requireContext(),
                    childFragmentManager,
                    R.string.date_picker_title
                ) { dateInMillis ->
                    viewModel.setFlightDate(dateInMillis)
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.flightDate.collect { dateInMillis ->
                    val dateFormat = DateFormat.format("d LLL, E", dateInMillis)
                    text = dateFormat
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

    private fun openAllTicketsListScreen() {
        val bundle = Bundle().apply {
            val flightRoute = "${viewModel.countryFrom.value}-${viewModel.countryTo.value}"
            putString(AllTicketsListFragment.KEY_FLIGHT_ROUTE, flightRoute)

            val flightDate = DateFormat.format("d LLLL", viewModel.flightDate.value)
            val tickets = resources.getString(R.string.selected_country_screen_filters_group_item_third_label_variant)
            val flightSpecification = "$flightDate, $tickets"
            putString(AllTicketsListFragment.KEY_FLIGHT_SPECIFICATION, flightSpecification)
        }

        (requireActivity() as MainActivity).openAllTicketsScreen(bundle = bundle)
    }


    companion object {
        const val KEY_COUNTRY_FROM = "countryFromKey"
        const val KEY_COUNTRY_TO = "countryToKey"
    }
}