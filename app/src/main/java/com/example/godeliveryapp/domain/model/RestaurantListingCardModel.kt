package com.example.godeliveryapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RestaurantListingCard(
//    @DrawableRes val imageId: Int?,
    val restaurantName: String,
    val address: String,
    val cuisine: List<String>?,
    val rating: String,
    val distance: String?,
    val restaurantId: Int,
    val time: String? = null
)

val Restaurants = listOf(

    RestaurantListingCard(
//        imageId = R.drawable.restaurant1,
        restaurantName = "Momo",
        cuisine = listOf("Chinese", "Thai", "Seafood"),
        rating = "4.2",
        distance = "0.5",
        time = "28",
        address = "BTM LayOut",
        restaurantId = 12323424
    ),
)
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant2,
//        restaurantName = "La Pizzeria",
//        cuisine = listOf("Italian", "Pizza", "Pasta"),
//        rating = "4.5",
//        distance = "1.2",
//        time = "35",
//        address = "",
//        restaurantId = 12323424
//    ),
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant1,
//        restaurantName = "Sushi Haven",
//        cuisine = listOf("Japanese", "Sushi", "Ramen"),
//        rating = "4.0",
//        distance = "0.8",
//        time = "30",
//        address = "",
//        restaurantId = 12323424
//    ),
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant2,
//        restaurantName = "Taco House",
//        cuisine = listOf("Mexican", "Tacos", "Burritos"),
//        rating = "4.3",
//        distance = "0.6",
//        time = "25",
//        address = "",
//        restaurantId = 12323424
//    ),
//
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant1,
//        restaurantName = "Burger Junction",
//        cuisine = listOf("American", "Burgers", "Fries"),
//        rating = "4.7",
//        distance = "1.0",
//        time = "40",
//        address = "",
//        restaurantId = 12323424
//    )
//
//
//)
//
//val DineIns = listOf(
//
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant1,
//        restaurantName = "Hotel Berkshire",
//        cuisine = listOf("Italian", "Continental", "Mexican"),
//        rating = "4.4",
//        distance = "1",
//        address = "",
//        restaurantId = 12323424
//    ),
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant2,
//        restaurantName = "Blue Ocean Restaurant",
//        cuisine = listOf("Seafood", "Asian Fusion", "Grill"),
//        rating = "4.8",
//        distance = "2",
//        address = "",
//        restaurantId = 12323424
//    ),
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant1,
//        restaurantName = "Sizzling Salsa",
//        cuisine = listOf("Mexican", "Tex-Mex", "Grill"),
//        rating = "4.2",
//        distance = "1.5",
//        address = "",
//        restaurantId = 12323424
//    ),
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant2,
//        restaurantName = "Pasta Paradise",
//        cuisine = listOf("Italian", "Pasta", "Pizza"),
//        rating = "4.6",
//        distance = "1.5",
//        address = "",
//        restaurantId = 12323424
//    ),
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant1,
//        restaurantName = "The Hungry Hut",
//        cuisine = listOf("Burgers", "American", "Fast Food"),
//        rating = "4.0",
//        distance = "0.8",
//        address = "",
//        restaurantId = 12323424
//    ),
//    RestaurantListingCard(
//        imageId = R.drawable.restaurant2,
//        restaurantName = "Sushi Haven",
//        cuisine = listOf("Sushi", "Japanese", "Asian Fusion"),
//        rating = "4.5",
//        distance = "1.3",
//        address = "",
//        restaurantId = 12323424
//    )
//
//
//)