package com.example.godeliveryapp.domain.model

import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemModel

data class CartItemModel(

    val restaurantId: Int? = null,
    val menuItemModel: MenuItemModel,
    val quantity: Int

)