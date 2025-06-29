package com.example.roomiesgo.ViewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var loginSuccess by mutableStateOf<Boolean?>(null)
        private set

    fun onUsernameChanged(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun login(onSuccess: () -> Unit, onError: () -> Unit) {
        isLoading = true
        viewModelScope.launch {
            delay(1000) // Simula tiempo de respuesta de red o BD

            // Lógica básica de autenticación
            if (username == "admin" && password == "1234") {
                loginSuccess = true
                onSuccess()
            } else {
                loginSuccess = false
                onError()
            }

            isLoading = false
        }
    }
}