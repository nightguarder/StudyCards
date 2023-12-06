package com.example.studycards.ui.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studycards.R
import com.example.studycards.data.register.RegisterUIEvent
import com.example.studycards.viewmodels.RegistrationViewModel

@Composable
fun Registration(
    registrationViewModel: RegistrationViewModel = viewModel(),
    onRegistrationComplete: () -> Unit
) {

    var isLoading by remember { mutableStateOf(false) }
    var errorState by remember { mutableStateOf<String?>(null) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmailTextField(
            labelValue = stringResource(id = R.string.email),
            painterResource = painterResource(id = R.drawable.email),
            onTextChanged = {
                registrationViewModel.onEvent(RegisterUIEvent.EmailChanged(it))
            },
            errorStatus = registrationViewModel.registrationUIState.value.emailError
        )

        PasswordTextField(
            labelValue = stringResource(id = R.string.password),
            painterResource = painterResource(id = R.drawable.lock),
            onValueSelected = {
                registrationViewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
            },
            errorStatus = registrationViewModel.registrationUIState.value.passwordError
        )

        PasswordTextField(
            labelValue = stringResource(id = R.string.password),
            painterResource = painterResource(id = R.drawable.lock),
            onValueSelected = {
                registrationViewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
            },
            errorStatus = registrationViewModel.registrationUIState.value.passwordError
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // TODO:? Validate the input using UIEvent
                registrationViewModel.onEvent(RegisterUIEvent.RegisterButtonClicked)
                //After the Validator check mar registration as complete
                onRegistrationComplete()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            // Show loading indicator
            CircularProgressIndicator()
        }

        errorState?.let {
            // Show error message if there is any
            Text(
                text = it,
                color = MaterialTheme.colors.error
            )
        }
    }
}

//Custom Composable for the Password Check and Email Input Fields
@Composable
fun EmailTextField(
    labelValue: String, painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            focusedLabelColor = Color.Black,
            cursorColor = Color.Black,
            backgroundColor = Color.Unspecified
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}

@Composable
fun PasswordTextField(
    labelValue: String, painterResource: Painter,
    onValueSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            focusedLabelColor = Color.Black,
            cursorColor = Color.Black,
            backgroundColor = Color.Unspecified
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onValueSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Preview(name = "Registration")
@Composable
private fun RegistrationPreview() {
    Registration(onRegistrationComplete = { })
}
