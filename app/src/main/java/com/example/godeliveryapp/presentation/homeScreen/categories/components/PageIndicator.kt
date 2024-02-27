package com.example.zomatoclone.presentation.homeScreen.OfferAds.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.zomatoclone.presentation.Dimens.IndicatorSize

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    selectedPage: Int,
    unselectedColor: Color = colorResource(id = R.color.lightGray)
) {

    Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween) {

        repeat(pageSize) { page ->

            Box(
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp)
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )

        }

    }


}