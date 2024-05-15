package com.example.effectivemobiletestapp.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class HomeViewModel : ViewModel() {

    private val _countryFrom = MutableStateFlow("")
    val countryFrom = _countryFrom.asStateFlow()


    fun setCountryFrom(country: String) {
        _countryFrom.value = country
    }
}