package com.example.effectivemobiletestapp.ui.screens.selected_country

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SelectedCountryViewModel : ViewModel() {

    private val _countryFrom = MutableStateFlow("")
    val countryFrom = _countryFrom.asStateFlow()

    private val _countryTo = MutableStateFlow("")
    val countryTo = _countryTo.asStateFlow()

    private val _flightDate = MutableStateFlow(System.currentTimeMillis())
    val flightDate = _flightDate.asStateFlow()


    fun setCountryFrom(country: String) {
        _countryFrom.value = country
    }

    fun setCountryTo(country: String) {
        _countryTo.value = country
    }

    fun setFlightDate(flightDate: Long) {
        _flightDate.value = flightDate
    }
}