package com.example.roomiesgo.ViewModel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class NewTaskViewModel : ViewModel() {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val date = mutableStateOf("")
    val time = mutableStateOf("")

    fun onTitleChange(newTitle: String) {
        title.value = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        description.value = newDescription
    }

    fun showDatePicker(context: Context) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, day ->
                date.value = "$day/${month + 1}/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun showTimePicker(context: Context) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            context,
            { _, hour, minute ->
                val formatted = minute.toString().padStart(2, '0')
                time.value = "$hour:$formatted"
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    fun saveTask() {
        // Aquí podrías insertar en base de datos, API, etc.
        println("Tarea guardada:")
        println("Título: ${title.value}")
        println("Descripción: ${description.value}")
        println("Fecha: ${date.value}")
        println("Hora: ${time.value}")
    }
}
