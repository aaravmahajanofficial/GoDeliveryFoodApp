package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    @SerialName("cartId")
    val cartId: Int,

    @SerialName("userId")
    val userId: Int,

    @SerialName("itemId")
    val itemId: Int,

    @SerialName("quantity")
    val quantity: Int

)
