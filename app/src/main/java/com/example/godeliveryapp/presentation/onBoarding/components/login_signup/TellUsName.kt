package com.example.godeliveryapp.presentation.onBoarding.components.login_signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.ui.theme.GoDeliveryApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TellUsName(modifier: Modifier = Modifier) {

    var textFieldValue by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier
            .padding(top = MediumPadding1)
            .fillMaxSize(),
        topBar = {
            TopAppBar({
                Text(
                    "Welcome to Zomato",
                    style = MaterialTheme.typography.titleLarge
                )
            }, navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Navigate back")
                }
            })
        },
        content =
        { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = "Tell us your name",
                    style = MaterialTheme.typography.bodyLarge
                )
                
                Spacer(modifier = Modifier.height(60.dp))


                TextField(
                    modifier = Modifier
                        .padding(start = MediumPadding1, end = MediumPadding1)
                        .width(
                            350.dp
                        ),
                    value = textFieldValue, onValueChange = { textFieldValue = it },
                    label = {
                        Text(
                            text = "Name",
                            style = MaterialTheme.typography.titleMedium
                        )
                    },

                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.White)
                    )

                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth()
                        .padding(start = MediumPadding1, end = MediumPadding1),
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (textFieldValue.isNotEmpty()) colorResource(id = R.color.primaryColor) else Color.LightGray
                    )
                ) {

                    Text(
                        text = "Continue", textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(

                            color = if (textFieldValue.isNotEmpty()) Color.White
                            else Color.Gray

                        )
                    )

                }

                Spacer(modifier = Modifier.height(MediumPadding1))


            }
        })

}

@Preview(showBackground = true)
@Composable
fun TellUsNamePreview() {
    GoDeliveryApp {
        TellUsName()
    }
}