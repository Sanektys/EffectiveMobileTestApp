package com.example.effectivemobiletestapp.ui.screens.all_tickets_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletestapp.databinding.FragmentAllTicketsListBinding
import com.example.effectivemobiletestapp.ui.MainActivity
import kotlinx.coroutines.launch

class AllTicketsListFragment : Fragment() {

    private var _binding: FragmentAllTicketsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllTicketsListViewModel by lazy {
        ViewModelProvider(this).get(AllTicketsListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTicketsListBinding.inflate(inflater, container, false)

        viewModel.setFlightRoute(arguments?.getString(KEY_FLIGHT_ROUTE) ?: "")
        viewModel.setFlightSpecification(arguments?.getString(KEY_FLIGHT_SPECIFICATION) ?: "")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupFlightInfo()

        binding.backArrowIcon.setOnClickListener {
            (requireActivity() as MainActivity).popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun setupFlightInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flightRoute.collect { value ->
                binding.flightInfoRoute.text = value
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flightSpecification.collect { value ->
                binding.flightInfoSpecification.text = value
            }
        }
    }


    companion object {
        const val KEY_FLIGHT_ROUTE = "flightRouteKey"
        const val KEY_FLIGHT_SPECIFICATION = "flightSpecificationKey"
    }
}