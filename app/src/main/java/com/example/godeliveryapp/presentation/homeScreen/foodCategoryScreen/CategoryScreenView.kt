package com.example.godeliveryapp.presentation.homeScreen.foodCategoryScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.godeliveryapp.R
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomLineBreak
import com.example.godeliveryapp.presentation.userProfile.myFavourites.FavouritesViewModel
import com.example.godeliveryapp.utils.PriceOptions.priceOptions
import com.example.godeliveryapp.utils.RateOptions.ratingOptions
import com.example.godeliveryapp.utils.SortOptions.sortOptions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    categoryDto: CategoryDto,
    navigateToDetails: (RestaurantListingCardModel) -> Unit,
    viewModel: CategoryScreenViewModel = hiltViewModel(),
    favouritesViewModel: FavouritesViewModel = hiltViewModel(),
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var showFilterCard by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    var selectedOption by remember { mutableStateOf("Relevance(Default)") }
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    val filteredList =
        viewModel.filteredList.collectAsState(initial = emptyList()).value

    val favouritesList =
        favouritesViewModel.favouritesList.collectAsState(initial = emptyList()).value

    val pureVegState by viewModel.isPureVeg.collectAsState(initial = false)
    val nonVegState by viewModel.isNonVeg.collectAsState(initial = false)
    val ratingState by viewModel.rating.collectAsState(initial = 0.0)
    val priceRange by viewModel.priceRange.collectAsState(initial = "")
    val takeOutState by viewModel.takeOut.collectAsState(initial = false)
    val isLoading = viewModel.isLoading.collectAsState(initial = false).value

    LaunchedEffect(Unit) {
        viewModel.getRestaurantByCategory(categoryDto.id)
    }

    LaunchedEffect(
        nonVegState,
        pureVegState,
        ratingState,
        takeOutState,
        priceRange
    ) {
        viewModel.applyFilters()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(id = R.color.primaryColor)
            )

        } else {
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
                                top = NormalPadding,
                                start = NormalPadding,
                                end = NormalPadding
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = colorResource(id = R.color.black),
                            modifier = Modifier
                                .scale(1.2f)
                                .clickable {
                                    navController.navigateUp()

                                }

                        )
                    }

                    Spacer(modifier = Modifier.height(MediumPadding1))

                    Text(
                        text = categoryDto.name,
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(start = NormalPadding)
                    )

                    Spacer(modifier = Modifier.height(NormalPadding))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(start = NormalPadding, end = NormalPadding)
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .background(color = Color.Transparent, shape = CircleShape)
                                    .height((screenHeight / 24))
                                    .width((screenHeight / 10))
                                    .border(
                                        BorderStroke(color = Color.Gray, width = 0.dp),
                                        shape = CircleShape
                                    )
                                    .clickable { showBottomSheet = !showBottomSheet },
                                contentAlignment = Alignment.Center
                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 6.dp, end = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Filter",
                                        color = colorResource(id = R.color.black),
                                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                                    )

                                    Icon(
                                        imageVector = Icons.Rounded.Tune,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .background(color = Color.Transparent, shape = CircleShape)
                                    .height((screenHeight / 24))
                                    .width((screenHeight / 10))
                                    .border(
                                        BorderStroke(color = Color.Gray, width = 0.dp),
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            showFilterCard = !showFilterCard
                                        }
                                        .padding(start = 6.dp, end = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
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
                                        modifier = Modifier.size(16.dp)
                                    )


                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = if (pureVegState) colorResource(id = R.color.black) else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .border(
                                        BorderStroke(color = Color.Gray, width = 0.dp),
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        viewModel.setPureVeg()
                                    }
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Pure veg",
                                    color = if (pureVegState) colorResource(id = R.color.white) else colorResource(
                                        id = R.color.black
                                    ),
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = if (takeOutState) colorResource(id = R.color.black) else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .border(
                                        BorderStroke(color = Color.Gray, width = 0.dp),
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        viewModel.setTakeOut()
                                    }
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Take-Out",
                                    color = if (takeOutState) colorResource(id = R.color.white) else colorResource(
                                        id = R.color.black
                                    ),
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = if (
                                            ratingState == 4.0
                                        ) colorResource(id = R.color.black) else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .border(
                                        BorderStroke(color = Color.Gray, width = 0.dp),
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        if (ratingState == 4.0) {
                                            viewModel.setRatingState(0.0)
                                        } else {
                                            viewModel.setRatingState(4.0)

                                        }
                                    }
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Rating 4.0+",
                                    color = if (ratingState == 4.0) colorResource(id = R.color.white) else colorResource(
                                        id = R.color.black
                                    ),
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                                )
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(MediumPadding2))

                }
                if (!filteredList.isNullOrEmpty()) {
                    item {
                        Text(
                            text = "Popular Restaurants",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(start = NormalPadding)
                        )
                    }
                    items(filteredList.size) { index ->
                        filteredList[index]?.let { restaurantIndex ->
                            FoodCard(
                                screenHeight = screenHeight,
                                removeFromFav = { restaurantId ->
                                    favouritesViewModel.removeFavourite(
                                        restaurantId
                                    )
                                },
                                addToFav = { restaurantId ->
                                    favouritesViewModel.addToFavourites(
                                        restaurantId
                                    )
                                },
                                isFavourite = favouritesList.contains(restaurantIndex.restaurantId),
                                navigateToDetails = { navigateToDetails(restaurantIndex) },
                                restaurantListingCardModel = restaurantIndex
                            )
                        }


                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(screenWidth / 4f)
                    .align(Alignment.Center)
                    .height(screenHeight / 3)
                    .width(screenHeight / 2),
                contentAlignment = Alignment.Center
            ) {
                DropdownMenu(
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.white)),
                    offset = DpOffset(5.dp, 5.dp),
                    expanded = showFilterCard,
                    properties = PopupProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    ),
                    onDismissRequest = { showFilterCard = false }) {

                    sortOptions.forEach { option ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = ExtraSmallPadding3),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = option,
                                style = if (option == selectedOption) MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                ) else MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Normal
                                ),
                                color = colorResource(id = R.color.black)
                            )

                            RadioButton(
                                selected = selectedOption == option,
                                onClick = { selectedOption = option },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = colorResource(id = R.color.secondaryColor),
                                    unselectedColor = colorResource(id = R.color.gray)
                                )
                            )

                        }


                    }


                }
            }



            if (showBottomSheet) {

                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                        viewModel.applyFilters()
                    },
                    sheetState = sheetState,
                    dragHandle = ({}),
                    containerColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight)
                ) {
                    Column(
                        modifier
                            .fillMaxSize()
                            .padding(NormalPadding),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Spacer(modifier = Modifier.height(NormalPadding))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Sort & Filter",
                                    style = MaterialTheme.typography.headlineLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = colorResource(
                                        id = R.color.black
                                    ),
                                )

                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.black),
                                    modifier = Modifier
                                        .scale(1f)
                                        .clickable { showBottomSheet = !showBottomSheet }
                                )
                            }

                            Spacer(modifier = Modifier.height(MediumPadding2))

                            Text(
                                text = "Veg/Non-Veg",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = colorResource(id = R.color.black),
                            )
                            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    modifier = Modifier.scale(0.8f),
                                    checked = pureVegState,
                                    onCheckedChange = {
                                        viewModel.setPureVeg()
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.black),
                                        uncheckedColor = colorResource(id = R.color.gray),
                                        checkmarkColor = colorResource(id = R.color.white)
                                    )
                                )
                                Text(
                                    text = "Pure Veg",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Normal
                                    ),
                                    color = colorResource(id = R.color.gray),
                                )

                                Checkbox(
                                    modifier = Modifier.scale(0.8f),
                                    checked = nonVegState,
                                    onCheckedChange = { viewModel.setNonVeg() },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.black),
                                        uncheckedColor = colorResource(id = R.color.gray),
                                        checkmarkColor = colorResource(id = R.color.white)
                                    )
                                )

                                Text(
                                    text = "Non Veg",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Normal
                                    ),
                                    color = colorResource(id = R.color.gray),
                                )

                            }

                            CustomLineBreak()

                            Text(
                                text = "Rating",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = colorResource(id = R.color.black),
                            )

                            Spacer(modifier = Modifier.height(NormalPadding))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ratingOptions.forEach { ratingOption ->
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = if (ratingState == ratingOption) colorResource(
                                                    id = R.color.black
                                                ) else Color.Transparent,
                                                shape = CircleShape
                                            )
                                            .clickable {
                                                if (ratingState == ratingOption) {
                                                    viewModel.setRatingState(0.0)
                                                } else {
                                                    viewModel.setRatingState(ratingOption)

                                                }
                                            }
                                            .height((screenHeight / 22))
                                            .width((screenHeight / 12))
                                            .border(
                                                BorderStroke(color = Color.Gray, width = 0.dp),
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(start = 5.dp, end = 5.dp),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.StarRate,
                                                contentDescription = null,
                                                modifier = Modifier.scale(0.6f),
                                                tint = if (ratingState == ratingOption) colorResource(
                                                    id = R.color.white
                                                ) else colorResource(
                                                    id = R.color.black
                                                )
                                            )

                                            Text(
                                                text = ratingOption.toString(),
                                                color = if (ratingState == ratingOption) colorResource(
                                                    id = R.color.white
                                                ) else colorResource(
                                                    id = R.color.black
                                                ),
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }

                                    }

                                    Spacer(modifier = Modifier.width(ExtraSmallPadding3))
                                }


                            }

                            CustomLineBreak()

                            Text(
                                text = "Price",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = colorResource(id = R.color.black),
                            )

                            Spacer(modifier = Modifier.height(NormalPadding))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                priceOptions.forEach { priceOption ->
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = if (priceRange == priceOption) colorResource(
                                                    id = R.color.black
                                                ) else Color.Transparent,
                                                shape = CircleShape
                                            )
                                            .clickable {
                                                if (priceRange == selectedOption) {
                                                    viewModel.setPriceRange("")
                                                } else {
                                                    viewModel.setPriceRange(priceOption)
                                                }
                                            }
                                            .height((screenHeight / 22))
                                            .width((screenHeight / 12))
                                            .border(
                                                BorderStroke(color = Color.Gray, width = 0.dp),
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Text(
                                            text = priceOption,
                                            color = if (priceRange == priceOption) colorResource(
                                                id = R.color.white
                                            ) else colorResource(
                                                id = R.color.black
                                            ),
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Medium
                                            )
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(ExtraSmallPadding3))
                                }

                            }

                        }

                        val paddingValues =
                            WindowInsets.navigationBars.asPaddingValues()

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            OutlinedButton(
                                onClick = {
                                    showBottomSheet = false
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
                                    .height(50.dp)
                            ) {

                                Text(
                                    "Apply",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                )

                            }
                            Spacer(modifier = Modifier.height(ExtraSmallPadding1))
                            OutlinedButton(
                                onClick = {},
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(
                                        id = R.color.white
                                    ),
                                ),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = paddingValues.calculateBottomPadding())
                                    .height(50.dp)
                            ) {

                                Text(
                                    "Clear all filters",
                                    color = colorResource(id = R.color.black),
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                )

                            }
                        }


                    }


                }

            }
        }
    }


}

