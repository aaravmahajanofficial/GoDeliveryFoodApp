package com.example.godeliveryapp.data.repository

import com.example.godeliveryapp.data.remote.RestaurantDto
import com.example.godeliveryapp.data.remote.RetrofitAPI
import com.example.godeliveryapp.data.remote.dataTransferObject.CartDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CartOrderItemDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto
import com.example.godeliveryapp.domain.model.APIMODEL.Item
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.repository.Repository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImplementation(
    private val postgrest: Postgrest,
    private val retrofitAPI: RetrofitAPI
) :
    Repository {

    override suspend fun getRestaurants(): List<RestaurantDto> {

        return withContext(Dispatchers.IO) {

            val restaurants =
                postgrest.from("Restaurants").select().decodeList<RestaurantDto>()

            restaurants
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

    override suspend fun existsInCart(itemId: Int): CartItemModel? {
        return withContext(Dispatchers.IO) {

            val existingItem = postgrest.from("OrderItems").select {
                filter {
                    eq("itemId", itemId)
                }
            }.decodeSingleOrNull<CartItemModel>()

            existingItem

        }
    }

    override suspend fun addCartItem(cartItem: CartOrderItemDto): Boolean {

        return try {

            withContext(Dispatchers.IO) {

                postgrest.from("OrderItems").insert(cartItem)
                true
            }

        } catch (e: java.lang.Exception) {
            throw e
        }

    }

    override suspend fun deleteCartItem(itemId: Int) {
        withContext(Dispatchers.IO) {
            postgrest.from("OrderItems").delete {
                filter {
                    eq("itemId", itemId)
                }
            }
        }
    }

    override suspend fun updateCartItem(cartItem: CartOrderItemDto) {

        withContext(Dispatchers.IO) {
            postgrest.from("OrderItems").update({

                set("quantity", cartItem.quantity)

            }) {
                filter {
                    eq("itemId", cartItem.itemId)
                }
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

    override suspend fun getCategories(): List<CategoryDto> {
        return withContext(Dispatchers.IO) {

            val categories =
                postgrest.from("Category").select().decodeList<CategoryDto>()

            categories
        }
    }

    override suspend fun getRestaurantsByCategory(id : Int): List<RestaurantDto> {
        return withContext(Dispatchers.IO) {

            val menuItems =
                postgrest.from("MenuItems").select { filter { eq("categoryId", id) } }
                    .decodeList<MenuItemsDto>()

            //now filter this menuItems to get the restaurantId

            val filterRestaurant = menuItems.map { menuItem ->

                postgrest.from("Restaurants").select { filter { eq("restaurantId", menuItem.restaurantId) } }
                    .decodeSingle<RestaurantDto>()

            }

            //how to return a list of unique restaurant objects, without duplicates
           filterRestaurant.distinctBy { it.restaurantId }

        }
    }

    override suspend fun getNearbyLocations(coordinates: String): List<Item> {
        return withContext(Dispatchers.IO) {

            val apiResponse = retrofitAPI.getNearbyLocations(coordinates = coordinates)

            apiResponse.items

        }

    }


}

