package com.example.roomiesgo.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomiesgo.R
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logop),
            contentDescription = "Logo",
            modifier = Modifier.size(255.dp)
        )

        Spacer(modifier = Modifier.height(35.dp))  // <-- Espacio inmediatamente después de la imagen

        Spacer(modifier = Modifier.height(20.dp))  // <-- Segundo espacio, justo antes del botón

        Button(
            onClick = { /* Acción iniciar sesión */ },
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688))
        ) {
            Text(
                "Iniciar sesión",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { /* Acción crear cuenta */ },
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFF009688)),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF009688))
        ) {
            Text(
                "Crear Cuenta",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¿Código de invitación?",
            fontSize = 14.sp,
            color = Color.Black
        )
        Text(
            text = "Únete a un hogar",
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}
