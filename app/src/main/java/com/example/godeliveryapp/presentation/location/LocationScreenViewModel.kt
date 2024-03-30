package com.example.godeliveryapp.presentation.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.model.LocationCardModel
import com.example.godeliveryapp.domain.repository.Repository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _nearbyLocationsCards = MutableStateFlow<List<LocationCardModel>?>(listOf())
    val nearbyLocationsCards: Flow<List<LocationCardModel>?> get() = _nearbyLocationsCards


    fun getNearbyLocations(coordinates: String) {
        viewModelScope.launch {
            val items = repository.getNearbyLocations(coordinates = coordinates)

            val locationCards = items?.map { item ->

                LocationCardModel(
                    title = item.title,
                    address = item.address
                )

            }

            _nearbyLocationsCards.emit(locationCards)

        }
    }


}