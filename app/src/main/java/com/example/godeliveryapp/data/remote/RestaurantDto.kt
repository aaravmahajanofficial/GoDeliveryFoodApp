package com.example.godeliveryapp.data.remote

import com.example.godeliveryapp.data.remote.dataTransferObject.Schedule
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDto(

    @SerialName("restaurantId")
    val restaurantId: Int,

    @SerialName("name")
    val name: String,

    @SerialName("about")
    val about: String,

    @SerialName("city")
    val city: String,

    @SerialName("country")
    val country: String,

    @SerialName("distance")
    val distance: String,

    @SerialName("cuisines")
    val cuisines: List<String>,

    @SerialName("features")
    val features: List<String>,

    @SerialName("isPureVeg")
    val isPureVeg: Boolean,

    @SerialName("meals")
    val meals: List<String>,

    @SerialName("schedule")
    val schedule: List<Schedule>,

    @SerialName("postalCode")
    val postalCode: String,

    @SerialName("rating")
    val rating: String,

    @SerialName("priceRange")
    val priceRange: String,

    @SerialName("streetAddress")
    val streetAddress: String,

    @SerialName("imageURL")
    val imageURL: String
)