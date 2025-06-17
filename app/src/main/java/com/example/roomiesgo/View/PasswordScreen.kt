package com.example.roomiesgo.View

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource // Para el icono de flecha
import androidx.navigation.NavController // Importa NavController
import androidx.navigation.compose.rememberNavController // Necesario para el Preview
import com.example.roomiesgo.R // Asegúrate de tener R.drawable.flecha_izquierda

@Composable
fun PasswordScreen(navController: NavController) { // <-- Añade NavController
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Mantener centrado
    ) {
        // Añadir un botón de retroceso en la parte superior izquierda
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { navController.popBackStack() }, // Vuelve a la pantalla anterior (LoginScreen)
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.flecha_izquierda), // Asumiendo este recurso
                    contentDescription = "Volver",
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp)) // Espacio después del botón de retroceso si es visible

        Text(
            text = "¿Olvidaste tu contraseña?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Ingresa tu correo electrónico y te enviaremos un enlace para restablecerla.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                // Aquí va la lógica para enviar el correo de recuperación con Firebase.
                // Una vez que el correo se ha intentado enviar:
                // 1. Podrías mostrar un Snackbar/Toast de éxito o error.
                // 2. Luego, podrías navegar de vuelta a la LoginScreen.
                navController.popBackStack() // Vuelve a la pantalla anterior (LoginScreen)
                // Opcional: mostrar un mensaje antes de volver, por ejemplo:
                // Toast.makeText(context, "Enlace enviado a $email", Toast.LENGTH_LONG).show()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF159E91),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Enviar enlace")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordScreenPreview() {
    PasswordScreen(navController = rememberNavController()) // Para el Preview
}