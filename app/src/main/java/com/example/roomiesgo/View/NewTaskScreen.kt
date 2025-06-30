package com.example.roomiesgo.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roomiesgo.ViewModel.NewTaskViewModel

@Composable
fun NewTaskScreen(
    navController: NavController,
    viewModel: NewTaskViewModel = viewModel()
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .widthIn(max = 340.dp)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Nueva tarea",
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            OutlinedTextField(
                value = viewModel.title.value,
                onValueChange = { viewModel.title.value = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.description.value,
                onValueChange = { viewModel.description.value = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.date.value,
                onValueChange = { viewModel.date.value = it },
                label = { Text("Fecha límite") },
                placeholder = { Text("DD/MM/YYYY") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.showDatePicker(context) },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { viewModel.showDatePicker(context) }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha")
                    }
                }
            )

            OutlinedTextField(
                value = viewModel.time.value,
                onValueChange = { viewModel.time.value = it },
                label = { Text("Hora") },
                placeholder = { Text("HH:MM") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.showTimePicker(context) },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { viewModel.showTimePicker(context) }) {
                        Icon(Icons.Default.Schedule, contentDescription = "Seleccionar hora")
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (viewModel.saveTask(context)) {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Guardar")
            }
        }
    }
}
