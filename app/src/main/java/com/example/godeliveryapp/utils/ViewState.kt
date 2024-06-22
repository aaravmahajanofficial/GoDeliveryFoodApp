package com.example.godeliveryapp.utils

sealed class ViewState {

    data object Loading : ViewState()
    data object Success : ViewState()
    data class Error(val string: String) : ViewState()

    data object Empty : ViewState()

}