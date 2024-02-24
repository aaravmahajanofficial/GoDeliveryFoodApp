package com.example.zomatoclone.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

object TextStylingUtils {
    @Composable
    fun boldWhiteText(textColor : Int, ) : TextStyle {
        return MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Bold,
            color = colorResource(id = textColor),
            fontFamily = monasans // assuming metropolis is defined somewhere
        )
    }
}