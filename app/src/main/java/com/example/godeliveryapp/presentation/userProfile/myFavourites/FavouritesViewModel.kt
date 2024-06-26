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

    private val _favouritesList = MutableStateFlow<List<Int>>(emptyList())
    val favouritesList: Flow<List<Int>> get() = _favouritesList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    init {
        getFavourites()
    }

    private fun getFavourites() {

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val results = repository.getFavourites()
                _favourites.emit(results)
                val list = results?.map { it.restaurantId } ?: emptyList()
                _favouritesList.emit(list)
            } catch (e: Exception) {
                Log.d("Error", "getFavourites: $e")
            }
            finally {
                _isLoading.value = false
            }
        }

    }

    fun addToFavourites(restaurantId: Int) {
        viewModelScope.launch {
            try {
                val favouritesListCopy = _favouritesList.value.toMutableList()
                if (repository.addToFavourite(restaurantId)) {
                    favouritesListCopy.add(restaurantId)
                    _favouritesList.emit(favouritesListCopy)
                }
            } catch (e: Exception) {
                Log.d("Error", "addToFavourites: $e")
            }
        }
    }

    fun removeFavourite(restaurantId: Int) {

        viewModelScope.launch {

            try {
                val favouritesListCopy = _favouritesList.value.toMutableList()
                if (repository.removeFavourite(restaurantId)) {
                    favouritesListCopy.remove(restaurantId)
                    _favouritesList.emit(favouritesListCopy)
                }
            } catch (e: Exception) {
                Log.d("Error", "addToFavourites: $e")
            }


        }

    }

}