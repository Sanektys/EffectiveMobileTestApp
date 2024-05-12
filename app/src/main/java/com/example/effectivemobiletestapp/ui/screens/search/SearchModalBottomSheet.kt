package com.example.effectivemobiletestapp.ui.screens.search

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.effectivemobiletestapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchModalBottomSheet : BottomSheetDialogFragment(R.layout.fragment_search_modal_bottom_sheet) {

    private lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (view.parent as View).layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    companion object {
        const val TAG = "SearchModalBottomSheet"
    }
}