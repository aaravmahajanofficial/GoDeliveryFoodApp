package com.example.godeliveryapp.presentation.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.SupabaseAuthViewModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.navigation.Route

@Composable
fun OtpPageView(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: SupabaseAuthViewModel = hiltViewModel()
) {

    val isEnable = remember {
        mutableStateOf(false)
    }

    // 6 text fields for OTP, so 6 mutable state
    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }
    var otp5 by remember { mutableStateOf("") }
    var otp6 by remember { mutableStateOf("") }

    val focusRequesters = remember {
        Array(6) { FocusRequester() }
    }

    val otpStatesArray = arrayOf(otp1, otp2, otp3, otp4, otp5, otp6)

    LaunchedEffect(otp1, otp2, otp3, otp4, otp5, otp6) {
        // navigate to next screen
        isEnable.value =
            otp1.isNotEmpty() && otp2.isNotEmpty() && otp3.isNotEmpty() && otp4.isNotEmpty() && otp5.isNotEmpty() && otp6.isNotEmpty()
    }


    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable { navController.navigateUp() }
                    .background(color = Color.White, shape = CircleShape)
                    .size(42.dp), contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.scale(1f)
                )
            }

            Box(
                modifier = Modifier
                    .padding(ExtraSmallPadding3)
                    .height(screenHeight.div(3)), contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.forgot_password_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }

            Spacer(modifier = Modifier.padding(NormalPadding))


            Text(
                text = "OTP Verification",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                text = "Enter the verification code we just sent on your email address.",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium, color = Color.Gray
                ),
                modifier = Modifier.align(Alignment.Start),
            )

            Spacer(modifier = Modifier.padding(ExtraSmallPadding1))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                for (i in otpStatesArray.indices) {
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = if (i != 5) ImeAction.Next else ImeAction.Done
                        ),
                        maxLines = 1,
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.black),
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        ),
                        singleLine = true,
                        value = otpStatesArray[i],
                        onValueChange = {
                            if (it.length <= 1) {
                                otpStatesArray[i] = it
                                when (i) {
                                    0 -> otp1 = it
                                    1 -> otp2 = it
                                    2 -> otp3 = it
                                    3 -> otp4 = it
                                    4 -> otp5 = it
                                    5 -> otp6 = it
                                }

                                if (it.length == 1 && i != 5) {
                                    focusRequesters[i + 1].requestFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .size(screenWidth / 8, screenHeight / 14)
                            .focusRequester(focusRequesters[i]),
                        shape = RoundedCornerShape(5.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(id = R.color.lightGray),
                            unfocusedContainerColor = if (otpStatesArray[i].isNotEmpty()) Color.White else colorResource(
                                id = R.color.lightestGray
                            ),
                            focusedContainerColor = Color.White,
                            focusedBorderColor = colorResource(id = R.color.primaryColor),
                        )
                    )
                }

            }

            Spacer(modifier = Modifier.padding(1.dp))

            TextButton(
                elevation = ButtonDefaults.elevatedButtonElevation(),
                onClick = { navController.navigate(Route.LoginPage.route) },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 14),
                shape = RoundedCornerShape(5.dp),
                colors = if (isEnable.value) ButtonDefaults.textButtonColors(colorResource(id = R.color.primaryColor)) else ButtonDefaults.textButtonColors(
                    Color.LightGray
                ),
            ) {

                Text(
                    text = "Verify",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.black)
                )

            }


        }


    }

}