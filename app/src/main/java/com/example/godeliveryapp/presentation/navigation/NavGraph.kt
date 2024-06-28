package com.example.godeliveryapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.domain.model.MyOrderModel
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.CartScreen.CartScreenView
import com.example.godeliveryapp.presentation.detailsScreen.DetailsScreenView
import com.example.godeliveryapp.presentation.homeScreen.HomeScreenView
import com.example.godeliveryapp.presentation.homeScreen.foodCategoryScreen.CategoryScreenView
import com.example.godeliveryapp.presentation.homeScreen.searchScreen.SearchScreenView
import com.example.godeliveryapp.presentation.onBoarding.components.boardingScreens.OnBoardingScreenView
import com.example.godeliveryapp.presentation.onBoarding.components.login_signup.CreateNewPasswordPageView
import com.example.godeliveryapp.presentation.onBoarding.components.login_signup.ForgotPasswordPageView
import com.example.godeliveryapp.presentation.onBoarding.components.login_signup.LoginPageView
import com.example.godeliveryapp.presentation.onBoarding.components.login_signup.OtpPageView
import com.example.godeliveryapp.presentation.onBoarding.components.login_signup.SignUpView
import com.example.godeliveryapp.presentation.onBoarding.components.login_signup.WelcomeScreen
import com.example.godeliveryapp.presentation.orderScreen.OrderScreenView
import com.example.godeliveryapp.presentation.promoCodeScreen.PromoCodeScreenView
import com.example.godeliveryapp.presentation.userProfile.ProfileScreenView
import com.example.godeliveryapp.presentation.userProfile.myFavourites.MyFavouritesScreenView
import com.example.godeliveryapp.presentation.userProfile.myOrders.MyOrderDetailScreenView
import com.example.godeliveryapp.presentation.userProfile.myOrders.MyOrdersScreenView
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {

    //when app first starts, shows up this screen
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Route.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }

        composable(route = Route.ForgotPasswordScreen.route) {
            ForgotPasswordPageView(navController = navController)
        }

        composable(route = Route.CreateNewPasswordScreen.route) {
            CreateNewPasswordPageView(navController = navController)
        }
        composable(
            route = Route.OtpScreen.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) {
            OtpPageView(
                navController = navController,
                userEmail = it.arguments?.getString("email") ?: ""
            )
        }

        composable(
            route = Route.CartScreen.route,
            arguments = listOf(navArgument("coupon_code") { type = NavType.StringType })
        ) {
            CartScreenView(
                navController = navController,
                couponCodeValue = it.arguments?.getString("coupon_code")
            )
        }

        composable(route = Route.ProfileScreen.route) {
            ProfileScreenView(navController = navController)
        }

        composable(route = Route.MyOrdersScreen.route) {
            MyOrdersScreenView(
                navController = navController,
                navigateToMyOrderDetailScreen = { myOrderModel ->
                    navigateToMyOrderDetailScreen(
                        navController = navController,
                        myOrderModel = myOrderModel
                    )
                },

                )
        }

        composable(
            route = Route.MyOrderDetailScreen.route,
        ) {

            val serializedOrder =
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("myOrderDetail")

            val myOrderModel = serializedOrder?.let { it1 ->
                Json.decodeFromString<MyOrderModel>(
                    it1
                )
            }
            if (myOrderModel != null) {
                MyOrderDetailScreenView(navController = navController, myOrderModel = myOrderModel)
            }

        }

        composable(route = Route.MyFavouritesScreen.route) {
            MyFavouritesScreenView(
                navController = navController,
                navigateToDetails = { restaurantListingCardModel ->
                    navigateToDetailsScreen(
                        navController,
                        restaurantListingCardModel
                    )
                })
        }

        composable(route = Route.OnBoardingScreen.route) {
            OnBoardingScreenView(navController = navController)
        }

        composable(route = Route.SearchScreen.route) {
            SearchScreenView(navController = navController,
                navigateToCategory = { categoryDto ->
                    navigateToCategoryScreen(
                        navController = navController,
                        categoryDto = categoryDto
                    )
                },
                navigateToDetails = { restaurantModel ->
                    navigateToDetailsScreen(
                        navController,
                        restaurantModel
                    )
                }
            )
        }

        composable(route = Route.HomeScreen.route) {

            HomeScreenView(
                navController = navController,
                navigateToDetails = { restaurantListingCardModel ->
                    //sending this whole function
                    navigateToDetailsScreen(
                        navController,
                        restaurantListingCardModel = restaurantListingCardModel
                    )
                },
                navigateToCategory = { categoryIndex ->
                    navigateToCategoryScreen(
                        navController = navController,
                        categoryDto = categoryIndex
                    )
                }


            )

        }

        composable(route = Route.PromoCodeScreen.route) {
            PromoCodeScreenView(navController = navController)
        }

        composable(route = Route.LoginPage.route) {
            LoginPageView(navController = navController)
        }

        composable(route = Route.SignUpPage.route) {
            SignUpView(navController = navController)
        }

        composable(route = Route.CategoryScreen.route) {

            val serializedCategory =
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("category")

            val categoryDto = serializedCategory?.let { it1 ->
                Json.decodeFromString<CategoryDto>(
                    it1
                )
            }

            if (categoryDto != null) {

                CategoryScreenView(
                    navController = navController,
                    categoryDto = categoryDto,
                    navigateToDetails = { restaurantModel ->
                        navigateToDetailsScreen(
                            navController,
                            restaurantModel
                        )
                    })

            }


        }

        composable(route = Route.OrderScreen.route) {
            OrderScreenView(navController = navController)
        }



        composable(route = Route.DetailsScreen.route) {
            val serializedRestaurantListingCard =
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("restaurantListingCard")
            val restaurantListingCardModel =
                serializedRestaurantListingCard?.let { it1 ->
                    Json.decodeFromString<RestaurantListingCardModel>(
                        it1
                    )
                }
            if (restaurantListingCardModel != null) {
                DetailsScreenView(
//                    navigateUp = {
//                        navController.navigateUp()
//                    },
                    navController = navController,
                    restaurantListingCardModel = restaurantListingCardModel
                )
            }
        }


    }


}

