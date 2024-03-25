package com.example.godeliveryapp.di

import com.example.godeliveryapp.data.repository.RepositoryImplementation
import com.example.godeliveryapp.domain.repository.Repository
import com.example.zomatoclone.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
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
            //install other modules
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }
//
//    @Provides
//    @Singleton
//    fun provideSupabaseStorage(client: SupabaseClient): Storage {
//        return client.storage
//    }

    @Provides
    @Singleton
    fun providesRestaurant(postgrest: Postgrest): Repository {

        return RepositoryImplementation(postgrest = postgrest)
    }


}