package com.example.zomatoclone.domain.repository

import com.example.zomatoclone.domain.model.RestaurantItem

interface Repository {

    suspend fun getRestaurants(): List<RestaurantItem>


}