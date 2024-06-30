package com.example.godeliveryapp.data.remote.dataTransferObject

import com.example.godeliveryapp.domain.model.removeAPI.Item

data class APIresponse(
    val items: List<Item>
)