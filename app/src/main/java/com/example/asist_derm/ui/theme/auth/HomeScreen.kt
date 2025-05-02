package com.example.asist_derm.ui.theme.auth

import androidx.compose.foundation.Image   // Importa la clase Image correcta
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.asist_derm.R

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        val image2 = painterResource(id = R.drawable.page2)
        Image(
            painter = image2,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}

