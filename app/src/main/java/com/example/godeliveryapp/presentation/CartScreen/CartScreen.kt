package com.example.godeliveryapp.presentation.CartScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CartDeliveryOptions
import com.example.godeliveryapp.presentation.common.CartItemCard
import com.example.godeliveryapp.presentation.common.CartPaymentDetailsCard


@Composable
fun CartScreen(modifier: Modifier = Modifier) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()

    ) {

        val (content, button) = createRefs()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(button.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.Start,
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = NormalPadding, start = NormalPadding, end = NormalPadding)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.scale(1.2f)

                    )
                }

                Box(modifier = Modifier.padding(start = NormalPadding, top = NormalPadding)) {
                    Text(
                        text = "Cart",
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.height(MediumPadding1))

                Row(
                    modifier = Modifier
                        .padding(
                            start = NormalPadding,
                            end = NormalPadding,

                            )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.location_on_fill0_wght200_grad0_opsz24),
                        contentDescription = null,
                        modifier = Modifier.scale(1.5f),
                    )

                    Spacer(modifier = Modifier.width(10.dp))

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
                        .fillMaxWidth()
                        .padding(
                            top = MediumPadding2,
                            start = NormalPadding,
                            end = NormalPadding,
                            bottom = MediumPadding2
                        )
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.lightGray))
                )

                Row(
                    modifier = Modifier
                        .padding(start = NormalPadding, end = NormalPadding)
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
                            color = colorResource(
                                id = R.color.secondaryColor
                            ),
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                        )

                    }
                }

                Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                CartItemCard()
                CartItemCard()

                Box(modifier = Modifier.padding(start = NormalPadding, top = NormalPadding)) {
                    Text(
                        text = "Delivery Time",
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.height(MediumPadding1))

                Row(
                    modifier = Modifier
                        .padding(start = NormalPadding, end = NormalPadding)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(75.dp)
                            .width(155.dp)
                            .background(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.Transparent
                            )
                            .border(
                                BorderStroke(
                                    width = (2.dp),
                                    color = colorResource(id = R.color.black)
                                ),
                                shape = RoundedCornerShape(12.dp)
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
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(75.dp)
                            .width(180.dp)
                            .background(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.Transparent
                            )
                            .border(
                                BorderStroke(
                                    width = (2.dp),
                                    color = colorResource(id = R.color.lightGray)
                                ),
                                shape = RoundedCornerShape(12.dp)
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


                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = MediumPadding2,
                            start = NormalPadding,
                            end = NormalPadding
                        )
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.lightGray))
                )

                CartDeliveryOptions()
                CartDeliveryOptions()
                CartDeliveryOptions()

                Box(modifier = Modifier.padding(start = NormalPadding, top = NormalPadding)) {
                    Text(
                        text = "Bill Details",
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }

                CartPaymentDetailsCard()


            }


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = NormalPadding,
                        end = NormalPadding
                    )
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier
                        .padding(top = ExtraSmallPadding3)
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
                        text = "₹ 1060",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.black)
                    )
                }

                Spacer(modifier = Modifier.height(NormalPadding))
                OutlinedButton(
                    onClick = {},
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(
                            id = R.color.black
                        ),
                    ),
                    border = BorderStroke(0.dp, Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 12.dp,
                        )
                        .height(50.dp)
                ) {

                    Text(
                        "Make Payment",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                    )

                }
            }
        }
    }

}