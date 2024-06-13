package com.example.godeliveryapp.data.repository

import android.util.Log
import com.example.godeliveryapp.R
import com.example.godeliveryapp.data.remote.RestaurantDto
import com.example.godeliveryapp.data.remote.RetrofitAPI
import com.example.godeliveryapp.data.remote.dataTransferObject.CartDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CartItemDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto
import com.example.godeliveryapp.data.remote.dataTransferObject.UserDto
import com.example.godeliveryapp.domain.model.APIMODEL.Item
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemModel
import com.example.godeliveryapp.utils.SharedPreferences
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class RepositoryImplementation(
    private val postgrest: Postgrest,
    private val retrofitAPI: RetrofitAPI,
    private val sharedPreferences: SharedPreferences
) :

    Repository {
    override suspend fun getRestaurants(): List<RestaurantDto> {

        return withContext(Dispatchers.IO) {

            val restaurants =
                postgrest.from("Restaurants").select().decodeList<RestaurantDto>()

            restaurants
        }
    }

    override suspend fun getCartItems(): List<CartItemModel> {
        return withContext(Dispatchers.IO) {

            val cartId = getOrCreateCart()
            val cartItems = postgrest.from("CartItems").select {
                filter { eq("cartId", cartId) }
            }.decodeList<CartItemDto>()

            //need to match the itemId to MenuItems Table to fetch the desired food item details from the MenuItems Table
            val addedItems: List<CartItemModel> = cartItems.map { cartItem ->

                //fetch the details of this itemId from Menu table
                val menuItem = postgrest.from("MenuItems").select {
                    filter { eq("itemId", cartItem.itemId) }
                }.decodeSingle<MenuItemsDto>()

                val menuItemModel = MenuItemModel(
                    itemId = menuItem.itemId,
                    itemName = menuItem.itemName,
                    itemPrice = menuItem.price,
                    itemDescription = menuItem.description,
                    itemImageId = R.drawable.restaurant1,
                    itemCategory = menuItem.itemCategory,
                    restaurantId = menuItem.restaurantId
                )

                CartItemModel(
                    menuItemModel = menuItemModel,
                    quantity = cartItem.quantity,
                )

            }

            addedItems.sortedByDescending { it.quantity }

        }
    }

    override suspend fun existsInCart(itemId: Int): CartItemModel? {
        return withContext(Dispatchers.IO) {

            val existingItem = postgrest.from("CartItems").select {
                filter {
                    eq("itemId", itemId)
                }
            }.decodeSingleOrNull<CartItemModel>()

            existingItem

        }
    }

    override suspend fun deleteCartItem(cartItemModel: CartItemModel) {
        withContext(Dispatchers.IO) {
            val cartId = getOrCreateCart()
            postgrest.from("CartItems").delete {
                filter {
                    eq("itemId", cartItemModel.menuItemModel.itemId)
                    eq("cartId", cartId)
                }
            }
        }
    }

    override suspend fun upsertCartItem(cartItemModel: CartItemModel) {

        return withContext(Dispatchers.IO) {
            val cartId = getOrCreateCart()
            postgrest.from("CartItems").upsert(
                CartItemDto(
                    itemId = cartItemModel.menuItemModel.itemId,
                    quantity = cartItemModel.quantity,
                    cartId = cartId
                )
            )

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

    override suspend fun getRestaurantsByCategory(id: Int): List<RestaurantDto> {
        return withContext(Dispatchers.IO) {

            val menuItems =
                postgrest.from("MenuItems").select { filter { eq("categoryId", id) } }
                    .decodeList<MenuItemsDto>()

            //now filter this menuItems to get the restaurantId

            val filterRestaurant = menuItems.map { menuItem ->

                postgrest.from("Restaurants")
                    .select { filter { eq("restaurantId", menuItem.restaurantId) } }
                    .decodeSingle<RestaurantDto>()

            }

            //how to return a list of unique restaurant objects, without duplicates
            filterRestaurant.distinctBy { it.restaurantId }

        }
    }

    override suspend fun getOrCreateCart(): String {

        return withContext(Dispatchers.IO) {

            sharedPreferences.getUserData("CART_ID") ?: createNewCart().also {
                sharedPreferences.saveUserData("CART_ID", it)
            }
        }

    }

    override suspend fun createNewCart(): String {

        return withContext(Dispatchers.IO) {

            val cartItemDto = CartDto(
                cartId = UUID.randomUUID().toString(),
                userId = sharedPreferences.getUserData("USER_ID")!!
            )
            Log.d("CART_ID", cartItemDto.cartId)
            Log.d("USER_ID", cartItemDto.userId)

            postgrest.from("Cart").insert(cartItemDto)

            cartItemDto.cartId

        }

    }

    override suspend fun getNearbyLocations(coordinates: String): List<Item> {
        return withContext(Dispatchers.IO) {

            val apiResponse = retrofitAPI.getNearbyLocations(coordinates = coordinates)

            apiResponse.items

        }

    }

    override suspend fun insertUserData(): Boolean {

        return withContext(Dispatchers.IO) {

            val userDto = UserDto(
                userId = sharedPreferences.getUserData("USER_ID")!!,
                userName = sharedPreferences.getUserData("USER_NAME")!!,
                userEmail = sharedPreferences.getUserData("USER_EMAIL")!!,
                userAddress = "",
                userPhone = "",
                landmark = ""
            )

            try {
                postgrest.from("Users").insert(userDto)
                true

            } catch (e: Exception) {
                false
            }
        }


    }

    override suspend fun getUserData(): UserDto {
        return withContext(Dispatchers.IO) {

            val userDto = UserDto(
                userId = sharedPreferences.getUserData("USER_ID")!!,
                userName = sharedPreferences.getUserData("USER_NAME")!!,
                userEmail = sharedPreferences.getUserData("USER_EMAIL")!!,
                userAddress = "",
                userPhone = "",
                landmark = ""
            )

            userDto
        }
    }

}

