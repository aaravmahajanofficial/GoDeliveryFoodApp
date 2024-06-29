package com.example.godeliveryapp.presentation.homeScreen.foodCategoryScreen

import android.util.Log
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

    private val _categoryRestaurants =
        MutableStateFlow<List<RestaurantListingCardModel>?>(emptyList())

    private val _filteredList =
        MutableStateFlow<List<RestaurantListingCardModel?>?>(emptyList())
    val filteredList: Flow<List<RestaurantListingCardModel?>?> get() = _filteredList


    private val _isPureVeg = MutableStateFlow(false)
    val isPureVeg: Flow<Boolean> get() = _isPureVeg

    private val _rating = MutableStateFlow(0.0)
    val rating: Flow<Double> get() = _rating

    private val _isNonVeg = MutableStateFlow(false)
    val isNonVeg: Flow<Boolean> get() = _isNonVeg

    private val _takeOut = MutableStateFlow(false)
    val takeOut: Flow<Boolean> get() = _takeOut

    private val _priceRange = MutableStateFlow("")
    val priceRange: Flow<String> get() = _priceRange

    fun setRatingState(rate: Double) {
        _rating.value = rate
    }

    fun setPriceRange(priceRange: String) {
        _priceRange.value = priceRange
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


    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    fun getRestaurantByCategory(categoryId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val results = repository.getRestaurantsByCategory(categoryId)
                _categoryRestaurants.emit(results.sortedByDescending { it.rating })
                _filteredList.emit(results.sortedByDescending { it.rating })
            } catch (e: Exception) {
                Log.d("CategoryScreenViewModel", "results: ${e.message}")
            } finally {
                _isLoading.emit(false)
            }
        }
    }

    fun applyFilters() {

        viewModelScope.launch {

            val applyFilterList = _categoryRestaurants.value?.filter { restaurant ->

                val isPureVegMatch = !_isPureVeg.value || restaurant.isPureVeg
                val isRatingMatch =
                    _rating.value == 0.0 || restaurant.rating >= _rating.value.toString()
                val isTakeOutMatch = !_takeOut.value || restaurant.features.contains("Takeout")
                val isNonVegMatch = !_isNonVeg.value || !restaurant.isPureVeg
                val isPriceMatch = _priceRange.value == "" || restaurant.priceRange.equals(
                    _priceRange.value,
                    ignoreCase = true
                )

                isPureVegMatch && isRatingMatch && isTakeOutMatch && isNonVegMatch && isPriceMatch
            }?.sortedByDescending { it.rating }

            _filteredList.emit(applyFilterList)

        }

    }

}