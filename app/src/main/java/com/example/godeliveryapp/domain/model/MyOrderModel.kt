package com.example.godeliveryapp.domain.model

import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemModel
import kotlinx.serialization.Serializable

@Serializable
data class MyOrderModel(
    val orderId: String,
    val items: List<MenuItemModel>,
    val restaurantName: String,
    val restaurantAddress: String,
    val restaurantImage: Int,
    val createdAt: String,
    val orderStatus: String,
    val orderTotal: String,
    val totalItems: Int,
    val paymentMode: String,
    val verificationCode : String
)