package com.example.godeliveryapp.presentation.homeScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.model.RestaurantListingCard
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _restaurants = MutableLiveData<List<RestaurantListingCard>>()
    val restaurants: LiveData<List<RestaurantListingCard>> get() = _restaurants

    init {
        fetchRestaurants()

    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            _restaurants.value = repository.getRestaurants()
            Log.d("restaurants.value?.get(1)", "${restaurants.value}")
        }
    }


}