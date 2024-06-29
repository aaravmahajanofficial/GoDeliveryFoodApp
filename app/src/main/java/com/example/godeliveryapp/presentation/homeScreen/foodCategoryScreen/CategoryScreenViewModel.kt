package com.example.godeliveryapp.presentation.homeScreen.foodCategoryScreen

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


    private val _isPureVeg = MutableStateFlow<Boolean>(false)
    val isPureVeg: Flow<Boolean> get() = _isPureVeg

    private val _ratingGreaterThanFour = MutableStateFlow<Boolean>(false)
    val ratingGreaterThanFour: Flow<Boolean> get() = _ratingGreaterThanFour

    private val _isNonVeg = MutableStateFlow<Boolean>(false)
    val isNonVeg: Flow<Boolean> get() = _isNonVeg

    private val _takeOut = MutableStateFlow<Boolean>(false)
    val takeOut: Flow<Boolean> get() = _takeOut

    fun setRatingGreaterThanFour() {
        _ratingGreaterThanFour.value = !_ratingGreaterThanFour.value
    }

    fun setTakeOut() {
        _takeOut.value = !_takeOut.value
    }

    fun setPureVeg() {
        _isPureVeg.value = !_isPureVeg.value
    }

    fun setNonVeg() {
        _isNonVeg.value = !_isNonVeg.value
    }


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
                val isPureVegMatch = !_isPureVeg.value || restaurant.isPureVeg
                val isRatingMatch =
                    !_ratingGreaterThanFour.value || restaurant.rating >= (4.0).toString()
                val isTakeOutMatch = !_takeOut.value || restaurant.features.contains("Takeout")
                val isNonVegMatch = !_isNonVeg.value || !restaurant.isPureVeg

                //only return those cards which satisfy all the three conditions
                isPureVegMatch && isRatingMatch && isTakeOutMatch && isNonVegMatch
            }

            if (applyFilterList != null) {
                _applyFilterRestaurant.emit(applyFilterList.sortedByDescending { it.rating })
            }


        }


    }


}