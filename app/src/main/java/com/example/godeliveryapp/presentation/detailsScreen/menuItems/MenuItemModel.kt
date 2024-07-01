package com.example.godeliveryapp.presentation.detailsScreen.menuItems

import kotlinx.serialization.Serializable

@Serializable
data class MenuItemModel(
    val restaurantId: Int,
    val itemId: Int,
    val itemName: String,
    val itemPrice: Double,
    val itemDescription: String,
    val itemCategory: Int,
    val imageId: String,
    val isVeg: Boolean
)
