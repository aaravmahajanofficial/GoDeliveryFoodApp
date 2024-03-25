package com.example.godeliveryapp.data.repository

import RestaurantDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CartDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CartOrderItemDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CuisineDto
import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.model.RestaurantWithCuisines
import com.example.godeliveryapp.domain.repository.Repository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImplementation(private val postgrest: Postgrest) :
    Repository {

    override suspend fun getRestaurants(): List<RestaurantWithCuisines> {

        return withContext(Dispatchers.IO) {

            val restaurants =
                postgrest.from("Restaurants").select().decodeList<RestaurantDto>()

            val restaurantCuisines =
                postgrest.from("Cuisines").select().decodeList<CuisineDto>()

            val restaurantWithCuisines = restaurants.map { restaurant: RestaurantDto ->
                val cuisinesList: List<String> =
                    restaurantCuisines.filter { it.restaurantId == restaurant.restaurantId }
                        .map { it.name }
                RestaurantWithCuisines(restaurant, cuisinesList)
            }

            restaurantWithCuisines
        }
    }

    override suspend fun getCartItems(userId: Int): List<CartItemModel> {
        return withContext(Dispatchers.IO) {

            //get the cartId, of the user and then traverse the orderItems table, to get the cartItems of the user
            val cartDetails = postgrest.from("Cart").select {
                filter { eq("userId", userId) }
            }.decodeSingle<CartDto>()

            //create a list of orderItems, with cartId
            val cartOrderItems = postgrest.from("OrderItems").select {
                filter { eq("cartId", cartDetails.cartId) }
            }.decodeList<CartOrderItemDto>()

            //need to match the itemId to MenuItems Table to fetch the desired food item details from the MenuItems Table
            val cartItems: List<CartItemModel> = cartOrderItems.map { cartOrderItem ->

                //Got the cartOrderItemId
                //fetch the details of this itemId from Menu table
                val menuItem = postgrest.from("MenuItems").select {
                    filter { eq("itemId", cartOrderItem.itemId) }
                }.decodeSingle<MenuItemsDto>()

                CartItemModel(
                    cartId = cartDetails.cartId,
                    quantity = cartOrderItem.quantity,
                    menuItem = menuItem,
                )

            }

            cartItems

        }
    }

    override suspend fun addCartItem(cartItem: CartItemModel): Boolean {

        return try {

            withContext(Dispatchers.IO) {

                val cartOrderItemDto = CartOrderItemDto(
                    cartId = cartItem.cartId,
                    quantity = cartItem.quantity,
                    itemId = cartItem.menuItem.itemId
                )
                postgrest.from("OrderItems").insert(cartOrderItemDto)
                true
            }

        } catch (e: java.lang.Exception) {
            throw e
        }

    }

    override suspend fun deleteCartItem(cartItem: CartOrderItemDto) {
        withContext(Dispatchers.IO) {
            postgrest.from("OrderItems").delete {
                filter {
                    eq("itemId", cartItem.itemId)
                }
            }
        }
    }

    override suspend fun updateCartItem(cartItem: CartOrderItemDto) {

        withContext(Dispatchers.IO) {

            postgrest.from("OrderItems").update({
                set("quantity", cartItem.quantity)
            }) {
                filter { eq("itemId", cartItem.itemId) }
            }

        }

    }

    override suspend fun getMenu(restaurantId: Int): List<MenuItemsDto> {

        return withContext(Dispatchers.IO) {

            val menuItems =
                postgrest.from("MenuItems").select { filter { eq("restaurantId", restaurantId) } }
                    .decodeList<MenuItemsDto>()

            menuItems
        }

    }


}

