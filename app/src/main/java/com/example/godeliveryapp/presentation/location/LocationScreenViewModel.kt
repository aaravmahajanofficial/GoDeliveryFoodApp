package com.example.godeliveryapp.presentation.location

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.model.LocationCardModel
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    private val _nearbyLocationsCards = MutableStateFlow<List<LocationCardModel>?>(emptyList())

    private val _filteredList = MutableStateFlow<List<LocationCardModel>?>(emptyList())
    val filteredList: Flow<List<LocationCardModel>?> get() = _nearbyLocationsCards

    private val _locationModel = MutableStateFlow<LocationCardModel?>(null)
    val locationModel: StateFlow<LocationCardModel?> get() = _locationModel

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading


    fun getNearbyLocations(coordinates: String) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val items = repository.getNearbyLocations(coordinates = coordinates)

                val locationCards = items?.map { item ->

                    LocationCardModel(
                        title = item.title,
                        address = item.address
                    )

                }

                _nearbyLocationsCards.emit(locationCards)
                _filteredList.emit(locationCards)
                _locationModel.emit(locationCards?.get(0))
                sharedPreferences.saveUserData(
                    "userCurrentLocation",
                    _locationModel.value?.address?.district.plus(", ")
                        .plus(_locationModel.value?.address?.state)
                )
            } catch (e: Exception) {
                Log.d("LocationScreenViewModel", "getNearbyLocations: ${e.message}")
            } finally {
                _isLoading.emit(false)
            }

        }
    }

    fun selectLocation(locationCardModel: LocationCardModel) {
        viewModelScope.launch {
            _locationModel.emit(locationCardModel)
            sharedPreferences.saveUserData(
                "userCurrentLocation",
                _locationModel.value?.address?.district.plus(",")
                    .plus(_locationModel.value?.address?.state)
            )
        }
    }

    fun filterLocations(query: String) {

        val lowerCase = query.lowercase()

        viewModelScope.launch {

            val filteredList = _filteredList.value?.filter { locationCardModel ->
                locationCardModel.title.contains(lowerCase, ignoreCase = true) || locationCardModel.address.label?.contains(lowerCase, ignoreCase = true) ?: false
            }

            _filteredList.emit(filteredList ?: emptyList())

        }

    }


}