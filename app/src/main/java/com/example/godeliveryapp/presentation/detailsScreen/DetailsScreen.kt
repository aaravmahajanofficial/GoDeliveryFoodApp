package com.example.godeliveryapp.presentation.detailsScreen

import MenuItem
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.RestaurantListingCard
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    restaurantListingCard: RestaurantListingCard
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    var checked by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
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
                        .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                ) {
//            restaurantListingCard.imageId?.let { painterResource(id = it) }?.let {
                    Image(
                        painter = painterResource(id = R.drawable.restaurant3),
                        contentDescription = null,
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
                                .background(color = Color.White, shape = CircleShape)
                                .size(42.dp), contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
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
                        text = restaurantListingCard.restaurantName,
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(
                            id = R.color.black
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    val items: List<String> = restaurantListingCard.cuisine
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

                            val ratings: List<String> = listOf(restaurantListingCard.rating)
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
                            restaurantListingCard.address,
                            "${restaurantListingCard.distance}Km",
                            restaurantListingCard.time?.let { "$it Mins Delivery" },
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
                                if (checked) {
                                } else null
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

                Spacer(modifier = Modifier.height(12.dp))

                fun onClick() {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = true
                        }
                    }
                }



                if (showBottomSheet) {

                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState,
                        dragHandle = ({}),
                        containerColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((screenHeight).dp)
                    ) {
                        Column {
                            //HeroSection
                            Box(
                                modifier = Modifier
                                    .height((screenHeight / 3).dp)
                                    .fillMaxWidth()
                                    .background(
                                        Color.Transparent,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                            ) {
                                //            restaurantListingCard.imageId?.let { painterResource(id = it) }?.let {
                                Image(
                                    painter = painterResource(id = R.drawable.restaurant2),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                //            }
                                Box(modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(NormalPadding)
                                    .clickable {
                                        showBottomSheet = false
                                    }
                                    .background(
                                        color = Color.White,
                                        shape = CircleShape
                                    )
                                    .size(42.dp),
                                    contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Rounded.Close,
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.black),
                                        modifier = Modifier.scale(1f)
                                    )
                                }

                            }
                            //Below Image
                            Column(
                                modifier
                                    .fillMaxSize()
                                    .padding(start = NormalPadding, end = NormalPadding),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = "Raan-E-Murgh Biryani \n(Chicken Whole Leg Biryani)",
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = colorResource(
                                            id = R.color.black
                                        ),
                                        overflow = TextOverflow.Visible,
                                        maxLines = 2,
                                        modifier = Modifier.fillMaxWidth()
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = "₹ 459",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = colorResource(id = R.color.black),
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = "Boneless, Served with 1 Gulab Jamun & Mint Raita. In this culinary jewel from Panchkula, Tender chicken pieces are marinated",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                        maxLines = 3,
                                        overflow = TextOverflow.Visible,
                                        modifier = Modifier.fillMaxWidth()
                                    )

                                    Spacer(modifier = Modifier.height(Dimens.MediumPadding2))

                                    Spacer(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(color = colorResource(id = R.color.lightGray))
                                    )
                                }

                                val paddingValues =
                                    WindowInsets.navigationBars.asPaddingValues()


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
                                        .padding(bottom = paddingValues.calculateBottomPadding() * 2)
                                        .height(50.dp)
                                ) {

                                    Text(
                                        "Add to cart - ₹ 599",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = FontWeight.Medium
                                        )
                                    )

                                }


                            }

                        }


                    }

                }

                MenuItem(onClick = { onClick() })
                MenuItem(onClick = { onClick() })
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
                        start = NormalPadding,
                        bottom = 12.dp,
                        top = 12.dp,
                        end = NormalPadding
                    )
                    .height(50.dp)
            ) {

                Text(
                    "Add to cart - ₹ 599",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )

            }
        }
    }
}