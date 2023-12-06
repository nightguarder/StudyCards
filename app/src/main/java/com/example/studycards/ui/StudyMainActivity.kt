package com.example.studycards.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import com.example.studycards.R
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class StudyMainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                ContextCompat.getColor(this, R.color.immersive_sys_ui)
            )
        )
        super.onCreate(savedInstanceState)

        //Initialize Firebase
        // Initialize Firebase Auth
        auth = Firebase.auth
        FirebaseApp.initializeApp(this)

        setContent {
            StudyCards { finish() }
        }
    }
}