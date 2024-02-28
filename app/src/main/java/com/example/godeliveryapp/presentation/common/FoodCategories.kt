package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.zomatoclone.ui.theme.GoDeliveryApp

@Composable
fun FoodOptions(

    title: String,

    ) {

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.restaurant1),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp), contentScale = ContentScale.Crop

        )

        Spacer(modifier = Modifier.padding(ExtraSmallPadding1))

        Text(
            text = title, textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }


}

//@Composable
//@Preview(showBackground = true)
//fun PreviewShow() {
//    GoDeliveryApp {
//        FoodOptions(title = "Pizza")
//    }
//}
