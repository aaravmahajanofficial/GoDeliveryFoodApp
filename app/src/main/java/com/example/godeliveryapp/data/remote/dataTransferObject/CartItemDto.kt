package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemDto(

    @SerialName("cartId")
    val cartId: String,
    @SerialName("restaurantId")
    val restaurantId: Int,
    @SerialName("itemId")
    val itemId: Int,
    @SerialName("quantity")
    val quantity: Int
)