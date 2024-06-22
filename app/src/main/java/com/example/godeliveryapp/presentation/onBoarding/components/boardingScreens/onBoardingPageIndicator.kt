package com.example.godeliveryapp.presentation.onBoarding.components.boardingScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1

@Composable
fun OnBoardingPageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        repeat(pageSize) { page ->

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = ExtraSmallPadding1, end = ExtraSmallPadding1)
                    .height(screenHeight / 200)
                    .clip(RoundedCornerShape(1.dp))
                    .background(
                        color = if (page == selectedPage) colorResource(id = R.color.black) else Color.LightGray

                    ),
            )


        }

    }

}