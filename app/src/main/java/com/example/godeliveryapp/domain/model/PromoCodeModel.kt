package com.example.godeliveryapp.domain.model

import com.example.godeliveryapp.R

data class PromoCodeModel(
    val couponCode: String,
    val couponDescription: String,
    val couponImage: Int
)

val codes = listOf(
    PromoCodeModel(
        couponCode = "AXIOSBNK40",
        couponDescription = "50% OFF on all orders",
        couponImage = R.drawable.bank_logo_1
    ),
    PromoCodeModel(
        couponCode = "HDFCBNK30",
        couponDescription = "30% OFF on all orders",
        couponImage = R.drawable.bank_logo_2
    ),
    PromoCodeModel(
        couponCode = "GPAYCC120",
        couponDescription = "Flat ₹120 OFF + Cashback",
        couponImage = R.drawable.bank_logo_3
    )

)