package com.example.godeliveryapp.domain.model

data class CartOrderItemModel (

    val cartId : Int,
    val itemId : Int,
    val itemName : String,
    val price : Double,
    val quantity : Int,

)