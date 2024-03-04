package com.example.godeliveryapp.data.repository

import com.example.godeliveryapp.domain.model.CuisineModel
import com.example.godeliveryapp.domain.model.RestaurantListingCard
import com.example.godeliveryapp.domain.model.RestaurantModel
import com.example.godeliveryapp.domain.repository.Repository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImplementation(private val postgrest: Postgrest) :
    Repository {

    override suspend fun getRestaurants(): List<RestaurantListingCard> {

        return withContext(Dispatchers.IO) {

            val restaurants =
                postgrest.from("Restaurants").select().decodeList<RestaurantModel>()

            val restaurantCuisines =
                postgrest.from("Cuisines").select().decodeList<CuisineModel>()

            val restaurantListingCards = restaurants.map { restaurant ->
                val cuisines =
                    restaurantCuisines.filter { restaurant.restaurantId == it.restaurantId }
                        .map { it.name }

                RestaurantListingCard(
                    restaurantName = restaurant.restaurantName,
                    address = restaurant.address,
                    cuisine = cuisines,
                    rating = restaurant.rating,
                    distance = restaurant.distance,
                    restaurantId = restaurant.restaurantId,
                    time = restaurant.time

                )

            }


            restaurantListingCards
        }
    }

}

