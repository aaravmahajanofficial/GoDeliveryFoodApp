package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.NormalPadding

@Composable
fun CartDeliveryOptions() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
    ) {
        Row(
            modifier = Modifier
                .padding(NormalPadding)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.Layers,
                contentDescription = null, modifier = Modifier.scale(1.2f),
                tint = colorResource(
                    id = R.color.black
                )
            )


            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = Dimens.ExtraSmallPadding3,
                        top = Dimens.ExtraSmallPadding2,
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {

                Text(
                    text = "Delivery Instructions",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.black
                    ),
                    maxLines = 2,
                )

                Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))

                Text(
                    text = "Leave At Door",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.black)
                )

            }

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                contentDescription = null, modifier = Modifier.scale(0.8f),
                tint = colorResource(id = R.color.gray),
            )


        }

    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = Dimens.NormalPadding,
                start = Dimens.NormalPadding,
                end = Dimens.NormalPadding
            )
            .height(1.dp)
            .background(color = colorResource(id = R.color.lightGray))
    )
}