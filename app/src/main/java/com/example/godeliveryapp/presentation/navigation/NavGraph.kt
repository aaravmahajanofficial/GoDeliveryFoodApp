package com.example.godeliveryapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.godeliveryapp.domain.model.RestaurantListingCard
import com.example.godeliveryapp.presentation.detailsScreen.DetailsScreen
import com.example.godeliveryapp.presentation.homeScreen.HomeScreen
import com.example.godeliveryapp.presentation.homeScreen.HomeScreenViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun SetupNavGraph(navController: NavHostController) {

    //when app first starts, shows up this screen
    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {

        composable(route = Route.HomeScreen.route) {

            val viewModel: HomeScreenViewModel = hiltViewModel()
            val restaurants = viewModel.restaurants
            HomeScreen(
                navController = navController,
                restaurants = restaurants,
                navigateToDetails = { restaurantListingCard ->
                    navigateToDetailsScreen(
                        navController,
                        restaurantListingCard = restaurantListingCard
                    )
                }
            )

        }

        composable(route = Route.DetailsScreen.route) {
            val serializedRestaurantListingCard =
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("restaurantListingCard")
            val restaurantListingCard =
                serializedRestaurantListingCard?.let { it1 ->
                    Json.decodeFromString<RestaurantListingCard>(
                        it1
                    )
                }
            if (restaurantListingCard != null) {
                DetailsScreen(
                    navigateUp = { navController.navigateUp() },
                    restaurantListingCard = restaurantListingCard
                )
            }
        }


    }


}

//this savedStateHandle is used in a fragment to access data, that was saved in some other fragment
//Fragment A: Displays a list of articles and allows the user to select one.
//Fragment B: Displays the details of the selected article.

//When the user navigates from Fragment A to Fragment B,
//Fragment A might save the selected article as an ListingCard object in its savedStateHandle using the key "listingCard".
//Then, in Fragment B, you could use the code snippet provided to retrieve the selected article and display its details.

private fun navigateToDetailsScreen(
    navController: NavHostController,
    restaurantListingCard: RestaurantListingCard
) {
    val serializedRestaurantListingCard = Json.encodeToString(restaurantListingCard)
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "restaurantListingCard",
        serializedRestaurantListingCard
    )
    navController.navigate(route = Route.DetailsScreen.route)

}