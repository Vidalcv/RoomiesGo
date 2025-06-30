package com.example.roomiesgo.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun HomeScreen(navController: NavController) {
    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("new_task_screen") },
                containerColor = Color(0xFF159E91)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Menú (tres puntos)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
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
                                navController.navigate("task_screen")
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Configuración") },
                            onClick = {
                                menuExpanded = false
                                // navController.navigate("settings_screen")
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Cerrar sesión") },
                            onClick = {
                                menuExpanded = false
                                navController.navigate("welcome_screen") {
                                    popUpTo("welcome_screen") { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }

            // Avatar personalizable
            AvatarSelector()

            Spacer(modifier = Modifier.height(12.dp))

            // Texto de bienvenida
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Hola, Usuario", fontSize = 22.sp, fontWeight = FontWeight.Medium)
                Text("Has acumulado ___ puntos esta semana", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.outline)
            Spacer(modifier = Modifier.height(20.dp))

            Text("TUS TAREAS DE HOY", fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Spacer(modifier = Modifier.height(20.dp))

            // Lista de tareas
            repeat(3) { index ->
                var completada by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
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
                                onClick = { completada = true },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (completada) Color.Gray else Color(0xFF8DAAF9)
                                ),
                                modifier = Modifier.height(30.dp),
                                enabled = !completada
                            ) {
                                Text(
                                    if (completada) "Completada" else "Marcar como hecha",
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp)) // Para no tapar contenido con FAB
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
