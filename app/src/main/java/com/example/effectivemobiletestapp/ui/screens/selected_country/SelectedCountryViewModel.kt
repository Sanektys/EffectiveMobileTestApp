package com.example.effectivemobiletestapp.ui.screens.selected_country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestapp.App
import com.example.effectivemobiletestapp.domain.entities.TicketOfferItemDto
import com.example.effectivemobiletestapp.domain.interactors.ApiInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SelectedCountryViewModel : ViewModel() {

    @Inject
    lateinit var apiInteractor: ApiInteractor

    private val _countryFrom = MutableStateFlow("")
    val countryFrom = _countryFrom.asStateFlow()

    private val _countryTo = MutableStateFlow("")
    val countryTo = _countryTo.asStateFlow()

    private val _flightDate = MutableStateFlow(System.currentTimeMillis())
    val flightDate = _flightDate.asStateFlow()

    private val _ticketsOffersList = MutableStateFlow(listOf<TicketOfferItemDto>())
    val ticketsOffersList = _ticketsOffersList.asStateFlow()


    init {
        App.instance.appComponent.inject(this)

        getTicketsOffersList()
    }


    fun setCountryFrom(country: String) {
        _countryFrom.value = country
    }

    fun setCountryTo(country: String) {
        _countryTo.value = country
    }

    fun setFlightDate(flightDate: Long) {
        _flightDate.value = flightDate
    }

    private fun getTicketsOffersList() {
        viewModelScope.launch {
            try {
                val max = MAX_ELEMENTS_IN_TICKETS_OFFERS_LIST
                val newList = apiInteractor.getTicketsOffersList()
                _ticketsOffersList.value =
                    if (newList.size > max) newList.subList(0, max) else newList
            } catch (_: Exception) {}
        }
    }


    companion object {
        const val MAX_ELEMENTS_IN_TICKETS_OFFERS_LIST = 3
    }
}