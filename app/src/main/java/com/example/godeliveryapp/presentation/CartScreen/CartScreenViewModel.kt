package com.example.godeliveryapp.presentation.CartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.CartDto
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartDto>?>(listOf())
    val cartItems: Flow<List<CartDto>?> get() = _cartItems

    init {

    }

    private fun getItems() {

        viewModelScope.launch {
            val cartItems = repository.getCartItems(1)




        }

    }


}