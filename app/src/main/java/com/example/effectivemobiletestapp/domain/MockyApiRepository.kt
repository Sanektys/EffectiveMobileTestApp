package com.example.effectivemobiletestapp.domain

import com.example.effectivemobiletestapp.domain.entities.TicketItemDto
import com.example.effectivemobiletestapp.domain.entities.OfferItemDto
import com.example.effectivemobiletestapp.domain.entities.TicketOfferItemDto


interface MockyApiRepository {

    suspend fun getOffersList(): List<OfferItemDto>

    suspend fun getTicketsOffersList(): List<TicketOfferItemDto>

    suspend fun getTicketsList(): List<TicketItemDto>
}