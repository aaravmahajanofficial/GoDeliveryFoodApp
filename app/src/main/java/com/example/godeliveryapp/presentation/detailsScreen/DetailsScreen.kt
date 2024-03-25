package com.example.godeliveryapp.presentation.detailsScreen

import MenuItemCardView
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateUp: (() -> Unit)? = null,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {

    val menuItemsCards = viewModel.menuItems.collectAsState(initial = listOf()).value
    val cartItems = viewModel.cartItems.collectAsState(initial = listOf()).value
    val screenHeight = LocalConfiguration.current.screenHeightDp
    var checked by remember { mutableStateOf(false) }

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
//Hero Section
                Box(
                    modifier = Modifier
                        .height((screenHeight / 3).dp)
                        .fillMaxWidth()
                        .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                ) {
//            restaurantListingCard.imageId?.let { painterResource(id = it) }?.let {
                    Image(
                        painter = painterResource(id = R.drawable.restaurant3),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

//            }
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
                                .clickable { navigateUp?.invoke() }
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
                        }
                    }

                }

//Below Image
                Column(
                    modifier
                        .fillMaxWidth()
                        .padding(start = NormalPadding, end = NormalPadding)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "restaurantListingCardModel.restaurantName",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(
                            id = R.color.black
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    val items: List<String>? = listOf("restaurantListingCardModel.cuisine")
                    val formattedString = items?.joinToString(" | ")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (formattedString != null) Text(
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

//                            val ratings: List<String> = listOf(restaurantListingCardModel.rating)
                            val ratings = listOf("Delicious, Mango")
                            val rating = ratings.joinToString(" ")
                            Text(
                                text = "${rating}(7.4k Ratings)",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                                color = colorResource(id = R.color.black),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,

                                )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//

                        val parameters = listOfNotNull(
                            "restaurantListingCardModel.address",
                            "restaurantListingCardModel.distanceKm",
                            " restaurantListingCardModel.time?.let { it_Mins_Delivery },"
                        ).joinToString(" | ")

                        Text(
                            text = parameters,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

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
                            checked = checked,
                            onCheckedChange = { checked = it },
                            thumbContent = {
//                                if (checked) {
//                                } else null
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = colorResource(id = R.color.green),
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color.LightGray,
                                checkedBorderColor = Color.Transparent,
                                uncheckedBorderColor = Color.Transparent
                            ),
                            modifier = Modifier.scale(0.8f)
                        )
                        Text(
                            text = "Only Veg",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
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

            items(menuItemsCards.size ?: 0) { index ->
                val menuItemCardModel = menuItemsCards[index]

                MenuItemCardView(menuItemCardModel = menuItemCardModel)

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = NormalPadding, end = NormalPadding)
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.lightGray))
                )

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
            OutlinedButton(
                onClick = {
                    navController.navigate(Route.CartScreen.route)
                },
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
                        start = NormalPadding,
                        bottom = 12.dp,
                        top = 12.dp,
                        end = NormalPadding
                    )
                    .height(50.dp)
            ) {

                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    "View Cart",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )

            }
        }
    }
}