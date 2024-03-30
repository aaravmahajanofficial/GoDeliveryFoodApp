package com.example.godeliveryapp.data.remote

import com.example.godeliveryapp.data.remote.dataTransferObject.APIresponse
import com.example.zomatoclone.utils.Constants.API_KEY
import com.example.zomatoclone.utils.Constants.LANGUAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("revgeocode")
    suspend fun getNearbyLocations(
        @Query("at") coordinates: String,
        @Query("lang") language: String = LANGUAGE,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("limit") limit: String = "20"

    ): APIresponse


}