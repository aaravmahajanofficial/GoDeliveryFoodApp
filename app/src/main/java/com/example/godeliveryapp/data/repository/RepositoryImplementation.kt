package com.example.godeliveryapp.data.repository

import android.util.Log
import com.example.godeliveryapp.R
import com.example.godeliveryapp.data.remote.RestaurantDto
import com.example.godeliveryapp.data.remote.RetrofitAPI
import com.example.godeliveryapp.data.remote.dataTransferObject.CartDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CartItemDto
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.data.remote.dataTransferObject.FavouriteDto
import com.example.godeliveryapp.data.remote.dataTransferObject.MenuItemsDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderDto
import com.example.godeliveryapp.data.remote.dataTransferObject.OrderItemDto
import com.example.godeliveryapp.data.remote.dataTransferObject.UserDto
import com.example.godeliveryapp.domain.model.APIMODEL.Item
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.domain.model.MyOrderModel
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemModel
import com.example.godeliveryapp.presentation.orderScreen.OrderState
import com.example.godeliveryapp.utils.SharedPreferences
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import java.util.UUID
import kotlin.random.Random
import kotlin.random.nextUInt

class RepositoryImplementation(
    private val postgrest: Postgrest,
    private val retrofitAPI: RetrofitAPI,
    private val sharedPreferences: SharedPreferences
) :

    Repository {
    override suspend fun getRestaurants(): List<RestaurantListingCardModel> {

        return withContext(Dispatchers.IO) {

            val restaurantDTOs = postgrest.from("Restaurants").select().decodeList<RestaurantDto>()
            val restaurants = restaurantDTOs.map { restaurantDtoToModel(it) }

            restaurants
        }
    }

    override suspend fun getCartItems(): List<CartItemModel> {
        return withContext(Dispatchers.IO) {

            val cartId = sharedPreferences.getCartData("CART_ID")
            val cartItems = postgrest.from("CartItems").select {
                filter {
                    if (cartId != null) {
                        eq("cartId", cartId)
                    }
                }
            }.decodeList<CartItemDto>()

            //need to match the itemId to MenuItems Table to fetch the desired food item details from the MenuItems Table
            val addedItems: List<CartItemModel> = cartItems.map { cartItem ->

                //fetch the details of this itemId from Menu table
                val menuItem = postgrest.from("MenuItems").select {
                    filter { eq("itemId", cartItem.itemId) }
                }.decodeSingle<MenuItemsDto>()

                CartItemModel(
                    restaurantId = cartItem.restaurantId,
                    menuItemModel = menuItemDtoToModel(menuItem),
                    quantity = cartItem.quantity,
                )

            }

            addedItems.sortedByDescending { it.quantity }

        }
    }

    override suspend fun deleteCartItem(cartItemModel: CartItemModel) {
        withContext(Dispatchers.IO) {
            val cartId = getOrCreateCart()
            postgrest.from("CartItems").delete {
                filter {
                    eq("itemId", cartItemModel.menuItemModel.itemId)
                    eq("cartId", cartId!!)
                }
            }
        }
    }

    override suspend fun upsertCartItem(cartItemModel: CartItemModel) {

        return withContext(Dispatchers.IO) {
            try {
                val cartId = getOrCreateCart()
                postgrest.from("CartItems").upsert(
                    CartItemDto(
                        itemId = cartItemModel.menuItemModel.itemId,
                        quantity = cartItemModel.quantity,
                        cartId = cartId!!,
                        restaurantId = cartItemModel.menuItemModel.restaurantId
                    )
                )
            } catch (e: Exception) {
                Log.d("ERROR WHILE INSERTING INTO CART DETECTED : ", e.toString())

            }

        }

    }

    override suspend fun getMenu(restaurantId: Int): List<MenuItemModel> {

        return withContext(Dispatchers.IO) {

            val menuItemDTOs =
                postgrest.from("MenuItems").select { filter { eq("restaurantId", restaurantId) } }
                    .decodeList<MenuItemsDto>()

            val menuItems = menuItemDTOs.map { menuItemDtoToModel(it) }

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

    override suspend fun getOrCreateCart(): String? {

        return withContext(Dispatchers.IO) {

            sharedPreferences.getCartData("CART_ID") ?: createNewCart().also {
                sharedPreferences.saveCartData("CART_ID", it)
            }
        }

    }

    override suspend fun createNewCart(): String? {

        return withContext(Dispatchers.IO) {
            try {
                val userId = sharedPreferences.getUserData("USER_ID")!!

                val existingCart = postgrest.from("Cart")
                    .select { filter { eq("userId", userId) } }
                    .decodeSingleOrNull<CartDto>()
                if (existingCart != null) {
                    existingCart.cartId
                } else {
                    val cartId = UUID.randomUUID().toString()
                    postgrest.from("Cart").insert(
                        CartDto(
                            cartId = cartId,
                            userId = userId
                        )
                    )
                    cartId
                }
            } catch (e: Exception) {
                Log.d("ERROR WHILE CREATING CART", e.toString())
                null
            }

        }

    }

    override suspend fun getNearbyLocations(coordinates: String): List<Item> {
        return withContext(Dispatchers.IO) {

            val apiResponse = retrofitAPI.getNearbyLocations(coordinates = coordinates)

            apiResponse.items

        }

    }

    override suspend fun insertUserData(userDto: UserDto): Boolean {

        return withContext(Dispatchers.IO) {

            try {
                postgrest.from("Users").insert(userDto)
                true

            } catch (e: Exception) {
                Log.d("ERROR WHILE INSERTING USER DATA", e.toString())
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
                userPhone = "",
                userAddress = "",
                landmark = ""
            )
            userDto

        }
    }

    override suspend fun getUserDataByEmail(userEmail: String): UserDto {
        return withContext(Dispatchers.IO) {

            val userDto = postgrest.from("Users").select {
                filter {
                    eq("userEmail", userEmail)
                }
            }.decodeSingle<UserDto>()

            userDto

        }
    }

    override suspend fun placeOrder(orderDto: OrderDto, orderItems: List<OrderItemDto>) {

        val randomId = Random.nextUInt()

        return withContext(Dispatchers.IO) {

            try {
                val dto = orderDto.copy(
                    orderId = randomId,
                    createdAt = Clock.System.now(),
                    userId = sharedPreferences.getUserData("USER_ID")!!,
                    orderStatus = OrderState.PENDING,
                    verificationCode = (1000..9999).random(),
                    paymentMode = "COD"
                )

                postgrest.from("Orders").insert(dto)

                orderItems.forEach() {
                    postgrest.from("OrderItems").insert(it.copy(orderId = randomId))
                }
                sharedPreferences.saveOrderData("ORDER_ID", randomId)

                clearCart().also { sharedPreferences.clearCartPreferences() }


            } catch (e: Exception) {
                Log.d("ERROR", e.toString())

            }
        }

    }

    override suspend fun updateOrderStatus(newState: OrderState): Boolean {

        return withContext(Dispatchers.IO) {

            try {
                postgrest.from("Orders").update({
                    set("orderStatus", newState)
                }) {
                    filter {
                        eq("orderId", sharedPreferences.getOrderData("ORDER_ID"))
                    }
                }
                true
            } catch (e: Exception) {
                Log.d("ERROR WHILE UPDATING ORDER STATUS", e.toString())
                false
            }

        }

    }

    //    override suspend fun getOrders(): List<OrderDto>? {
//        TODO("Not yet implemented")
//    }
//
    override suspend fun getOrder(orderId: Int): OrderDto {

        return withContext(Dispatchers.IO) {

            val orderDto = postgrest.from("Orders").select {
                filter {
                    eq("orderId", orderId)
                }
            }.decodeSingle<OrderDto>()

            orderDto

        }

    }

//    override suspend fun cancelOrder(orderId: Int): Boolean {
//        TODO("Not yet implemented")
//    }

    override suspend fun getOrderItems(): List<MyOrderModel> {
        return withContext(Dispatchers.IO) {

            try {

                // fetch all the orders of the user
                val orders = postgrest.from("Orders")
                    .select { filter { eq("userId", sharedPreferences.getUserData("USER_ID")!!) } }
                    .decodeList<OrderDto>()

                val myOrders = mutableListOf<MyOrderModel>()

                // get the order items for the particular orderId
                orders.forEach { id ->

                    // get restaurant details for each order
                    val restaurant = postgrest.from("Restaurants").select {
                        filter { eq("restaurantId", id.restaurantId!!) }
                    }.decodeSingle<RestaurantDto>()

                    // get the items ordered in that order

                    val orderItems = postgrest.from("OrderItems").select {
                        filter { eq("orderId", id.orderId!!) }
                    }.decodeList<OrderItemDto>()

                    // now get the dto of each item in the order

                    val individualItems: List<MenuItemsDto> = orderItems.map { item ->
                        postgrest.from("MenuItems").select {
                            filter { eq("itemId", item.itemId) }
                        }.decodeSingle<MenuItemsDto>()
                    }

                    val myOrderModel = MyOrderModel(
                        orderId = id.orderId!!,
                        items = individualItems.map { menuItemDtoToModel(it) },
                        restaurantName = restaurant.name,
                        restaurantAddress = restaurant.streetAddress.split(",")[0].plus(", ")
                            .plus(restaurant.city),
                        restaurantImage = R.drawable.restaurant1,
                        createdAt = id.createdAt.toString(),
                        orderStatus = id.orderStatus.toString(),
                        orderTotal = id.totalAmount.toString(),
                        totalItems = orderItems.size,
                        paymentMode = id.paymentMode.toString()
                    )

                    myOrders.add(myOrderModel)

                }

                myOrders

            } catch (e: Exception) {
                Log.d("ERROR WHILE FETCHING ORDER ITEMS", e.toString())
                emptyList()
            }

        }
    }

    override suspend fun clearCart() {
        withContext(Dispatchers.IO) {
            val cartId = getOrCreateCart()
            postgrest.from("Cart").delete {
                filter {
                    eq("cartId", cartId!!)
                }
            }
        }

    }

    override suspend fun getFavourites(): List<RestaurantListingCardModel>? {

        return withContext(Dispatchers.IO) {

            try {

                val userId = sharedPreferences.getUserData("USER_ID")!!

                val favouriteDTOs = postgrest.from("Favourites").select {
                    filter { eq("userId", userId) }
                }.decodeList<FavouriteDto>()

                val restaurants = mutableListOf<RestaurantListingCardModel>()

                favouriteDTOs.forEach { index ->
                    val restaurantDto = postgrest.from("Restaurants")
                        .select { filter { eq("restaurantId", index.restaurantId) } }
                        .decodeSingleOrNull<RestaurantDto>()

                    restaurants.add(restaurantDtoToModel(restaurantDto!!))
                }

                restaurants


            } catch (e: Exception) {
                Log.d("ERROR WHILE FETCHING FAVOURITES", e.toString())
                null
            }


        }
    }

    override suspend fun addToFavourite(restaurantId: Int) : Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val userId = sharedPreferences.getUserData("USER_ID")!!

                val existingItem = postgrest.from("Favourites").select {
                    filter {
                        eq("userId", userId)
                        eq("restaurantId", restaurantId)
                    }
                }.decodeSingleOrNull<FavouriteDto>()

                if (existingItem == null) {
                    postgrest.from("Favourites").insert(
                        FavouriteDto(
                            userId = userId,
                            restaurantId = restaurantId
                        )
                    )
                } else {
                    null
                }

                true

            } catch (e: Exception) {
                Log.d("ERROR WHILE ADDING TO FAVOURITES Repository", e.toString())
                false
            }
        }
    }

    override suspend fun removeFavourite(restaurantId: Int) : Boolean {

        return withContext(Dispatchers.IO) {

            try {

                val userId = sharedPreferences.getUserData("USER_ID")!!

                postgrest.from("Favourites").delete {
                    filter {
                        eq("userId", userId)
                        eq("restaurantId", restaurantId)
                    }
                }

                true

            } catch (e: Exception) {
                Log.d("ERROR WHILE REMOVING FAVOURITE", e.toString())
                false

            }
        }

    }

}

