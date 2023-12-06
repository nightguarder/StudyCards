package com.example.studycards.ui.nav

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object welcomeScreen : Screen()
    object registerScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
}

object ScreenAppRouter {
    //CurrentScreen default is Welcome

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.welcomeScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}
