package com.example.roomiesgo.ViewModel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WelcomeViewModel : ViewModel() {

    private val _username = MutableStateFlow("Usuario")
    val username: StateFlow<String> = _username

    private val _tareasCount = MutableStateFlow(5)
    val tareasCount: StateFlow<Int> = _tareasCount

    private val _puntos = MutableStateFlow(1200)
    val puntos: StateFlow<Int> = _puntos

    var selectedImageUri = mutableStateOf<Uri?>(null)
        private set

    fun onImageSelected(uri: Uri?) {
        selectedImageUri.value = uri
    }
}
