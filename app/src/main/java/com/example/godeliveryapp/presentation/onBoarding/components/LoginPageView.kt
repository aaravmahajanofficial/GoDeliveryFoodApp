package com.example.godeliveryapp.presentation.onBoarding.components

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.SupabaseAuthViewModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPageView(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SupabaseAuthViewModel = hiltViewModel()
) {

    val openDialog = remember { mutableStateOf(false) }

    val userState = viewModel.userState.collectAsState(initial = UserState.Empty).value
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ExtraSmallPadding3)
                    .height(screenHeight.div(5)), contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.drone_delivery_amico),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(MediumPadding2))


            Text(
                text = "Get started with App",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.padding(ExtraSmallPadding2))

            val annotatedLoginOrSignUpString = buildAnnotatedString {

                append("Login or ")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold, color = colorResource(
                            id = R.color.black
                        ),
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("SignUp")
                }
                append(" to use app")
            }

            Text(
                text = annotatedLoginOrSignUpString,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Medium, color = colorResource(
                        id = R.color.gray
                    )
                ),
                modifier = Modifier.align(Alignment.Start),

                )

            Spacer(modifier = Modifier.padding(NormalPadding))


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                label = {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                        color = Color.LightGray
                    )
                },
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

            Spacer(modifier = Modifier.padding(ExtraSmallPadding2))

            OutlinedTextField(
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                label = {
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
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

                    )

            )

            Spacer(modifier = Modifier.height(NormalPadding))

            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                color = colorResource(id = R.color.gray),
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(screenHeight / 6))

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

                        viewModel.login(
                            context = context,
                            userEmail = emailFieldController,
                            userPassword = passwordFieldController
                        )

                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.textButtonColors(colorResource(id = R.color.black))
                ) {

                    Text(
                        text = "Continue", textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )

                }

                Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                val annotatedString = buildAnnotatedString {
                    append("By continuing, I accept the ")

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold, color = colorResource(
                                id = R.color.black
                            )
                        )
                    ) {
                        append("Terms & Conditions")
                    }

                    append(" & ")

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold, color = colorResource(
                                id = R.color.black
                            )
                        )
                    ) {
                        append("Privacy Policy")
                    }
                }


                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    text = annotatedString,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.gray),
                    maxLines = 1

                )
            }


        }
    }

    when (userState) {
        is UserState.Error -> {

            openDialog.value = true

        }

        UserState.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        }

        UserState.Success -> {
            navController.navigate(Route.HomeScreen.route) {
                popUpTo(Route.LoginPage.route) { inclusive = true }
            }
        }

        UserState.Empty -> {

        }
    }

    if (openDialog.value) {
        AlertDialog(
            title = {
                Text(text = "Oops!")
            },
            text = {
                Text(text = (userState as UserState.Error).string)
            },
            onDismissRequest = { openDialog.value = false }, confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        viewModel.resetUserState()

                    }) {

                    Text(text = "Confirm")

                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        viewModel.resetUserState()
                    }) {

                    Text(text = "Dismiss")


                }
            })
    }


}
