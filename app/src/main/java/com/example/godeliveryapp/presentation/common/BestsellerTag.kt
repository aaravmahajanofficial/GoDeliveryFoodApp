package com.example.go_deliver.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R

@Composable
fun BestsellerTag() {
    Box(
        modifier = Modifier
            .height(24.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.black)),
        contentAlignment = Alignment.Center


    ) {

        Text(
            "Bestseller",
            style = MaterialTheme.typography.bodySmall
        )

    }
}