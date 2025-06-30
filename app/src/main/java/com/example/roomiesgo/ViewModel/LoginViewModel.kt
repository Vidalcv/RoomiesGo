package com.example.roomiesgo.ViewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

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

    fun login(
        onSuccess: () -> Unit,
        onError: (String) -> Unit // <-- ACEPTA EL MENSAJE DE ERROR
    ) {
        isLoading = true
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(username, password).await()
                loginSuccess = true
                onSuccess()
            } catch (e: Exception) {
                loginSuccess = false
                onError(e.message ?: "Error desconocido al iniciar sesión")
            } finally {
                isLoading = false
            }
        }
    }
}
