package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartOrderItemDto(

    @SerialName("cartId")
    val cartId: Int,
    @SerialName("itemId")
    val itemId: Int,
    @SerialName("quantity")
    val quantity: Int,

    )
