package com.example.godeliveryapp.presentation.userProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.UserDto
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

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            try {
                val userDto = repository.getUserData()
                _userDetails.emit(userDto)
            } catch (e: Exception) {
                //handle error
            }
        }
    }


}