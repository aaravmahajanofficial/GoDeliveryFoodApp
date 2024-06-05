package com.example.godeliveryapp.presentation.foodScreen.foodCategoryScreen

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
class CategoryScreenViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _filterRestaurantList =
        MutableStateFlow<List<RestaurantListingCardModel>?>(listOf())
    val filterRestaurantList: Flow<List<RestaurantListingCardModel>?> get() = _filterRestaurantList

    private val _applyFilterRestaurant =
        MutableStateFlow<List<RestaurantListingCardModel?>?>(listOf())

    val appliedFilterRestaurants: Flow<List<RestaurantListingCardModel?>?> get() = _applyFilterRestaurant

    var isPureVeg = MutableStateFlow<Boolean>(false)

    val ratingGreaterThanFour = MutableStateFlow<Boolean>(false)

    val takeOut = MutableStateFlow<Boolean>(false)


    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    fun filterRestaurants(categoryId: Int) {

        _isLoading.value = true

        viewModelScope.launch {
            try {
                val results = repository.getRestaurantsByCategory(categoryId)

                val restaurants = results?.map { item ->

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

                if (restaurants != null) {
                    _filterRestaurantList.emit(restaurants.sortedByDescending { it.rating })
                }
            } finally {

                _isLoading.emit(false)
            }


        }

    }

    fun applyFilters() {

        viewModelScope.launch {

            val applyFilterList = _filterRestaurantList.value?.filter { restaurant ->
                val isPureVegMatch = !isPureVeg.value || restaurant.isPureVeg
                val isRatingMatch =
                    !ratingGreaterThanFour.value || restaurant.rating >= (4.0).toString()
                val isTakeOutMatch = !takeOut.value || restaurant.features.contains("Takeout")

                //only return those cards which satisfy all the three conditions
                isPureVegMatch && isRatingMatch && isTakeOutMatch
            }

            if (applyFilterList != null) {
                _applyFilterRestaurant.emit(applyFilterList.sortedByDescending { it.rating })
            }


        }


    }


}