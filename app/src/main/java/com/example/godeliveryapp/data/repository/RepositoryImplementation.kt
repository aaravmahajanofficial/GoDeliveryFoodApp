package com.example.godeliveryapp.data.repository

import com.example.godeliveryapp.domain.model.RestaurantListingCard
import com.example.godeliveryapp.domain.model.Restaurants
import com.example.godeliveryapp.domain.repository.Repository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class RepositoryImplementation(private val supabaseClient: SupabaseClient) : Repository {

    override suspend fun getRestaurants(): List<RestaurantListingCard> {

        val restaurants = supabaseClient.from("Restaurants").select().decodeList<RestaurantListingCard>()

        return restaurants ?: emptyList()


    }

}