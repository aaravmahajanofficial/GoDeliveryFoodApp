package com.example.godeliveryapp.presentation.orderScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Summarize
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.LargePadding
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CartCustomisationCardView
import com.example.godeliveryapp.presentation.navigation.Route

@Composable
fun OrderScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    orderScreenViewModel: OrderScreenViewModel = hiltViewModel()
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val isLoading = orderScreenViewModel.isLoading.collectAsState(initial = false).value
    val order by orderScreenViewModel.order.collectAsState(initial = null)

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(NormalPadding),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(screenHeight / 16))

                CircularProgressIndicator(
                    modifier = Modifier
                        .size(screenHeight / 6)
                        .align(Alignment.CenterHorizontally),
                    strokeWidth = 10.dp,
                    color = colorResource(id = R.color.secondaryColor),
                    trackColor = colorResource(id = R.color.lightestGray)

                )

                Spacer(modifier = Modifier.height(screenHeight / 16))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Placing your order",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(
                            id = R.color.black
                        )
                    )

                    Spacer(modifier = Modifier.height(LargePadding))

                    CartCustomisationCardView(
                        title = "Delivery Address",
                        description = "HSR Layout, Bengaluru, Karnataka, India",
                        imageVector = Icons.Outlined.LocationOn,
                        showArrow = false
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = MediumPadding1, bottom = MediumPadding1)
                            .height(1.dp)
                            .background(color = colorResource(id = R.color.lightGray))
                    )

                    CartCustomisationCardView(
                        title = "Delivery Time",
                        description = "Standard Delivery: 25-30 Mins",
                        imageVector = Icons.Outlined.Timer,
                        showArrow = false
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = MediumPadding1, bottom = MediumPadding1)
                            .height(1.dp)
                            .background(color = colorResource(id = R.color.lightGray))
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Summarize,
                            contentDescription = null,
                            modifier = Modifier.scale(1.2f),
                            tint = colorResource(
                                id = R.color.black
                            )
                        )

                        Spacer(modifier = Modifier.width(ExtraSmallPadding3))

                        Text(
                            text = "Order summary",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                            color = colorResource(
                                id = R.color.black
                            ),
                            maxLines = 2,
                        )

                    }

                    Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .width(screenWidth / 2)
                                .padding(start = LargePadding)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "Lazeez Bhuna Murgh Chicken Dum Biryani",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                                color = colorResource(id = R.color.black),
                                overflow = TextOverflow.Visible
                            )
                        }

                        Text(
                            text = " x1",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Center,
                        )

                    }

                    Spacer(modifier = Modifier.height(screenHeight / 8))

                    TextButton(
                        onClick = { navController.navigate(Route.OrderSuccessScreen.route) },
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
                            text = "Cancel Order",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = colorResource(id = R.color.black)
                        )

                    }

                }


            }
        }
    } else {

        if (order != null) {
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
                            text = "#${order!!.orderId}",
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
                                text = order!!.verificationCode,
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
                                onClick = {
                                    navController.navigate(Route.HomeScreen.route) {
                                        popUpTo(Route.HomeScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                                border = BorderStroke(
                                    0.5.dp,
                                    colorResource(id = R.color.lightGray)
                                ),
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


    }


}