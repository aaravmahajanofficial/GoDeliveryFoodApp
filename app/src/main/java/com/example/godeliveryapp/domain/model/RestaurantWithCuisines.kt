package com.example.godeliveryapp.domain.model

import RestaurantDto

data class RestaurantWithCuisines(

    val restaurantDto: RestaurantDto,
    val cuisines: List<String>

)
