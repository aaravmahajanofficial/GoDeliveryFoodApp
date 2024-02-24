package com.example.godeliveryapp.presentation.navigation

sealed class Route(val route: String) {

    data object HomeScreen : Route(route = "home_screen")
    data object DetailsScreen : Route(route = "details_screen")

}