package com.example.godeliveryapp.presentation.orderScreen

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.navigation.Route
import kotlin.random.Random
import kotlin.random.nextUInt

@Composable
fun OrderSuccessScreenView(modifier: Modifier = Modifier, navController: NavController) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(screenHeight / 16))
            Box(
                modifier = Modifier
                    .height(screenHeight / 3)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.order_success_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(screenHeight / 16))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Order placed!!",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.black
                    )
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = "Order ID",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = colorResource(id = R.color.black),
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding2))

                Text(
                    text = "#${Random.nextUInt()}",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.gray),
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Accept your order delivery with OTP",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                        color = colorResource(id = R.color.black),
                    )

                    Text(
                        text = "${Random.nextUInt(from = 1000u, until = 9999u)}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(modifier = Modifier.height(screenHeight / 10))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Spacer(modifier = Modifier.height(MediumPadding1))

                    TextButton(
                        onClick = { navController.navigate(Route.LoginPage.route) },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 14),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = colorResource(id = R.color.black),
                        )
                    ) {

                        Text(
                            text = "Track order",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = colorResource(id = R.color.white)
                        )

                    }

                    Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                    TextButton(
                        onClick = { navController.navigateUp() },
                        border = BorderStroke(0.5.dp, colorResource(id = R.color.lightGray)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 14),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = colorResource(id = R.color.lightGray),
                        )
                    ) {

                        Text(
                            text = "Close",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = colorResource(id = R.color.black)
                        )

                    }

                }
            }


        }


    }


}