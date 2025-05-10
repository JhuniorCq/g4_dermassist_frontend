package com.example.asist_derm.ui.theme.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top =40.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Descubre el poder de DermAssist",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                val image = painterResource(id = R.drawable.page2)
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(24.dp))
                )

                App_funtions()
                Buttons(navController)
            }
        }
    }

    @Composable
    fun App_funtions() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FunctionItem(R.drawable.page2_1, "Detecta enfermedades cutáneas")
            FunctionItem(R.drawable.page2_2, "Asistencia médica inteligente")
            FunctionItem(R.drawable.page2_3, "Diagnóstico rápido móvil")
        }
    }

    @Composable
    fun FunctionItem(imageId: Int, label: String) {
        Column(
            modifier = Modifier
                .width(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image = painterResource(id = imageId)
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
            )
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    @Composable
    fun Buttons(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth(),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC0E7FF),
                    contentColor = Color.Black
                )
            ) {
                Text("Log in", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

            Button(
                onClick = { navController.navigate("register") },
                modifier = Modifier.fillMaxWidth(),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text("Sign up", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }

@Composable
@Preview
fun HomeScreenPreview() {
    val navController: NavHostController = rememberNavController()
    HomeScreen(navController)
}

