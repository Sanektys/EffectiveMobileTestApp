package com.example.effectivemobiletestapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestapp.App
import com.example.effectivemobiletestapp.domain.entities.OfferItemDto
import com.example.effectivemobiletestapp.domain.interactors.ApiInteractor
import com.example.effectivemobiletestapp.domain.interactors.DbInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel : ViewModel() {

    @Inject
    lateinit var dbInteractor: DbInteractor
    @Inject
    lateinit var apiInteractor: ApiInteractor

    private val _countryFrom = MutableStateFlow("")
    val countryFrom = _countryFrom.asStateFlow()

    private val _offersList = MutableStateFlow(listOf<OfferItemDto>())
    val offersList = _offersList.asStateFlow()


    init {
        App.instance.appComponent.inject(this)

        getOffersList()
        getLatestEnteredCountry()
        observeToSetLatestEnteredCountry()
    }


    fun setCountryFrom(country: String) {
        _countryFrom.value = country
    }

    private fun getLatestEnteredCountry() {
        viewModelScope.launch {
            dbInteractor.observeLatestCountryFrom().collect { enteredCountry ->
                _countryFrom.value = enteredCountry
            }
        }
    }

    private fun observeToSetLatestEnteredCountry() {
        viewModelScope.launch {
            _countryFrom.collect { newCountry ->
                if (newCountry.isNotEmpty()) {
                    dbInteractor.changeLatestCountryFrom(newCountry)
                }
            }
        }
    }

    private fun getOffersList() = viewModelScope.launch {
        try {
            _offersList.value = apiInteractor.getOffersList()
        } catch (_: Exception) {}
    }
}