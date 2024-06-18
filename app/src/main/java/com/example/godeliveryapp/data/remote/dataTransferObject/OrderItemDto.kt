package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemDto(

    @SerialName("orderId")
    val orderId: UInt? = null,

    @SerialName("itemId")
    val itemId: Int,

    @SerialName("quantity")
    val quantity: Int,

    @SerialName("price")
    val price: Double
)
