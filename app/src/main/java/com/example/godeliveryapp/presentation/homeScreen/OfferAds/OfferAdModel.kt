package com.example.zomatoclone.presentation.homeScreen.OfferAds

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.godeliveryapp.R

data class OfferAd(

    val title: String,
    @ColorRes var background: Int,
    @ColorRes var buttonColor: Int,
    @DrawableRes val imageId: Int
)

val OfferAds = listOf<OfferAd>(

    OfferAd(
        "Get up to 25% on all food orders",
        buttonColor = R.color.orderNowButtonColor,
        background = R.color.offerAD1Color,
        imageId = R.drawable.foodoffer
    ),
    OfferAd(
        "Get up to 35% on all desserts.",
        buttonColor = R.color.orderNowButtonColor,
        background = R.color.offerAD2Color,
        imageId = R.drawable.mart2
    ),
    OfferAd(
        "Get up to 40% on all coffee orders",
        buttonColor = R.color.orderNowButtonColor,
        background = R.color.offerAD3Color,
        imageId = R.drawable.mart3
    )


)