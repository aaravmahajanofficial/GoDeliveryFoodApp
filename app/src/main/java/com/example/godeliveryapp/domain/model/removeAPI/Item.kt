package com.example.godeliveryapp.domain.model.removeAPI

data class Item(
    val access: List<Access>,
    val address: Address,
    val distance: Int,
    val houseNumberType: String,
    val id: String,
    val mapView: MapView,
    val position: Position,
    val resultType: String,
    val title: String
)