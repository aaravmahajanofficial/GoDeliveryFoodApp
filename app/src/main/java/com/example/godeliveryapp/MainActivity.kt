package com.example.godeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.godeliveryapp.presentation.navigation.SetupNavGraph
import com.example.zomatoclone.ui.theme.GoDeliveryApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }
        setContent {

            GoDeliveryApp {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)

//                    AddDetails()

//                    PizzaScreen()

                }
            }
        }
    }
}

