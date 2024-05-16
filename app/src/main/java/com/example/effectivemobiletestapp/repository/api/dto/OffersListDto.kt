package com.example.effectivemobiletestapp.repository.api.dto

import com.google.gson.annotations.SerializedName


data class OffersListDto(
    @SerializedName("offers")
    val offers: List<Offer> = listOf()
) {
    data class Offer(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("town")
        val town: String = "",
        @SerializedName("price")
        val price: Price = Price()
    ) {
        data class Price(
            @SerializedName("value")
            val value: Int = 0
        )
    }
}