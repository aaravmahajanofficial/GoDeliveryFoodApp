package com.example.godeliveryapp.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
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

    private val _categories = MutableStateFlow<List<CategoryDto>?>(listOf())

    val categories: Flow<List<CategoryDto>?> get() = _categories

    private var _isLoading = MutableStateFlow(true)

    val isLoading: Flow<Boolean> = _isLoading


    init {
        fetchRestaurants()
        getCategories()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val restaurantDetails = repository.getRestaurants()
                val restaurantListingCards = restaurantDetails?.map { item ->
                    RestaurantListingCardModel(
                        about = item.about,
                        city = item.city,
                        country = item.country,
                        cuisines = item.cuisines,
                        distance = item.distance,
                        features = item.features,
                        isPureVeg = item.isPureVeg,
                        meals = item.meals,
                        name = item.name,
                        postalCode = item.postalCode,
                        priceRange = item.priceRange,
                        rating = item.rating,
                        restaurantId = item.restaurantId,
                        schedule = item.schedule,
                        streetAddress = item.streetAddress,
                        imageURL = item.imageURL
                    )

                }

                _restaurants.emit(restaurantListingCards)
                _isLoading.value = false
            } catch (e: Exception) {
//               handle error
            }

        }
    }

    private fun getCategories() {

        viewModelScope.launch {

            val categoryList = repository.getCategories()

            _categories.emit(categoryList)


        }


    }

}