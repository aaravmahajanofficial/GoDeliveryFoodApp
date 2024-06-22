package com.example.zomatoclone.presentation.homeScreen.OfferAds.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedColor: Color = colorResource(id = R.color.black),
    selectedPage: Int,
    unselectedColor: Color = colorResource(id = R.color.lightGray)
) {

    Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween) {

        repeat(pageSize) { page ->

            Box(
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp)
                    .height(4.dp)
                    .width(if (selectedPage == page) 24.dp else 12.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )

        }

    }


}