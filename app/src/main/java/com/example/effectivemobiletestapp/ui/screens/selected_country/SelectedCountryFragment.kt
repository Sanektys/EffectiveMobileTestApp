package com.example.effectivemobiletestapp.ui.screens.selected_country

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentSelectedCountryBinding
import com.example.effectivemobiletestapp.domain.entities.TicketOfferItemDto
import com.example.effectivemobiletestapp.ui.MainActivity
import com.example.effectivemobiletestapp.ui.screens.all_tickets_list.AllTicketsListFragment
import com.example.effectivemobiletestapp.ui.utils.CyrillicTextFilter
import com.example.effectivemobiletestapp.ui.utils.DatePickerDialogBuilder
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch


class SelectedCountryFragment : Fragment(R.layout.fragment_selected_country) {

    private var _binding: FragmentSelectedCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this).get( SelectedCountryViewModel::class.java )}

    private var recyclerAdapter: ListDelegationAdapter<List<TicketOfferItemDto>>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSelectedCountryBinding.bind(view)

        viewModel.setCountryFrom(arguments?.getString(KEY_COUNTRY_FROM) ?: "")
        viewModel.setCountryTo(arguments?.getString(KEY_COUNTRY_TO) ?: "")

        initializeRouteFieldComponents()
        initializeFiltersGroup()
        initializeRecycler()

        binding.allTicketsButton.setOnClickListener {
            openAllTicketsListScreen()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
        recyclerAdapter = null
    }

    private fun initializeRouteFieldComponents() {
        binding.backArrowIcon.setOnClickListener {
            (requireActivity() as MainActivity).popBackStack()
        }
        binding.crossIcon.setOnClickListener {
            viewModel.setCountryTo("")
        }
        binding.swapCountriesIcon.setOnClickListener {
            val temp = viewModel.countryFrom.value
            viewModel.setCountryFrom(viewModel.countryTo.value)
            viewModel.setCountryTo(temp)
        }

        binding.countryTo.let { countryTo ->
            countryTo.filters = arrayOf(CyrillicTextFilter)

            countryTo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val newInput = s.toString()
                    if (newInput != viewModel.countryTo.value) {
                        viewModel.setCountryTo(newInput)
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.countryTo.collect { value ->
                    countryTo.setText(value)
                    countryTo.setSelection(value.length)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countryFrom.collect { value ->
                binding.countryFrom.text = value
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
        if (viewModel.countryTo.value.length < MINIMUM_COUNTRY_NAME_LENGTH) return

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

    private fun initializeRecycler() {
        recyclerAdapter = ListDelegationAdapter(ticketsOffersAdapterDelegate())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ticketsOffersList.collect { newList ->
                recyclerAdapter?.items = newList
                binding.flightsList.adapter = recyclerAdapter
            }
        }
    }


    companion object {
        const val KEY_COUNTRY_FROM = "countryFromKey"
        const val KEY_COUNTRY_TO = "countryToKey"

        const val MINIMUM_COUNTRY_NAME_LENGTH = 3
    }
}