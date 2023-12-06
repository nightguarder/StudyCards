package com.example.studycards.data.model

import androidx.compose.runtime.Immutable

@Immutable // Tell Compose runtime that this object will not change so it can perform optimizations
data class Course(
    val id: Long,
    val name: String,
    val subject: String,
    val thumbUrl: String,
    val thumbContentDesc: String,
    val description: String = "",
    val steps: Int,
    val step: Int,
    val instructor: String = "https://i.pravatar.cc/112?$id"
)

//TODO: Move this to Db
//Fake Repository for Courses
object CourseRepo {
    fun getCourse(courseId: Long): Course = courses.find { it.id == courseId }!!
}

//Study courses *Ad more later*

val courses = listOf(
    Course(
        id = 0,
        name = "ARP",
        subject = "Architektura Počítačů",
        thumbUrl = "https://images.unsplash.com/photo-1518770660439-4636190af475?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1Mzc3NDF8MHwxfGFsbHx8fHx8fHx8fDE3MDE4OTQ1OTl8&ixlib=rb-4.0.3&q=80&w=1080",
        thumbContentDesc = "",
        steps = 7,
        step = 1
    ),
    Course(
        id = 1,
        name = "PAA",
        subject = "Programování Mobilních aplikací",
        thumbUrl = "https://images.unsplash.com/photo-1600087626014-e652e18bbff2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1Mzc3NDF8MHwxfGFsbHx8fHx8fHx8fDE3MDE4OTQ5NzF8&ixlib=rb-4.0.3&q=80&w=1080",
        thumbContentDesc = "",
        steps = 12,
        step = 1
    ),
    Course(
        id = 2,
        name = "GRA",
        subject = "Gramatika a Automaty",
        thumbUrl = "https://images.unsplash.com/photo-1620969427101-7a2bb6d83273?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1Mzc3NDF8MHwxfGFsbHx8fHx8fHx8fDE3MDE4OTUyMjV8&ixlib=rb-4.0.3&q=80&w=1080",
        thumbContentDesc = "",
        steps = 18,
        step = 1
    )
)