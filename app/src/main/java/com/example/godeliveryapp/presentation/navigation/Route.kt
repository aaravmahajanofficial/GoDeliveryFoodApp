package com.example.godeliveryapp.presentation.navigation

sealed class Route(val route: String) {

    data object HomeScreen : Route(route = "home_screen")
    data object DetailsScreen : Route(route = "details_screen")

    data object PizzaScreen : Route(route = "pizza_screen")

    data object FoodScreen : Route(route = "food_screen")
    data object CartScreen : Route(route = "cart_screen")

}