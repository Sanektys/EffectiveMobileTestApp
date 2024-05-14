package com.example.effectivemobiletestapp.ui.screens.all_tickets_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.effectivemobiletestapp.databinding.FragmentAllTicketsListBinding
import com.example.effectivemobiletestapp.ui.MainActivity

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backArrowIcon.setOnClickListener {
            (requireActivity() as MainActivity).popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}