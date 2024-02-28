package com.example.zomatoclone.presentation.onBoarding.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
) {

    var textFieldValue by remember {
        mutableStateOf(
            ""
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            painter = painterResource(id = R.drawable.mart3),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(MediumPadding2))

        Text(
            text = "India's #1 Food Delivery\nand Dining App",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(ExtraSmallPadding1),
            style = MaterialTheme.typography.titleLarge

        )

        Spacer(modifier = Modifier.padding(ExtraSmallPadding2))

        Text(
            text = "Log in or sign up",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(ExtraSmallPadding1),
            style = MaterialTheme.typography.bodyLarge

        )

        Spacer(modifier = Modifier.padding(ExtraSmallPadding2))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1, end = MediumPadding1),
        ) {

            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                label = {
                    Text(
                        "Enter Phone Number",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                shape = RoundedCornerShape(10.dp),

                )

        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        TextButton(
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .padding(start = MediumPadding1, end = MediumPadding1),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.textButtonColors(colorResource(id = R.color.primaryColor))
        ) {

            Text(
                text = "Continue", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = "or",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge

        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        BorderStroke(
                            color = Color.LightGray,
                            width = 1.dp,
                        ),
                        shape = RoundedCornerShape(100.dp),
                    ),
            ) {

                Image(

                    painterResource(id = R.drawable.mart3), contentDescription = null,
                    modifier = Modifier.size(32.dp),
                )

            }

            Spacer(modifier = Modifier.width(MediumPadding2))

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        BorderStroke(
                            color = Color.LightGray,
                            width = 1.dp,
                        ),
                        shape = RoundedCornerShape(100.dp),
                    ),
            ) {

                androidx.compose.material3.Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null
                )

            }
        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            textAlign = TextAlign.Center,
            text = "By continuing, you agree to our \nTerms of Service Privacy Policy Content Policy",
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(
                id = R.color.primaryColor
            )

        )


    }


}

