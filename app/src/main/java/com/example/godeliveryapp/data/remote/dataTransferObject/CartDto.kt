package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(

    @SerialName("cartId")
    val cartId: String,

    @SerialName("userId")
    val userId: String
)