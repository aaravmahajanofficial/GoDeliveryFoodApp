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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CategoryButtonView
import com.example.godeliveryapp.presentation.common.PageIndicator
import com.example.godeliveryapp.presentation.homeScreen.listings.components.RestaurantListingCardView
import com.example.godeliveryapp.presentation.homeScreen.slidingAds.SlidingAdBanners
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.godeliveryapp.presentation.userProfile.myFavourites.FavouritesViewModel
import com.example.godeliveryapp.utils.SharedPreferences

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenView(
    modifier: Modifier = Modifier, navController: NavController,
    navigateToDetails: (RestaurantListingCardModel) -> Unit,
    navigateToCategory: (CategoryDto) -> Unit,
    favouritesViewModel: FavouritesViewModel = hiltViewModel(),
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    val sharedPreferences = SharedPreferences(context = LocalContext.current)
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value

    val currentLocation = sharedPreferences.getUserData("userCurrentLocation")

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val pageState = rememberPagerState(initialPage = 0) {
        SlidingAdBanners.size
    }

    val itemsList = viewModel.restaurants.collectAsState(initial = listOf()).value
    val categoriesList = viewModel.categories.collectAsState(initial = listOf()).value
    val halfSize = categoriesList?.size?.div(2)

    val favouritesList = favouritesViewModel.favouritesList.collectAsState(initial = listOf()).value

// Hero Section

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colorResource(id = R.color.primaryColor))
        }
    } else {
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
                            .padding(Dimens.NormalPadding),
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
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                if (currentLocation != null) {
                                    Text(
                                        text = currentLocation,
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            color = colorResource(
                                                id = R.color.black
                                            )
                                        )
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Rounded.KeyboardArrowDown,
                                    contentDescription = null,
                                    modifier = Modifier.size(screenWidth / 12)
                                )

                            }


                        }

                        Box(
                            modifier = Modifier
                                .size(screenHeight / 16)
                                .background(
                                    color = colorResource(id = R.color.lightGray),
                                    shape = CircleShape
                                ), contentAlignment = Alignment.Center

                        ) {

                            Icon(
                                modifier = Modifier
                                    .scale(1.2f)
                                    .clickable {
                                        navController.navigate(route = Route.ProfileScreen.route)
                                    },
                                imageVector = Icons.Outlined.Person,
                                contentDescription = null,
                            )

                        }

                    }

                    //search bar
                    Box(
                        modifier = Modifier.padding(start = NormalPadding, end = NormalPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        SearchBar(navController = navController)
                    }

                    Spacer(modifier = Modifier.height(MediumPadding2))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            contentPadding = PaddingValues(start = NormalPadding)
                        ) {
                            if (categoriesList != null) {
                                items(halfSize!!) { index ->
                                    CategoryButtonView(
                                        category = categoriesList[index],
                                        navigateToCategoryScreen = {
                                            navigateToCategory(
                                                categoriesList[index]
                                            )
                                        },
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(MediumPadding1))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            contentPadding = PaddingValues(start = NormalPadding)
                        ) {
                            if (categoriesList != null) {
                                items(halfSize!!) { index ->
                                    CategoryButtonView(
                                        category = categoriesList[index + halfSize],
                                        navigateToCategoryScreen = {
                                            navigateToCategory(
                                                categoriesList[index + halfSize]
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                            }
                        }


                    }

                    Spacer(modifier = Modifier.height(32.dp))

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

                    Spacer(modifier = Modifier.height(Dimens.MediumPadding2))

                    Box(modifier = Modifier.padding(start = Dimens.NormalPadding)) {
                        Text(
                            text = "Popular Restaurants",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    //Popular Restaurants
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(NormalPadding),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        this.items(itemsList.size) { index ->
                            val restaurantIndex = itemsList[index]
                            RestaurantListingCardView(
                                restaurantListingCardModel = restaurantIndex,
                                addToFav = { restaurantId ->
                                    favouritesViewModel.addToFavourites(
                                        restaurantId
                                    )
                                },
                                isFavourite = favouritesList.contains(restaurantIndex.restaurantId),
                                removeFromFav = { restaurantId ->
                                    favouritesViewModel.removeFavourite(
                                        restaurantId
                                    )
                                },
                                navigateToDetails = { navigateToDetails(restaurantIndex) })
                            Spacer(modifier = Modifier.width(14.dp))
                        }

                    }

                    Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

                    Box(modifier = Modifier.padding(start = NormalPadding)) {
                        Text(
                            text = "Best To Dine-In", style = MaterialTheme.typography.titleLarge
                        )
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(NormalPadding),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        this.items(itemsList.size) { index ->
                            val restaurantIndex = itemsList[index]
                            RestaurantListingCardView(
                                restaurantListingCardModel = restaurantIndex,
                                addToFav = { restaurantId ->
                                    favouritesViewModel.addToFavourites(
                                        restaurantId
                                    )
                                },
                                isFavourite = favouritesList.contains(restaurantIndex.restaurantId),
                                removeFromFav = { restaurantId ->
                                    favouritesViewModel.removeFavourite(
                                        restaurantId
                                    )
                                },
                                navigateToDetails = { navigateToDetails(restaurantIndex) })
                            Spacer(modifier = Modifier.width(14.dp))
                        }

                    }


                }


            }
        }
    }


}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    navController: NavController
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .clickable { navController.navigate(Route.SearchScreen.route) }
            .background(
                color = colorResource(id = R.color.lightGray),
                shape = shape
            )
            .fillMaxWidth()
            .height(screenHeight / 14), contentAlignment = Alignment.Center
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = ExtraSmallPadding3, end = ExtraSmallPadding3)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier.size(screenHeight / 32),
                    tint = colorResource(id = R.color.black),
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    "Search for dishes & restaurants",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal),
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
                    modifier = Modifier.size(screenHeight / 32),
                    tint = colorResource(id = R.color.black)
                )

                Text(
                    " | ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.black)
                )

                Icon(
                    painter = painterResource(id = R.drawable.mix_logo),
                    contentDescription = null,
                    modifier = Modifier.size(screenHeight / 32),
                    tint = colorResource(id = R.color.black)
                )
            }
        }


    }


}