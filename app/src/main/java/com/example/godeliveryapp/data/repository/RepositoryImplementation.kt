package com.example.godeliveryapp.data.repository

import RestaurantDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CuisineDto
import com.example.godeliveryapp.domain.model.RestaurantWithCuisines
import com.example.godeliveryapp.domain.repository.Repository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImplementation(private val postgrest: Postgrest) :
    Repository {

    override suspend fun getRestaurants(): List<RestaurantWithCuisines> {

        return withContext(Dispatchers.IO) {

            val restaurants =
                postgrest.from("Restaurants").select().decodeList<RestaurantDto>()

            val restaurantCuisines =
                postgrest.from("Cuisines").select().decodeList<CuisineDto>()

            val restaurantWithCuisines = restaurants.map { restaurant: RestaurantDto ->
                val cuisinesList: List<String> =
                    restaurantCuisines.filter { it.restaurantId == restaurant.restaurantId }
                        .map { it.name }
                RestaurantWithCuisines(restaurant, cuisinesList)
            }

            restaurantWithCuisines
        }
    }

}

