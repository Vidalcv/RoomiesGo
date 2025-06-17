package com.example.roomiesgo.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.roomiesgo.R

@Composable
fun HomeScreen(navController: NavController) { // <-- Añade NavController como parámetro
    var menuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(45.dp), // Este padding afecta a toda la pantalla.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header con logo centrado y botón de menú a la derecha
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(48.dp)) // Espacio para alinear visualmente

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logop),
                    contentDescription = "Logo",
                    modifier = Modifier.size(65.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

            }

            // Botón de menú (3 puntos)
            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Historial") },
                        onClick = {
                            menuExpanded = false
                            navController.navigate("history_screen") // <-- Navega a HistoryScreen
                        }
                    )
                    DropdownMenuItem(text = { Text("Configuración") }, onClick = {
                        menuExpanded = false
                        // navController.navigate("settings_screen") // <-- Si tienes una pantalla de configuración
                    })
                    DropdownMenuItem(
                        text = { Text("Cerrar sesión") },
                        onClick = {
                            menuExpanded = false
                            // Navega a la WelcomeScreen y elimina todas las pantallas anteriores
                            navController.navigate("welcome_screen") {
                                popUpTo("welcome_screen") { inclusive = true }
                            }
                        }
                    )
                }
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = Color.Gray
        )

        // Avatar de usuario
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.user1),
                contentDescription = "Avatar",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Hola, Usuario", fontSize = 22.sp, fontWeight = FontWeight.Medium)
            Text("Has acumulado ___ puntos esta semana", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text("TUS TAREAS DE HOY", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Spacer(modifier = Modifier.height(20.dp))

        repeat(3) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.Gray),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Tarea ${index + 1}", fontWeight = FontWeight.SemiBold)
                    Text("Hora: 10:00 AM", fontSize = 12.sp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("+10 puntos", color = Color(0xFF159E91), fontWeight = FontWeight.Bold)
                        Button(
                            onClick = { /* Acción para marcar tarea como hecha */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8DAAF9)),
                            modifier = Modifier.height(30.dp)
                        ) {
                            Text("Marcar como hecha", fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Floating Action Button para agregar tarea
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomStart) {
            // Dentro de HomeScreen...
            FloatingActionButton(
                onClick = {
                    navController.navigate("task_screen") // <-- Navega a NewTaskScreen (que es tu TaskScreen)
                },
                containerColor = Color(0xFF159E91)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Para el Preview, proporciona un NavController simulado
    HomeScreen(navController = rememberNavController())
}