package com.example.godeliveryapp.presentation.location

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding

@Composable
fun AddDetails(modifier: Modifier = Modifier) {

    var houseNumber by remember {
        mutableStateOf("")
    }

    val floorNumber by remember {
        mutableStateOf("")
    }

    val apartmentName by remember {
        mutableStateOf("")
    }

    val howToReach by remember {
        mutableStateOf("")
    }

    val contactNumber by remember {
        mutableStateOf("")
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

            Spacer(modifier = Modifier.height(MediumPadding2))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
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
                        .height(36.dp)
                        .width(80.dp)
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

            Column(modifier = Modifier.fillMaxWidth()) {

                Text(
                    text = "title",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )

                OutlinedTextField(value = houseNumber, onValueChange = { houseNumber = it })

            }


        }

    }


}
