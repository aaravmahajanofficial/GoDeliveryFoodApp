package com.example.zomatoclone.di

import com.example.zomatoclone.data.repository.RepositoryImplementation
import com.example.zomatoclone.domain.repository.Repository
import com.example.zomatoclone.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = Constants.BASE_URL,
            supabaseKey = Constants.supabaseKey
        ) {
            install(Auth)
            install(Postgrest)
            //install other modules
        }
    }

    @Provides
    @Singleton
    fun providesRestaurant(supabaseClient: SupabaseClient): Repository {

        return RepositoryImplementation(supabaseClient)
    }


}