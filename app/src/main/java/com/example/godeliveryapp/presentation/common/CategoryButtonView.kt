package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.godeliveryapp.R
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto

@Composable
fun CategoryButtonView(
    modifier: Modifier = Modifier,
    navigateToCategoryScreen: (() -> Unit)? = null,
    category: CategoryDto
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToCategoryScreen?.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(screenHeight / 10)
                .background(
                    color = colorResource(id = R.color.lightGray),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            AsyncImage(
                model = category.imageUrl,
                modifier = Modifier.scale(0.8f),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = category.name,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
            color = colorResource(
                id = R.color.black


            ),
        )
    }
}