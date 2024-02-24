package com.example.zomatoclone.presentation.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomatoclone.domain.model.RestaurantItem
import com.example.zomatoclone.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _restaurants = MutableLiveData<List<RestaurantItem>>()
    val restaurants: LiveData<List<RestaurantItem>> get() = _restaurants

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            _restaurants.value = repository.getRestaurants()
        }
    }


}