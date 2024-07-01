package com.example.godeliveryapp.presentation.orderScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderItemDto
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.model.MyOrderModel
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _orders = MutableStateFlow<List<MyOrderModel>?>(emptyList())
    val orders: Flow<List<MyOrderModel>?> get() = _orders

    private val _order = MutableStateFlow<MyOrderModel?>(null)
    val order: StateFlow<MyOrderModel?> get() = _order

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    private val _itemsOrdered = MutableStateFlow<List<CartItemModel>?>(emptyList())
    val itemsOrdered: StateFlow<List<CartItemModel>?> get() = _itemsOrdered

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

                _itemsOrdered.emit(items)

            } catch (e: Exception) {
                Log.d("ERROR WHILE PLACING ORDER : ", e.toString())
            }

        }

    }

    fun loadOrders() {

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val orders = repository.getOrders()
                _orders.emit(orders)
                if (_orders.value?.isNotEmpty() == true) {
                    _order.emit(_orders.value?.first())
                    delay(3500L)
                }
                _isLoading.value = false
            } catch (e: Exception) {
                Log.d("Error", "getOrders: $e")
            }

        }

    }


}