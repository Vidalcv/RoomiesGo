package com.example.roomiesgo.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.roomiesgo.R
@Preview
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // Logo y texto "INICIO" centrados
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logop),
                    contentDescription = "Logo",
                    modifier = Modifier.size(55.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("INICIO", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = Color.Gray
        )

        // Imagen de usuario con borde
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

        // Texto saludo centrado
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

        // Tareas de ejemplo estáticas (sin lógica)
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
                            onClick = { /* Sin lógica */ },
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

        // Botón flotante para agregar tarea
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomStart) {
            FloatingActionButton(
                onClick = { /* Acción vacía */ },
                containerColor = Color(0xFF159E91)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    }
}
