package com.example.godeliveryapp.presentation.onBoarding.components.login_signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.SupabaseAuthViewModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomBackArrowButton
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.godeliveryapp.utils.ViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPageView(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: SupabaseAuthViewModel = hiltViewModel()
) {

    val openDialog = remember { mutableStateOf(false) }
    val viewState = authViewModel.viewState.collectAsState(initial = ViewState.Empty).value
    val context = LocalContext.current

    var emailFieldController by remember {
        mutableStateOf(
            ""
        )
    }

    var passwordFieldController by remember {
        mutableStateOf(
            ""
        )
    }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val isEnable = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(
        emailFieldController,
        passwordFieldController,
    ) {
        isEnable.value =
            emailFieldController.isNotEmpty() && passwordFieldController.isNotEmpty()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            CustomBackArrowButton(navController = navController)

            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(ExtraSmallPadding3)
                    .height(screenHeight.div(12)), contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.scale(1.2f),
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.padding(ExtraSmallPadding3))


            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.SemiBold),
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                text = "Login to continue using the app",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium, color = colorResource(
                        id = R.color.gray
                    )
                ),
                modifier = Modifier.align(Alignment.Start),

                )

            Spacer(modifier = Modifier.padding(ExtraSmallPadding3))


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    placeholder = {
                        Text(
                            text = "Enter your email",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = Color.LightGray
                        )
                    },
                    maxLines = 1,
                    singleLine = true,
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
                        unfocusedTextColor = colorResource(id = R.color.black)
                    )

                )

                Spacer(modifier = Modifier.padding(ExtraSmallPadding3))

                OutlinedTextField(
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            text = "Enter password",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = Color.LightGray
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordFieldController,
                    onValueChange = { passwordFieldController = it },
                    shape = RoundedCornerShape(5.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = colorResource(id = R.color.black),
                        focusedTextColor = colorResource(id = R.color.black),
                        cursorColor = colorResource(id = R.color.black),
                        errorCursorColor = Color.Red,
                        errorBorderColor = Color.Red,
                        unfocusedTextColor = colorResource(id = R.color.black)

                    )

                )
            }

            Spacer(modifier = Modifier.height(ExtraSmallPadding1))

            val annotatedString = buildAnnotatedString {
                withStyle(
                    MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.gray)
                    )
                        .toSpanStyle()
                ) {
                    append("Forgot Password?")
                }
            }

            ClickableText(
                onClick = {

                    navController.navigate(Route.ForgotPasswordScreen.route)

                },
                modifier = Modifier.align(Alignment.End),
                text = annotatedString,
            )

            Spacer(modifier = Modifier.height(screenHeight / 4))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(),
                    onClick = {

                        if (isEnable.value) {
                            authViewModel.login(
                                context = context,
                                userEmail = emailFieldController,
                                userPassword = passwordFieldController
                            )
                        }

                    },
                    shape = RoundedCornerShape(5.dp),
                    colors = if (isEnable.value) ButtonDefaults.textButtonColors(colorResource(id = R.color.black)) else ButtonDefaults.textButtonColors(
                        Color.LightGray
                    ),
                ) {

                    Text(
                        text = "Continue", textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )

                }


            }


        }
    }

    when (viewState) {
        is ViewState.Error -> {

            openDialog.value = true

        }

        ViewState.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        }

        ViewState.Success -> {
            navController.navigate(Route.HomeScreen.route) {
                popUpTo(Route.LoginPage.route) { inclusive = true }
            }
            authViewModel.resetUserState()
        }

        ViewState.Empty -> {

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
