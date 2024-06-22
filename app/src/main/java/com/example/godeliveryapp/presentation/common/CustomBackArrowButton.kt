package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.R

@Composable
fun CustomBackArrowButton(modifier: Modifier = Modifier, navController: NavController) {

    Box(
        modifier = Modifier
            .clickable { navController.navigateUp() }
            .size(42.dp),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = null,
            tint = colorResource(id = R.color.black),
            modifier = Modifier.scale(1.2f)
        )
    }


}