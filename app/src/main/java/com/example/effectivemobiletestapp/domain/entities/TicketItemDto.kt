package com.example.effectivemobiletestapp.domain.entities


data class TicketItemDto(
    val badge: String? = null,
    val price: Int = 0,
    val departure: Airport = Airport(),
    val arrival: Airport = Airport(),
    val hasTransfer: Boolean = false,
) {
    data class Airport(
        val date: String = "",
        val airport: String = ""
    )
}
