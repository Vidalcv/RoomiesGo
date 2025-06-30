package com.example.roomiesgo.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

object UserRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun obtenerNombreUsuario(onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val uid = auth.currentUser?.uid ?: return onError("Usuario no logueado")
        db.collection("usuarios").document(uid).get()
            .addOnSuccessListener {
                onSuccess(it.getString("nombre") ?: "Usuario")
            }
            .addOnFailureListener {
                onError("Error al obtener nombre")
            }
    }

    fun obtenerGrupoId(onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val uid = auth.currentUser?.uid ?: return onError("Usuario no logueado")
        db.collection("usuarios").document(uid).get()
            .addOnSuccessListener {
                val grupoId = it.getString("grupoId") ?: return@addOnSuccessListener onError("No tiene grupo")
                onSuccess(grupoId)
            }
            .addOnFailureListener {
                onError("Error al obtener grupo")
            }
    }

    fun obtenerTareasDeHoy(grupoId: String, onSuccess: (List<Tarea>) -> Unit, onError: (String) -> Unit) {
        val uid = auth.currentUser?.uid ?: return onError("Usuario no logueado")
        val hoy = LocalDate.now().toString()

        db.collection("grupos").document(grupoId)
            .collection("tareas")
            .whereEqualTo("asignadaA", uid)
            .whereEqualTo("fecha", hoy)
            .whereEqualTo("estado", "pendiente")
            .get()
            .addOnSuccessListener { result ->
                val tareas = result.map {
                    val tarea = it.toObject(Tarea::class.java)
                    tarea.copy(id = it.id)
                }
                onSuccess(tareas)
            }
            .addOnFailureListener { onError("Error al obtener tareas") }
    }

    fun marcarTareaComoHecha(grupoId: String, tareaId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        db.collection("grupos").document(grupoId)
            .collection("tareas").document(tareaId)
            .update("estado", "completada")
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Error al actualizar tarea") }
    }
}