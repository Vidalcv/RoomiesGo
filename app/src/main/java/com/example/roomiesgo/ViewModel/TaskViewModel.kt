// NewTaskListViewModel.kt
package com.example.roomiesgo.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Task(val title: String, val user: String, val date: String)

class NewTaskListViewModel : ViewModel() {
    var tasks = mutableStateListOf<Task>()
        private set

    init {
        // Datos simulados
        repeat(10) {
            tasks.add(Task("Título de la tarea $it", "Usuario $it", "12/06/2025"))
        }
    }

    fun clearTasks() {
        tasks.clear()
    }
}
