package com.example.godeliveryapp.domain.repository

import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderItemDto
import com.example.godeliveryapp.data.remote.dataTransferObject.UserDto
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.model.MyOrderModel
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.domain.model.removeAPI.Item
import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemModel

interface Repository {
    suspend fun getRestaurants(): List<RestaurantListingCardModel>?

    suspend fun getCartItems(): List<CartItemModel>?

    suspend fun deleteCartItem(cartItemModel: CartItemModel)

    suspend fun upsertCartItem(cartItemModel: CartItemModel)

    suspend fun getMenu(restaurantId: Int): List<MenuItemModel>

    suspend fun getNearbyLocations(coordinates: String): List<Item>?

    suspend fun getCategories(): List<CategoryDto>?

    suspend fun getRestaurantsByCategory(id: Int): List<RestaurantListingCardModel>

    suspend fun upsertUserData(userDto: UserDto): Boolean

    suspend fun getOrCreateCart(): String?

    suspend fun createNewCart(): String?

    suspend fun getUserData(): UserDto

    suspend fun getUserDataByEmail(userEmail: String): UserDto

    suspend fun placeOrder(orderDto: OrderDto, orderItems: List<OrderItemDto>)

    suspend fun clearCart()

    suspend fun getOrders(): List<MyOrderModel>

    suspend fun getFavourites(): List<RestaurantListingCardModel>?

    suspend fun addToFavourite(restaurantId: Int): Boolean

    suspend fun removeFavourite(restaurantId: Int): Boolean


}