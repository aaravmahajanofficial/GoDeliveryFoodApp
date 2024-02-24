package com.example.zomatoclone.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.zomatoclone.presentation.Dimens.ExtraSmallPadding1

@Composable
fun FoodCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    color: Color,
    imageID: Int,
) {

    Card(
        modifier = Modifier
            .height(160.dp)
            .width(140.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp),
        content = {
            Column(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp)
                        .padding(
                            8.dp
                        ),
                    painter = painterResource(id = imageID),
                    contentDescription = null
                )
                Text(
                    text = "Gourmet", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,

                    )

                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "Selections", textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )


            }
        }

    )

}
