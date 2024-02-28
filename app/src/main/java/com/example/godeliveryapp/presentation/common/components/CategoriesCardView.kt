package com.example.godeliveryapp.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R

@Composable
fun CategoryCardView(
    modifier: Modifier = Modifier,
    categoryCard: CategoryCard
) {
    Card(
        modifier = Modifier
            .height(140.dp)
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.lightGray))
    ) {

        Box(
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Text(
                    text = categoryCard.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(
                        id = R.color.black
                    )
                )
                Text(
                    text = categoryCard.description,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
            }

            Image(
                painter = painterResource(id = categoryCard.imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomEnd)
                    .offset(y = 2.dp, x = 20.dp)
            )
        }


    }


}

@Composable
fun OfferCardView(title: String, description: String, imageId: Int) {

    Card(
        modifier = Modifier
            .height(140.dp)
            .fillMaxWidth()
            .padding(top = 5.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.lightGray))
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(
                        id = R.color.black
                    )
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
            }

            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.BottomEnd)
            )
        }

    }


}
