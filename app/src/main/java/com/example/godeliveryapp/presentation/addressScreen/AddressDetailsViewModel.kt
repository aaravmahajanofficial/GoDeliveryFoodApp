package com.example.godeliveryapp.presentation.addressScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _houseNumber = MutableStateFlow("")
    val houseNumber: StateFlow<String> get() = _houseNumber

    private val _floorNumber = MutableStateFlow("")
    val floorNumber: StateFlow<String> get() = _floorNumber

    private val _apartmentName = MutableStateFlow("")
    val apartmentName: StateFlow<String> get() = _apartmentName

    private val _howToReach = MutableStateFlow("")
    val howToReach: StateFlow<String> get() = _howToReach

    private val _contactNumber = MutableStateFlow("")
    val contactNumber: StateFlow<String> get() = _contactNumber

    private val _isFilled = MutableStateFlow(false)
    val isFilled: StateFlow<Boolean> get() = _isFilled

    init {
        viewModelScope.launch {
            combine(
                houseNumber,
                floorNumber,
                howToReach,
                contactNumber
            ) { fields ->
                fields.all { it.isNotBlank() }
            }.collect {
                _isFilled.value = it
            }

        }
    }

    fun setHouseNumber(houseNumber: String) {
        _houseNumber.value = houseNumber
    }

    fun setFloorNumber(floorNumber: String) {
        _floorNumber.value = floorNumber
    }

    fun setApartmentName(apartmentName: String) {
        _apartmentName.value = apartmentName
    }

    fun setHowToReach(howToReach: String) {
        _howToReach.value = howToReach
    }

    fun setContactNumber(contactNumber: String) {
        _contactNumber.value = contactNumber
    }

    fun upsertUserData() {
        viewModelScope.launch {
            try {
                val userDto = repository.getUserData()
                val updatedDto = userDto.copy(
                    userAddress = "${_houseNumber.value}, ${_floorNumber.value}, ${_apartmentName.value}",
                    userPhone = _contactNumber.value,
                    landmark = _howToReach.value
                )
                repository.upsertUserData(updatedDto)
            } catch (e: Exception) {
                Log.d("AddressDetailsViewModel ERROR", e.toString())
            }
        }
    }


}