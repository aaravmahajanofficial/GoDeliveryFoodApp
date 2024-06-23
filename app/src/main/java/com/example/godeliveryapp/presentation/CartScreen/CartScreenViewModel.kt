package com.example.godeliveryapp.presentation.CartScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderItemDto
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.presentation.orderScreen.OrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // collection of individual card of _cartItems, or _cartItemsCard
    private val _cartItems = MutableStateFlow<List<CartItemModel>>(emptyList())
    val cartItems: Flow<List<CartItemModel>> get() = _cartItems

    private val _cartSubTotal = MutableStateFlow(0.0)
    val cartSubTotal: Flow<Double> get() = _cartSubTotal

    private val _totalItemsInCart = MutableStateFlow(0)
    val totalItemsInCart: Flow<Int> get() = _totalItemsInCart

    private val _orderState = MutableStateFlow<OrderState>(OrderState.EMPTY)
    val orderState: StateFlow<OrderState> get() = _orderState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading


    init {
        getItems()
    }

    fun getItems() {

        viewModelScope.launch {

            _isLoading.value = true
            //list of cartItems
            val cartItems = repository.getCartItems()
            _cartItems.emit(cartItems ?: emptyList())
            if (cartItems != null) {
                _totalItemsInCart.emit(cartItems.size)
            }
            calculateCartValue()
            _isLoading.value = false
        }

    }

    private fun calculateCartValue() {

        val items = _cartItems.value
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
                _orderState.emit(OrderState.PENDING)

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

                repository.updateOrderStatus(OrderState.CONFIRMED)

                _orderState.emit(OrderState.CONFIRMED)

                _cartItems.value = emptyList()

            } catch (e: Exception) {
                Log.d("ERROR WHILE PLACING ORDER : ", e.toString())
            }

        }

    }

    fun resetOrderState() {
        _orderState.value = OrderState.EMPTY
    }


}