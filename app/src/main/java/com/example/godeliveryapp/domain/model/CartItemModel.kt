package com.example.godeliveryapp.domain.model

import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemModel

data class CartItemModel(

    val restaurantId : Int,
    val menuItemModel: MenuItemModel,
    var quantity: Int

)