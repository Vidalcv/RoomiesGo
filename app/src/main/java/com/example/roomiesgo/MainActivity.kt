package com.example.roomiesgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Importa todas tus pantallas Composable
import com.example.roomiesgo.View.WelcomeScreen // Asumo que WelcomeScreen es tu Splash/Inicio
import com.example.roomiesgo.View.LoginScreen // Necesitas crear este Composable
import com.example.roomiesgo.View.CreateAccountScreen // Ya lo tenemos
import com.example.roomiesgo.View.HomeScreen // Necesitas crear este Composable
import com.example.roomiesgo.View.NewTaskScreen // Necesitas crear este Composable
import com.example.roomiesgo.View.TaskScreen // Necesitas crear este Composable
import com.example.roomiesgo.View.AnimatedSplashScreen
import com.example.roomiesgo.View.HistoryScreen
import com.example.roomiesgo.View.PasswordScreen
import com.example.roomiesgo.ui.theme.RoomiesGoTheme // Asegúrate de tener tu tema importado


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Esto se mantiene para el efecto de "borde a borde"
        setContent {
            RoomiesGoTheme { // Envuelve tu aplicación con tu tema
                // Un Surface para el fondo general de la aplicación
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation() // Aquí llamamos a nuestro Composable de navegación
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "animated_splash_screen", // <-- Nueva ruta para el splash animado
        modifier = Modifier.fillMaxSize()
    ) {
        composable("animated_splash_screen") { // <-- Define la ruta para el splash
            // No necesitas el Column con padding aquí si el splash es full screen
            AnimatedSplashScreen(navController = navController)
        }

        composable("welcome_screen") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                WelcomeScreen(navController = navController)
            }
        }

        // 2. Login Screen (Pantalla de Inicio de Sesión)
        composable("login_screen") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LoginScreen(navController = navController)
            }
        }
        composable("password_screen") { // <-- Nueva ruta para PasswordScreen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp), // Puedes ajustar este padding si la PasswordScreen lo maneja internamente
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PasswordScreen(navController = navController)
            }
        }

        // 3. Create Account Screen (Pantalla de Creación de Cuenta)
        composable("create_account_screen") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Aquí usamos el Composable CreateAccountScreen que ya modificamos
                CreateAccountScreen(navController = navController)
            }
        }

        composable("home_screen") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp), // Ajusta este padding si Home tiene un diseño full-width
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top // Normalmente HomeScreen inicia desde arriba
            ) {
                HomeScreen(navController = navController)
            }
        }

        // 5. New Task Screen (Pantalla de Nueva Tarea)
        composable("new_task_screen") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                NewTaskScreen(navController = navController)
            }
        }
        composable("task_screen") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TaskScreen(navController = navController)
            }
        }
        composable("history_screen") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HistoryScreen(navController = navController)
            }
        }
    }
}