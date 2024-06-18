package com.example.godeliveryapp.data.remote.dataTransferObject

import com.example.godeliveryapp.presentation.orderScreen.OrderState
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(

    @SerialName("orderId")
    var orderId: UInt? = null,

    @SerialName("createdAt")
    var createdAt: Instant? = null,

    @SerialName("userId")
    var userId: String? = null,

    @SerialName("restaurantId")
    var restaurantId: Int? = null,

    @SerialName("totalAmount")
    var totalAmount: Double,

    @SerialName("orderStatus")
    var orderStatus: OrderState? = null,

    @SerialName("verificationCode")
    var verificationCode: Int? = null,

    @SerialName("deliveryInstructions")
    var deliveryInstructions: String? = null

)
