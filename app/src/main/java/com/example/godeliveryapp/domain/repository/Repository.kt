package com.example.godeliveryapp.domain.repository

import com.example.godeliveryapp.data.remote.RestaurantDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CartOrderItemDto
import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto
import com.example.godeliveryapp.domain.model.APIMODEL.Item
import com.example.godeliveryapp.domain.model.CartItemModel

interface Repository {

    suspend fun getRestaurants(): List<RestaurantDto>?
    suspend fun getCartItems(userId: Int): List<CartItemModel>?

    suspend fun existsInCart(itemId: Int): CartItemModel?

    suspend fun addCartItem(cartItem: CartOrderItemDto): Boolean

    suspend fun deleteCartItem(itemId: Int)

    suspend fun updateCartItem(cartItem: CartOrderItemDto)

    suspend fun getMenu(restaurantId: Int): List<MenuItemsDto>

    suspend fun getNearbyLocations(coordinates: String): List<Item>?

}