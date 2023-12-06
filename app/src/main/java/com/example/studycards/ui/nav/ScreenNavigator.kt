package com.example.studycards.ui.nav

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object ScreenNavigator {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.registerScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}