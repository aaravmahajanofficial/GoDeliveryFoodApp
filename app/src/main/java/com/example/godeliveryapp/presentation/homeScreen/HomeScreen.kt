package com.example.godeliveryapp.presentation.homeScreen

import SlidingAdBannerView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.RestaurantList
import com.example.godeliveryapp.presentation.common.components.CategoryCardList
import com.example.godeliveryapp.presentation.common.components.CategoryCardView
import com.example.godeliveryapp.presentation.common.components.OfferCardView
import com.example.godeliveryapp.presentation.homeScreen.slidingAds.SlidingAdBanners
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.zomatoclone.presentation.common.RestaurantCard
import com.example.zomatoclone.presentation.homeScreen.OfferAds.components.PageIndicator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToDetails: (RestaurantListingCardModel) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    val isLoading = viewModel.isLoading.collectAsState(initial = true)

//    LaunchedEffect(key1 = isLoading) {
//
//
//    }

    val textFieldValue by remember {
        mutableStateOf("")
    }

    val pageState = rememberPagerState(initialPage = 0) {
        SlidingAdBanners.size
    }

    val itemsList = viewModel.restaurants.collectAsState(initial = listOf()).value

// Hero Section

    if (!itemsList.isNullOrEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                //header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(NormalPadding),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(modifier = Modifier) {
                        Text(
                            "Deliver Now", style = MaterialTheme.typography.labelLarge.copy(
                                color = colorResource(
                                    id = R.color.gray
                                )
                            )


                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Google", style = MaterialTheme.typography.titleLarge.copy(
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
                            .size(42.dp)
                            .background(
                                color = colorResource(id = R.color.lightGray), shape = CircleShape
                            ), contentAlignment = Alignment.Center

                    ) {

                        Icon(
                            modifier = Modifier
                                .scale(1.2f)
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

                //search bar
                Box(
                    modifier = Modifier
                        .padding(start = NormalPadding, end = NormalPadding)
                        .background(
                            color = colorResource(id = R.color.lightGray),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .fillMaxWidth()
                        .height(50.dp), contentAlignment = Alignment.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null,
                                modifier = Modifier.scale(1f),
                                tint = colorResource(id = R.color.black),
                            )
                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                "Search for food, grocery, meat etc.",
                                style = MaterialTheme.typography.labelLarge,
                                color = colorResource(id = R.color.gray)
                            )
                        }

                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.MicNone,
                                contentDescription = null,
                                modifier = Modifier.scale(1f),
                                tint = colorResource(id = R.color.black)
                            )
                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                " | ",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.black)
                            )
                            Spacer(modifier = Modifier.width(5.dp))

                            Icon(
                                imageVector = Icons.Outlined.Tune,
                                contentDescription = null,
                                modifier = Modifier.scale(1f),
                                tint = colorResource(id = R.color.black)
                            )
                        }
                    }


                }

                Spacer(modifier = Modifier.height(MediumPadding1))

                //Category Grid
                Column(modifier = Modifier.height(420.dp)) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxWidth(),
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(start = 13.dp, end = 13.dp),
                    ) {
                        items(CategoryCardList) { card ->
                            CategoryCardView(categoryCard = card)
                        }

                    }

                    Box(modifier = Modifier.padding(start = NormalPadding, end = NormalPadding)) {
                        OfferCardView(
                            title = "Gold Membership",
                            description = "Free delivery on all orders",
                            imageId = R.drawable.giftcard
                        )
                    }
                }


                Spacer(modifier = Modifier.height(MediumPadding2))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPager(
                        state = pageState,
                        pageSpacing = 8.dp,
                        contentPadding = PaddingValues(
                            start = NormalPadding,
                            end = NormalPadding
                        )
                    ) { index ->
                        SlidingAdBannerView(slidingAdBanner = SlidingAdBanners[index])
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    PageIndicator(
                        pageSize = SlidingAdBanners.size, selectedPage = pageState.currentPage
                    )
                }

                Spacer(modifier = Modifier.height(MediumPadding2))

                Box(modifier = Modifier.padding(start = NormalPadding)) {
                    Text(
                        text = "Popular Restaurants", style = MaterialTheme.typography.titleLarge
                    )
                }

                RestaurantList(restaurants = itemsList) { navigateToDetails(it) }

                Spacer(modifier = Modifier.height(MediumPadding1))

                Box(modifier = Modifier.padding(start = NormalPadding)) {
                    Text(
                        text = "Best To Dine-In", style = MaterialTheme.typography.titleLarge
                    )
                }

                RestaurantList(restaurants = itemsList) { navigateToDetails(it) }

                RestaurantCard()


            }


        }
    }

}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewClass() {
//
//    GoDeliveryApp {
//
//        HomeScreen(navController = rememberNavController())
//
//    }
//}

