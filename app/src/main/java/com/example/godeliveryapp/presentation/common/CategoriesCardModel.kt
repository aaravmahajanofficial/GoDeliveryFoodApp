package com.example.godeliveryapp.presentation.common

import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.navigation.Route

data class CategoryCard(
    val title: String, val description: String, val imageId: Int, val route: String? = null
)

val CategoryCardList = listOf(

    CategoryCard(
        title = "Food",
        description = "25 mins",
        imageId = R.drawable.fastfood,
        route = Route.HomeScreen.route
    ),

    CategoryCard(
        title = "Mart",
        description = "20 mins",
        imageId = R.drawable.groceries
    ),

    CategoryCard(
        title = "Courier",
        description = "30 mins",
        imageId = R.drawable.delivery2
    ),

    CategoryCard(
        title = "Dine in",
        description = "No waiting",
        imageId = R.drawable.dinein
    )


)