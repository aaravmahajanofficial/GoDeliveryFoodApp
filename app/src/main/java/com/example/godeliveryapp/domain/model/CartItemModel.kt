package com.example.godeliveryapp.domain.model

import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto

data class CartItemModel(

    val cartId : Int,
    val quantity: Int,
    val menuItem: MenuItemsDto

)