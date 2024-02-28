package com.example.godeliveryapp.domain.repository

import com.example.godeliveryapp.domain.model.RestaurantListingCard

interface Repository {

    suspend fun getRestaurants(): List<RestaurantListingCard>


}