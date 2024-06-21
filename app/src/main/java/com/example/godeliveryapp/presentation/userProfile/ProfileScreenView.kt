package com.example.godeliveryapp.presentation.userProfile

import SlidingAdBannerView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.homeScreen.slidingAds.SlidingAdBanners
import com.example.godeliveryapp.presentation.navigation.Route

@Composable
fun ProfileScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val userDetails = profileScreenViewModel.userDetails.collectAsState(initial = null).value

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                modifier = Modifier
                    .clickable { navController.navigateUp() }
                    .size(42.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.scale(1.2f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    if (userDetails != null) {
                        Text(
                            text = userDetails.userName,
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = colorResource(id = R.color.black)
                        )
                    }

                    Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                    Text(
                        text = "+91 9876543210",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                        color = Color.Gray
                    )

                }

                Box(
                    modifier = Modifier
                        .size(screenHeight / 10)
                        .border(
                            BorderStroke(0.5.dp, colorResource(id = R.color.lightGray)),
                            shape = CircleShape
                        )
                        .background(
                            shape = CircleShape,
                            color = colorResource(id = R.color.lightGray)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Outlined.PersonOutline,
                        contentDescription = null,
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.size(screenHeight / 20)
                    )


                }

            }

            Spacer(modifier = Modifier.height(MediumPadding1))

            SlidingAdBannerView(slidingAdBanner = SlidingAdBanners[1])

            Spacer(modifier = Modifier.height(MediumPadding2))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = ExtraSmallPadding1, end = ExtraSmallPadding1),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                OptionRow(
                    screenHeight = screenHeight,
                    optionTitle = "My Account",
                    imageVector = Icons.Outlined.PersonOutline
                )
                Spacer(modifier = Modifier.height(MediumPadding2))
                OptionRow(
                    screenHeight = screenHeight,
                    optionTitle = "My Orders",
                    imageVector = Icons.Outlined.ShoppingBag,
                    onClick = {
                        navController.navigate(Route.MyOrdersScreen.route)
                    }
                )
                Spacer(modifier = Modifier.height(MediumPadding2))
                OptionRow(
                    screenHeight = screenHeight,
                    optionTitle = "Payments",
                    imageVector = Icons.Outlined.Payments
                )
                Spacer(modifier = Modifier.height(MediumPadding2))
                OptionRow(
                    screenHeight = screenHeight,
                    optionTitle = "Address",
                    imageVector = Icons.Outlined.LocationOn
                )
                Spacer(modifier = Modifier.height(MediumPadding2))
                OptionRow(
                    screenHeight = screenHeight,
                    optionTitle = "Favourites",
                    imageVector = Icons.Outlined.FavoriteBorder
                )

                Spacer(modifier = Modifier.height(MediumPadding2))

                OptionRow(
                    screenHeight = screenHeight,
                    optionTitle = "Settings",
                    imageVector = Icons.Outlined.Settings
                )

            }

            Spacer(modifier = Modifier.height(screenHeight / 5))


        }


    }


}

@Composable
private fun OptionRow(
    screenHeight: Dp,
    optionTitle: String,
    imageVector: ImageVector,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
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
                imageVector = imageVector,
                contentDescription = null,
                tint = colorResource(id = R.color.black),
                modifier = Modifier.size(screenHeight / 28)
            )

            Spacer(modifier = Modifier.width(NormalPadding))

            Text(
                text = optionTitle,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                color = colorResource(id = R.color.black)
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null,
            tint = colorResource(id = R.color.black),
            modifier = Modifier.size(screenHeight / 26)
        )

    }
}