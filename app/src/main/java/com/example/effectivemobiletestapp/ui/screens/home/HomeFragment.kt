package com.example.effectivemobiletestapp.ui.screens.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentHomeBinding
import com.example.effectivemobiletestapp.ui.MainActivity
import com.example.effectivemobiletestapp.ui.screens.search.SearchModalBottomSheet
import com.example.effectivemobiletestapp.ui.utils.CyrillicTextFilter
import kotlinx.coroutines.launch


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
        if (viewModel.countryFrom.value.length >= MINIMUM_COUNTRY_NAME_LENGTH) {
            val bundle = Bundle().apply {
                putString(SearchModalBottomSheet.KEY_COUNTRY_FROM, viewModel.countryFrom.value)
            }

            (requireActivity() as MainActivity).showSearchDialog(
                fragmentManager = childFragmentManager,
                bundle = bundle
            )
        }
    }

    private fun initializeRouteFieldComponents() {
        binding.countryFrom.let { countryFrom ->
            countryFrom.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_NEXT -> {
                        showSearchDialog()
                        true
                    }
                    else -> false
                }
            }
            countryFrom.filters = arrayOf(CyrillicTextFilter)

            countryFrom.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    val newInput = s.toString()
                    if (newInput != viewModel.countryFrom.value) {
                        viewModel.setCountryFrom(newInput)
                    }
                }
            })

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.countryFrom.collect { value ->
                    countryFrom.setText(value)
                    countryFrom.setSelection(value.length)
                }
            }
        }
        binding.countryTo.setOnClickListener {
            showSearchDialog()
        }
    }


    companion object {
        const val MINIMUM_COUNTRY_NAME_LENGTH = 1
    }
}