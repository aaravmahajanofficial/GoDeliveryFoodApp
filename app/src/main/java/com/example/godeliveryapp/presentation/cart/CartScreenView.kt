package com.example.godeliveryapp.presentation.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CartCustomisationCardView
import com.example.godeliveryapp.presentation.common.CartItemCardView
import com.example.godeliveryapp.presentation.common.CustomBackArrowButton
import com.example.godeliveryapp.presentation.common.CustomLineBreak
import com.example.godeliveryapp.presentation.common.PaymentDetailsCard
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.godeliveryapp.presentation.orderScreen.OrderScreenViewModel


@Composable
fun CartScreenView(
    modifier: Modifier = Modifier,
    couponCodeValue: String,
    cartViewModel: CartViewModel = hiltViewModel(),
    orderScreenViewModel: OrderScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())
    val cartSubTotal by cartViewModel.cartSubTotal.collectAsState(initial = 0.0)
    val cartTotal by cartViewModel.cartTotal.collectAsState(initial = 0.0)
    val totalSavings by cartViewModel.totalSavings.collectAsState(initial = 0.0)
    val isLoading by cartViewModel.isLoading.collectAsState(initial = false)

    var openDialog by remember {
        mutableStateOf(false)
    }

    var deliveryInstructionsValue by remember {
        mutableStateOf("")
    }

    var textFieldValue by remember {
        mutableStateOf("")
    }

    var standardDelivery by remember {
        mutableStateOf(true)
    }

    var scheduleDelivery by remember {
        mutableStateOf(false)
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colorResource(id = R.color.primaryColor))
        }

    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = NormalPadding, top = NormalPadding, end = NormalPadding),
                horizontalAlignment = Alignment.Start
            ) {
                if (cartItems.isEmpty()) {

                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {

                                CustomBackArrowButton(navController = navController)

                                Spacer(modifier = Modifier.height(screenHeight / 8))

                                Box(
                                    modifier = Modifier
                                        .height(screenHeight / 3)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.empty_cart_logo),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                Spacer(modifier = Modifier.height(screenHeight / 14))

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = "Your cart is empty",
                                        color = colorResource(id = R.color.black),
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        textAlign = TextAlign.Justify
                                    )

                                    Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                                    Box(modifier = Modifier.width(screenWidth / 2)) {
                                        Text(
                                            text = "Looks like you haven't made your choice yet...",
                                            color = colorResource(id = R.color.black),
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontWeight = FontWeight.Normal
                                            ),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(screenHeight / 8))

                            }
                        }
                    }

                } else {
                    item {
                        CustomBackArrowButton(navController = navController)

                        Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                        Text(
                            text = "Cart",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold)
                        )

                        Spacer(modifier = Modifier.height(MediumPadding1))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.location_on_fill0_wght200_grad0_opsz24),
                                contentDescription = null,
                                modifier = Modifier.scale(1.5f),
                            )

                            Spacer(modifier = Modifier.width(ExtraSmallPadding3))

                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {

                                Text(
                                    text = "Delivery Address",
                                    color = colorResource(id = R.color.black),
                                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "562, First Floor, Sector 6, Panchkula, Haryana",
                                    color = Color.LightGray,
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                                contentDescription = null,
                                tint = colorResource(id = R.color.gray),
                                modifier = Modifier.scale(0.8f)
                            )

                        }


                        CustomLineBreak()

                        Row(
                            modifier = Modifier

                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Items in your cart",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = null,
                                    tint = colorResource(
                                        id = R.color.secondaryColor
                                    ), modifier = Modifier.scale(0.6f)
                                )
                                Text(
                                    text = "Add more",
                                    modifier = Modifier.clickable { navController.navigateUp() },
                                    color = colorResource(
                                        id = R.color.secondaryColor
                                    ),
                                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                                )

                            }

                        }
                        Spacer(modifier = Modifier.height(MediumPadding1))
                    }

                    items(cartItems.size) {
                        //List of Items in the Cart
                            index ->
                        val cartItemCardIndex = cartItems[index]
                        CartItemCardView(
                            cartItemModel = cartItemCardIndex,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {

                        Spacer(modifier = Modifier.height(MediumPadding2))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = textFieldValue,
                            onValueChange = { textFieldValue = it },
                            shape = RoundedCornerShape(5.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = colorResource(id = R.color.lightGray),
                                unfocusedBorderColor = colorResource(id = R.color.lightGray),
                                focusedContainerColor = colorResource(id = R.color.lightGray),
                                unfocusedContainerColor = colorResource(id = R.color.lightGray),
                                focusedTextColor = colorResource(id = R.color.black),
                                unfocusedTextColor = colorResource(id = R.color.black)
                            ),
                            placeholder = {
                                Text(
                                    text = "Add cooking instructions",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                    color = colorResource(id = R.color.gray)
                                )
                            },
                            maxLines = 1,
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(MediumPadding2))

                    }

                    item {

                        Text(
                            text = "Delivery Time",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(MediumPadding1))

                        Row(
                            modifier = Modifier

                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        standardDelivery = true
                                        scheduleDelivery = false
                                    }
                                    .height(screenHeight.div(10))
                                    .width(screenWidth.div(3))
                                    .background(
                                        shape = RoundedCornerShape(8.dp),
                                        color = Color.Transparent
                                    )
                                    .border(
                                        if (!scheduleDelivery) {
                                            BorderStroke(
                                                width = (2.dp),
                                                color = colorResource(id = R.color.black)
                                            )
                                        } else {
                                            BorderStroke(
                                                width = (2.dp),
                                                color = colorResource(id = R.color.lightGray)
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp)
                                    ), contentAlignment = Alignment.Center
                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(ExtraSmallPadding3),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Icon(
                                        imageVector = Icons.Rounded.Bolt,
                                        contentDescription = null, modifier = Modifier.scale(1.2f),
                                        tint = colorResource(
                                            id = R.color.black
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                                    Column(
                                        modifier = Modifier.padding(end = NormalPadding),
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "Standard",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.SemiBold
                                            ),
                                            color = colorResource(
                                                id = R.color.black
                                            ),
                                            maxLines = 2,
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "25-30 Mins",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontWeight = FontWeight.Normal
                                            ),
                                            color = Color.LightGray
                                        )
                                    }

                                }

                            }

                            Spacer(modifier = Modifier.width(ExtraSmallPadding3))
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        scheduleDelivery = true
                                        standardDelivery = false
                                    }
                                    .height(screenHeight.div(10))
                                    .width(screenWidth.div(2))
                                    .background(
                                        shape = RoundedCornerShape(8.dp),
                                        color = Color.Transparent
                                    )
                                    .border(
                                        if (!standardDelivery) {
                                            BorderStroke(
                                                width = (2.dp),
                                                color = colorResource(id = R.color.black)
                                            )
                                        } else {
                                            BorderStroke(
                                                width = (2.dp),
                                                color = colorResource(id = R.color.lightGray)
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp)
                                    ), contentAlignment = Alignment.Center
                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(ExtraSmallPadding3),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {


                                    Icon(
                                        imageVector = Icons.Rounded.AccessTime,
                                        contentDescription = null, modifier = Modifier.scale(1f),
                                        tint = colorResource(
                                            id = R.color.black
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                                    Column(
                                        modifier = Modifier.padding(end = NormalPadding),
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "Schedule",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.SemiBold
                                            ),
                                            color = colorResource(
                                                id = R.color.black
                                            ),
                                            maxLines = 2,
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "Select Time",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontWeight = FontWeight.Normal
                                            ),
                                            color = Color.LightGray
                                        )
                                    }

                                }

                            }
                        }

                        CustomLineBreak()

                        CartCustomisationCardView(
                            title = "Delivery Instructions",
                            description = deliveryInstructionsValue.ifBlank { "Standard Delivery" },
                            imageVector = Icons.Outlined.Layers,
                            showArrow = true,
                            onClick = {
                                openDialog = true
                            }
                        )
                        CustomLineBreak()
                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(id = R.color.secondaryColor),
                                    fontWeight = FontWeight.Normal
                                )
                            ) {
                                append(couponCodeValue)
                            }
                            append(" ")
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(id = R.color.black),
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append("₹${totalSavings}")
                            }
                            append(" coupon savings")
                        }
                        if (couponCodeValue != "{coupon_code}") {
                            cartViewModel.setPromoCode(couponCodeValue)
                            CartCustomisationCardView(
                                title = "Promo code Applied",
                                annotatedString = annotatedString,
                                imageVector = Icons.Outlined.Discount,
                                showArrow = true,
                                onClick = {
                                    navController.navigate(Route.PromoCodeScreen.route)
                                }
                            )
                        } else {
                            CartCustomisationCardView(
                                title = "Apply Promo Code",
                                description = "Enter code to unlock your discount",
                                imageVector = Icons.Outlined.Discount,
                                showArrow = true,
                                onClick = {
                                    navController.navigate(Route.PromoCodeScreen.route)
                                }
                            )
                        }
                        CustomLineBreak()

                        Text(
                            text = "Bill Details",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(MediumPadding1))

                        PaymentDetailsCard(
                            subTotal = cartSubTotal,
                            total = cartTotal,
                            totalSavings = totalSavings
                        )

                        Spacer(modifier = Modifier.height(MediumPadding1))

                    }
                    item {

                        Box(
                            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                        ) {
                            OutlinedButton(
                                onClick = {
                                    navController.navigate(Route.OrderScreen.route)
                                    val deliveryInstructions = textFieldValue
                                    orderScreenViewModel.placeOrder(
                                        totalAmount = cartTotal,
                                        deliveryInstructions = deliveryInstructions,
                                        items = cartItems
                                    )

                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(
                                        id = R.color.black
                                    ),
                                ),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier
                                    .height(screenHeight / 14)
                                    .fillMaxWidth()
                            ) {

                                Text(
                                    "Place Order",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                                )

                            }
                        }

                        Spacer(modifier = Modifier.height(NormalPadding))

                    }
                }

            }

            if (openDialog) {
                AlertDialog(
                    shape = MaterialTheme.shapes.extraSmall,
                    containerColor = colorResource(id = R.color.white),
                    text = {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = deliveryInstructionsValue,
                            onValueChange = { deliveryInstructionsValue = it },
                            shape = RoundedCornerShape(5.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = colorResource(id = R.color.lightGray),
                                unfocusedBorderColor = colorResource(id = R.color.lightGray),
                                focusedContainerColor = colorResource(id = R.color.lightGray),
                                unfocusedContainerColor = colorResource(id = R.color.lightGray),
                                focusedTextColor = colorResource(id = R.color.black),
                                unfocusedTextColor = colorResource(id = R.color.black)
                            ),
                            placeholder = {
                                Text(
                                    text = "Add delivery instructions",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                    color = colorResource(id = R.color.gray)
                                )
                            },
                            maxLines = 1,
                            singleLine = true
                        )
                    },
                    onDismissRequest = { openDialog = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog = false

                            }) {

                            Text(
                                text = "Done",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                color = colorResource(id = R.color.black)
                            )

                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                            }) {

                            Text(
                                text = "Cancel",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                color = colorResource(id = R.color.black)
                            )

                        }
                    }
                )

            }

        }
    }


}


