package com.example.effectivemobiletestapp.repository.api.utils

import com.example.effectivemobiletestapp.domain.entities.TicketItemDto
import com.example.effectivemobiletestapp.domain.entities.OfferItemDto
import com.example.effectivemobiletestapp.domain.entities.TicketOfferItemDto
import com.example.effectivemobiletestapp.repository.api.dto.TicketsListDto
import com.example.effectivemobiletestapp.repository.api.dto.OffersListDto
import com.example.effectivemobiletestapp.repository.api.dto.TicketsOffersListDto


object MockyApiConverter {

    fun convertRawOffersDtoToPrettyDto(
        rawDto: OffersListDto
    ): List<OfferItemDto> {
        val newList = ArrayList<OfferItemDto>(rawDto.offers.size)

        rawDto.offers.forEach { rawItem ->
            val prettyItem = OfferItemDto(
                title = rawItem.title,
                town = rawItem.town,
                price = rawItem.price.value
            )
            newList.add(prettyItem)
        }
        return newList
    }

    fun convertRawTicketsOffersDtoToPrettyDto(
        rawDto: TicketsOffersListDto
    ): List<TicketOfferItemDto> {
        val newList = ArrayList<TicketOfferItemDto>(rawDto.ticketsOffers.size)

        rawDto.ticketsOffers.forEach { rawItem ->
            val prettyItem = TicketOfferItemDto(
                title = rawItem.title,
                timeRange = rawItem.timeRange,
                price = rawItem.price.value
            )
            newList.add(prettyItem)
        }
        return newList
    }

    fun convertRawTicketsDtoToPrettyDto(
        rawDto: TicketsListDto
    ): List<TicketItemDto> {
        val newList = ArrayList<TicketItemDto>(rawDto.tickets.size)

        rawDto.tickets.forEach { rawItem ->
            val prettyItem = TicketItemDto(
                badge = rawItem.badge,
                price = rawItem.price.value,
                hasTransfer = rawItem.hasTransfer,
                departure = TicketItemDto.Airport(
                    date = rawItem.departure.date,
                    airport = rawItem.departure.airport
                ),
                arrival = TicketItemDto.Airport(
                    date = rawItem.departure.date,
                    airport = rawItem.departure.airport
                )
            )
            newList.add(prettyItem)
        }
        return newList
    }
}