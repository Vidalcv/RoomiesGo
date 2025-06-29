// AvatarSelector.kt
package com.example.roomiesgo.View

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.roomiesgo.R

@Composable
fun AvatarSelector(
    modifier: Modifier = Modifier,
    imageSize: Int = 128
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri
    }

    Box(
        modifier = modifier
            .size(imageSize.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(androidx.compose.material3.MaterialTheme.colorScheme.surface)
            .clickable {
                launcher.launch("image/*")
            },
        contentAlignment = Alignment.Center
    ) {
        if (selectedImageUri != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(selectedImageUri)
                    .size(128) // Limita a 128x128
                    .crossfade(true)
                    .build(),
                contentDescription = "Avatar seleccionado",
                modifier = Modifier.matchParentSize()
            )
        } else {
            Image(
                painter = androidx.compose.ui.res.painterResource(id = R.drawable.user1),
                contentDescription = "Avatar por defecto",
                modifier = Modifier.matchParentSize()
            )
        }
    }
}
