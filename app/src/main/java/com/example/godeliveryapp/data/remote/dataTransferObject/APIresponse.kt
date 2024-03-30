package com.example.godeliveryapp.data.remote.dataTransferObject

import com.example.godeliveryapp.domain.model.APIMODEL.Item

data class APIresponse(
    val items: List<Item>
)