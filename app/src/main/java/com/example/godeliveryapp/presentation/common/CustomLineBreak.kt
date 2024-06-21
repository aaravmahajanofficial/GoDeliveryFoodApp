package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2

@Composable
fun CustomLineBreak(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = Modifier
            .padding(
                top = MediumPadding2,
                bottom = MediumPadding2
            )
            .fillMaxWidth()
            .height(1.dp)
            .background(color = colorResource(id = R.color.lightGray))
    )
}