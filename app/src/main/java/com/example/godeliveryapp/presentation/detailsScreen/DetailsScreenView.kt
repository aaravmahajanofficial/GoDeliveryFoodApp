package com.example.godeliveryapp.presentation.detailsScreen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.CartScreen.CartScreenViewModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemCardView
import com.example.godeliveryapp.presentation.navigation.Route

@Composable
fun DetailsScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    cartScreenViewModel: CartScreenViewModel = hiltViewModel(),
    restaurantListingCardModel: RestaurantListingCardModel
) {

    LaunchedEffect(Unit) {
        viewModel.getMenu(restaurantListingCardModel.restaurantId)
        cartScreenViewModel.getItems()
    }

    val isLoading = viewModel.isLoading.collectAsState(initial = false).value

    val menuItems = viewModel.menuItems.collectAsState(initial = listOf()).value
    val filteredRestaurants = viewModel.filteredList.collectAsState(initial = listOf()).value
    val totalItems = cartScreenViewModel.totalItemsInCart.collectAsState(initial = 0).value
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var onlyVeg by remember { mutableStateOf(false) }

    if (isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = colorResource(id = R.color.primaryColor))
        }

    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            item {
                //Hero Section
                Box(
                    modifier = Modifier
                        .height((screenHeight / 3))
                        .fillMaxWidth()
                        .background(Color.Transparent, shape = RoundedCornerShape(12.dp)),
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = rememberAsyncImagePainter(restaurantListingCardModel.imageURL),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = NormalPadding,
                                start = NormalPadding,
                                end = NormalPadding
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clickable { navController.navigateUp() }
                                .background(color = Color.White, shape = CircleShape)
                                .size(42.dp), contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = null,
                                tint = colorResource(id = R.color.black),
                                modifier = Modifier.scale(1f)
                            )
                        }
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(color = Color.White, shape = CircleShape)
                                    .size(42.dp), contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.FavoriteBorder,
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.black),
                                    modifier = Modifier.scale(1f)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Box(
                                modifier = Modifier
                                    .background(color = Color.White, shape = CircleShape)
                                    .size(42.dp), contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.black),
                                    modifier = Modifier.scale(1f)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Box(
                                modifier = Modifier
                                    .background(color = Color.White, shape = CircleShape)
                                    .size(42.dp)
                                    .clickable { navController.navigate(Route.CartScreen.route) },
                                contentAlignment = (Alignment.TopEnd),
                            ) {
                                if (totalItems > 0) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = colorResource(id = R.color.secondaryColor),
                                                shape = CircleShape
                                            )
                                            .size(NormalPadding),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = totalItems.toString(),
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.SemiBold
                                            ),
                                            color = colorResource(id = R.color.black),
                                            textAlign = TextAlign.Center,
                                        )
                                    }
                                }
                                Icon(
                                    imageVector = Icons.Outlined.ShoppingCart,
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.black),
                                    modifier = Modifier
                                        .scale(1f)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }

                }

                //Below Image
                Column(
                    modifier
                        .fillMaxWidth()
                        .padding(start = NormalPadding, end = NormalPadding),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = restaurantListingCardModel.name,
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(
                            id = R.color.black
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    val items: List<String> = restaurantListingCardModel.cuisines
                    val formattedString = items.joinToString(" | ")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = formattedString,
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal),
                            color = colorResource(id = R.color.black),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )

                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                imageVector = Icons.Rounded.StarRate,
                                contentDescription = null,
                                tint = colorResource(id = R.color.orange)
                            )

                            val ratings: List<String> = listOf(restaurantListingCardModel.rating)
                            val rating = ratings.joinToString(" ")
                            Text(
                                text = "${rating}(7.4k Ratings)",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = colorResource(id = R.color.black),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1

                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = restaurantListingCardModel.streetAddress,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                        )

                        Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            val parameters = listOfNotNull(
                                "${restaurantListingCardModel.distance}Km",
                                12.let { "$it Mins Delivery" },
                            ).joinToString(" | ")

                            Text(
                                text = parameters,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Start
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = colorResource(id = R.color.lightGray))
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            checked = onlyVeg.also {
                                viewModel.isVeg.value = it
                                viewModel.applyFilter()
                            },
                            onCheckedChange = { onlyVeg = it },
                            thumbContent = {
                                if (onlyVeg) {
                                    Icon(
                                        imageVector = Icons.Rounded.Check,
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.secondaryColor)
                                    )
                                }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = colorResource(id = R.color.secondaryColor),
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color.LightGray,
                                checkedBorderColor = Color.Transparent,
                                uncheckedBorderColor = Color.Transparent,
                            ),
                            modifier = Modifier.scale(0.8f)
                        )
                        Text(
                            text = "Only Veg",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                            color = colorResource(id = R.color.black),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )

                        Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                        Icon(
                            painter = painterResource(id = R.drawable.veg_only_logo),
                            contentDescription = null,
                            tint = colorResource(id = R.color.secondaryColor),
                            modifier = Modifier.size(screenHeight / 42)
                        )

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(start = NormalPadding)) {
                    Text(
                        text = "Most Popular", style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(NormalPadding))

            }

            if (filteredRestaurants.isNotEmpty()) {
                items(filteredRestaurants.size) { index ->
                    val menuItem = filteredRestaurants[index]

                    MenuItemCardView(
                        menuItemModel = menuItem
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = MediumPadding1, end = MediumPadding1)
                            .height(1.dp)
                            .background(color = colorResource(id = R.color.lightGray))
                    )

                }
            } else {
                items(menuItems.size) { index ->
                    val menuItem = menuItems[index]

                    MenuItemCardView(
                        menuItemModel = menuItem
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = MediumPadding1, end = MediumPadding1)
                            .height(1.dp)
                            .background(color = colorResource(id = R.color.lightGray))
                    )

                }
            }

        }
    }
}