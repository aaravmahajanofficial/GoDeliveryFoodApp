package com.example.godeliveryapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.go_deliver.presentation.common.BestsellerTag
import com.example.godeliveryapp.R
import com.example.zomatoclone.presentation.Dimens.ExtraSmallPadding2
import com.example.zomatoclone.presentation.Dimens.ExtraSmallPadding3
import com.example.zomatoclone.presentation.Dimens.MediumPadding1

@Composable
fun MenuItem(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier
            .height(200.dp)
            .padding(top = MediumPadding1, start = MediumPadding1),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {

        Column {
            Row(modifier = Modifier) {
                Icon(
                    imageVector = Icons.Rounded.AccountBox,
                    tint = colorResource(id = R.color.primaryColor),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                BestsellerTag()
            }

            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

            Text(
                text = "Blueberry Cheese Cake",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding2))

            Text(
                text = "₹155",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(end = MediumPadding1),
            contentAlignment = Alignment.BottomEnd

        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(160.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,

                )

            OutlinedButton(
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
                    .offset(y = 16.dp)
                    .offset(x = (-(22).dp)),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primaryColor),
                ),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(id = R.color.primaryColor)
                )

            ) {
                Text(
                    "ADD +", style = MaterialTheme.typography.titleMedium

                )

            }
        }


    }


}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewPage() {
//
//    ZomatoCloneTheme {
//        MenuItem()
//    }
//
//}