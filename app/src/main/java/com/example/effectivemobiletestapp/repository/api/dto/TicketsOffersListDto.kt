package com.example.effectivemobiletestapp.repository.api.dto

import com.google.gson.annotations.SerializedName


data class TicketsOffersListDto(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketsOffer> = listOf()
) {
    data class TicketsOffer(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("time_range")
        val timeRange: List<String> = listOf(),
        @SerializedName("price")
        val price: Price = Price()
    ) {
        data class Price(
            @SerializedName("value")
            val value: Int = 0
        )
    }
}