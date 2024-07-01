package com.example.godeliveryapp.presentation.userProfile.myFavourites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomBackArrowButton
import com.example.godeliveryapp.presentation.common.CustomLineBreak

@Composable
fun MyFavouritesScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToDetails: (RestaurantListingCardModel) -> Unit,
    favouritesViewModel: FavouritesViewModel = hiltViewModel()
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val favourites = favouritesViewModel.favourites.collectAsState(initial = emptyList()).value
    val isLoading = favouritesViewModel.isLoading.collectAsState(initial = false).value

    LaunchedEffect(Unit) {
        favouritesViewModel.getFavourites()
    }

    if (!isLoading) {
        if (favourites != null) {
            if (favourites.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(NormalPadding),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        CustomBackArrowButton(navController = navController)

                        Box(
                            modifier = Modifier
                                .height(screenHeight / 3)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.no_favourites_logo),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "No Favourites Yet!",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                                textAlign = TextAlign.Justify
                            )

                            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                            Box(
                                modifier = Modifier.width(screenWidth / 1.5f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "You can add an item to your favourites by clicking on the ''Heart Icon''",
                                    color = colorResource(id = R.color.black),
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(screenHeight / 8))

                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = NormalPadding,
                                end = NormalPadding,
                                top = NormalPadding
                            ),
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

                            CustomLineBreak()

                        }

                        items(favourites.size) { index ->
                            ItemCard(
                                screenHeight = screenHeight,
                                screenWidth = screenWidth,
                                navigateToDetails = {
                                    navigateToDetails(favourites[index])
                                },
                                restaurantListingCardModel = favourites[index]
                            )

                        }


                    }

                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colorResource(id = R.color.primaryColor))
        }
    }

}

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    navigateToDetails: (() -> Unit)? = null,
    screenHeight: Dp,
    screenWidth: Dp,
    restaurantListingCardModel: RestaurantListingCardModel
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight.div(6))
            .clickable { navigateToDetails?.invoke() },
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
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

                    AsyncImage(
                        model = restaurantListingCardModel.imageURL,
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
                        text = restaurantListingCardModel.name,
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
                            text = "${restaurantListingCardModel.distance} Kms | 20 Mins",
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
                                text = restaurantListingCardModel.rating,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = colorResource(id = R.color.black),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1

                            )
                        }
                    }

                }


            }
        }
    }


}