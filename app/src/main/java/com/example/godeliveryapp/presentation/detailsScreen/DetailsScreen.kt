package com.example.godeliveryapp.presentation.detailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.presentation.common.MenuItem
import com.example.zomatoclone.presentation.Dimens.ExtraSmallPadding2
import com.example.zomatoclone.presentation.Dimens.ExtraSmallPadding3
import com.example.zomatoclone.presentation.Dimens.MediumPadding1
import com.example.zomatoclone.presentation.common.GreenRatingCard
import com.example.godeliveryapp.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(modifier: Modifier = Modifier, navController: NavController) {

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar({}, navigationIcon = {

            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = "Navigate back"
                )
            }
        }, actions = {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Rounded.FavoriteBorder, contentDescription = "Navigate back")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Rounded.Share, contentDescription = "Navigate back")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Rounded.MoreVert, contentDescription = "Navigate back")
                }

            }
        })

    }, content = { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Spacer(modifier = Modifier.height(MediumPadding1))

            Text(
                modifier = Modifier.clickable {
                    navController.navigate(
                        route = Route.HomeScreen.route
                    ) {
                        popUpTo(route = Route.HomeScreen.route) {
                            inclusive = true
                        }
                    }
                },
                text = "The Ganache",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(ExtraSmallPadding2))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,

                ) {

                //TODO : implement a list and join by separator "."
                Text(
                    text = "Cafe.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Beverage.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Desserts",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )

            }

            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GreenRatingCard()
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = "4.7K ratings",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )


            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MediumPadding1, bottom = MediumPadding1)
                    .height(0.8.dp)
                    .background(Color.LightGray)
            )

            MenuItem()

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MediumPadding1, bottom = MediumPadding1)
                    .height(0.8.dp)
                    .background(Color.LightGray)
            )


        }

    }


    )


}
