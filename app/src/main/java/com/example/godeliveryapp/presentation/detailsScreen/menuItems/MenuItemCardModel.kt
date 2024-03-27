package com.example.godeliveryapp.presentation.detailsScreen.menuItems

import com.example.godeliveryapp.R

data class MenuItemCardModel(
    val itemId : Int,
    val itemName: String,
    val itemPrice: Double,
    val itemDescription: String,
    val itemImageId: Int? = R.drawable.restaurant1,
)
