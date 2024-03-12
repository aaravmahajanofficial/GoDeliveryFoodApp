package com.example.godeliveryapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NOTUSEDRestaurantModel(
    val restaurantId: Int,
    val restaurantName: String,
    val address: String,
    val time: String,
    val rating: String,
    val distance: String
)