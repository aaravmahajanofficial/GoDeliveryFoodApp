package com.example.godeliveryapp.presentation.CartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderItemDto
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // collection of individual card of _cartItems, or _cartItemsCard
    private val _cartItems = MutableStateFlow<List<CartItemModel>>(listOf())
    val cartItems: Flow<List<CartItemModel>?> get() = _cartItems

    private val _orderItems = MutableStateFlow<List<OrderItemDto>>(listOf())
    val orderItems: Flow<List<OrderItemDto>> get() = _orderItems

    private val _cartSubTotal = MutableStateFlow(0.0)
    val cartSubTotal: Flow<Double> get() = _cartSubTotal

    private val _totalItemsInCart = MutableStateFlow(0)
    val totalItemsInCart: Flow<Int> get() = _totalItemsInCart

    init {
        getItems()
    }

    private fun getItems() {

        viewModelScope.launch {
            //list of cartItems
            val cartItems = repository.getCartItems()
            _cartItems.emit(cartItems!!)
            _orderItems.emit(cartItems.map {
                OrderItemDto(
                    itemId = it.menuItemModel.itemId,
                    quantity = it.quantity,
                    price = (it.menuItemModel.itemPrice * it.quantity)
                )
            })
            _totalItemsInCart.emit(cartItems.size)
            calculateCartValue()
        }

    }

    private fun calculateCartValue() {

        val items = _cartItems.value ?: emptyList()
        val sum = items.sumOf { (it.quantity * it.menuItemModel.itemPrice) }
        _cartSubTotal.value = sum

    }

    fun upsertCartItem(cartItem: CartItemModel) {
        viewModelScope.launch {
            repository.upsertCartItem(cartItem)
            getItems()
        }
    }

    fun deleteCartItem(cartItem: CartItemModel) {
        viewModelScope.launch {
            repository.deleteCartItem(cartItem)
            getItems()
        }
    }

    fun placeOrder(totalAmount: Double, deliveryInstructions: String, items: List<CartItemModel>) {

        viewModelScope.launch {

            try {
                val orderToPlace = OrderDto(
                    restaurantId = items.first().restaurantId,
                    totalAmount = totalAmount,
                    deliveryInstructions = deliveryInstructions,
                )

                repository.placeOrder(orderToPlace, items.map {
                    OrderItemDto(
                        itemId = it.menuItemModel.itemId,
                        quantity = it.quantity,
                        price = (it.menuItemModel.itemPrice * it.quantity)
                    )
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }


}