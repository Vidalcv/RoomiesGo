package com.example.roomiesgo.View

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // Importa NavController
import androidx.navigation.compose.rememberNavController // Necesario para el Preview
import com.example.roomiesgo.R
import java.util.*

@Composable
fun NewTaskScreen(navController: NavController) { // <-- Añade NavController como parámetro
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Icono de volver
        IconButton(
            onClick = {
                // Acción para volver a la pantalla anterior (HomeScreen o TaskScreen)
                navController.popBackStack()
            },
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Start)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.flecha_izquierda), // Asumiendo que tienes este recurso
                contentDescription = "Volver",
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Nueva tarea",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        // Fecha con selector
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, year, month, day ->
                            date = "$day/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = {},
                label = { Text("Fecha límite") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Hora con selector
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val calendar = Calendar.getInstance()
                    TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            val formatted = minute.toString().padStart(2, '0')
                            time = "$hour:$formatted"
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                }
        ) {
            OutlinedTextField(
                value = time,
                onValueChange = {},
                label = { Text("Hora") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                navController.popBackStack() // <-- Vuelve a la pantalla anterior (HomeScreen o TaskScreen)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688))
        ) {
            Text("Guardar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewTaskScreenPreview() {
    // Para el Preview, proporciona un NavController simulado
    NewTaskScreen(navController = rememberNavController())
}