//this savedStateHandle is used in a fragment to access data, that was saved in some other fragment
//Fragment A: Displays a list of listingCard and allows the user to select one.
//Fragment B: Displays the details of the selected listingCard.

//When the user navigates from Fragment A to Fragment B,
//Fragment A might save the selected listingCard as an ListingCard object in its savedStateHandle using the key "listingCard".
//Then, in Fragment B, you could use the code snippet provided to retrieve the selected article and display its details.


//simple way to understand this, is when we click a card in the Restaurant List, it stores that particular card in the string named "restaurantListingCard" and this is saved inside a stack.
//This value is then used in DetailsScreen Route, in which we extract this saved string(which contains the selected card in string format) from the stack
// and this card is then given to the details screen to show the details of the selected card
private fun navigateToDetailsScreen(
    navController: NavHostController,
    restaurantListingCardModel: RestaurantListingCardModel
) {
    val serializedRestaurantListingCard = Json.encodeToString(restaurantListingCardModel)
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "restaurantListingCard",
        serializedRestaurantListingCard
    )
    navController.navigate(route = Route.DetailsScreen.route)

}

private fun navigateToCategoryScreen(navController: NavHostController, categoryDto: CategoryDto) {
    val serializedCategory = Json.encodeToString(categoryDto)
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "category",
        serializedCategory
    )
    navController.navigate(route = Route.CategoryScreen.route)
}

private fun navigateToMyOrderDetailScreen(
    navController: NavHostController,
    myOrderModel: MyOrderModel
) {

    val serializedOrder = Json.encodeToString(myOrderModel)

    navController.currentBackStackEntry?.savedStateHandle?.set(
        "myOrderDetail",
        serializedOrder
    )

    navController.navigate(route = Route.MyOrderDetailScreen.route)
}