fun menuItemDtoToModel(menuItem: MenuItemsDto): MenuItemModel {
    return MenuItemModel(
        itemId = menuItem.itemId,
        itemName = menuItem.itemName,
        itemPrice = menuItem.price,
        itemDescription = menuItem.description,
        itemImageId = R.drawable.restaurant1,
        itemCategory = menuItem.itemCategory,
        restaurantId = menuItem.restaurantId,
        isVeg = menuItem.isVeg
    )
}

fun restaurantDtoToModel(restaurantDto: RestaurantDto): RestaurantListingCardModel {
    return RestaurantListingCardModel(
        about = restaurantDto.about,
        city = restaurantDto.city,
        country = restaurantDto.country,
        cuisines = restaurantDto.cuisines,
        distance = restaurantDto.distance,
        features = restaurantDto.features,
        isPureVeg = restaurantDto.isPureVeg,
        meals = restaurantDto.meals,
        name = restaurantDto.name,
        postalCode = restaurantDto.postalCode,
        priceRange = restaurantDto.priceRange,
        rating = restaurantDto.rating,
        restaurantId = restaurantDto.restaurantId,
        schedule = restaurantDto.schedule,
        streetAddress = restaurantDto.streetAddress,
        imageURL = restaurantDto.imageURL
    )
}

