package com.example.godeliveryapp.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.model.RestaurantListingCard
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _restaurants = MutableStateFlow<List<RestaurantListingCard>?>(listOf())
    val restaurants: Flow<List<RestaurantListingCard>?> get() = _restaurants

    private var _isLoading = MutableStateFlow(true)

    val isLoading: Flow<Boolean> = _isLoading

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            val restaurantWithCuisines = repository.getRestaurants()
            val restaurantListingCard = restaurantWithCuisines?.map { resCui ->
                val restaurantDto = resCui.restaurantDto
                val cuisinesList = resCui.cuisines

                RestaurantListingCard(
                    restaurantName = restaurantDto.restaurantName,
                    address = restaurantDto.address,
                    restaurantId = restaurantDto.restaurantId,
                    distance = restaurantDto.distance,
                    rating = restaurantDto.rating,
                    time = restaurantDto.time,
                    cuisine = cuisinesList

                )
            }

            _restaurants.emit(restaurantListingCard)
            _isLoading.value = false

        }
    }

}


//private fun fetchRestaurants() {
//        viewModelScope.launch {
//            val restaurantsDTOs = repository.getRestaurants()
//            _restaurants.emit(restaurantsDTOs?.map { it -> it.asDomainModel() })
//        }
//    }
//
//    private fun RestaurantDto.asDomainModel(): RestaurantListingCard {
//        return RestaurantListingCard(
//            restaurantName = this.restaurantName,
//            address = this.address,
//            restaurantId = this.restaurantId,
//            distance = this.distance,
//            rating = this.rating,
//            time = this.time,
//            cuisine = listOf() // Initialize as empty list
//        )
//    }