package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemsDto(

    @SerialName("itemId")
    val itemId: Int,

    @SerialName("itemName")
    val itemName: String,

    @SerialName("menuId")
    val menuId: Int,

    @SerialName("price")
    val price: Int,

    @SerialName("quantity")
    val quantity: Int,

    @SerialName("description")
    val description: String,

    )