package com.example.asist_derm.ui.theme.auth

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.asist_derm.data.model.UserData
import com.example.asist_derm.utils.UserSessionManager
import com.example.asist_derm.utils.uriToFile
import com.example.asist_derm.viewmodel.PredictViewModel

@Composable
fun PredictScreen(navController: NavHostController, uri: Uri?, onBack: () -> Unit) {
    val context = LocalContext.current
    val userData = remember { mutableStateOf<UserData?>(null) }
    val viewModel = remember { PredictViewModel() }

    LaunchedEffect(Unit) {
        userData.value = UserSessionManager.getUser(context)
    }
    val prediction = viewModel.prediction
    val isLoading = viewModel.isLoading

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
        .verticalScroll(
        rememberScrollState()
    )
            , horizontalAlignment = Alignment.CenterHorizontally) {

            Greeting(username = userData.value?.username ?: "Usuario")

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
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {navController.navigate ("camera")},
                    modifier = Modifier.padding(10.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                )
                        {
                            Text("Reintentar", color = Color.Black)
                        }
                Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { uri?.let {
                        val file = uriToFile(context, it)
                        val uid = userData.value?.uid ?: ""
                        viewModel.analyzeImage(uid, file)
                        }
                    },
                        modifier = Modifier.padding(10.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4393C5)))
                    {
                        Text(if (isLoading) "Analizando..." else "Analizar imagen", color = Color.White)
                    }
            }
             prediction?.let { pred ->
              if (!pred.prediction.isNullOrBlank()) {
                  Text_predict(
                      enfermedad = pred.prediction,
                      detalle = "Es una inflamación de la piel que causa picazón, enrojecimiento y resequedad. No es contagiosa.\n" +
                              "Consulta con un dermatólogo para un diagnóstico preciso",
                      porcentaje = pred.probability * 100
                  )
              }
            }

    }
    }
}
@Composable
fun Text_predict(enfermedad: String, detalle:String, porcentaje: Double ){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE6F0F8), Color.White)
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "\"Según nuestra IA, esta imagen se parece en un ${"%.2f".format(porcentaje)}% a esta enfermedad por lo que podrías tener signos de:",
            style = MaterialTheme.typography.bodyMedium, fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2F5D7C))
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$enfermedad",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE0E0E0))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "$detalle",
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Esto no reemplaza un diagnóstico médico.",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
    }
}









