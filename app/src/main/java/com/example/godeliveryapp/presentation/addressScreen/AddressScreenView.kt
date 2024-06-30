package com.example.godeliveryapp.presentation.addressScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.LocationCardModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomBackArrowButton
import com.example.godeliveryapp.presentation.navigation.Route


@Composable
fun AddressScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    addressScreenViewModel: AddressScreenViewModel = hiltViewModel(),
    locationModel : LocationCardModel
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val houseNumber by addressScreenViewModel.houseNumber.collectAsState()
    val floorNumber by addressScreenViewModel.floorNumber.collectAsState()
    val apartmentName by addressScreenViewModel.apartmentName.collectAsState()
    val howToReach by addressScreenViewModel.howToReach.collectAsState()
    val contactNumber by addressScreenViewModel.contactNumber.collectAsState()
    val isFilled by addressScreenViewModel.isFilled.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }, contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = NormalPadding, end = NormalPadding, top = NormalPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            item {

                CustomBackArrowButton(navController = navController)

                Text(
                    text = "Add Address Details",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold)
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                Box(
                    modifier = Modifier
                        .height(screenHeight / 4.5f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.deliverysvg),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
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
                            text = locationModel.title,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = colorResource(id = R.color.black)
                        )

                        Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                        Text(
                            text = locationModel.address.label ?: "Address not found",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Gray
                        )
                    }

                    CustomButton(
                        buttonTitle = "Change",
                        onClick = { navController.navigate(Route.LocationScreen.route) })
                }

                Spacer(modifier = Modifier.height(MediumPadding2))
            }

            item {

                CustomTextFields(
                    value = houseNumber,
                    title = "House / Flat number",
                    focusRequester = focusRequester,
                    onValueChange = addressScreenViewModel::setHouseNumber
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                CustomTextFields(
                    value = floorNumber,
                    title = "Floor number",
                    focusRequester = focusRequester,
                    onValueChange = addressScreenViewModel::setFloorNumber
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                CustomTextFields(
                    value = apartmentName,
                    title = "Apartment / Building name",
                    focusRequester = focusRequester,
                    onValueChange = addressScreenViewModel::setApartmentName
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                CustomTextFields(
                    value = howToReach,
                    title = "How to reach",
                    focusRequester = focusRequester,
                    onValueChange = addressScreenViewModel::setHowToReach
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                CustomTextFields(
                    value = contactNumber,
                    title = "Contact number",
                    focusRequester = focusRequester,
                    onValueChange = addressScreenViewModel::setContactNumber
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
                    CustomButton(buttonTitle = "Home")
                    Spacer(modifier = Modifier.width(NormalPadding))
                    CustomButton(buttonTitle = "Office")
                    Spacer(modifier = Modifier.width(NormalPadding))
                    CustomButton(buttonTitle = "Other")
                }

                Spacer(modifier = Modifier.height(MediumPadding2))
            }

            item {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    TextButton(
                        onClick = {
                            navController.navigate(Route.HomeScreen.route).also {
                                addressScreenViewModel.upsertUserData()
                            }
                        },
                        enabled = isFilled,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 14),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.textButtonColors(
                            disabledContainerColor = Color.LightGray,
                            containerColor = colorResource(id = R.color.black),
                        )
                    ) {

                        Text(
                            text = "Add Address",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = colorResource(id = R.color.white)
                        )

                    }

                    Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                    TextButton(
                        onClick = { navController.navigate(Route.HomeScreen.route) },
                        border = BorderStroke(0.5.dp, colorResource(id = R.color.black)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 14),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = colorResource(id = R.color.white),
                        )
                    ) {

                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = colorResource(id = R.color.black)
                        )

                    }

                    Spacer(modifier = Modifier.padding(NormalPadding))

                }
            }


        }
    }


}

@Composable
private fun CustomButton(
    modifier: Modifier = Modifier,
    buttonTitle: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(color = Color.Transparent, shape = CircleShape)
            .border(
                BorderStroke(color = Color.Gray, width = 0.dp),
                shape = CircleShape
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonTitle,
            color = colorResource(id = R.color.black),
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun CustomTextFields(
    value: String,
    title: String,
    focusRequester: FocusRequester,
    onValueChange: (String) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(), contentAlignment = Alignment.Center
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
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
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
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = colorResource(id = R.color.black),
                    focusedTextColor = colorResource(id = R.color.black),
                    cursorColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black)
                ),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.black),
                    fontWeight = FontWeight.Normal
                ),

                keyboardOptions = KeyboardOptions(
                    keyboardType = if (title == "Contact number") KeyboardType.Phone else KeyboardType.Text,
                    imeAction = if (title == "Contact number") ImeAction.Done else ImeAction.Next
                ),
            )

        }
    }
}