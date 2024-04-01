package com.example.godeliveryapp.data.remote.dataTransferObject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Schedule(

    @SerialName("close")
    val close: String,

    @SerialName("open")
    val open: String,

    @SerialName("day")
    val day: String

)