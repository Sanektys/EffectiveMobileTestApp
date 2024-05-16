package com.example.effectivemobiletestapp.ui.screens.all_tickets_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestapp.App
import com.example.effectivemobiletestapp.domain.entities.TicketItemDto
import com.example.effectivemobiletestapp.domain.interactors.ApiInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class AllTicketsListViewModel : ViewModel() {

    @Inject
    lateinit var apiInteractor: ApiInteractor

    private val _flightRoute = MutableStateFlow("")
    val flightRoute = _flightRoute.asStateFlow()

    private val _flightSpecification = MutableStateFlow("")
    val flightSpecification = _flightSpecification.asStateFlow()

    private val _ticketsList = MutableStateFlow(listOf<TicketItemDto>())
    val ticketsList = _ticketsList.asStateFlow()


    init {
        App.instance.appComponent.inject(this)

        getTicketsList()
    }


    fun setFlightRoute(flightRoute: String) {
        _flightRoute.value = flightRoute
    }

    fun setFlightSpecification(flightSpecification: String) {
        _flightSpecification.value = flightSpecification
    }

    private fun getTicketsList() {
        viewModelScope.launch {
            try {
                _ticketsList.value = apiInteractor.getTicketsList()
            } catch (_: Exception) {}
        }
    }
}