package com.example.roomiesgo.ViewModel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class NewTaskViewModel : ViewModel() {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val date = mutableStateOf("")
    val time = mutableStateOf("")

    fun showDatePicker(context: Context) {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formattedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
                date.value = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    fun showTimePicker(context: Context) {
        val calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                val formattedTime = "%02d:%02d".format(hourOfDay, minute)
                time.value = formattedTime
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }

    fun saveTask(context: Context): Boolean {
        return if (title.value.isBlank() || description.value.isBlank() || date.value.isBlank() || time.value.isBlank()) {
            Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            false
        } else {
            // Lógica para guardar la tarea
            Toast.makeText(context, "Tarea guardada exitosamente", Toast.LENGTH_SHORT).show()
            true
        }
    }

}
