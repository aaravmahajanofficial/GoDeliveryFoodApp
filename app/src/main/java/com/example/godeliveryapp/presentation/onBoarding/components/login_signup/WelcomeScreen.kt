package com.example.godeliveryapp.presentation.onBoarding.components.login_signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.navigation.Route

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, navController: NavController) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(screenHeight / 10))
            Box(
                modifier = Modifier
                    .height(screenHeight / 3)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome_screen_background),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(screenHeight / 12))


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .height(screenHeight / 20),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "GoDelivery",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),

                        color = colorResource(
                            id = R.color.black
                        )
                    )

                    Spacer(modifier = Modifier.height(ExtraSmallPadding1))


                }

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = "Everything you need is in\none place",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.black
                    )
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                Text(
                    text = "Say goodbye to waiting and hello to satisfaction, as delicious meals are just a tap away!",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.gray),
                )
            }

            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Spacer(modifier = Modifier.height(MediumPadding1))

                TextButton(
                    elevation = ButtonDefaults.elevatedButtonElevation(),
                    onClick = { navController.navigate(Route.LoginPage.route) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 14),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = colorResource(id = R.color.primaryColor),
                    )
                ) {

                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = colorResource(id = R.color.black)
                    )

                }

                Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                TextButton(
                    elevation = ButtonDefaults.elevatedButtonElevation(),
                    onClick = { navController.navigate(Route.SignUpPage.route) },
                    border = BorderStroke(0.5.dp, colorResource(id = R.color.black)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 14),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = colorResource(id = R.color.white),
                    )
                ) {

                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = colorResource(id = R.color.black)
                    )

                }

                Spacer(modifier = Modifier.padding(NormalPadding))


            }

        }


    }
}