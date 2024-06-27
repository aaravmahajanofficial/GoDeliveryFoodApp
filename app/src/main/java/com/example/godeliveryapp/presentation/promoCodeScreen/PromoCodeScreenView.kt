package com.example.godeliveryapp.presentation.promoCodeScreen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.PromoCodeModel
import com.example.godeliveryapp.domain.model.codes
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomBackArrowButton
import com.example.godeliveryapp.presentation.common.CustomLineBreak
import com.example.godeliveryapp.presentation.navigation.Route


@Composable
fun PromoCodeScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    var textFieldValue by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        LazyColumn(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
                .fillMaxSize()
                .padding(start = NormalPadding, end = NormalPadding, top = NormalPadding),
            horizontalAlignment = Alignment.Start,
        ) {

            item {

                CustomBackArrowButton(navController = navController)


                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "Coupons",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Row(modifier = Modifier) {
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .weight(1f)
                            .height(55.dp),
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        shape = RoundedCornerShape(
                            topStart = 5.dp,
                            bottomStart = 5.dp
                        ),
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
                                text = "Type coupon code",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                color = colorResource(id = R.color.gray)
                            )
                        },
                        maxLines = 1,
                        singleLine = true
                    )

                    TextButton(
                        modifier = Modifier
                            .height(55.dp)
                            .weight(0.3f),
                        onClick = {
                            if (codes.any { it.couponCode == textFieldValue }) {
                                // Apply coupon code
                            } else {
                                Toast.makeText(
                                    context,
                                    "Invalid Coupon Code",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        shape = RoundedCornerShape(
                            topEnd = 5.dp,
                            bottomEnd = 5.dp
                        ),
                        colors = ButtonDefaults.textButtonColors(
                            colorResource(
                                id = R.color.black
                            )
                        ),
                    ) {

                        Text(
                            text = "Apply", textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.white),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )

                    }
                }

                Spacer(modifier = Modifier.height(MediumPadding2))

                Text(
                    modifier = Modifier,
                    text = "Available Coupons",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )

                Spacer(modifier = Modifier.height(MediumPadding2))

            }

            items(codes.size) { index ->
                AvailableCouponCard(
                    screenHeight = screenHeight,
                    promoCodeModel = codes[index],
                    navController = navController
                )
                CustomLineBreak()
            }

        }


    }


}

@Composable
fun AvailableCouponCard(
    modifier: Modifier = Modifier,
    screenHeight: Dp,
    navController: NavController,
    promoCodeModel: PromoCodeModel,
) {

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .height(screenHeight.div(14))
                        .width(screenHeight.div(14))
                        .background(
                            Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, colorResource(id = R.color.lightGray))
                        )
                        .clip(
                            RoundedCornerShape(12.dp)
                        ), contentAlignment = Alignment.Center
                ) {

                    Image(
                        modifier = Modifier.scale(0.6f),
                        painter = painterResource(id = promoCodeModel.couponImage),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )

                }

                Column(
                    modifier = Modifier
                        .padding(start = NormalPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(screenHeight / 32)
                                    .width(screenHeight / 7)
                                    .background(color = colorResource(id = R.color.lightGray))
                                    .border(
                                        shape = RoundedCornerShape(3.dp),
                                        border = BorderStroke(
                                            1.dp,
                                            colorResource(id = R.color.lightGray)
                                        )
                                    )
                                    .align(Alignment.Start), contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(ExtraSmallPadding1),
                                    text = promoCodeModel.couponCode,
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                                    color = colorResource(id = R.color.black)
                                )

                            }

                            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                            Text(
                                modifier = Modifier,
                                text = promoCodeModel.couponDescription,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = colorResource(
                                    id = R.color.black
                                ),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Box(modifier = Modifier
                            .clickable {
                                navController.navigate("cart_screen/${promoCodeModel.couponCode}") {
                                    popUpTo(Route.CartScreen.route) {
                                        inclusive = true
                                    }

                                }
                            }
                            .align(Alignment.CenterVertically)) {
                            Text(
                                text = "Apply",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                                color = colorResource(id = R.color.secondaryColor)
                            )

                        }
                    }
                }

            }
        }
    }


}