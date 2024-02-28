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
import androidx.lifecycle.LiveData
import com.example.godeliveryapp.domain.model.RestaurantListingCard
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.homeScreen.listings.components.RestaurantListingCardView

@Composable
fun RestaurantList(
    modifier: Modifier = Modifier,
    restaurants: LiveData<List<RestaurantListingCard>>,
    onClick: (RestaurantListingCard) -> Unit,
) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(Dimens.NormalPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        this.items(restaurants.value?.size ?: 0) { index ->
            val result = restaurants.value?.get(index)
            if (result != null) {
                RestaurantListingCardView(
                    restaurantListingCard = result,
                    onClick = { onClick(result) })
            }
            Spacer(modifier = Modifier.width(12.dp))
        }

    }
}