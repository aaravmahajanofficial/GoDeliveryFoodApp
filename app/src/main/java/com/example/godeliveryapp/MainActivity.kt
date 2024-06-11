package com.example.godeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.godeliveryapp.domain.model.SupabaseAuthViewModel
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.godeliveryapp.presentation.navigation.SetupNavGraph
import com.example.godeliveryapp.presentation.onBoarding.components.UserState
import com.example.zomatoclone.ui.theme.GoDeliveryApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val viewModel by viewModels<MainViewModel>()

    private val authViewModel by viewModels<SupabaseAuthViewModel>()

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

                    val context = LocalContext.current

                    val userState =
                        authViewModel.userState.collectAsState(initial = UserState.Loading).value

                    LaunchedEffect(Unit) {

                        authViewModel.isUserLoggedIn(context = context)

                    }

                    when (userState) {
                        is UserState.Error -> {

                            SetupNavGraph(
                                navController = navController,
                                startDestination = Route.ProfileScreen.route
                            )
                        }

                        is UserState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {

                                CircularProgressIndicator(color = colorResource(id = R.color.primaryColor))

                            }
                        }

                        is UserState.Success -> {
                            SetupNavGraph(
                                navController = navController,
                                startDestination = Route.HomeScreen.route
                            )
                        }

                        UserState.Empty -> {

                        }
                    }


                }
            }
        }
    }
}