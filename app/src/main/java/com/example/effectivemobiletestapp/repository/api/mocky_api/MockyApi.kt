package com.example.effectivemobiletestapp.repository.api.mocky_api

import com.example.effectivemobiletestapp.repository.api.dto.OffersListDto
import com.example.effectivemobiletestapp.repository.api.dto.TicketsListDto
import com.example.effectivemobiletestapp.repository.api.dto.TicketsOffersListDto
import retrofit2.http.GET


interface MockyApi {
    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getOffersList(): OffersListDto

    @GET("7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun getTicketsOffersList(): TicketsOffersListDto

    @GET("670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun getTicketsList(): TicketsListDto
}