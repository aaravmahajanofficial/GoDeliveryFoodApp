package com.example.godeliveryapp.presentation.CartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.model.CartItemCardModel
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    //    collection of individual card of _cartItems, or _cartItemsCard
    private val _cartItems = MutableStateFlow<List<CartItemCardModel>?>(listOf())
    val cartItems: Flow<List<CartItemCardModel>?> get() = _cartItems

    init {
        getItems()
    }

    private fun getItems() {

        viewModelScope.launch {
            //list of cartItems
            val cartItems = repository.getCartItems(1)

            val cartItemsCardList = cartItems?.map { item ->
                CartItemCardModel(
                    price = item.cartItem.price,
                    itemName = item.cartItem.itemName,
                    quantity = item.quantity
                )
            }

            _cartItems.value = cartItemsCardList

        }


    }


}