package com.example.effectivemobiletestapp.domain.interactors

import com.example.effectivemobiletestapp.domain.MockyApiRepository
import com.example.effectivemobiletestapp.domain.entities.OfferItemDto
import com.example.effectivemobiletestapp.domain.entities.TicketItemDto
import com.example.effectivemobiletestapp.domain.entities.TicketOfferItemDto
import javax.inject.Inject


class ApiInteractor @Inject constructor(private val mockyApiRepository: MockyApiRepository) {

    suspend fun getOffersList(): List<OfferItemDto>
            = mockyApiRepository.getOffersList()

    suspend fun getTicketsOffersList(): List<TicketOfferItemDto>
            = mockyApiRepository.getTicketsOffersList()

    suspend fun getTicketsList(): List<TicketItemDto>
            = mockyApiRepository.getTicketsList()
}