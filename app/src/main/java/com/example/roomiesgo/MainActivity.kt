package com.example.roomiesgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.roomiesgo.View.CreateAccountScreen
import com.example.roomiesgo.View.HomeScreen
import com.example.roomiesgo.View.LoginScreen
import com.example.roomiesgo.View.TaskScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskScreen()
            }

        }
    }

