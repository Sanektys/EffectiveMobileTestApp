package com.example.effectivemobiletestapp.domain.entities


data class TicketOfferItemDto(
    val title: String = "",
    val timeRange: List<String> = listOf(),
    val price: Int = 0
)
