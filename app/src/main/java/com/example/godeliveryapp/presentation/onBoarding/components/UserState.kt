package com.example.godeliveryapp.presentation.onBoarding.components

sealed class UserState {

    data object Loading : UserState()
    data object Success : UserState()
    data class Error(val string: String) : UserState()

}