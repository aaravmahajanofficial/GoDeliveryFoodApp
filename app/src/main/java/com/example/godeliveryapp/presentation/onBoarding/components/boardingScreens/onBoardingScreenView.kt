package com.example.godeliveryapp.presentation.onBoarding.components.boardingScreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.navigation.Route

data class OnBoardingScreenModel(
    val image: Int,
    val title: String,
    val description: String
)

val screens = listOf(

    OnBoardingScreenModel(
        image = R.drawable.onboarding_logo_1,
        title = "Food delivery at door step",
        description = "Get yummy delicious food at your service in within less time."
    ),

    OnBoardingScreenModel(
        image = R.drawable.onboarding_logo_2,
        title = "Dine in fine restaurants",
        description = "Enjoy delectable cuisine served promptly at our fine dining establishments."
    ),

    OnBoardingScreenModel(
        image = R.drawable.onboarding_logo_3,
        title = "Easy & Secure Payment",
        description = "Make payments effortlessly and securely with our reliable system."
    )

)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreenView(modifier: Modifier = Modifier, navController: NavController) {

    val pagerState = rememberPagerState(initialPage = 0) { 3 }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var goToNextPage by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(goToNextPage) {

        if (goToNextPage) {
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
            goToNextPage = false
        }

    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(modifier = Modifier.fillMaxWidth()) {
                OnBoardingPageIndicator(pageSize = 3, selectedPage = pagerState.currentPage)
            }

            Spacer(modifier = Modifier.height(screenHeight / 48))

            HorizontalPager(state = pagerState) { page ->
                OnBoardingPage(
                    onBoardingScreenModel = screens[page],
                    navController = navController,
                    screenHeight = screenHeight,
                    screenWidth = screenWidth
                )
            }

            if (pagerState.currentPage != 2) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    TextButton(
                        onClick = {
                            goToNextPage = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 14),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = colorResource(id = R.color.black),
                        )
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Next",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = colorResource(id = R.color.white)
                            )
                            Icon(
                                modifier = Modifier.scale(0.6f),
                                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                                contentDescription = null,
                                tint = colorResource(id = R.color.white),
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                    TextButton(
                        onClick = {
                            navController.navigate(Route.WelcomeScreen.route) {
                                popUpTo(Route.OnBoardingScreen.route) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 14),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = colorResource(id = R.color.white),
                        )
                    ) {

                        Text(
                            text = "Skip",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = colorResource(id = R.color.black)
                        )

                    }

                }
            } else {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    TextButton(
                        onClick = {
                            navController.navigate(Route.WelcomeScreen.route) {
                                popUpTo(Route.OnBoardingScreen.route) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 14),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = colorResource(id = R.color.black),
                        )
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Get Started",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = colorResource(id = R.color.white)
                            )
                            Icon(
                                modifier = Modifier.scale(0.6f),
                                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                                contentDescription = null,
                                tint = colorResource(id = R.color.white),
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 14)
                            .background(
                                color = colorResource(id = R.color.white),
                                shape = RoundedCornerShape(5.dp)
                            )
                    )

                }

            }

        }


    }


}

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    onBoardingScreenModel: OnBoardingScreenModel,
    navController: NavController,
    screenHeight: Dp,
    screenWidth: Dp
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .height(screenHeight / 3)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = onBoardingScreenModel.image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(screenHeight / 12))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = onBoardingScreenModel.title,
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(ExtraSmallPadding2))

            Box(modifier = Modifier.width(screenWidth / 1.5f)) {
                Text(
                    text = onBoardingScreenModel.description,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}