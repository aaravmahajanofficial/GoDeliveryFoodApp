package com.example.zomatoclone.domain.model

import kotlinx.serialization.Serializable
import java.sql.Time
import java.sql.Timestamp

@Serializable
data class RestaurantItem(
    val Address: String,
    val Cuisine: String,
    val Name: String,
    val Opening_Hours: String,
    val Rating: Int,
    val RestaurantID: Int
)