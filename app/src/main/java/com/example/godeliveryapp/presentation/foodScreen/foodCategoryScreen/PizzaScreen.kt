package com.example.godeliveryapp.presentation.foodScreen.foodCategoryScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.NormalPadding

@Composable
fun PizzaScreen(modifier: Modifier = Modifier) {

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = Dimens.NormalPadding,
                        start = Dimens.NormalPadding,
                        end = Dimens.NormalPadding
                    )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier
                        .scale(1.2f)
                        .clickable {

                        }

                )
            }

            Spacer(modifier = Modifier.height(MediumPadding1))

            Box(
                modifier = Modifier.padding(
                    start = NormalPadding
                )
            ) {
                Text(
                    text = "Pizza",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            Spacer(modifier = Modifier.height(NormalPadding))

            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(start = NormalPadding, end = NormalPadding)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 16).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Tune,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 10).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 6.dp, end = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Sort By",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                            )
                            Icon(
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )


                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 10).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Pure Veg",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 8).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Fast Delivery",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 8).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ratings 4.0+",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                        )
                    }


                }
            }
        }

    }


}