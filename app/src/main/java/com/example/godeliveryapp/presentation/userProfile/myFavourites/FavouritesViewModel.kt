package com.example.godeliveryapp.presentation.userProfile.myFavourites

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
class FavouritesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _favourites = MutableStateFlow<List<RestaurantListingCardModel>?>(emptyList())
    val favourites: Flow<List<RestaurantListingCardModel>?> get() = _favourites

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    init {
        getFavourites()
    }

    private fun getFavourites() {

        viewModelScope.launch {
            try {
                _isLoading.value = true
                val results = repository.getFavourites()
                _favourites.emit(results?.distinctBy { it.restaurantId })
                _isLoading.value = false
            } catch (e: Exception) {
                Log.d("Error", "getFavourites: $e")
            }
        }

    }

    fun addToFavourites(restaurantId: Int) {
        viewModelScope.launch {
            try {
                repository.addToFavourite(restaurantId)
            } catch (e: Exception) {
                Log.d("Error", "addToFavourites: $e")
            }
        }
    }

}