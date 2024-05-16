package com.example.effectivemobiletestapp.ui.screens.home

import androidx.appcompat.content.res.AppCompatResources
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.TripSuggestionItemBinding
import com.example.effectivemobiletestapp.domain.entities.OfferItemDto
import com.example.effectivemobiletestapp.ui.utils.arrangeDigits
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun offersAdapterDelegate() = adapterDelegateViewBinding<OfferItemDto, OfferItemDto, TripSuggestionItemBinding>(
    viewBinding = { layoutInflater, parent ->
        TripSuggestionItemBinding.inflate(layoutInflater, parent, false)
    }
) {

    bind {
        binding.suggestionItemName.text = item.title
        binding.suggestionItemLocation.text = item.town
        binding.suggestionItemPrice.text = context.getString(
            R.string.list_of_suggestions_item_price, arrangeDigits(item.price)
        )
        binding.suggestionItemPicture.setImageDrawable(
            when (bindingAdapterPosition) {
                0 -> AppCompatResources.getDrawable(context, R.drawable.band_image1)
                1 -> AppCompatResources.getDrawable(context, R.drawable.band_image2)
                2 -> AppCompatResources.getDrawable(context, R.drawable.band_image3)
                else -> AppCompatResources.getDrawable(context, R.drawable.mock_suggestion_item_image)
            }
        )
    }
}