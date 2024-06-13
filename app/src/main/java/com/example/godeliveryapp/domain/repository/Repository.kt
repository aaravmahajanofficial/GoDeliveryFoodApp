package com.example.godeliveryapp.domain.repository

import com.example.godeliveryapp.data.remote.RestaurantDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto
import com.example.godeliveryapp.domain.model.APIMODEL.Item
import com.example.godeliveryapp.domain.model.CartItemModel

interface Repository {
    suspend fun getRestaurants(): List<RestaurantDto>?
    suspend fun getCartItems(): List<CartItemModel>?

    suspend fun existsInCart(itemId: Int): CartItemModel?

    suspend fun deleteCartItem(cartItemModel: CartItemModel)

    suspend fun upsertCartItem(cartItemModel: CartItemModel)

    suspend fun getMenu(restaurantId: Int): List<MenuItemsDto>

    suspend fun getNearbyLocations(coordinates: String): List<Item>?

    suspend fun getCategories(): List<CategoryDto>?

    suspend fun getRestaurantsByCategory(id: Int): List<RestaurantDto>?

    suspend fun getOrCreateCart(): String

    suspend fun createNewCart() : String

    suspend fun insertUserData() : Boolean

}