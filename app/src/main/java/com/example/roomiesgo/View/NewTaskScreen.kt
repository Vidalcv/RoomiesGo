package com.example.roomiesgo.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier.size(32.dp)
            )
        }

        Text(
            text = "Nueva tarea",
            fontSize = 26.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = viewModel.title.value,
            onValueChange = viewModel::onTitleChange,
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.description.value,
            onValueChange = viewModel::onDescriptionChange,
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        // Fecha
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.showDatePicker(context) }
        ) {
            OutlinedTextField(
                value = viewModel.date.value,
                onValueChange = {},
                label = { Text("Fecha límite") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Hora
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.showTimePicker(context) }
        ) {
            OutlinedTextField(
                value = viewModel.time.value,
                onValueChange = {},
                label = { Text("Hora") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.saveTask()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688))
        ) {
            Text("Guardar")
        }
    }
}
