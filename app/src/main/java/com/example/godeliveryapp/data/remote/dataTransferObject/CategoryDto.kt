package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CategoryDto(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("imageUrl")
    val imageUrl: String

)