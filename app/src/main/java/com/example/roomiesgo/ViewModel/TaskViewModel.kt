package com.example.roomiesgo.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomiesgo.model.Tarea
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    var tareas = mutableStateOf(listOf<Tarea>())
        private set

    fun cargarTareas() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            db.collection("tareas")
                .whereEqualTo("asignadaA", uid)
                .whereEqualTo("completada", true)
                .get()
                .addOnSuccessListener { documents ->
                    val lista = mutableListOf<Tarea>()
                    for (doc in documents) {
                        val tarea = doc.toObject(Tarea::class.java).copy(id = doc.id)
                        lista.add(tarea)
                    }
                    tareas.value = lista
                }
        }
    }

    fun limpiarTareas() {
        tareas.value = emptyList()
    }
}
