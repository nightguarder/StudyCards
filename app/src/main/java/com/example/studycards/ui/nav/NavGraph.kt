package com.example.studycards.ui.nav

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.studycards.ui.courses.CourseTabs
import com.example.studycards.ui.courses.courses
import com.example.studycards.ui.login.Login
import com.example.studycards.ui.registration.Registration
import com.example.studycards.ui.welcome.Welcome
import com.google.firebase.auth.FirebaseAuth

object MainDestinations {
    const val WELCOME_ROUTE = "welcome"
    const val REGISTER_ROUTE = "registration"
    const val LOGIN_ROUTE = "login"
    const val COURSES_ROUTE = "courses"
    const val COURSE_DETAIL_ROUTE = "course"
    const val COURSE_DETAIL_ID_KEY = "courseId"
}

private lateinit var auth: FirebaseAuth

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.WELCOME_ROUTE,
    showWelcomeInitially: Boolean = true
) {
    // welcome could be read from shared preferences.
    val welcomeComplete = remember(showWelcomeInitially) {
        mutableStateOf(!showWelcomeInitially)
    }
    //Don't show welcome after Registry and go to Login
    val registerComplete = remember(showWelcomeInitially) {
        mutableStateOf(!showWelcomeInitially)
    }


    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.LOGIN_ROUTE) {
            //Check if user is logged in or not
        }
        //The app navigates using composable
        composable(MainDestinations.WELCOME_ROUTE) {
            // Intercept back in welcome: make it finish the activity
            BackHandler {
                finishActivity()
            }
            //DONEß WELCOME page
            Welcome(
                welcomeComplete = {
                    // Set the flag so that welcome is not shown next time.
                    welcomeComplete.value = true
                    actions.welcomeComplete()
                }
            )

        }
        composable(route = "registration") {
            // DONE: Registration Page
            Registration(
                onRegistrationComplete = {
                    // Navigate to Login after successful registration
                    //And set flag so welcome is not shown next time.
                    registerComplete.value = true
                    actions.navigateToLogin()
                }
            )
        }

        composable(route = "login") {
            // TODO: Login Page
            Login(
                onLoginComplete = {
                    // Navigate to Courses after successful login
                    navController.navigate(MainDestinations.COURSES_ROUTE)
                },
                onWelcomeComplete = {
                    //Navigate back to Register page
                    navController.navigate(MainDestinations.REGISTER_ROUTE)
                }
            )

        }
        navigation(
            route = MainDestinations.COURSES_ROUTE,
            startDestination = CourseTabs.FEATURED.route
        ) {
            courses(
                onCourseSelected = actions.openCourse,
                welcomeComplete = welcomeComplete,
                navController = navController,
                modifier = modifier
            )
        }
    }
}


/**
 * Models the navigation actions in the app and remembers the state.
 */
class MainActions(navController: NavHostController) {
    //First Start
    val welcomeComplete: () -> Unit = {
        navController.navigate("${MainDestinations.REGISTER_ROUTE}")
        //pop Back Stack goes to home screen
        //navController.popBackStack()
    }

    // Used from COURSES_ROUTE
    val openCourse = { newCourseId: Long, from: NavBackStackEntry ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.COURSE_DETAIL_ROUTE}/$newCourseId")
        }
    }

    // Used from COURSE_DETAIL_ROUTE
    val relatedCourse = { newCourseId: Long, from: NavBackStackEntry ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.COURSE_DETAIL_ROUTE}/$newCourseId")
        }
    }

    //Navigate after successful registration
    val navigateToLogin: () -> Unit = {
        navController.navigate("${MainDestinations.LOGIN_ROUTE}")
    }
    val navigateToCourses: () -> Unit = {
    }

    // Used from COURSE_DETAIL_ROUTE
    val upPress: (from: NavBackStackEntry) -> Unit = { from ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigateUp()
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED