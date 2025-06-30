package com.example.roomiesgo.model

data class Tarea(
    val id: String = "",
    val titulo: String = "",
    val fecha: String = "",
    val puntos: Int = 0,
    val completada: Boolean = false,
    val asignadaA: String = ""
)
