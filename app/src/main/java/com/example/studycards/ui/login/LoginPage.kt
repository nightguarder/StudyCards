package com.example.studycards.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studycards.R
import com.example.studycards.data.login.LoginUIEvent
import com.example.studycards.ui.registration.ClickableLoginText
import com.example.studycards.ui.registration.EmailTextField
import com.example.studycards.ui.registration.NormalText
import com.example.studycards.ui.registration.PasswordTextField
import com.example.studycards.viewmodels.LoginViewModel


@Composable
fun Login(
    loginViewModel: LoginViewModel = viewModel(),
    onLoginComplete: () -> Unit,
    onWelcomeComplete: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Login Header
        NormalText(value = stringResource(id = R.string.login))
        //Some Space
        Spacer(modifier = Modifier.height(20.dp))

        //Email
        EmailTextField(
            labelValue = stringResource(id = R.string.email),
            painterResource = painterResource(id = R.drawable.email),
            onTextChanged = {
                loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
            },
            errorStatus = loginViewModel.loginUIState.value.emailError
        )

        Spacer(modifier = Modifier.height(5.dp))

        //Password
        PasswordTextField(
            labelValue = stringResource(id = R.string.password),
            painterResource(id = R.drawable.lock),
            onValueSelected = {
                loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
            },
            errorStatus = loginViewModel.loginUIState.value.passwordError
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonCustom(
            value = stringResource(id = R.string.login),
            onButtonClicked = {
                loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                onLoginComplete()
            },
            isEnabled = loginViewModel.allValidationsPassed.value
        )
        Spacer(modifier = Modifier.height(16.dp))
        ClickableLoginText(tryingToLogin = false, onTextSelected = {
            onWelcomeComplete()
        })
    }
}

@Composable
fun ButtonCustom(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }

    }
}

@Preview(name = "Login")
@Composable
private fun LoginPreview() {
    Login(onLoginComplete = { }, onWelcomeComplete = { })
}