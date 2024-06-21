package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(

    @SerialName("userId")
    val userId: String,

    @SerialName("userEmail")
    val userEmail: String,

    @SerialName("userName")
    val userName: String,

    @SerialName("userAddress")
    val userAddress: String,

    @SerialName("userPhone")
    val userPhone: String? = null,

    @SerialName("landmark")
    val landmark: String

)
