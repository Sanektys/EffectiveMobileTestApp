package com.example.effectivemobiletestapp.ui.screens.selected_country

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectivemobiletestapp.R

class SelectedCountryFragment : Fragment() {

    companion object {
        fun newInstance() = SelectedCountryFragment()
    }

    private lateinit var viewModel: SelectedCountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_country, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectedCountryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}