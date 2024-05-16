package com.example.effectivemobiletestapp.ui.screens.all_tickets_list

import android.view.View
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.TicketItemBinding
import com.example.effectivemobiletestapp.domain.entities.TicketItemDto
import com.example.effectivemobiletestapp.ui.utils.arrangeDigits
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun ticketsAdapterDelegate() = adapterDelegateViewBinding<TicketItemDto, TicketItemDto, TicketItemBinding>(
    viewBinding = { layoutInflater, parent ->
        TicketItemBinding.inflate(layoutInflater, parent, false)
    }
) {

    bind {
        if (item.badge != null) {
            binding.badgeText.text = item.badge
        } else {
            binding.badge.visibility = View.GONE
        }
        binding.price.text = context.getString(
            R.string.tickets_suggestions_item_price, arrangeDigits(item.price)
        )
        binding.timeFrom.text = item.departure.time
        binding.codeFrom.text = item.departure.airport
        binding.timeTo.text = item.arrival.time
        binding.codeTo.text = item.arrival.airport
        binding.flightDuration.text = if (item.flightDuration != 0f) context.getString(
            R.string.all_tickets_list_item_flight_duration, item.flightDuration
        ) else null

        if (item.hasTransfer) {
            binding.slash.visibility = View.GONE
            binding.noTransfer.visibility = View.GONE
        }
    }
}