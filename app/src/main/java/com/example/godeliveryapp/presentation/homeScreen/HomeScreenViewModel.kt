package com.example.godeliveryapp.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _restaurants = MutableStateFlow<List<RestaurantListingCardModel>?>(listOf())
    val restaurants: Flow<List<RestaurantListingCardModel>?> get() = _restaurants

    private var _isLoading = MutableStateFlow(true)

    val isLoading: Flow<Boolean> = _isLoading

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            val restaurantWithCuisines = repository.getRestaurants()
            val restaurantListingCards = restaurantWithCuisines?.map { resCui ->
                val restaurantDto = resCui.restaurantDto
                val cuisinesList = resCui.cuisines

                RestaurantListingCardModel(
                    restaurantName = restaurantDto.restaurantName,
                    address = restaurantDto.address,
                    restaurantId = restaurantDto.restaurantId,
                    distance = restaurantDto.distance,
                    rating = restaurantDto.rating,
                    time = restaurantDto.time,
                    cuisine = cuisinesList

                )
            }

            _restaurants.emit(restaurantListingCards)
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