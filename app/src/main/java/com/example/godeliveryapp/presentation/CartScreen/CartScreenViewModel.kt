package com.example.godeliveryapp.presentation.CartScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.utils.Constants.DELIVERY_FEE
import com.example.godeliveryapp.utils.Constants.TAX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    // collection of individual card of _cartItems, or _cartItemsCard
    private val _cartItems = MutableStateFlow<List<CartItemModel>>(emptyList())
    val cartItems: StateFlow<List<CartItemModel>> get() = _cartItems.asStateFlow()

    private val _cartSubTotal = MutableStateFlow(0.0)
    val cartSubTotal: StateFlow<Double> get() = _cartSubTotal.asStateFlow()

    private val _cartTotal = MutableStateFlow(0.0)
    val cartTotal: StateFlow<Double> get() = _cartTotal.asStateFlow()

    private val _totalSavings = MutableStateFlow(0.0)
    val totalSavings: StateFlow<Double> get() = _totalSavings.asStateFlow()

    private val _totalItemsInCart = MutableStateFlow(0)
    val totalItemsInCart: StateFlow<Int> get() = _totalItemsInCart.asStateFlow()

    private val _promoCode = MutableStateFlow("")
    val promoCode = _promoCode.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()


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
            calculateTotal()
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

    fun setPromoCode(promoCode: String) {
        viewModelScope.launch {
            _promoCode.emit(promoCode)
            Log.d("PromoCode", promoCode)
            calculateTotal()
        }
    }

    private fun calculateTotal() {

        when (_promoCode.value) {

            "AXIOSBNK40" -> {
                _totalSavings.value = _cartSubTotal.value * 0.4
                _cartTotal.value =
                    (_cartSubTotal.value - _totalSavings.value) + DELIVERY_FEE + TAX
            }

            "HDFCBNK30" -> {
                _totalSavings.value = _cartSubTotal.value * 0.3
                _cartTotal.value =
                    (_cartSubTotal.value - _totalSavings.value) + DELIVERY_FEE + TAX
            }

            "GPAYCC120" -> {
                _totalSavings.value = 120.0
                _cartTotal.value = (_cartSubTotal.value - _totalSavings.value) + DELIVERY_FEE + TAX
            }

            else -> {
                _cartTotal.value = _cartSubTotal.value + DELIVERY_FEE + TAX
            }

        }

    }

}