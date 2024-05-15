package com.example.effectivemobiletestapp.ui.screens.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentSearchModalBottomSheetBinding
import com.example.effectivemobiletestapp.ui.MainActivity
import com.example.effectivemobiletestapp.ui.screens.selected_country.SelectedCountryFragment
import com.example.effectivemobiletestapp.ui.utils.CyrillicTextFilter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch


class SearchModalBottomSheet : BottomSheetDialogFragment(R.layout.fragment_search_modal_bottom_sheet) {

    private var _binding: FragmentSearchModalBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchModalBottomSheetBinding.bind(view)

        viewModel.setCountryFrom(arguments?.getString(KEY_COUNTRY_FROM) ?: "")

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

            countryTo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val newInput = s.toString()
                    if (newInput != viewModel.countryTo.value) {
                        viewModel.setCountryTo(newInput)
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.countryTo.collect { value ->
                    countryTo.setText(value)
                    countryTo.setSelection(value.length)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countryFrom.collect { value ->
                binding.countryFrom.text = value
            }
        }

        binding.crossIcon.setOnClickListener {
            viewModel.setCountryTo("")
        }
    }

    private fun initializeSubMenu() {
        val activity = requireActivity() as MainActivity

        binding.subMenuFirstItem.setOnClickListener {
            activity.openEmptyPlaceholderScreen()
        }
        binding.subMenuSecondItem.setOnClickListener {
            viewModel.setCountryTo(
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
            viewModel.setCountryTo(
                resources.getString(R.string.search_screen_suggestions_list_first_item_title)
            )
            openSelectedCountryScreen()
        }
        binding.suggestionsListSecondItem.setOnClickListener {
            viewModel.setCountryTo(
                resources.getString(R.string.search_screen_suggestions_list_second_item_title)
            )
            openSelectedCountryScreen()
        }
        binding.suggestionsListThirdItem.setOnClickListener {
            viewModel.setCountryTo(
                resources.getString(R.string.search_screen_suggestions_list_third_item_title)
            )
            openSelectedCountryScreen()
        }
    }

    private fun openSelectedCountryScreen() {
        val bundle = Bundle().apply {
            putString(SelectedCountryFragment.KEY_COUNTRY_FROM, viewModel.countryFrom.value)
            putString(SelectedCountryFragment.KEY_COUNTRY_TO, viewModel.countryTo.value)
        }

        (requireActivity() as MainActivity).openSelectedCountryScreen(bundle = bundle)
    }


    companion object {
        const val TAG = "SearchModalBottomSheet"
        const val KEY_COUNTRY_FROM = "countryFromKey"

        const val MINIMUM_COUNTRY_NAME_LENGTH = 1
    }
}