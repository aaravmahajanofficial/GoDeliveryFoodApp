package com.example.godeliveryapp.domain.model

import com.example.godeliveryapp.domain.model.removeAPI.Address
import kotlinx.serialization.Serializable

@Serializable
data class LocationCardModel(

    val title: String,
    val address: Address

)