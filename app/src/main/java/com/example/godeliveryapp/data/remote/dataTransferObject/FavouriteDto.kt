package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavouriteDto(
    @SerialName("id")
    var id: UInt? = null,
    @SerialName("restaurantId")
    val restaurantId: Int,
    @SerialName("userId")
    val userId: String

)