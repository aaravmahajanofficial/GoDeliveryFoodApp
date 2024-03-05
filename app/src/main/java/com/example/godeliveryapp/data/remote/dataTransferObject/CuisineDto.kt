package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CuisineDto(

    @SerialName("restaurantId")
    val restaurantId: Int,

    @SerialName("cuisineId")
    val cuisineId: Int,

    @SerialName("name")
    val name: String

)