package com.example.go_deliver.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.zomatoclone.presentation.Dimens

@Composable
fun CategoryItem(
    title: String, description: String, imageId: Int
) {

    Card(
        modifier = Modifier.size(220.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.lightGray))
    ) {

        Column(
            modifier = Modifier.padding(Dimens.MediumPadding1),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(
                    id = R.color.black
                )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
        }

        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .offset(y = (-5).dp, x = 60.dp),
            )

    }


}

@Composable
fun OfferCard(title: String, description: String, imageId: Int) {

    Card(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.lightGray))
    ) {

        Column(
            modifier = Modifier.padding(Dimens.MediumPadding1),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(
                    id = R.color.black
                )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
        }

        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.8f)
                .align(Alignment.End)
                .offset(x = 100.dp, y = (-10).dp)
        )

    }


}
