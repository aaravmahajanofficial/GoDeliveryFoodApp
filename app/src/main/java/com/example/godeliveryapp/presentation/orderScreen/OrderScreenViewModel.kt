package com.example.godeliveryapp.presentation.orderScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderItemDto
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    init {
        viewModelScope.launch {
            _isLoading.emit(true)
            delay(5000L)
            _isLoading.emit(false)

        }
    }

    fun placeOrder(totalAmount: Double, deliveryInstructions: String, items: List<CartItemModel>) {
        viewModelScope.launch {
            try {

                Log.d("ORDER ITEMS : ", _isLoading.value.toString())

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

            } catch (e: Exception) {
                Log.d("ERROR WHILE PLACING ORDER : ", e.toString())
            }

        }

    }
}