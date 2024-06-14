package com.example.godeliveryapp.presentation.onBoarding.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.SupabaseAuthViewModel
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.NormalPadding

@Composable
fun ForgotPasswordPageView(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: SupabaseAuthViewModel = hiltViewModel()
) {
    var emailFieldController by remember {
        mutableStateOf(
            ""
        )
    }
    val openDialog = remember { mutableStateOf(false) }
    val viewState = authViewModel.viewState.collectAsState(initial = ViewState.Empty).value
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

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
                    .padding(Dimens.ExtraSmallPadding3)
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
                text = "Forgot Password?",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                text = "Don't worry! It occurs. Please enter the email address linked with your account.",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium, color = Color.Gray

                ),
                modifier = Modifier.align(Alignment.Start),

                )

            Spacer(modifier = Modifier.padding(Dimens.ExtraSmallPadding1))

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                maxLines = 1,
                placeholder = {
                    Text(
                        text = "Enter your email",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        color = Color.LightGray
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                value = emailFieldController,
                onValueChange = { emailFieldController = it },
                shape = RoundedCornerShape(5.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = colorResource(id = R.color.black),
                    focusedTextColor = colorResource(id = R.color.black),
                    cursorColor = colorResource(id = R.color.black),
                    errorCursorColor = Color.Red,
                    errorBorderColor = Color.Red,

                    )

            )

            Spacer(modifier = Modifier.padding(1.dp))

            TextButton(
                elevation = ButtonDefaults.elevatedButtonElevation(),
                onClick = { authViewModel.forgotPassword(emailFieldController) },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 14),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = colorResource(id = R.color.primaryColor),
                )
            ) {

                Text(
                    text = "Send Code",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.black)
                )

            }


        }

        when (viewState) {

            is ViewState.Error -> {
                openDialog.value = true
            }

            ViewState.Empty -> {}
            ViewState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(color = colorResource(id = R.color.primaryColor))

                }
            }

            ViewState.Success -> {
                navController.navigate("otp_screen/${emailFieldController}")
                Toast.makeText(
                    navController.context,
                    "Password reset link sent to your email",
                    Toast.LENGTH_SHORT
                ).show()
                authViewModel.resetUserState()
            }
        }

        if (openDialog.value) {
            AlertDialog(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ErrorOutline,
                        contentDescription = null,
                        tint = colorResource(id = R.color.black)
                    )

                },
                shape = MaterialTheme.shapes.extraSmall,
                containerColor = colorResource(id = R.color.white),
                title = {
                    Text(
                        text = "Authentication Error",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium),
                        color = colorResource(id = R.color.black)
                    )
                },
                text = {
                    Text(
                        text = (viewState as ViewState.Error).string,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        color = colorResource(id = R.color.black)
                    )
                },
                onDismissRequest = { openDialog.value = false }, confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            authViewModel.resetUserState()

                        }) {

                        Text(
                            text = "Ok",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = colorResource(id = R.color.black)
                        )

                    }
                })

        }


    }


}