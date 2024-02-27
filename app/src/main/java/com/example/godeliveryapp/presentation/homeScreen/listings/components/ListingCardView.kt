package com.example.godeliveryapp.presentation.homeScreen.listings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.godeliveryapp.presentation.homeScreen.listings.ListingCard

@Composable
fun ListingCardView(modifier: Modifier = Modifier, listingCard: ListingCard) {

    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.lightGray))
    ) {

        Image(
            painter = painterResource(id = listingCard.imageId),
            contentDescription = null,
            modifier = Modifier
                .scale(0.8f)
                .align(Alignment.End)

        )

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = listingCard.title,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(
                    id = R.color.black
                )
            )
            Text(
                text = listingCard.options[0],
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
        }


    }

}