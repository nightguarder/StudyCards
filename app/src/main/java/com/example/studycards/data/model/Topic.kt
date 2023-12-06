package com.example.studycards.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class Topic(
    val name: String,
    val courses: Int,
    val imageUrl: String
)

//Temporary repo for Subjects topic
val topics = listOf(
    Topic(
        "Computer",
        8,
        "https://images.unsplash.com/photo-1568333261345-0918efdce2d9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1Mzc3NDF8MHwxfGFsbHx8fHx8fHx8fDE3MDE4OTY5NjN8&ixlib=rb-4.0.3&q=80&w=1080"
    ),
    Topic(
        "Languages",
        12,
        "https://images.unsplash.com/photo-1550376026-33cbee34f79e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1Mzc3NDF8MHwxfGFsbHx8fHx8fHx8fDE3MDE4OTcwNjJ8&ixlib=rb-4.0.3&q=80&w=1080"
    ),
    Topic(
        "Gaming",
        10,
        "https://images.unsplash.com/photo-1560253023-3ec5d502959f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1Mzc3NDF8MHwxfGFsbHx8fHx8fHx8fDE3MDE4OTcxMjJ8&ixlib=rb-4.0.3&q=80&w=1080"
    ),
    Topic(
        "Programming",
        2,
        "https://images.unsplash.com/photo-1542831371-29b0f74f9713?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1Mzc3NDF8MHwxfGFsbHx8fHx8fHx8fDE3MDE4OTcxNzR8&ixlib=rb-4.0.3&q=80&w=1080"
    ),
    Topic("Technology", 8, "https://images.unsplash.com/photo-1535223289827-42f1e9919769")
)