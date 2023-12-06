package com.example.studycards.data.login

data class LoginUIState(
    var email: String = "",
    var password: String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false

)