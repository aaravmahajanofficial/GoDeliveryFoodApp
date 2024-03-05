package com.example.godeliveryapp.domain.repository

import com.example.godeliveryapp.domain.model.RestaurantWithCuisines

interface Repository {
    suspend fun getRestaurants(): List<RestaurantWithCuisines>?

}