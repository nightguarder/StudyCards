package com.example.studycards.ui.course

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studycards.R
import com.example.studycards.data.model.Course
import com.example.studycards.data.model.CourseRepo
import com.example.studycards.data.model.courses
import com.example.studycards.ui.utils.NetworkImage

@Composable
fun CourseDetails(
    courseId: Long,
    selectCourse: (Long) -> Unit,
) {
    // Simplified for the sample
    val course = remember(courseId) { CourseRepo.getCourse(courseId) }
    // TODO: Show error if course not found.
    CourseDetails(course, selectCourse)
}

@Composable
fun CourseDetails(
    course: Course,
    selectCourse: (Long) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4f / 3f)
            .clickable { selectCourse(course.id) }
    ) {
        NetworkImage(
            url = course.thumbUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = course.name,
                style = TextStyle(
                    color = Color.Yellow,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.course_desc),
                style = TextStyle(color = Color.White, fontSize = 14.sp)
            )
        }
    }
}

@Preview(name = "Course Details")
@Composable
private fun CourseDetailsPreview() {
    val courseId = courses.first().id
    CourseDetails(
        courseId = courseId,
        selectCourse = { },
    )
}