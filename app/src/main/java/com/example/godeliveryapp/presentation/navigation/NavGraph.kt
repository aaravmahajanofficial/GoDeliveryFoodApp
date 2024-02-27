package com.example.godeliveryapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.godeliveryapp.presentation.detailsScreen.DetailsScreen
import com.example.godeliveryapp.presentation.homeScreen.HomeScreen
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.zomatoclone.presentation.homeScreen.HomeScreenViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {

    //when app first starts, shows up this screen
    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {

        composable(route = Route.HomeScreen.route) {

            val viewModel: HomeScreenViewModel = hiltViewModel()
            val restaurants = viewModel.restaurants
            HomeScreen(navController = navController)

        }

        composable(route = Route.DetailsScreen.route) {
            DetailsScreen(navController = navController)
        }


    }


}