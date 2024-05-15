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
        initializeSubMenu()
        initializeSuggestionsList()
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
                            openSelectedCountryScreen()
                        }
                        true
                    }
                    else -> false
                }
            }
            countryTo.filters = arrayOf(CyrillicTextFilter)
        }

        binding.crossIcon.setOnClickListener {
            binding.countryTo.setText("")
        }
    }

    private fun initializeSubMenu() {
        val activity = requireActivity() as MainActivity

        binding.subMenuFirstItem.setOnClickListener {
            activity.openEmptyPlaceholderScreen()
        }
        binding.subMenuSecondItem.setOnClickListener {
            binding.countryTo.setText(
                resources.getString(R.string.search_screen_sub_menu_second_item_label)
            )
        }
        binding.subMenuThirdItem.setOnClickListener {
            activity.openEmptyPlaceholderScreen()
        }
        binding.subMenuFourthItem.setOnClickListener {
            activity.openEmptyPlaceholderScreen()
        }
    }

    private fun initializeSuggestionsList() {
        binding.suggestionsListFirstItem.setOnClickListener {
            binding.countryTo.setText(
                resources.getString(R.string.search_screen_suggestions_list_first_item_title)
            )
            openSelectedCountryScreen()
        }
        binding.suggestionsListSecondItem.setOnClickListener {
            binding.countryTo.setText(
                resources.getString(R.string.search_screen_suggestions_list_second_item_title)
            )
            openSelectedCountryScreen()
        }
        binding.suggestionsListThirdItem.setOnClickListener {
            binding.countryTo.setText(
                resources.getString(R.string.search_screen_suggestions_list_third_item_title)
            )
            openSelectedCountryScreen()
        }
    }

    private fun openSelectedCountryScreen() {
        (requireActivity() as MainActivity).openSelectedCountryScreen()
    }


    companion object {
        const val TAG = "SearchModalBottomSheet"

        const val MINIMUM_COUNTRY_NAME_LENGTH = 1
    }
}