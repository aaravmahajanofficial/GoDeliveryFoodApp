package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.homeScreen.listings.components.RestaurantListingCardView

@Composable
fun RestaurantList(
    modifier: Modifier = Modifier,
    restaurants: List<RestaurantListingCardModel>?,
    onClick: (RestaurantListingCardModel) -> Unit,
) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(Dimens.NormalPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        this.items(restaurants?.size ?: 0) { index ->
            val restaurantIndex = restaurants?.get(index)
            if (restaurantIndex != null) {
                RestaurantListingCardView(
                    restaurantListingCardModel = restaurantIndex,
                    onClick = { onClick(restaurantIndex) })
            }
            Spacer(modifier = Modifier.width(12.dp))
        }

    }
}