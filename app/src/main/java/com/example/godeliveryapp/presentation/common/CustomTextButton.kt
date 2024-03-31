package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    textColorId: Int,
    buttonColorId: Int,
    fontWeight: FontWeight,
    onClick: (() -> Unit)? = null,
    isFilled: Boolean = true
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        OutlinedButton(
            enabled = isFilled,
            onClick = {
                onClick?.invoke()
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = buttonColorId
                ),
                disabledContainerColor = Color.LightGray
            ),
            border = BorderStroke(0.dp, Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {

            Text(
                text = buttonText,
                color = colorResource(id = textColorId),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight =
                    fontWeight
                )
            )


        }
    }

}