package com.example.effectivemobiletestapp.ui.screens.all_tickets_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentAllTicketsListBinding
import com.example.effectivemobiletestapp.domain.entities.TicketItemDto
import com.example.effectivemobiletestapp.ui.MainActivity
import com.example.effectivemobiletestapp.ui.utils.RecyclerVerticalSpaceItemDecoration
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch

class AllTicketsListFragment : Fragment() {

    private var _binding: FragmentAllTicketsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllTicketsListViewModel by lazy {
        ViewModelProvider(this).get(AllTicketsListViewModel::class.java)
    }

    private var recyclerAdapter: ListDelegationAdapter<List<TicketItemDto>>? = null


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

        initializeRecycler()

        binding.backArrowIcon.setOnClickListener {
            (requireActivity() as MainActivity).popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
        recyclerAdapter = null
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

    private fun initializeRecycler() {
        binding.allTicketsList.addItemDecoration(
            RecyclerVerticalSpaceItemDecoration(
                resources.getDimension(R.dimen.all_tickets_list_item_space_between).toInt()
            )
        )

        recyclerAdapter = ListDelegationAdapter(ticketsAdapterDelegate())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ticketsList.collect { newList ->
                recyclerAdapter?.items = newList
                binding.allTicketsList.adapter = recyclerAdapter
            }
        }
    }


    companion object {
        const val KEY_FLIGHT_ROUTE = "flightRouteKey"
        const val KEY_FLIGHT_SPECIFICATION = "flightSpecificationKey"
    }
}