package com.example.godeliveryapp.presentation.homeScreen.listings

import androidx.annotation.DrawableRes
import com.example.godeliveryapp.R

data class ListingCard(

    @DrawableRes val imageId: Int,
    val title: String,
    val options: List<String>,
    val rating: String,
    val distance: String,
    val time: String? = null
)

val Restaurants = listOf(

    ListingCard(
        imageId = R.drawable.restaurant1,
        title = "Hotel Empire",
        options = listOf("Chinese", "Thai", "Seafood"),
        rating = "4,2",
        distance = "0.5",
        time = "28"
    ),
    ListingCard(
        imageId = R.drawable.restaurant2,
        title = "La Pizzeria",
        options = listOf("Italian", "Pizza", "Pasta"),
        rating = "4.5",
        distance = "1.2",
        time = "35"
    ),
    ListingCard(
        imageId = R.drawable.restaurant1,
        title = "Sushi Haven",
        options = listOf("Japanese", "Sushi", "Ramen"),
        rating = "4.0",
        distance = "0.8",
        time = "30"
    ),
    ListingCard(
        imageId = R.drawable.restaurant2,
        title = "Taco House",
        options = listOf("Mexican", "Tacos", "Burritos"),
        rating = "4.3",
        distance = "0.6",
        time = "25"
    ),

    ListingCard(
        imageId = R.drawable.restaurant1,
        title = "Burger Junction",
        options = listOf("American", "Burgers", "Fries"),
        rating = "4.7",
        distance = "1.0",
        time = "40"
    )


)