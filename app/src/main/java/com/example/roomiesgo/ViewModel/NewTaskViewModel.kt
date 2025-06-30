package com.example.roomiesgo.ViewModel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class NewTaskViewModel : ViewModel() {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val date = mutableStateOf("")
    val time = mutableStateOf("")

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

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
        if (title.value.isBlank() || description.value.isBlank() || date.value.isBlank() || time.value.isBlank()) {
            Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }

        viewModelScope.launch {
            val uid = auth.currentUser?.uid
            if (uid == null) {
                Toast.makeText(context, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val task = hashMapOf(
                "titulo" to title.value,
                "descripcion" to description.value,
                "fecha" to date.value,
                "hora" to time.value,
                "completada" to false,
                "puntos" to 10,
                "asignadaA" to uid
            )

            try {
                db.collection("tareas").add(task).await()
                Toast.makeText(context, "Tarea guardada exitosamente", Toast.LENGTH_SHORT).show()
                // Limpia campos después de guardar (opcional)
                title.value = ""
                description.value = ""
                date.value = ""
                time.value = ""
            } catch (e: Exception) {
                Toast.makeText(context, "Error al guardar la tarea: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }
}
