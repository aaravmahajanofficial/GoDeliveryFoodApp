package com.example.zomatoclone.data.repository

import com.example.zomatoclone.domain.model.RestaurantItem
import com.example.zomatoclone.domain.repository.Repository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class RepositoryImplementation(private val supabaseClient: SupabaseClient) : Repository {

    override suspend fun getRestaurants(): List<RestaurantItem> {


        val restaurants = supabaseClient.from("Restaurants").select().decodeList<RestaurantItem>()

        return restaurants ?: emptyList()


    }

}