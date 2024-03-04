package com.example.godeliveryapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CuisineModel(
    val restaurantId: Int,
    val cuisineId: Int,
    val name: String
)