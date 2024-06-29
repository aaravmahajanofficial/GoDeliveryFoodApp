package com.example.godeliveryapp.di

import android.content.Context
import com.example.godeliveryapp.data.remote.RetrofitAPI
import com.example.godeliveryapp.data.repository.RepositoryImplementation
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.utils.Constants
import com.example.godeliveryapp.utils.Constants.BASE_API_URL
import com.example.godeliveryapp.utils.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = Constants.BASE_URL,
            supabaseKey = Constants.SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)

        }
    }

    @Provides
    @Singleton
    fun provideAPI(): RetrofitAPI {

        return Retrofit.Builder().baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RetrofitAPI::class.java)

    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    @Singleton
    fun providesRestaurant(postgrest: Postgrest, retrofitAPI: RetrofitAPI, sharedPreferences: SharedPreferences): Repository =
        RepositoryImplementation(postgrest = postgrest, retrofitAPI = retrofitAPI, sharedPreferences = sharedPreferences)

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return SharedPreferences(context)
    }

}