package com.example.godeliveryapp.domain.model


data class OrderModel(
    var orderId: UInt? = null,
    var createdAt: String? = null,
    var userId: String? = null,
    var restaurantId: Int? = null,
    var totalAmount: Double,
    var orderStatus: String? = null,
    var verificationCode: Int? = null,
    var deliveryInstructions: String? = null,
    val paymentMode: String? = null,
    val itemsCount: Int
)