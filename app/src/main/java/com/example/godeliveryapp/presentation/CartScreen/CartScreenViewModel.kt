package com.example.godeliveryapp.presentation.CartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.CartOrderItemDto
import com.example.godeliveryapp.domain.model.CartOrderItemModel
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    //    collection of individual card of _cartItems, or _cartItemsCard
    private val _cartItems = MutableStateFlow<List<CartOrderItemModel>?>(listOf())
    val cartItems: Flow<List<CartOrderItemModel>?> get() = _cartItems

    private val _cartSubTotal = MutableStateFlow(0.0)
    val cartSubTotal: Flow<Double> get() = _cartSubTotal

    init {
        getItems()
    }

    private fun getItems() {

        viewModelScope.launch {
            //list of cartItems
            val cartItems = repository.getCartItems(1)

            val cartItemsCardList = cartItems?.map { item ->
                CartOrderItemModel(
                    cartId = item.cartId,
                    price = item.menuItem.price,
                    itemName = item.menuItem.itemName,
                    quantity = item.quantity,
                    itemId = item.menuItem.itemId
                )
            }

            _cartItems.emit(cartItemsCardList)
            calculateCartValue()

        }

    }

    private fun calculateCartValue() {

        val items = _cartItems.value ?: emptyList()
        val sum = items.sumOf { (it.quantity * it.price) }
        _cartSubTotal.value = sum

    }

    fun updateCartItem(cartItem: CartOrderItemDto) {

        viewModelScope.launch {

            repository.updateCartItem(cartItem)
            getItems()

        }

    }

    fun createItem(cartItem: CartOrderItemDto) {

        viewModelScope.launch {
            repository.addCartItem(cartItem)
            getItems()
        }
    }

    fun deleteCartItem(itemId: Int) {
        viewModelScope.launch {
            repository.deleteCartItem(itemId)
            getItems()
        }
    }


}