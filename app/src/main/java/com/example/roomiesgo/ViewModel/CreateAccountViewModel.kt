package com.example.roomiesgo.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CreateAccountViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val db = Firebase.firestore

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun createAccount(
        onSuccess: () -> Unit,
        onError: (String) -> Unit = {}
    ) {
        val nameValue = _name.value.trim()
        val emailValue = _email.value.trim()
        val passwordValue = _password.value.trim()

        if (nameValue.isEmpty() || emailValue.isEmpty() || passwordValue.isEmpty()) {
            onError("Todos los campos son obligatorios.")
            return
        }

        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(emailValue, passwordValue).await()
                val user = result.user

                user?.let {
                    val userMap = mapOf(
                        "uid" to it.uid,
                        "name" to nameValue,
                        "email" to emailValue,
                        "photoUrl" to null
                    )

                    db.collection("users").document(it.uid).set(userMap).await()
                    onSuccess()
                } ?: run {
                    onError("No se pudo obtener el usuario.")
                }

            } catch (e: Exception) {
                Log.e("CreateAccount", "Error al crear cuenta", e)
                onError(e.message ?: "Error desconocido")
            }
        }
    }
}
