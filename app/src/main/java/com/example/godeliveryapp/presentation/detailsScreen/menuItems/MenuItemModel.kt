package com.example.godeliveryapp.presentation.detailsScreen.menuItems

import com.example.godeliveryapp.R
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemModel(
    val restaurantId: Int,
    val itemId: Int,
    val itemName: String,
    val itemPrice: Double,
    val itemDescription: String,
    val itemCategory: Int,
    val itemImageId: Int? = R.drawable.restaurant1,
    val isVeg: Boolean
)
