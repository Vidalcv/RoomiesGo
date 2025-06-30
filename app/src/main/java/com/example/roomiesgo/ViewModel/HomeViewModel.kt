package com.example.roomiesgo.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.roomiesgo.model.Tarea
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class HomeViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    var nombreUsuario = mutableStateOf("Usuario")
        private set

    var tareasHoy = mutableStateOf(listOf<Tarea>())
        private set

    private var listener: ListenerRegistration? = null

    init {
        cargarNombreUsuario()
        suscribirTareasEnTiempoReal()
    }

    private fun cargarNombreUsuario() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("users").document(uid).get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    nombreUsuario.value = doc.getString("name") ?: "Usuario"
                }
            }
    }

    private fun suscribirTareasEnTiempoReal() {
        val uid = auth.currentUser?.uid ?: return
        listener = db.collection("tareas")
            .whereEqualTo("asignadaA", uid)
            .whereEqualTo("completada", false)
            .addSnapshotListener { snapshot, error ->
                if (error != null) return@addSnapshotListener

                if (snapshot != null && !snapshot.isEmpty) {
                    val lista = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(Tarea::class.java)?.copy(id = doc.id)
                    }
                    tareasHoy.value = lista
                } else {
                    tareasHoy.value = emptyList()
                }
            }
    }

    fun marcarComoCompletada(idTarea: String) {
        db.collection("tareas").document(idTarea)
            .update("completada", true)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
                println("Error al marcar tarea como completada: ${e.message}")
            }
    }

    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }
}
