package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemDto(
    @SerialName("id")
    var id: Int? = null,

    @SerialName("orderId")
    val orderId: Int? = null,

    @SerialName("itemId")
    val itemId: Int,

    @SerialName("quantity")
    val quantity: Int,

    @SerialName("price")
    val price: Double
)
