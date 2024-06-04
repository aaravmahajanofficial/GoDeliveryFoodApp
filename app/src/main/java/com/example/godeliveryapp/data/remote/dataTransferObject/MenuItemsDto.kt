package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemsDto(

    @SerialName("itemId")
    val itemId: Int,

    @SerialName("itemName")
    val itemName: String,

    @SerialName("restaurantId")
    val restaurantId: Int,

    @SerialName("price")
    val price: Double,

    @SerialName("description")
    val description: String,

    @SerialName("categoryId")
    val itemCategory: Int
)