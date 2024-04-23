package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.navigation.Route

@Composable
fun DishCategoryButton(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(route = Route.PizzaScreen.route) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(70.dp)
                .background(
                    color = colorResource(id = R.color.lightGray),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Image(
                modifier = Modifier.scale(0.6f),
                painter = painterResource(id = R.drawable.mart2),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Sandwich",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
            color = colorResource(
                id = R.color.black
            ),
        )
    }
}