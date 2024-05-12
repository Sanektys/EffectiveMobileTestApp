package com.example.effectivemobiletestapp.ui.screens.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentHomeBinding
import com.example.effectivemobiletestapp.ui.screens.search.SearchModalBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)

        val toCountryTextView = binding.countryTo
        toCountryTextView.setOnClickListener {
            SearchModalBottomSheet().let { dialog ->
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
                dialog.show(childFragmentManager, SearchModalBottomSheet.TAG)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}