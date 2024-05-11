package com.example.effectivemobiletestapp.ui.screens.all_tickets_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectivemobiletestapp.R

class AllTicketsListFragment : Fragment() {

    companion object {
        fun newInstance() = AllTicketsListFragment()
    }

    private lateinit var viewModel: AllTicketsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_tickets_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllTicketsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}