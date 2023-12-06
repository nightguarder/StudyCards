package com.example.studycards.ui

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
import com.example.studycards.ui.welcome.Welcome

object MainDestinations {
    const val WELCOME_ROUTE = "welcome"
    const val COURSES_ROUTE = "courses"
    const val COURSE_DETAIL_ROUTE = "course"
    const val COURSE_DETAIL_ID_KEY = "courseId"
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.COURSES_ROUTE,
    showWelcomeInitially: Boolean = true
) {
    // welcome could be read from shared preferences.
    val welcomeComplete = remember(showWelcomeInitially) {
        mutableStateOf(!showWelcomeInitially)
    }

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.WELCOME_ROUTE) {
            // Intercept back in welcome: make it finish the activity
            BackHandler {
                finishActivity()
            }
            //TODO: WELCOME page
            Welcome(
                welcomeComplete = {
                    // Set the flag so that welcome is not shown next time.
                    welcomeComplete.value = true
                    actions.welcomeComplete()
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
        //TODO: Routing by composable

    }
}


/**
 * Models the navigation actions in the app and remembers the state.
 */
class MainActions(navController: NavHostController) {
    val welcomeComplete: () -> Unit = {
        navController.popBackStack()
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