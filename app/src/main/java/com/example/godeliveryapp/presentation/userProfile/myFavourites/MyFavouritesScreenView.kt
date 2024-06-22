package com.example.godeliveryapp.presentation.userProfile.myFavourites

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomBackArrowButton

@Composable
fun MyFavouritesScreenView(modifier: Modifier = Modifier, navController: NavController) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = NormalPadding, end = NormalPadding, top = NormalPadding),
            horizontalAlignment = Alignment.Start,
        ) {
            item {
                CustomBackArrowButton(navController = navController)

                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "Favourites",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold)
                )

                Spacer(modifier = Modifier.height(MediumPadding2))

            }

            item {
                ItemCard(screenHeight = screenHeight, screenWidth = screenWidth)

            }


        }

    }

}

@Composable
fun ItemCard(modifier: Modifier = Modifier, screenHeight: Dp, screenWidth: Dp) {

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(screenHeight.div(8))
                        .width(screenWidth.div(4))
                        .background(
                            Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(
                            RoundedCornerShape(12.dp)
                        ), contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.restaurant3),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = ExtraSmallPadding3),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = "Faasos - Wraps & Rolls",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = colorResource(
                            id = R.color.black
                        ),
                        textAlign = TextAlign.Justify,
                        maxLines = 2,
                    )

                    Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "2.2 Kms | 20 Mins",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                            color = Color.Gray,
                            maxLines = 1,
                        )
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                modifier = Modifier.size(screenWidth / 20),
                                imageVector = Icons.Filled.StarRate,
                                contentDescription = null,
                                tint = colorResource(id = R.color.orange)
                            )

                            Spacer(modifier = Modifier.width(ExtraSmallPadding1))

                            Text(
                                text = "${4.4}",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = colorResource(id = R.color.black),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1

                            )
                        }
                    }

                }


            }

            Spacer(modifier = Modifier.height(ExtraSmallPadding3))
        }
    }


}