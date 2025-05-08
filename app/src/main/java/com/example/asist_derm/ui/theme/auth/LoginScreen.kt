package com.example.asist_derm.ui.theme.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.asist_derm.R

@Composable
fun LoginScreen(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        val image_login = painterResource(id = R.drawable.page3)
        Image(
            painter = image_login,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Inicio de sesión",
                textAlign = TextAlign.Center, fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
            )

            @OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = {
                    Text(
                        text = "Email",
                        style = TextStyle(color = Color.White.copy(alpha = 0.5f))
                    )
                },
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.3f))
            )
            @OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
            TextField(
                value = text2,
                onValueChange = { text2 = it },
                placeholder = {
                    Text(
                        text = "Password",
                        style = TextStyle(color = Color.White.copy(alpha = 0.5f))
                    )
                },
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.3f))
            )
            Button(onClick = {  navController.navigate("actions") }
                ,modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ,colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4393C5),
                    contentColor = Color.White )) {
                Text(text = "Log in", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    val navController: NavHostController = rememberNavController()
    LoginScreen(navController)
}
