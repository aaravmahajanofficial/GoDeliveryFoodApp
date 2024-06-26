package com.example.godeliveryapp.presentation.userProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.UserDto
import com.example.godeliveryapp.domain.model.MyOrderModel
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _userDetails = MutableStateFlow<UserDto?>(null)
    val userDetails: Flow<UserDto?> get() = _userDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            try {
                val userDto = repository.getUserData()
                _userDetails.value = userDto
            } catch (e: Exception) {
                Log.d("Error", "getUserData: $e")
            }
        }
    }


}