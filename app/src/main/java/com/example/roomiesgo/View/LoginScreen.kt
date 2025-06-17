package com.example.roomiesgo.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // Importa NavController
import androidx.navigation.compose.rememberNavController // Necesario para el Preview
import com.example.roomiesgo.R

@Composable
fun LoginScreen(navController: NavController) { // <-- Añade NavController como parámetro

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 40.dp)
                .align(Alignment.TopStart)
        ) {
            // Botón de retroceso
            IconButton(
                onClick = {
                    // Acción para regresar a WelcomeScreen
                    navController.popBackStack("welcome_screen", inclusive = false)
                    // Opcional: navController.popBackStack() si quieres ir a la pantalla anterior sin especificar.
                },
                modifier = Modifier
                    .size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.flecha_izquierda),
                    contentDescription = "Volver",
                    modifier = Modifier
                        .size(40.dp)

                )
            }

            Spacer(modifier = Modifier.height(120.dp)) // Espacio entre botón y título

            // Título centrado horizontalmente
            Text(
                text = "INICIAR SESIÓN",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp)) // Espacio antes del Card

            // Tarjeta de inicio de sesión
            Card(
                modifier = Modifier
                    .width(380.dp)
                    .height(350.dp)
                    .align(Alignment.CenterHorizontally),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Usuario") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            // Aquí iría tu lógica de autenticación real
                            // Si el inicio de sesión es exitoso:
                            navController.navigate("home_screen") {
                                // Limpia la pila de navegación hasta welcome_screen (inclusive)
                                // para que no se pueda volver atrás a las pantallas de registro/login.
                                popUpTo("welcome_screen") { inclusive = true }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF159E91)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Iniciar Sesión", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "¿Olvidaste la contraseña?",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.Start)
                            .clickable { navController.navigate("password_screen") }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Para el Preview, necesitas proporcionar una instancia de NavController simulada.
    LoginScreen(navController = rememberNavController())
}