package com.example.roomiesgo.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateAccountViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _accountCreated = MutableStateFlow(false)
    val accountCreated = _accountCreated.asStateFlow()

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun createAccount() {
        // Aquí va tu lógica de validación o llamada a base de datos
        if (_name.value.isNotBlank() && _email.value.isNotBlank() && _password.value.isNotBlank()) {
            _accountCreated.value = true
        }
    }

    fun resetAccountCreatedFlag() {
        _accountCreated.value = false
    }
}
