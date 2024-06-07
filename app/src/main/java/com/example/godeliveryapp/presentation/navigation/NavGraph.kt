package com.example.godeliveryapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.CartScreen.CartScreen
import com.example.godeliveryapp.presentation.detailsScreen.DetailsScreen
import com.example.godeliveryapp.presentation.foodScreen.FoodScreenView
import com.example.godeliveryapp.presentation.foodScreen.foodCategoryScreen.CategoryScreenView
import com.example.godeliveryapp.presentation.homeScreen.HomeScreen
import com.example.godeliveryapp.presentation.onBoarding.components.LoginPageView
import com.example.godeliveryapp.presentation.onBoarding.components.SignUpView
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {

    //when app first starts, shows up this screen
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Route.HomeScreen.route) {

            HomeScreen(
                navController = navController,
                navigateToDetails = { restaurantListingCardModel ->
                    //sending this whole function
                    navigateToDetailsScreen(
                        navController,
                        restaurantListingCardModel = restaurantListingCardModel
                    )
                }
            )

        }

        composable(route = Route.FoodScreen.route) {

            FoodScreenView(
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

        composable(route = Route.LoginPage.route) {
            LoginPageView(navController = navController)
        }

        composable(route = Route.SignUpPage.route) {
            SignUpView(navController = navController)
        }

        composable(route = Route.CartScreen.route) {
            CartScreen(navController = navController)
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

                CategoryScreenView(navController = navController, categoryDto = categoryDto)

            }


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
                DetailsScreen(
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