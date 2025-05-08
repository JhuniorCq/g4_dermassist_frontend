package com.example.asist_derm.ui.theme.auth

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter

@Composable
fun PredictScreen(navController: NavHostController, uri: Uri?, onBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavPanel(navController)
        }
    ) { paddingValues ->
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF4393C5),
                Color.White
            )
        )
    Column(modifier = Modifier.fillMaxSize().padding(paddingValues).background(brush = gradientBrush)
            , horizontalAlignment = Alignment.CenterHorizontally) {

            Greeting(name = "Airton", apellidos = "Collachagua")

            Box(modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
                .padding(16.dp)){
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
                } else
                    Text(text = "No se pudo cargar la imagen.")
                }
            Text_predict("Dermatitis", "Es una inflamación de la piel que causa picazón, enrojecimiento y resequedad. No es contagiosa.\n" +
                    "Consulta con un dermatólogo para un diagnóstico preciso" )

    }
    }
}
@Composable
fun Text_predict(enfermedad: String, detalle:String){

    Box(modifier = Modifier
        .padding(16.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color.White)
        .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
        .padding(16.dp)) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Podrias tener signos de $enfermedad", textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "$detalle", textAlign = TextAlign.Start, color = Color.Black,
                 fontSize = 15.sp
            )
            Button(onClick = { },modifier = Modifier.padding(bottom = 30.dp,top = 30.dp).fillMaxWidth()
                ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ,colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4393C5)))
            {
                Text(text = "Ver clínicas", color = Color.White)
            }

        }
    }
}








