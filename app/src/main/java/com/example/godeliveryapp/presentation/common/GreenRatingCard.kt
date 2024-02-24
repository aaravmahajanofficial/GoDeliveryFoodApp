package com.example.zomatoclone.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R

@Composable
fun GreenRatingCard(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier
            .height(28.dp)
            .width(55.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.primaryColor)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        ) {

        Text(
            "3.7",
            style = MaterialTheme.typography.bodyMedium,
        )

        Icon(
            imageVector = Icons.Rounded.Star,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )

    }


}