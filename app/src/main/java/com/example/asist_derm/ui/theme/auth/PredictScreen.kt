package com.example.asist_derm.ui.theme.auth

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter

@Composable
fun PredictScreen(uri: Uri?, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (uri != null) {
            Log.d("PredictScreen", "URI recibida: $uri")
            val context = LocalContext.current
            val painter = rememberImagePainter(uri)

            Image(
                painter = painter,
                contentDescription = "Foto Capturada",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Texto debajo de la imagen.",
                modifier = Modifier.padding(16.dp)
            )
        } else
            Text(text = "No se pudo cargar la imagen.")
    }
}





