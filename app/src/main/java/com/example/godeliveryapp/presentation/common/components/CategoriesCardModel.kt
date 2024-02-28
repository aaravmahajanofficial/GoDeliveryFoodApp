package com.example.godeliveryapp.presentation.common.components

import com.example.godeliveryapp.R

data class CategoryCard(
    val title: String, val description: String, val imageId: Int
)

val CategoryCardList = listOf(

    CategoryCard(
        title = "Food",
        description = "25 mins",
        imageId = R.drawable.fastfood
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