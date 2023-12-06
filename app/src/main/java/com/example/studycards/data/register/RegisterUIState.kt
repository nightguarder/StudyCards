package com.example.studycards.data.register

//Class for RegistrationViewModel
data class RegisterUIState(
    var userName: String = "",
    var email: String = "",
    var password: String = "",


    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false,


    )
