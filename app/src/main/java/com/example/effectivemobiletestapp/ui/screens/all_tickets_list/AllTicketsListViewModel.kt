package com.example.effectivemobiletestapp.ui.screens.all_tickets_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class AllTicketsListViewModel : ViewModel() {

    private val _flightRoute = MutableStateFlow("")
    val flightRoute = _flightRoute.asStateFlow()

    private val _flightSpecification = MutableStateFlow("")
    val flightSpecification = _flightSpecification.asStateFlow()


    fun setFlightRoute(flightRoute: String) {
        _flightRoute.value = flightRoute
    }

    fun setFlightSpecification(flightSpecification: String) {
        _flightSpecification.value = flightSpecification
    }
}