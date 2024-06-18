package com.example.godeliveryapp.presentation.CartScreen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CartCustomisationCardView
import com.example.godeliveryapp.presentation.common.CartItemCardView
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.zomatoclone.utils.Constants.DELIVERY_FEE
import com.example.zomatoclone.utils.Constants.PROMOCODE
import com.example.zomatoclone.utils.Constants.TAX
import java.math.RoundingMode
import java.text.DecimalFormat


@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cartItems = cartViewModel.cartItems.collectAsState(initial = listOf()).value
    val cartSubTotal = cartViewModel.cartSubTotal.collectAsState(initial = 0.0).value
    val cartTotal = (cartSubTotal - PROMOCODE) + DELIVERY_FEE + TAX

    val textFieldValue = remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = NormalPadding, top = NormalPadding, end = NormalPadding),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigateUp() }
                        .background(color = Color.White, shape = CircleShape)
                        .size(42.dp),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.scale(1f)
                    )
                }

                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "Cart",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
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


                Spacer(
                    modifier = Modifier
                        .padding(top = MediumPadding2, bottom = MediumPadding2)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.lightGray))
                )

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

            items(cartItems?.size ?: 0) {
                //List of Items in the Cart
                    index ->
                val cartItemCardIndex = cartItems?.get(index)
                if (cartItemCardIndex != null) {
                    CartItemCardView(
                        cartItemModel = cartItemCardIndex,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {

                Spacer(modifier = Modifier.height(MediumPadding2))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = textFieldValue.value,
                    onValueChange = { textFieldValue.value = it },
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
                    }
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
                            .height(screenHeight.div(10))
                            .width(screenWidth.div(3))
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.Transparent
                            )
                            .border(
                                BorderStroke(
                                    width = (2.dp),
                                    color = colorResource(id = R.color.black)
                                ),
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
                                    text = "Standard",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                    color = colorResource(
                                        id = R.color.black
                                    ),
                                    maxLines = 2,
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(
                                    text = "25-30 Mins",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                    color = Color.LightGray
                                )
                            }

                        }

                    }

                    Spacer(modifier = Modifier.width(ExtraSmallPadding3))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(screenHeight.div(10))
                            .width(screenWidth.div(2))
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.Transparent
                            )
                            .border(
                                BorderStroke(
                                    width = (2.dp),
                                    color = colorResource(id = R.color.lightGray)
                                ),
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
                                imageVector = Icons.Rounded.CalendarMonth,
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
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                    color = colorResource(
                                        id = R.color.black
                                    ),
                                    maxLines = 2,
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(
                                    text = "Select Time",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                    color = Color.LightGray
                                )
                            }

                        }

                    }
                }

                Spacer(modifier = Modifier.height(MediumPadding2))


                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.lightGray))
                )

                Spacer(modifier = Modifier.height(MediumPadding2))

                CartCustomisationCardView(
                    title = "Delivery Options",
                    description = "Standard Delivery",
                    imageVector = Icons.Outlined.Layers,
                    showArrow = true
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MediumPadding2, bottom = MediumPadding2)
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.lightGray))
                )
                CartCustomisationCardView(
                    title = "Promo code Applied",
                    description = "₹85 coupon savings",
                    imageVector = Icons.Outlined.Discount,
                    showArrow = true
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MediumPadding2, bottom = MediumPadding2)
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.lightGray))
                )

                Text(
                    text = "Bill Details",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                CartPaymentDetailsCard(cartSubTotal = cartTotalRoundOff(cartSubTotal))

                Spacer(modifier = Modifier.height(MediumPadding1))

            }
            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = cartTotalRoundOff(cartTotal),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = colorResource(id = R.color.black)
                            )
                        }

                        Spacer(modifier = Modifier.height(NormalPadding))
                        OutlinedButton(
                            onClick = {
                                val deliveryInstructions = textFieldValue.value
                                if (cartItems != null) {
                                    cartViewModel.placeOrder(
                                        totalAmount = cartTotal,
                                        deliveryInstructions = deliveryInstructions,
                                        items = cartItems
                                    )
                                }

                                navController.navigate(Route.OrderScreen.route)
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

                        Spacer(modifier = Modifier.height(NormalPadding))
                    }
                }

            }

        }
    }


}

private fun cartTotalRoundOff(cartTotal: Double): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(cartTotal)
}

@Composable
fun CartPaymentDetailsCard(modifier: Modifier = Modifier, cartSubTotal: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Subtotal",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.gray)
                )

                Text(
                    text = cartSubTotal,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.black)
                )


            }

            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Promocode",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.gray)
                )

                Text(
                    text = "- ₹ $PROMOCODE",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.secondaryColor)
                )

            }
            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding3))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Delivery Fee",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.gray)
                )

                Text(
                    text = "₹ $DELIVERY_FEE",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.black)
                )


            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding3))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Tax & other fees",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.gray)
                )

                Text(
                    text = "₹ $TAX",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.black)
                )


            }


        }
    }


}
