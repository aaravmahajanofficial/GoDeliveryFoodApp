package com.example.godeliveryapp.presentation.homeScreen.listings.components

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
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.NormalPadding

@Composable
fun RestaurantListingCardView(
    modifier: Modifier = Modifier,
    addToFav: (Int) -> Unit,
    removeFromFav: (Int) -> Unit,
    isFavourite: Boolean,
    restaurantListingCardModel: RestaurantListingCardModel,
    navigateToDetails: (() -> Unit)? = null,
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier
            .width(screenHeight / 4.5f)
            .clickable { navigateToDetails?.invoke() },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .height(screenHeight / 4.25f)
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                .clip(
                    RoundedCornerShape(12.dp)
                )
        ) {


            AsyncImage(
                model = restaurantListingCardModel.imageURL,
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .clickable {
                        if (isFavourite) {
                            removeFromFav(restaurantListingCardModel.restaurantId)
                        } else {
                            addToFav(restaurantListingCardModel.restaurantId)
                        }
                    }
                    .align(Alignment.TopEnd)
                    .padding(NormalPadding)
                    .background(color = Color.White, shape = CircleShape)
                    .size(42.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavourite) Color.Red else colorResource(id = R.color.black),
                    modifier = Modifier.scale(1f)
                )
            }

        }
        Spacer(modifier = Modifier.height(ExtraSmallPadding3))
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
                modifier = Modifier.size(screenWidth / 20),
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