package com.example.godeliveryapp.presentation.homeScreen.searchScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CategoryButtonView
import com.example.godeliveryapp.presentation.common.CustomBackArrowButton
import com.example.godeliveryapp.presentation.homeScreen.HomeScreenViewModel
import com.example.godeliveryapp.presentation.homeScreen.listings.components.RestaurantListingCardView

@Composable
fun SearchScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToCategory: (CategoryDto) -> Unit,
    navigateToDetails: (RestaurantListingCardModel) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val categories = homeScreenViewModel.categories.collectAsState(initial = emptyList()).value
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val searchResults =
        homeScreenViewModel.filterList.collectAsState(initial = emptyList()).value

    var textFieldValue by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {

            item {
                Box(
                    modifier = Modifier.padding(
                        start = NormalPadding,
                        end = NormalPadding,
                        top = NormalPadding,
                    )
                ) {
                    CustomBackArrowButton(navController = navController)
                }

                Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            homeScreenViewModel.searchKeyWord(textFieldValue)
                        }
                    ),
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            "Search for dishes & restaurants",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal),
                            color = colorResource(id = R.color.gray)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            modifier = Modifier.size(screenHeight / 28),
                            tint = colorResource(id = R.color.black),
                        )
                    },

                    trailingIcon = {
                        Row(
                            modifier = Modifier.padding(end = ExtraSmallPadding3),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.MicNone,
                                contentDescription = null,
                                modifier = Modifier.size(screenHeight / 32),
                                tint = colorResource(id = R.color.black)
                            )

                            VerticalDivider(
                                modifier = Modifier
                                    .height(screenHeight / 32)
                                    .padding(start = ExtraSmallPadding1, end = ExtraSmallPadding1),
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.mix_logo),
                                contentDescription = null,
                                modifier = Modifier.size(screenHeight / 32),
                                tint = colorResource(id = R.color.black)
                            )
                        }

                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = NormalPadding, end = NormalPadding),
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    shape = RoundedCornerShape(5.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = colorResource(id = R.color.lightGray),
                        unfocusedContainerColor = colorResource(id = R.color.lightGray),
                        unfocusedBorderColor = colorResource(id = R.color.lightGray),
                        focusedBorderColor = colorResource(id = R.color.gray),
                        focusedTextColor = colorResource(id = R.color.black),
                        cursorColor = colorResource(id = R.color.black),
                        unfocusedTextColor = colorResource(id = R.color.black)

                    )

                )

                Spacer(modifier = Modifier.height(MediumPadding1))
            }

            if (searchResults != null) {
                if (searchResults.isNotEmpty() && textFieldValue.isNotEmpty()) {

                    item {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(NormalPadding),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            this.items(searchResults.size) { index ->
                                val result = searchResults[index]
                                RestaurantListingCardView(
                                    restaurantListingCardModel = result.restaurant,
                                    navigateToDetails = { navigateToDetails(result.restaurant) })
                                Spacer(modifier = Modifier.width(14.dp))
                            }

                        }
                    }

                } else {
                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                modifier = Modifier.padding(
                                    start = NormalPadding,
                                    end = NormalPadding
                                ),
                                text = "Top Categories",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                            )

                            Spacer(modifier = Modifier.height(MediumPadding1))

                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(
                                    start = NormalPadding,
                                    end = NormalPadding
                                ),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                if (categories != null) {
                                    items(categories.size) { index ->
                                        CategoryButtonView(
                                            category = categories[index],
                                            navigateToCategoryScreen = {
                                                navigateToCategory(categories[index])
                                            })
                                        Spacer(modifier = Modifier.width(NormalPadding))
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(screenHeight / 12))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = NormalPadding, end = NormalPadding),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Recent Searches",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                            )

                            Text(
                                text = "Clear all",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Normal
                                ),
                                color = colorResource(
                                    id = R.color.secondaryColor
                                ),
                                maxLines = 1,
                            )


                        }

                        Spacer(modifier = Modifier.height(MediumPadding2))
                    }

                    items(5) {
                        SearchTerm(screenHeight = screenHeight, searchTitle = "Cake Emporium")
                        Spacer(modifier = Modifier.height(NormalPadding))
                    }
                }
            }


        }


    }

}

@Composable
private fun SearchTerm(
    screenHeight: Dp,
    searchTitle: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(start = NormalPadding, end = NormalPadding)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Outlined.History,
                contentDescription = null,
                tint = colorResource(id = R.color.black),
                modifier = Modifier.size(screenHeight / 36)
            )

            Spacer(modifier = Modifier.width(ExtraSmallPadding3))

            Text(
                text = searchTitle,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                color = colorResource(id = R.color.black)
            )
        }
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = null,
            tint = colorResource(id = R.color.black),
            modifier = Modifier.size(screenHeight / 36)
        )

    }
}