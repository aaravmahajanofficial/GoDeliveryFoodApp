package com.example.godeliveryapp.presentation.foodScreen.foodCategoryScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Tune
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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.godeliveryapp.R
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding

@Composable
fun CategoryScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    categoryDto: CategoryDto
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = NormalPadding,
                        start = NormalPadding,
                        end = NormalPadding
                    )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier
                        .scale(1.2f)
                        .clickable {
                            navController.navigateUp()

                        }

                )
            }

            Spacer(modifier = Modifier.height(MediumPadding1))

            Text(
                text = categoryDto.name,
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(start = NormalPadding)
            )

            Spacer(modifier = Modifier.height(NormalPadding))

            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(start = NormalPadding, end = NormalPadding)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 16).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Tune,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 10).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 6.dp, end = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Sort By",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                            )
                            Icon(
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )


                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 10).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Pure Veg",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 8).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Fast Delivery",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent, shape = CircleShape)
                            .height((screenHeight / 24).dp)
                            .width((screenHeight / 8).dp)
                            .border(
                                BorderStroke(color = Color.Gray, width = 0.dp),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ratings 4.0+",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                        )
                    }


                }
            }

            Spacer(modifier = Modifier.height(MediumPadding2))

            Text(
                text = "Popular Restaurants",
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = NormalPadding)
            )

            FoodCard(categoryDto = categoryDto)


        }

    }


}

@Composable
private fun FoodCard(categoryDto: CategoryDto) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(NormalPadding),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .height(180.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                .clip(
                    RoundedCornerShape(12.dp)
                )
        ) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(model = categoryDto.imageUrl),
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Hello Dhaba!",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.black
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()

                )
                Spacer(modifier = Modifier.height(5.dp))
                val items: List<String> = listOf("American", "Italian", "Indian")
                val formattedString = items.joinToString(" | ")

                Text(
                    text = formattedString,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.gray),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
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
                    Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding1))

                    Text(
                        text = "4.1",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.black),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                val parameters = listOfNotNull(
                    "4Km",
                    12.let { "$it Mins" },
                ).joinToString(" | ")

                Text(
                    text = parameters,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}