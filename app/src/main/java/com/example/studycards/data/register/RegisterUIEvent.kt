package com.example.studycards.data.register


//Watch for these values
sealed class RegisterUIEvent {
    data class FirstNameChanged(val firstName: String) : RegisterUIEvent()
    data class EmailChanged(val email: String) : RegisterUIEvent()
    data class PasswordChanged(val password: String) : RegisterUIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterUIEvent()
    object RegisterButtonClicked : RegisterUIEvent()
}


