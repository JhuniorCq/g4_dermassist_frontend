package com.example.asist_derm.ui.theme.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.asist_derm.R

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize().weight(2f),) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(10.dp),
                        contentAlignment = Alignment.Center) {
                        Text(
                            "Descubre el poder de DermAssist", textAlign = TextAlign.Center,
                            fontSize = 28.sp, fontFamily= FontFamily.Serif, fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxSize().padding(top = 30.dp)
                        )
                    }
                    Box(modifier = Modifier.fillMaxSize().weight(2f)) {
                        val image2 = painterResource(id = R.drawable.page2)
                        Image(
                            painter = image2,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize().padding(20.dp)
                                .clip(RoundedCornerShape(40.dp))
                        )
                    }
                }
                Box(modifier = Modifier.fillMaxSize().weight(1f)) {
                    App_funtions()

                }
                Box(modifier = Modifier.fillMaxSize().weight(1f)) {
                    Buttons(navController)
                }
            }

        }
    }

@Composable
fun App_funtions(){
    Row(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.weight(1f).fillMaxSize().padding(30.dp)) {
            val image_funtion1= painterResource(id = R.drawable.page2_1)
            Image(painter = image_funtion1,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                )
            Text("Detecta enfermedades cutáneas", fontSize = 12.sp, fontWeight = FontWeight.Bold
                , textAlign = TextAlign.Center, modifier = Modifier.padding(top =8.dp))

        }
        Column(modifier = Modifier.weight(1f).fillMaxSize().padding(30.dp)) {
            val image_funtion2= painterResource(id = R.drawable.page2_2)
            Image(painter = image_funtion2,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Text("Asistencia médica inteligente",fontSize = 12.sp, fontWeight = FontWeight.Bold
                , textAlign = TextAlign.Center,modifier = Modifier.padding(top =8.dp))

        }
        Column(modifier = Modifier.weight(1f).fillMaxSize().padding(30.dp)) {
            val image_funtion3= painterResource(id = R.drawable.page2_3)
            Image(painter = image_funtion3,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Text("Diagnostico rápido móvil",fontSize = 12.sp, fontWeight = FontWeight.Bold
                , textAlign = TextAlign.Center,modifier = Modifier.padding(top =8.dp))
        }
    }

}
@Composable
fun Buttons(navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { navController.navigate("login") }
            ,modifier = Modifier.fillMaxWidth()
            ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ,colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC0E7FF),
            contentColor = Color.Black )) {
            Text(text = "Log in", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Button(onClick = { navController.navigate("register") }
            ,modifier = Modifier.fillMaxWidth()
            ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ,colors = ButtonDefaults.buttonColors(containerColor = Color.White,
                contentColor = Color.Black ))
        {
            Text(text = "Sign up", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    val navController: NavHostController = rememberNavController()
    HomeScreen(navController)
}

