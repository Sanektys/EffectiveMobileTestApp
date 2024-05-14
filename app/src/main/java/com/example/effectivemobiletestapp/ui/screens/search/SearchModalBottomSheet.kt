package com.example.effectivemobiletestapp.ui.screens.search

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentSearchModalBottomSheetBinding
import com.example.effectivemobiletestapp.ui.MainActivity
import com.example.effectivemobiletestapp.ui.utils.CyrillicTextFilter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchModalBottomSheet : BottomSheetDialogFragment(R.layout.fragment_search_modal_bottom_sheet) {

    private var _binding: FragmentSearchModalBottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SearchViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchModalBottomSheetBinding.bind(view)

        (view.parent as View).layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED

        initializeRouteFieldComponents()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun initializeRouteFieldComponents() {
        binding.countryTo.let { countryTo ->
            countryTo.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        if (countryTo.length() >= MINIMUM_COUNTRY_NAME_LENGTH) {
                            (requireActivity() as MainActivity).openSelectedCountryScreen()
                        }
                        true
                    }
                    else -> false
                }
            }
            countryTo.filters = arrayOf(CyrillicTextFilter)
        }
    }


    companion object {
        const val TAG = "SearchModalBottomSheet"

        const val MINIMUM_COUNTRY_NAME_LENGTH = 1
    }
}