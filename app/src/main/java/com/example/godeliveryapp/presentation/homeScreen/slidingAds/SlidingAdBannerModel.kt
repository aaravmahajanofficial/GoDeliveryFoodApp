package com.example.godeliveryapp.presentation.homeScreen.slidingAds

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.godeliveryapp.R

data class SlidingAdBanner(

    val title: String,
    @ColorRes var background: Int,
    @ColorRes var buttonColor: Int,
    @DrawableRes val imageId: Int
)

val SlidingAdBanners = listOf(

    SlidingAdBanner(
        "Get up to 25% on all food orders",
        buttonColor = R.color.orderNowButtonColor,
        background = R.color.offerAD1Color,
        imageId = R.drawable.foodoffer
    ),
    SlidingAdBanner(
        "Get up to 35% on all desserts.",
        buttonColor = R.color.orderNowButtonColor,
        background = R.color.offerAD2Color,
        imageId = R.drawable.mart2
    ),
    SlidingAdBanner(
        "Get up to 40% on all coffee orders",
        buttonColor = R.color.orderNowButtonColor,
        background = R.color.offerAD3Color,
        imageId = R.drawable.mart3
    )


)