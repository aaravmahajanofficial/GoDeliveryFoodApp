package com.example.godeliveryapp.domain.model.removeAPI

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val city: String?,
    val countryCode: String?,
    val countryName: String?,
    val county: String?,
    val district: String?,
    val houseNumber: String?,
    val label: String?,
    val postalCode: String?,
    val state: String?,
    val street: String?
)