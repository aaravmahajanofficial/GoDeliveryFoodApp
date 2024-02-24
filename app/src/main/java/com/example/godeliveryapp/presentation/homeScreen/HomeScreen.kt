package com.example.godeliveryapp.presentation.homeScreen

import OfferAdCard
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.go_deliver.presentation.common.CategoryItem
import com.example.go_deliver.presentation.common.OfferCard
import com.example.godeliveryapp.R
import com.example.zomatoclone.domain.model.RestaurantItem
import com.example.zomatoclone.presentation.Dimens.MediumPadding1
import com.example.zomatoclone.presentation.Dimens.MediumPadding2
import com.example.zomatoclone.presentation.common.RestaurantCard
import com.example.zomatoclone.presentation.homeScreen.OfferAds.OfferAds
import com.example.zomatoclone.presentation.homeScreen.OfferAds.components.PageIndicator
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.zomatoclone.ui.theme.monasans

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, restaurants: LiveData<List<RestaurantItem>>) {

    val textFieldValue by remember {
        mutableStateOf("")
    }

    val pageState = rememberPagerState(initialPage = 0) {
        OfferAds.size
    }

// Hero Section

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MediumPadding1,
                            end = MediumPadding1,
                            top = MediumPadding1
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(modifier = Modifier) {
                        Text(
                            "Deliver Now",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = colorResource(
                                    id = R.color.gray
                                )
                            )


                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Google",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    color = colorResource(
                                        id = R.color.black
                                    )
                                )
                            )
                            Icon(
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )

                        }


                    }

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = colorResource(id = R.color.lightGray),
                                shape = CircleShape
                            ), contentAlignment = Alignment.Center

                    ) {

                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    navController.navigate(
                                        route = Route.DetailsScreen.route
                                    )
                                },
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                        )

                    }

                }

                Box(
                    modifier = Modifier
                        .padding(
                            start = MediumPadding1,
                            end = MediumPadding1,
                            top = 120.dp
                        )
                        .background(
                            color = colorResource(id = R.color.lightGray),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .fillMaxWidth()
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = MediumPadding1, end = MediumPadding1),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = colorResource(id = R.color.black)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                "Search for food,grocery,meat etc.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.gray)
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.MicNone,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = colorResource(id = R.color.black)
                            )
                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                "|",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.black)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Icon(
                                imageVector = Icons.Outlined.Tune,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = colorResource(id = R.color.black)
                            )
                        }


                    }


                }

            }

            Spacer(modifier = Modifier.height(60.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MediumPadding2, end = MediumPadding1)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    CategoryItem(
                        title = "Food",
                        description = "25 mins",
                        imageId = R.drawable.fastfood
                    )

                    CategoryItem(
                        title = "Mart",
                        description = "20 mins",
                        imageId = R.drawable.groceries
                    )

                    CategoryItem(
                        title = "Courier",
                        description = "30 mins",
                        imageId = R.drawable.delivery2
                    )

                }
                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    CategoryItem(
                        title = "Dine in",
                        description = "No waiting",
                        imageId = R.drawable.dinein
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    OfferCard(
                        title = "Gold Membership",
                        description = "Free delivery on all orders",
                        imageId = R.drawable.giftcard
                    )


                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            HorizontalPager(state = pageState, pageSize = PageSize.Fixed(750.dp)) { index ->
                OfferAdCard(offerAd = OfferAds[index])
            }

            Spacer(modifier = Modifier.height(1.dp))

            Row(
                modifier = Modifier
                    .padding(MediumPadding2)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PageIndicator(
                    pageSize = OfferAds.size,
                    selectedPage = pageState.currentPage
                )
            }


//            LazyRow(modifier = Modifier, contentPadding = PaddingValues(16.dp)) {
//                items(restaurants.value?.size ?: 0) { index ->
//                    val restaurant = restaurants.value?.get(index)
//
//                    if (restaurant != null) {
//                        FoodCard(
//                            title = restaurant.Name,
//                            description = restaurant.RestaurantID.toString(),
//                            color = Color.White,
//                            imageID = R.drawable.thai_food
//                        )
//                        Spacer(modifier = Modifier.width(CardPadding))
//
//                    }
//
////                    FoodCard(
////                        title = "Offers",
////                        color = colorResource(id = R.color.purple_700),
////                        description = "Up to 60% OFF",
////                        imageID = R.drawable.summer_sale
////                    )
////                    Spacer(modifier = Modifier.width(CardPadding))
////                    FoodCard(
////                        title = "Legends",
////                        color = colorResource(id = R.color.purple_500),
////                        description = "From across India",
////                        imageID = R.drawable.aeroplane
////                    )
////                    Spacer(modifier = Modifier.width(CardPadding))
////                    FoodCard(
////                        title = "Gourmet",
////                        color = Color.Gray,
////                        description = "Selections",
////                        imageID = R.drawable.spaghetti_alle_vongole
////                    )
////                    Spacer(modifier = Modifier.width(CardPadding))
////                    FoodCard(
////                        title = "Healthy",
////                        color = Color.Gray,
////                        description = "Curated dishes",
////                        imageID = R.drawable.protein
////                    )
////                    Spacer(modifier = Modifier.width(CardPadding))
////                    FoodCard(
////                        title = "Play & Win",
////                        color = Color.Gray,
////                        description = "Exciting Prizes",
////                        imageID = R.drawable.resource_throw
////                    )
////                    Spacer(modifier = Modifier.width(CardPadding))
//
//                }
//            }


            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "ALL RESTAURANTS", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontFamily = monasans,
                    color = colorResource(id = R.color.primaryColor), letterSpacing = 1.5.sp

                )
            )

            Spacer(modifier = Modifier.height(MediumPadding1))

            Text(
                text = "562 restaurants delivering to you", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontFamily = monasans,
                    color = Color.Gray

                )
            )

            Spacer(modifier = Modifier.height(MediumPadding1))

            Text(
                text = "FEATURED", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontFamily = monasans,
                    color = Color.Gray, letterSpacing = 1.5.sp

                )
            )

            Spacer(modifier = Modifier.height(MediumPadding1))

            RestaurantCard()


        }


    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewClass() {
//
//    ZomatoCloneTheme {
//
//        HomeScreen()
//
//    }
//}
//