@Composable
private fun FoodCard(
    screenHeight: Dp,
    addToFav: (Int) -> Unit,
    removeFromFav: (Int) -> Unit,
    isFavourite: Boolean,
    navigateToDetails: (() -> Unit)? = null,
    restaurantListingCardModel: RestaurantListingCardModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { navigateToDetails?.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = NormalPadding,
                    end = NormalPadding,
                    top = NormalPadding,
                    bottom = ExtraSmallPadding2
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .height(screenHeight / 4)
                    .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
            ) {
                AsyncImage(
                    model = restaurantListingCardModel.imageURL,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .clickable {
                            if (isFavourite) {
                                removeFromFav(restaurantListingCardModel.restaurantId)
                            } else {
                                addToFav(restaurantListingCardModel.restaurantId)
                            }
                        }
                        .align(Alignment.TopEnd)
                        .padding(NormalPadding)
                        .background(color = Color.White, shape = CircleShape)
                        .size(42.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isFavourite) Color.Red else colorResource(id = R.color.black),
                        modifier = Modifier.scale(1f)
                    )
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(2f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = restaurantListingCardModel.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(
                            id = R.color.black
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()

                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    val items: List<String> = restaurantListingCardModel.cuisines
                    val formattedString = items.joinToString(" | ")

                    Text(
                        text = formattedString,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                        color = colorResource(id = R.color.gray),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Filled.StarRate,
                            contentDescription = null,
                            tint = colorResource(id = R.color.orange)
                        )
                        Spacer(modifier = Modifier.width(ExtraSmallPadding1))

                        Text(
                            text = restaurantListingCardModel.rating,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = colorResource(id = R.color.black),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    val parameters = listOfNotNull(
                        "${restaurantListingCardModel.distance}Km",
                        12.let { "$it Mins" },
                    ).joinToString(" | ")

                    Text(
                        text = parameters,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                        color = Color.Gray,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}