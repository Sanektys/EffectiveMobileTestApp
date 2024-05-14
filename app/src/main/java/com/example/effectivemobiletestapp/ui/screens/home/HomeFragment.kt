package com.example.effectivemobiletestapp.ui.screens.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentHomeBinding
import com.example.effectivemobiletestapp.ui.screens.search.SearchModalBottomSheet
import com.example.effectivemobiletestapp.ui.utils.CyrillicTextFilter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)

        initializeRouteFieldComponents()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun showSearchDialog() {
        if (childFragmentManager.findFragmentByTag(SearchModalBottomSheet.TAG) != null) return

        SearchModalBottomSheet().let { dialog ->
            dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
            dialog.show(childFragmentManager, SearchModalBottomSheet.TAG)
        }
    }

    private fun initializeRouteFieldComponents() {
        binding.countryFrom.let { countryFrom ->
            countryFrom.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_NEXT -> {
                        if (countryFrom.length() >= MINIMUM_COUNTRY_NAME_LENGTH) {
                            showSearchDialog()
                        }
                        true
                    }
                    else -> false
                }
            }
            countryFrom.filters = arrayOf(CyrillicTextFilter)
        }
        binding.countryTo.setOnClickListener {
            if (binding.countryFrom.length() >= MINIMUM_COUNTRY_NAME_LENGTH) {
                showSearchDialog()
            }
        }
    }


    companion object {
        const val MINIMUM_COUNTRY_NAME_LENGTH = 1
    }
}