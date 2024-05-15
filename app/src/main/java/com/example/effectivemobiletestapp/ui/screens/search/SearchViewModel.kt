package com.example.effectivemobiletestapp.ui.screens.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SearchViewModel : ViewModel() {

    private val _countryFrom = MutableStateFlow("")
    val countryFrom = _countryFrom.asStateFlow()

    private val _countryTo = MutableStateFlow("")
    val countryTo = _countryTo.asStateFlow()


    fun setCountryFrom(country: String) {
        _countryFrom.value = country
    }

    fun setCountryTo(country: String) {
        _countryTo.value = country
    }
}