package com.example.zomatoclone.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3

@Composable
fun RestaurantCard() {

    Card(
        modifier = Modifier
            .height(400.dp)
            .padding(14.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(18.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.mart3),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Crazy Cheesy",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(
                        start = ExtraSmallPadding3,
                        top = ExtraSmallPadding3
                    )
                )
                GreenRatingCard()
            }

            Text(
                text = "Pizza • Sandwich • ₹300 for one",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    start = ExtraSmallPadding3,
                    top = ExtraSmallPadding1,
                    bottom = ExtraSmallPadding1
                )
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = null,
                    tint = colorResource(id = R.color.primaryColor),
                    modifier = Modifier
                        .padding(
                            start = ExtraSmallPadding3,
                        )
                        .size(20.dp)
                )

                Text(
                    text = "30-35 min • 3km",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = ExtraSmallPadding2,
                        top = ExtraSmallPadding2,
                        bottom = ExtraSmallPadding2
                    )
                )

            }
            Box(modifier = Modifier.padding(ExtraSmallPadding3)) {
                Spacer(
                    modifier = Modifier
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
            }

            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = colorResource(id = R.color.primaryColor),
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    text = "Buy 2 get 1 free",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = ExtraSmallPadding2
                    )
                )


            }


        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewApp() {
//
//    ZomatoCloneTheme {
//        RestaurantCard()
//    }
//
//}
