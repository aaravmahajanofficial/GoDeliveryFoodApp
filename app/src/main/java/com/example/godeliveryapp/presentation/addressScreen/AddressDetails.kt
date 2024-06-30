package com.example.godeliveryapp.presentation.addressScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomTextButton


@Composable
fun AddDetails(modifier: Modifier = Modifier) {

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp

    var houseNumber by remember {
        mutableStateOf("")
    }

    var floorNumber by remember {
        mutableStateOf("")
    }
    var apartmentName by remember {
        mutableStateOf("")
    }

    var howToReach by remember {
        mutableStateOf("")
    }

    var contactNumber by remember {
        mutableStateOf("")
    }

    val isFilled by remember {
        derivedStateOf {
            houseNumber.isNotBlank() && floorNumber.isNotBlank() && apartmentName.isNotBlank() && howToReach.isNotBlank() && contactNumber.isNotBlank()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(NormalPadding),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        item {

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null,
                modifier = Modifier.scale(1.2f)
            )

            Spacer(modifier = Modifier.height(NormalPadding))

            Text(
                text = "Add Address Details",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.black)
            )

            Spacer(modifier = Modifier.height(NormalPadding))


            Box(
                modifier = Modifier
                    .padding(ExtraSmallPadding3)
                    .height((screenHeight / 4).dp), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.deliverysvg),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(NormalPadding))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = "HSR Layout",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.black)
                    )

                    Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                    Text(
                        text = "Bengaluru, Karnataka, India",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Gray
                    )
                }

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
                        text = "Change",
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }

            Spacer(modifier = Modifier.height(MediumPadding2))
        }

        item {

            CustomTextFields(
                value = houseNumber,
                title = "House / Flat number",
                onValueChange = { newValue: String -> houseNumber = newValue })

            Spacer(modifier = Modifier.height(MediumPadding1))

            CustomTextFields(
                value = floorNumber,
                title = "Floor number",
                onValueChange = { newValue: String -> floorNumber = newValue })

            Spacer(modifier = Modifier.height(MediumPadding1))

            CustomTextFields(
                value = apartmentName,
                title = "Apartment / Building name",
                onValueChange = { newValue: String -> apartmentName = newValue })

            Spacer(modifier = Modifier.height(MediumPadding1))

            CustomTextFields(
                value = howToReach,
                title = "How to reach",
                onValueChange = { newValue: String -> howToReach = newValue })

            Spacer(modifier = Modifier.height(MediumPadding1))

            CustomTextFields(
                value = contactNumber,
                title = "Contact number",
                onValueChange = { newValue: String -> contactNumber = newValue },
            )
            Spacer(modifier = Modifier.height(MediumPadding1))

        }


        item {

            Text(
                text = "Address type",
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .background(color = Color.Transparent, shape = CircleShape)
                        .height((screenHeight / 24).dp)
                        .width((screenWidth / 5).dp)
                        .border(
                            BorderStroke(color = Color.Gray, width = 0.dp),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Home",
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                }

                Spacer(modifier = Modifier.width(NormalPadding))

                Box(
                    modifier = Modifier
                        .background(color = Color.Transparent, shape = CircleShape)
                        .height((screenHeight / 24).dp)
                        .width((screenWidth / 5).dp)
                        .border(
                            BorderStroke(color = Color.Gray, width = 0.dp),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Office",
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                }

                Spacer(modifier = Modifier.width(NormalPadding))

                Box(
                    modifier = Modifier
                        .background(color = Color.Transparent, shape = CircleShape)
                        .height((screenHeight / 24).dp)
                        .width((screenWidth / 5).dp)
                        .border(
                            BorderStroke(color = Color.Gray, width = 0.dp),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Others",
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                }

            }

            Spacer(modifier = Modifier.height(MediumPadding2))
        }

        item {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween

            ) {

                CustomTextButton(
                    buttonText = "Add Address",
                    textColorId = R.color.white,
                    buttonColorId = if (isFilled) R.color.black else R.color.black,
                    fontWeight = FontWeight.Medium,
                    isFilled = isFilled
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                CustomTextButton(
                    buttonText = "Cancel",
                    textColorId = R.color.black,
                    buttonColorId = R.color.lightGray,
                    fontWeight = FontWeight.SemiBold,
                    onClick = { },
                )
            }
        }


    }


}

@Composable
fun CustomTextFields(
    value: String,
    title: String,
    onValueChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            color = colorResource(id = R.color.black),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        Spacer(modifier = Modifier.height(ExtraSmallPadding2))

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = {
                if (title == "Contact number") {
                    val newValue = it.take(10)
                    onValueChange(newValue)
                } else {
                    onValueChange(it)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray
            ),
            textStyle = TextStyle(
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Normal
            ),
            shape = MaterialTheme.shapes.extraSmall,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (title == "Contact number") KeyboardType.Phone else KeyboardType.Text,
                imeAction = if (title == "Contact number") ImeAction.Done else ImeAction.Next
            ),
            isError = value.isBlank()

        )

    }
}