package com.example.effectivemobiletestapp.ui.screens.selected_country

import android.content.res.ColorStateList
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.TicketSuggestionItemBinding
import com.example.effectivemobiletestapp.domain.entities.TicketOfferItemDto
import com.example.effectivemobiletestapp.ui.utils.arrangeDigits
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun ticketsOffersAdapterDelegate() = adapterDelegateViewBinding<TicketOfferItemDto, TicketOfferItemDto, TicketSuggestionItemBinding>(
    viewBinding = { layoutInflater, parent ->
        TicketSuggestionItemBinding.inflate(layoutInflater, parent, false)
    }
) {

    bind {
        binding.title.text = item.title
        binding.price.text = context.getString(
            R.string.tickets_suggestions_item_price, arrangeDigits(item.price)
        )
        binding.flightsSequence.text = item.timeRange.reduce { acc, str -> "$acc $str" }
        binding.dotIcon.imageTintList =
            when (bindingAdapterPosition) {
                0 -> ColorStateList.valueOf(getColor(R.color.red))
                1 -> ColorStateList.valueOf(getColor(R.color.blue))
                else -> ColorStateList.valueOf(getColor(R.color.white))
            }
    }
}