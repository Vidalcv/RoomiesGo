package com.example.roomiesgo.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // Importa NavController
import androidx.navigation.compose.rememberNavController // Necesario para el Preview
import com.example.roomiesgo.R

@Composable
fun HistoryScreen(navController: NavController) { // <-- Añade NavController
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header con icono, logo y título centrado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            ) {
                // Icono volver a la izquierda
                IconButton(
                    onClick = { navController.popBackStack() }, // Vuelve a la pantalla anterior (HomeScreen)
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.flecha_izquierda), // Asegúrate de tener este recurso
                        contentDescription = "Volver",
                        modifier = Modifier.size(32.dp)
                    )
                }
                // Spacer(modifier = Modifier.width(48.dp)) // Este Spacer puede causar problemas de alineación con el Box, lo comento.

                // Row con logo a la izquierda y título centrado (ajustado para funcionar con IconButton)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center // Centra el contenido de este Row
                ) {
                    // Puedes ajustar el tamaño del logo si es necesario para el centrado visual
                    Image(
                        painter = painterResource(id = R.drawable.logop),
                        contentDescription = "Logo",
                        modifier = Modifier.size(65.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "HISTORIAL",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Bienvenida Lucía",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            repeat(4) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Tarea", fontWeight = FontWeight.Bold)
                        Text("Lucía")
                        Text("Fecha")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Aquí iría la lógica para eliminar el historial.
                    // Podrías mostrar un Diálogo de confirmación antes de eliminar.
                    // Después de la acción, no hay navegación automática, se queda en la misma pantalla.
                    // Opcional: mostrar un Toast/Snackbar de éxito.
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))
            ) {
                Text("Eliminar historial")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorialUIPreview() {
    HistoryScreen(navController = rememberNavController()) // Para el Preview
}