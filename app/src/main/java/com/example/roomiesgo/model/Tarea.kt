package com.example.roomiesgo.model

data class Tarea (
    val id: String = "",
    val titulo: String = "",
    val hora: String = "",
    val puntos: Int = 0,
    val hecha: Boolean = false
)