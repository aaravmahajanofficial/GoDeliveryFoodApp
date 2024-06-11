package com.example.godeliveryapp.presentation.homeScreen.slidingAds

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.godeliveryapp.R

data class SlidingAdBanner(

    val title: String,
    val description: String? = null,
    val buttonTitle: String,
    @ColorRes var background: Int,
    @ColorRes var buttonColor: Int,
    @DrawableRes val imageId: Int
)

val SlidingAdBanners = listOf(

    SlidingAdBanner(
        title = "Get up to 25% on all food orders",
        buttonColor = R.color.orderNowButtonColor,
        background = R.color.offerAD1Color,
        imageId = R.drawable.foodoffer,
        buttonTitle = "Order Now",
    ),
    SlidingAdBanner(
        title = "Gold Membership",
        description = "Free delivery on all orders",
        buttonColor = R.color.orderNowButtonColor,
        background = R.color.offerAD1Color,
        imageId = R.drawable.foodoffer,
        buttonTitle = "Know more",
    ),
)