package com.example.godeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.godeliveryapp.domain.model.Restaurants
import com.example.godeliveryapp.presentation.detailsScreen.DetailsScreen
import com.example.zomatoclone.ui.theme.GoDeliveryApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            GoDeliveryApp {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

//                    navController = rememberNavController()
//                    SetupNavGraph(navController = navController)

                    DetailsScreen(navigateUp = { /*TODO*/ }, restaurantListingCard = Restaurants[0])


                }
            }
        }
    }
}

