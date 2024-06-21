package com.example.godeliveryapp.domain.repository

import com.example.godeliveryapp.data.remote.RestaurantDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderItemDto
import com.example.godeliveryapp.data.remote.dataTransferObject.UserDto
import com.example.godeliveryapp.domain.model.APIMODEL.Item
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.model.MyOrderModel
import com.example.godeliveryapp.presentation.orderScreen.OrderState

interface Repository {
    suspend fun getRestaurants(): List<RestaurantDto>?

    suspend fun getCartItems(): List<CartItemModel>?

//    suspend fun existsInCart(itemId: Int): CartItemModel?

    suspend fun deleteCartItem(cartItemModel: CartItemModel)

    suspend fun upsertCartItem(cartItemModel: CartItemModel)

    suspend fun getMenu(restaurantId: Int): List<MenuItemsDto>

    suspend fun getNearbyLocations(coordinates: String): List<Item>?

    suspend fun getCategories(): List<CategoryDto>?

    suspend fun getRestaurantsByCategory(id: Int): List<RestaurantDto>?

    suspend fun insertUserData(userDto: UserDto): Boolean

    suspend fun getOrCreateCart(): String

    suspend fun createNewCart(): String

    suspend fun getUserData(): UserDto

    suspend fun placeOrder(orderDto: OrderDto, orderItems: List<OrderItemDto>)

    suspend fun updateOrderStatus(newState: OrderState): Boolean
//    suspend fun getOrders(): List<OrderDto>?
//
    suspend fun getOrder(orderId: Int): OrderDto?
//
//    suspend fun cancelOrder(orderId: Int): Boolean

    suspend fun getOrderItems(): List<MyOrderModel>


}