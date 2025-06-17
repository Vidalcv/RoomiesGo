package com.example.roomiesgo.View

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController // Importa NavController
import androidx.navigation.compose.rememberNavController // Necesario para el Preview
import com.example.roomiesgo.R

@Composable
fun AnimatedSplashScreen(navController: NavController) { // <-- Ahora recibe NavController

    // Animación de escala y opacidad
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "scale"
    )
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 2000),
        label = "alpha"
    )

    // Navegar luego de 3.5 segundos
    LaunchedEffect(Unit) {
        delay(1500)
        // Navega a la WelcomeScreen y elimina el splash de la pila
        navController.navigate("welcome_screen") {
            popUpTo("animated_splash_screen") { inclusive = true } // Elimina el splash de la pila
        }
    }

    // Fondo degradado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo con animación
            Image(
                painter = painterResource(id = R.drawable.logop),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(140.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            )

            Spacer(modifier = Modifier.height(35.dp))

            // Mensaje con fade
            Text(
                text = "¡Bienvenido a RoomiesGo!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = alpha)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedSplashScreenPreview() {
    AnimatedSplashScreen(navController = rememberNavController()) // Para el Preview
}