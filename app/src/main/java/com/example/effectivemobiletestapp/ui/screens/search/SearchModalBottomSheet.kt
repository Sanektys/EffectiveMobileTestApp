package com.example.effectivemobiletestapp.ui.screens.search

import com.example.effectivemobiletestapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchModalBottomSheet : BottomSheetDialogFragment(R.layout.fragment_search_modal_bottom_sheet) {

    private lateinit var viewModel: SearchViewModel


    companion object {
        const val TAG = "SearchModalBottomSheet"
    }
}