package com.example.effectivemobiletestapp.repository.api.utils

import com.example.effectivemobiletestapp.App
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.domain.entities.TicketItemDto
import com.example.effectivemobiletestapp.domain.entities.OfferItemDto
import com.example.effectivemobiletestapp.domain.entities.TicketOfferItemDto
import com.example.effectivemobiletestapp.repository.api.dto.TicketsListDto
import com.example.effectivemobiletestapp.repository.api.dto.OffersListDto
import com.example.effectivemobiletestapp.repository.api.dto.TicketsOffersListDto
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


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

        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            App.instance.resources.configuration.locales[0]
        )
        val timeFormat = SimpleDateFormat(
            "HH:mm",
            App.instance.resources.configuration.locales[0]
        )

        var departureDate: Date?
        var arrivalDate: Date?

        rawDto.tickets.forEach { rawItem ->
            val departureTime = try {
                departureDate = dateFormat.parse(rawItem.departure.date)!!
                timeFormat.format(departureDate!!)
            } catch (e: Exception) {
                departureDate = null
                "error"
            }

            val arrivalTime = try {
                arrivalDate = dateFormat.parse(rawItem.arrival.date)!!
                timeFormat.format(arrivalDate!!)
            } catch (e: Exception) {
                arrivalDate = null
                "error"
            }

            val duration = arrivalDate?.time?.minus(departureDate?.time ?: 0)
            val flightDuration = duration?.toFloat()?.div(1000)?.div(60)?.div(60)

            val prettyItem = TicketItemDto(
                badge = rawItem.badge,
                price = rawItem.price.value,
                hasTransfer = rawItem.hasTransfer,
                departure = TicketItemDto.Airport(
                    time = departureTime,
                    airport = rawItem.departure.airport
                ),
                arrival = TicketItemDto.Airport(
                    time = arrivalTime,
                    airport = rawItem.arrival.airport
                ),
                flightDuration = flightDuration ?: 0f
            )
            newList.add(prettyItem)
        }
        return newList
    }
}