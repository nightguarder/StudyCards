package com.example.studycards.ui.courses

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.studycards.R
import com.example.studycards.ui.MainDestinations


/**
 * Temporary Destinations
 */
private object CoursesDestinations {
    const val FEATURED_ROUTE = "courses/featured"
    const val MY_COURSES_ROUTE = "courses/my"
    const val SEARCH_COURSES_ROUTE = "courses/search"
}

//TODO: Redo Courses to Classes and use ROOM
fun NavGraphBuilder.courses(
    onCourseSelected: (Long, NavBackStackEntry) -> Unit,
    welcomeComplete: State<Boolean>, // https://issuetracker.google.com/174783110
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(CourseTabs.FEATURED.route) { from ->
        // Show Welcome instead if not shown yet.
        LaunchedEffect(welcomeComplete) {
            if (!welcomeComplete.value) {
                navController.navigate(MainDestinations.WELCOME_ROUTE)
            }
        }
        if (welcomeComplete.value) {
            FeaturedCourses(
            )
        }
    }
    composable(CourseTabs.MY_COURSES.route) { from ->
        MyCourses(
        )
    }
    composable(CourseTabs.SEARCH.route) {
        SearchCourses()
    }
}

@Composable
fun CoursesAppBar() {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.height(80.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.google_downasaur),
            contentDescription = null
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.label_profile)
            )
        }
    }
}

enum class CourseTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    MY_COURSES(R.string.my_courses, R.drawable.apps, CoursesDestinations.MY_COURSES_ROUTE),
    FEATURED(R.string.featured, R.drawable.hexagon_multiple, CoursesDestinations.FEATURED_ROUTE),
    SEARCH(R.string.search, R.drawable.ic_search, CoursesDestinations.SEARCH_COURSES_ROUTE)
}
