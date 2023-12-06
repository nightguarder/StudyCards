package com.example.studycards.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.studycards.data.register.RegisterUIEvent
import com.example.studycards.data.register.RegisterUIState
import com.example.studycards.data.validator.Validator
import com.google.firebase.auth.FirebaseAuth

class RegistrationViewModel : ViewModel() {

    private val TAG = RegistrationViewModel::class.simpleName

    //Local states
    var registrationUIState = mutableStateOf(RegisterUIState())

    var allValidationsPassed = mutableStateOf(false)

    var registerInProgress = mutableStateOf(false)

    fun onEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    userName = event.firstName
                )
                printState()
            }

            is RegisterUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }


            is RegisterUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }
            //Trigger the registration only if the validation is OK
            is RegisterUIEvent.RegisterButtonClicked -> {
                register()
            }

            else -> {
                printState()
            }
        }
        validateDataWithRules()
    }


    private fun register() {
        Log.d(TAG, "Inside_register()")
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.userName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )


        Log.d(TAG, "Inside_validateDataWithRules()")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )
        // UI State update
        allValidationsPassed.value = fNameResult.status &&
                emailResult.status && passwordResult.status

    }

    //PErforming the check
    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }


    private fun createUserInFirebase(email: String, password: String) {

        registerInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")

                registerInProgress.value = false
                if (it.isSuccessful) {
                    //DONE: Navigate to LOGIN Screen
                    //ScreenNavigator.navigateTo(Screen.registerScreen)
                    Log.d(TAG, "Registration process completed successfully")
                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }
    }
}
