package com.example.roomiesgo.View

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.credentials.*
import androidx.navigation.NavController
import com.example.roomiesgo.R
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.launch // Asegúrate de importar launch
import kotlinx.coroutines.tasks.await

@Composable
fun GoogleLoginScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val auth = remember { Firebase.auth }
    val db = remember { Firebase.firestore }

    var loading by rememberSaveable { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Obtenemos un CoroutineScope ligado al ciclo de vida del Composable
    val coroutineScope = rememberCoroutineScope()

    // --- Launcher para fallback con GoogleSignIn ---
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            if (idToken != null) {
                // Lanzamos una corrutina para llamar a la función suspend
                coroutineScope.launch { // <--- AQUÍ LA MODIFICACIÓN
                    signInWithFirebase(idToken, auth, db, navController, onError = {
                        errorMessage = it
                        loading = false
                    })
                }
            } else {
                errorMessage = "ID Token es nulo desde GoogleSignIn"
                loading = false
            }
        } catch (e: Exception) {
            Log.e("GoogleLogin", "Error en GoogleSignIn fallback", e)
            errorMessage = "Error al iniciar sesión: ${e.localizedMessage}"
            loading = false
        }
    }

    LaunchedEffect(Unit) {
        Log.d("GoogleLogin", "Iniciando flujo de autenticación con Google...")

        try {
            val credentialManager = CredentialManager.create(context)

            val googleSignInOption = GetSignInWithGoogleOption.Builder(
                serverClientId = context.getString(R.string.default_web_client_id)
            ).build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleSignInOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val credential = result.credential as? GoogleIdTokenCredential

            val idToken = credential?.idToken
            if (idToken != null) {
                // Esta llamada ya está dentro de LaunchedEffect, que es un ámbito de corrutina
                signInWithFirebase(idToken, auth, db, navController, onError = {
                    errorMessage = it
                    loading = false
                })
            } else {
                Log.e("GoogleLogin", "Credencial nula. Mostrando GoogleSignIn clásico.")
                launchGoogleSignIn(activity, context.getString(R.string.default_web_client_id), googleSignInLauncher)
            }
        } catch (e: Exception) {
            Log.e("GoogleLogin", "Error con CredentialManager: ${e.localizedMessage}")
            // Fallback automático
            launchGoogleSignIn(activity, context.getString(R.string.default_web_client_id), googleSignInLauncher)
        }
    }

    // --- UI ---
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            loading -> CircularProgressIndicator()
            errorMessage != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = errorMessage ?: "Ocurrió un error",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { navController.popBackStack() }) {
                        Text("Volver")
                    }
                }
            }
        }
    }
}

// --- Función que autentica con Firebase ---
suspend fun signInWithFirebase(
    idToken: String,
    auth: com.google.firebase.auth.FirebaseAuth,
    db: com.google.firebase.firestore.FirebaseFirestore,
    navController: NavController,
    onError: (String) -> Unit
) {
    try {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val result = auth.signInWithCredential(credential).await()
        val user = result.user

        user?.let {
            val userMap = mapOf(
                "uid" to it.uid,
                "name" to it.displayName,
                "email" to it.email,
                "photoUrl" to it.photoUrl?.toString()
            )

            db.collection("users").document(it.uid)
                .set(userMap, SetOptions.merge())
                .await()

            navController.navigate("home_screen") {
                popUpTo("google_login_screen") { inclusive = true }
            }
        } ?: run {
            onError("Usuario nulo después de autenticación")
        }
    } catch (e: Exception) {
        Log.e("GoogleLogin", "Error Firebase login", e)
        onError(e.localizedMessage ?: "Error desconocido")
    }
}

// --- Fallback con Google Sign-In clásico ---
fun launchGoogleSignIn(
    activity: Activity,
    webClientId: String,
    launcher: androidx.activity.result.ActivityResultLauncher<Intent>
) {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(webClientId)
        .requestEmail()
        .build()
    val client = GoogleSignIn.getClient(activity, gso)
    launcher.launch(client.signInIntent)
}