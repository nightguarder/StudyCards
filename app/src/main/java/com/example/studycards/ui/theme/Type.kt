package com.example.studycards.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.studycards.R


//Fira Sans Custom Font
val firaSansFamily = FontFamily(
    Font(R.font.fira_sans_light, FontWeight.Light),
    Font(R.font.fira_sans, FontWeight.Normal),
    Font(R.font.fira_sans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.fira_sans_medium, FontWeight.Medium),
    Font(R.font.fira_sans_bold, FontWeight.Bold),
    Font(R.font.fira_sans_thin, FontWeight.Thin),

    )

// Set of Material typography styles to start with
//This Override the defaults
val typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 55.sp,
        lineHeight = 45.sp,
        letterSpacing = 0.5.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
        lineHeight = 45.sp,
        letterSpacing = 0.5.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),

    subtitle1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.5.sp
    )

)