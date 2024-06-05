package com.example.godeliveryapp.domain.model

import com.example.godeliveryapp.data.remote.dataTransferObject.Schedule
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantListingCardModel(
    val imageURL : String,
    val about: String,
    val city: String,
    val country: String,
    val cuisines: List<String>,
    val distance: String,
    val features: List<String>,
    val isPureVeg: Boolean,
    val meals: List<String>,
    val name: String,
    val postalCode: String,
    val priceRange: String,
    val rating: String,
    val restaurantId: Int,
    val schedule: List<Schedule>,
    val streetAddress: String
)