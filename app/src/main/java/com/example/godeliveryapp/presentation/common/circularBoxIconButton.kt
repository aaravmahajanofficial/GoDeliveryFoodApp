package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R

@Composable
fun circularBoxIconButton(image: ImageVector) {
    Box(
        modifier = Modifier
            .background(color = Color.White, shape = CircleShape)
            .size(42.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = image,
            contentDescription = null,
            tint = colorResource(id = R.color.black),
            modifier = Modifier.scale(1f)
        )
    }
}