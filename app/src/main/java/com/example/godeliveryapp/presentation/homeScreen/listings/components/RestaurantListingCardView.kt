package com.example.godeliveryapp.presentation.homeScreen.listings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.NormalPadding

@Composable
fun RestaurantListingCardView(
    modifier: Modifier = Modifier,
    restaurantListingCardModel: RestaurantListingCardModel,
    navigateToDetails: (() -> Unit)? = null,
) {

    Column(
        modifier = Modifier
            .width(170.dp)
            .clickable { navigateToDetails?.invoke() },
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .height(175.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                .clip(
                    RoundedCornerShape(12.dp)
                )
        ) {


            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(restaurantListingCardModel.imageURL),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(NormalPadding)
                    .background(color = Color.White, shape = CircleShape)
                    .size(42.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.scale(1f)
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = restaurantListingCardModel.name,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.black
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(5.dp))
        val items: List<String> = restaurantListingCardModel.cuisines
        val formattedString = items.joinToString(" | ")

        Text(
            text = formattedString,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
            color = colorResource(id = R.color.gray),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Filled.StarRate,
                contentDescription = null,
                tint = colorResource(id = R.color.orange)
            )
            Spacer(modifier = Modifier.width(ExtraSmallPadding1))

            val parameters = listOfNotNull(
                restaurantListingCardModel.rating,
                "${restaurantListingCardModel.distance}Km",
                12.let { "$it Mins" },
            ).joinToString(" | ")

            Text(
                text = parameters,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}