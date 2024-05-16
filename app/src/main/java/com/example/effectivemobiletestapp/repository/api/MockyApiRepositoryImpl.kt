package com.example.effectivemobiletestapp.repository.api

import com.example.effectivemobiletestapp.domain.MockyApiRepository
import com.example.effectivemobiletestapp.domain.entities.OfferItemDto
import com.example.effectivemobiletestapp.domain.entities.TicketItemDto
import com.example.effectivemobiletestapp.domain.entities.TicketOfferItemDto
import com.example.effectivemobiletestapp.repository.api.mocky_api.MockyApi
import com.example.effectivemobiletestapp.repository.api.utils.MockyApiConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MockyApiRepositoryImpl @Inject constructor(private val mockyApi: MockyApi)
    : MockyApiRepository
{

    override suspend fun getOffersList(): List<OfferItemDto> = withContext(Dispatchers.IO) {
        MockyApiConverter.convertRawOffersDtoToPrettyDto(mockyApi.getOffersList())
    }

    override suspend fun getTicketsOffersList(): List<TicketOfferItemDto> = withContext(Dispatchers.IO) {
        MockyApiConverter.convertRawTicketsOffersDtoToPrettyDto(mockyApi.getTicketsOffersList())
    }

    override suspend fun getTicketsList(): List<TicketItemDto> = withContext(Dispatchers.IO) {
        MockyApiConverter.convertRawTicketsDtoToPrettyDto(mockyApi.getTicketsList())
    }
}