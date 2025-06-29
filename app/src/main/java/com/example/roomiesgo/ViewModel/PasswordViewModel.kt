package com.example.roomiesgo.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PasswordViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun sendRecoveryEmail(onSuccess: () -> Unit, onError: () -> Unit) {
        // Aquí simula lógica de envío real, validación, etc.
        if (_email.value.contains("@") && _email.value.isNotBlank()) {
            // Simular éxito
            onSuccess()
        } else {
            onError()
        }
    }
}
