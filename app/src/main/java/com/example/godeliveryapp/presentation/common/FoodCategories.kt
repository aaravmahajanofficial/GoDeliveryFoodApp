package com.example.zomatoclone.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zomatoclone.presentation.Dimens.ExtraSmallPadding1

@Composable
fun FoodOptions(

    title: String,
    imageID: Int
) {

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageID),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp), contentScale = ContentScale.Crop

        )

        Spacer(modifier = Modifier.padding(ExtraSmallPadding1))

        Text(
            text = title, textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }


}

