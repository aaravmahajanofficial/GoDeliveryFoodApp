package com.example.godeliveryapp.presentation.common

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.cart.CartViewModel

@Composable
fun CartItemCardView(
    modifier: Modifier = Modifier,
    cartItemModel: CartItemModel,
    viewModel: CartViewModel = hiltViewModel()
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(screenHeight.div(8))
                    .width(screenWidth.div(4))
                    .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                    .clip(
                        RoundedCornerShape(12.dp)
                    ), contentAlignment = Alignment.Center
            ) {

                AsyncImage(
                    model = cartItemModel.menuItemModel.imageId,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = ExtraSmallPadding3)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {

                Text(
                    text = cartItemModel.menuItemModel.itemName,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.black
                    ),
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.height(MediumPadding2))

                Text(
                    text = "₹ ${cartItemModel.menuItemModel.itemPrice}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.gray)
                )

            }

            Box(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .background(
                        color = colorResource(id = R.color.lightGray),
                        shape = RoundedCornerShape(18.dp)
                    )
                    .height(35.dp)
                    .width(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Remove,
                        contentDescription = null, modifier = Modifier
                            .scale(0.8f)
                            .clickable {

                                if (cartItemModel.quantity > 1) {
                                    viewModel.upsertCartItem(cartItemModel.copy(quantity = cartItemModel.quantity - 1))
                                } else {
                                    viewModel.deleteCartItem(cartItemModel)
                                }

                            },
                        tint = colorResource(
                            id = R.color.black
                        )
                    )
                    Text(
                        text = cartItemModel.quantity.toString(),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .scale(0.8f)
                            .clickable {
                                viewModel.upsertCartItem(cartItemModel.copy(quantity = cartItemModel.quantity + 1))
                            },
                        tint = colorResource(
                            id = R.color.black
                        )
                    )

                }
            }
        }

    }

}