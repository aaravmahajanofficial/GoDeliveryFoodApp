package com.example.godeliveryapp.domain.model

import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto

data class CartItemModel(

    val quantity: Int,
    val cartItem: MenuItemsDto

)